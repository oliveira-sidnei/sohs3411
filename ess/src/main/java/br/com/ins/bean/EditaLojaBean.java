package br.com.ins.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.ins.control.ControladorEvento;
import br.com.ins.control.ControladorLoja;
import br.com.ins.control.ControladorLojista;
import br.com.ins.control.ControladorMensagens;
import br.com.ins.control.ControladorStatus;
import br.com.ins.core.Loja;
import br.com.ins.core.Lojista;
import br.com.ins.core.Status;
import br.com.ins.core.TipoEvento;

@ViewScoped
@ManagedBean
public class EditaLojaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Loja lojaSelecionada;

	@EJB
	ControladorLoja controladorLoja;
	@EJB
	ControladorEvento controladorEvento;
	@EJB
	ControladorLojista controladorLojista;
	@EJB
	ControladorMensagens controladorMensagem;
	@EJB
	ControladorStatus controladorStatus;

	private List<TipoEvento> listaTipoEvento = new ArrayList<TipoEvento>();
	private List<TipoEvento> listaTiposEventoSelecionado = new ArrayList<TipoEvento>();

	private List<Status> listaStatus = new ArrayList<Status>();

	private List<Lojista> lojistas = new ArrayList<Lojista>();
	private TipoEvento tipoEventoSelecionado;

	private Lojista lojistaSelecionado;

	private Lojista lojistaConsultado;

	@PostConstruct
	public void iniciar() {
		String idLojaString = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
				.get("LojaId");

		listaStatus = controladorStatus.listaStatusLoja();
		
		lojaSelecionada = controladorLoja.buscaLoja(Integer.parseInt(idLojaString));

		lojistaConsultado = lojaSelecionada.getLojista();

		listaTipoEvento = controladorEvento.retornaTiposEvento();

		listaTiposEventoSelecionado.addAll(lojaSelecionada.getListaTiposEvento());

		lojistas = controladorLojista.retornaTodosLojistas();

	}

	public String salva() {
		try {
			if (lojistaSelecionado == null) {
				lojaSelecionada.setLojista(lojistaConsultado);
			}

			lojaSelecionada.setListaTiposEvento(new HashSet<TipoEvento>());
			lojaSelecionada.getListaTiposEvento().addAll(listaTiposEventoSelecionado);

			controladorLoja.atualizaLoja(lojaSelecionada);
			controladorMensagem.addMsgInfo("loja.editada");
			return "consultaLojas.xhtml";

		} catch (Exception e) {
			return null;
		}

	}

	public String cancela() {
		return "consultaLojas?faces-redirect=true";

	}

	public void adicionarTipoEvento() {
		if (tipoEventoSelecionado == null) {
			controladorMensagem.addMsgAlerta("nenhum.tipo.selecionado");

		} else {

			boolean contem = false;
			for (TipoEvento tipoEvento : listaTiposEventoSelecionado) {
				if (tipoEventoSelecionado.getDescricao().equals(tipoEvento.getDescricao())) {
					contem = true;
					controladorMensagem.addMsgAlerta("erro.tipo.selecionado");
					break;
				}
			}
			if (!contem) {
				listaTiposEventoSelecionado.add(tipoEventoSelecionado);
			}
		}
		tipoEventoSelecionado = new TipoEvento();

	}

	public void excluiTipoEvento(TipoEvento tipoEvento) {
		listaTiposEventoSelecionado.remove(tipoEvento);

	}

	public Loja getLojaSelecionada() {
		return lojaSelecionada;
	}

	public void setLojaSelecionada(Loja lojaSelecionada) {
		this.lojaSelecionada = lojaSelecionada;
	}

	public List<TipoEvento> getListaTipoEvento() {
		return listaTipoEvento;
	}

	public List<Lojista> getLojistas() {
		return lojistas;
	}

	public TipoEvento getTipoEventoSelecionado() {
		return tipoEventoSelecionado;
	}

	public void setTipoEventoSelecionado(TipoEvento tipoEventoSelecionado) {
		this.tipoEventoSelecionado = tipoEventoSelecionado;
	}

	public List<TipoEvento> getListaTiposEventoSelecionado() {
		return listaTiposEventoSelecionado;
	}

	public void setListaTiposEventoSelecionado(List<TipoEvento> listaTiposEventoSelecionado) {
		this.listaTiposEventoSelecionado = listaTiposEventoSelecionado;
	}

	public Lojista getLojistaSelecionado() {
		return lojistaSelecionado;
	}

	public void setLojistaSelecionado(Lojista lojistaSelecionado) {
		this.lojistaSelecionado = lojistaSelecionado;
	}

	public Lojista getLojistaConsultado() {
		return lojistaConsultado;
	}

	public void setLojistaConsultado(Lojista lojistaConsultado) {
		this.lojistaConsultado = lojistaConsultado;
	}

	public List<Status> getListaStatus() {
		return listaStatus;
	}

	public void setListaStatus(List<Status> listaStatus) {
		this.listaStatus = listaStatus;
	}

}
