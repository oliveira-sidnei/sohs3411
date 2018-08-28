package br.com.ins.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.ins.control.ControladorEvento;
import br.com.ins.control.ControladorFuncionario;
import br.com.ins.control.ControladorLoja;
import br.com.ins.control.ControladorMensagens;
import br.com.ins.control.ControladorStatus;
import br.com.ins.core.Evento;
import br.com.ins.core.Funcionario;
import br.com.ins.core.Loja;
import br.com.ins.core.Status;
import br.com.ins.core.TipoEvento;

@ViewScoped
@ManagedBean
public class EditaEventoBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	ControladorLoja controladorLoja;

	@EJB
	ControladorEvento controladorEvento;

	@EJB
	ControladorMensagens controladorMensagens;
	@EJB
	ControladorStatus controladorStatus;
	@EJB
	ControladorFuncionario controladorFuncionario;

	private Evento eventoSelecionado;

	private TipoEvento tipoEventoSelecionado;
	
	private Funcionario funcionarioSelecionado;

	private List<TipoEvento> listaTipoEvento = new ArrayList<TipoEvento>();

	private List<TipoEvento> listaTipoEventoSelecionado = new ArrayList<TipoEvento>();

	private List<Loja> listaLojas = new ArrayList<Loja>();

	private List<Loja> listaLojasSelecionadas = new ArrayList<Loja>();
	
	private List<Funcionario> listaFuncionarios = new ArrayList<Funcionario>();

	private List<Status> listaStatus = new ArrayList<Status>();

	private Loja lojaSelecionada;
	
	private Date dataMinima = new Date();


	@PostConstruct
	public void iniciar() {
		buscaEventoSelecionado();

		// listaLojasSelecionadas =
		// controladorEvento.buscaLojasEvento(eventoSelecionado);
		listaLojas = controladorLoja.listaLojas();
		listaStatus = controladorStatus.listaStatusEvento();
		
		listaFuncionarios = controladorFuncionario.retornaTodosFuncionarios();		

		listaTipoEventoSelecionado.addAll(eventoSelecionado.getListaTipoEvento());
		listaTipoEvento = controladorEvento.retornaTiposEvento();

	}

	public void buscaEventoSelecionado() {

		String idEventoString = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
				.get("EventoId");

		eventoSelecionado = controladorEvento.buscaEvento(Integer.parseInt(idEventoString));
		System.out.println(eventoSelecionado.getFuncionario());

		// DateFormat df = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzzz yyyy",
		// Locale.US);

	}

	public String salva() {
		try {
			if (eventoSelecionado.getDataInicio().after(eventoSelecionado.getDataFim())) {
				controladorMensagens.addMsgAlerta("data.incoerente");
			} else {
				
				eventoSelecionado.setListaTipoEvento(new HashSet<TipoEvento>());
				eventoSelecionado.getListaTipoEvento().addAll(listaTipoEventoSelecionado);
				if (validaFormulario()) {
					controladorEvento.atualizaEvento(eventoSelecionado);
					controladorMensagens.addMsgInfo("evento.atualizado");
					return "consultaEventos.xhtml";

				} else {
					controladorMensagens.addMsgErro("erro.banco");
				}

			}
			return null;

		} catch (Exception e) {
			controladorMensagens.addMsgErro("erro.banco");
			return null;
		}
	}

	private boolean validaFormulario() {
		if (eventoSelecionado.getListaLojas().size() > 0 && eventoSelecionado.getListaTipoEvento().size() > 0
				&& eventoSelecionado.getFuncionario() != null) {

			return true;
		} else {

			controladorMensagens.addMsgAlerta("form.incompleto");
			return false;
		}
	}

	public void adicionaLoja() {
		if (lojaSelecionada == null) {
			controladorMensagens.addMsgAlerta("loja.nao.selecionada");

		} else {

			boolean contem = false;
			for (Loja loja : eventoSelecionado.getListaLojas()) {
				if (loja.getCnpj().equals(lojaSelecionada.getCnpj())) {
					contem = true;
					controladorMensagens.addMsgAlerta("loja.contida");
					break;
				}
			}
			if (!contem) {
				eventoSelecionado.getListaLojas().add(lojaSelecionada);
			}
		}
		lojaSelecionada = new Loja();

	}
	
	
	public void setaFuncionario() {
		eventoSelecionado.setFuncionario(funcionarioSelecionado);

	}

	public void excluiLoja(Loja loja) {
		eventoSelecionado.getListaLojas().remove(loja);

	}

	public void excluiTipoEvento(TipoEvento tipoEvento) {
		listaTipoEventoSelecionado.remove(tipoEvento);

	}

	public void adicionarTipoEvento() {
		if (tipoEventoSelecionado == null) {
			controladorMensagens.addMsgAlerta("nenhum.tipo.selecionado");

		} else {

			boolean contem = false;
			for (TipoEvento tipoEvento : listaTipoEventoSelecionado) {
				if (tipoEventoSelecionado.getDescricao().equals(tipoEvento.getDescricao())) {
					contem = true;
					controladorMensagens.addMsgAlerta("erro.tipo.selecionado");
					break;
				}
			}
			if (!contem) {
				listaTipoEventoSelecionado.add(tipoEventoSelecionado);
			}
		}
		tipoEventoSelecionado = new TipoEvento();

	}

	public TipoEvento getTipoEventoSelecionado() {
		return tipoEventoSelecionado;
	}

	public void setTipoEventoSelecionado(TipoEvento tipoEventoSelecionado) {
		this.tipoEventoSelecionado = tipoEventoSelecionado;
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

	public String cancela() {
		return "consultaEventos?faces-redirect=true";
	}

	public List<Loja> getListaLojas() {
		return listaLojas;
	}

	public void setListaLojas(List<Loja> listaLojas) {
		this.listaLojas = listaLojas;
	}

	public List<Loja> getListaLojasSelecionadas() {
		return listaLojasSelecionadas;
	}

	public void setListaLojasSelecionadas(List<Loja> listaLojasSelecionadas) {
		this.listaLojasSelecionadas = listaLojasSelecionadas;
	}

	public Evento getEventoSelecionado() {
		return eventoSelecionado;
	}

	public void setEventoSelecionado(Evento eventoSelecionado) {
		this.eventoSelecionado = eventoSelecionado;
	}

	public Loja getLojaSelecionada() {
		return lojaSelecionada;
	}

	public void setLojaSelecionada(Loja lojaSelecionada) {
		this.lojaSelecionada = lojaSelecionada;
	}

	public List<Status> getListaStatus() {
		return listaStatus;
	}

	public void setListaStatus(List<Status> listaStatus) {
		this.listaStatus = listaStatus;
	}

	public List<Funcionario> getListaFuncionarios() {
		return listaFuncionarios;
	}

	public void setListaFuncionarios(List<Funcionario> listaFuncionarios) {
		this.listaFuncionarios = listaFuncionarios;
	}

	public Date getDataMinima() {
		return dataMinima;
	}

	public Funcionario getFuncionarioSelecionado() {
		return funcionarioSelecionado;
	}

	public void setFuncionarioSelecionado(Funcionario funcionarioSelecionado) {
		this.funcionarioSelecionado = funcionarioSelecionado;
	}
	
	

}
