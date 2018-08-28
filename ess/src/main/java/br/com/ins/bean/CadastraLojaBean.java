package br.com.ins.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.ins.control.ControladorEvento;
import br.com.ins.control.ControladorLoja;
import br.com.ins.control.ControladorLojista;
import br.com.ins.control.ControladorMensagens;
import br.com.ins.control.ControladorStatus;
import br.com.ins.core.Funcionario;
import br.com.ins.core.Loja;
import br.com.ins.core.Lojista;
import br.com.ins.core.Status;
import br.com.ins.core.TipoEvento;

@ViewScoped
@ManagedBean
public class CadastraLojaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<TipoEvento> listaTipoEvento = new ArrayList<TipoEvento>();
	private List<TipoEvento> listaTipoEventoSelecionado = new ArrayList<TipoEvento>();
	private Lojista lojistaSelecionado;
	private TipoEvento tipoEventoSelecionado;
	private List<Lojista> lojistas = new ArrayList<Lojista>();
	private Loja lojaSelecionada = new Loja();
	private Status statusInicial;
	private Date dataMinima = new Date();

	@EJB
	ControladorLoja controladorLoja;
	@EJB
	ControladorLojista controladorLojista;
	@EJB
	ControladorEvento controladorEvento;
	@EJB
	ControladorMensagens controladorMensagens;
	@EJB
	ControladorStatus controladorStatus;

	@PostConstruct
	public void iniciar() {

		statusInicial = controladorStatus.inicializaAtivo();

		lojaSelecionada.setStatus(statusInicial);

		lojistas = controladorLojista.retornaTodosLojistas();

		listaTipoEvento = controladorEvento.retornaTiposEvento();

	}

	public void adicionarTipoEvento() {
		if (listaTipoEventoSelecionado.contains(tipoEventoSelecionado)) {
			controladorMensagens.addMsgAlerta("erro.tipo.selecionado");
		} else if (tipoEventoSelecionado == null) {
			controladorMensagens.addMsgAlerta("nenhum.tipo.selecionado");
		} else {
			listaTipoEventoSelecionado.add(tipoEventoSelecionado);
		}
		tipoEventoSelecionado = new TipoEvento();

	}

	public void excluiTipoEvento(TipoEvento tipoEvento) {
		listaTipoEventoSelecionado.remove(tipoEvento);

	}

	public String salva() {
		try {
			if (listaTipoEventoSelecionado.size() < 1 || lojaSelecionada.getLojista() == null) {
				controladorMensagens.addMsgAlerta("form.incompleto.loja");

			} else if (lojaSelecionada.getDataInicio().after(lojaSelecionada.getDataFim())) {
				controladorMensagens.addMsgAlerta("data.incoerente");
			} else {
				lojaSelecionada.getListaTiposEvento().addAll(listaTipoEventoSelecionado);
				controladorLoja.insereLoja(lojaSelecionada);
				controladorMensagens.addMsgInfo("loja.inserida");
				return "consultaLojas.xhtml";

			}

		} catch (Exception e) {

		}
		return null;
	}

	public String cancela() {
		return "consultaLojas?faces-redirect=true";
	}

	public List<TipoEvento> getListaTipoEvento() {
		return listaTipoEvento;
	}

	public void setListaTipoEvento(List<TipoEvento> listaTipoEvento) {
		this.listaTipoEvento = listaTipoEvento;
	}

	public List<TipoEvento> getListaTipoEventoSelecionado() {
		return listaTipoEventoSelecionado;
	}

	public void setListaTipoEventoSelecionado(List<TipoEvento> listaTipoEventoSelecionado) {
		this.listaTipoEventoSelecionado = listaTipoEventoSelecionado;
	}

	public Lojista getLojistaSelecionado() {
		return lojistaSelecionado;
	}

	public void setLojistaSelecionado(Lojista lojistaSelecionado) {
		this.lojistaSelecionado = lojistaSelecionado;
	}

	public List<Lojista> getLojistas() {
		return lojistas;
	}

	public void setLojistas(List<Lojista> lojistas) {
		this.lojistas = lojistas;
	}

	public Loja getLojaSelecionada() {
		return lojaSelecionada;
	}

	public void setLojaSelecionada(Loja lojaSelecionada) {
		this.lojaSelecionada = lojaSelecionada;
	}

	public TipoEvento getTipoEventoSelecionado() {
		return tipoEventoSelecionado;
	}

	public void setTipoEventoSelecionado(TipoEvento tipoEventoSelecionado) {
		this.tipoEventoSelecionado = tipoEventoSelecionado;
	}

	public Date getDataMinima() {
		return dataMinima;
	}

}
