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
import br.com.ins.control.ControladorFuncionario;
import br.com.ins.control.ControladorLoja;
import br.com.ins.control.ControladorMensagens;
import br.com.ins.control.ControladorStatus;
import br.com.ins.core.Evento;
import br.com.ins.core.Funcionario;
import br.com.ins.core.Loja;
import br.com.ins.core.TipoEvento;

@ViewScoped
@ManagedBean
public class CadastraEventoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	ControladorEvento controladorEvento;
	@EJB
	ControladorLoja controladorLoja;
	@EJB
	ControladorFuncionario controladorFuncionario;
	@EJB
	ControladorMensagens controladorMensagens;
	@EJB
	ControladorStatus controladorStatus;

	private Evento eventoSelecionado = new Evento();
	private Loja lojaSelecionada = new Loja();
	private List<Loja> lojas = new ArrayList<Loja>();
	private List<Loja> listaLojasSelecionadas = new ArrayList<Loja>();
	private Loja lojaExibicao = new Loja();
	private Date dataMinima = new Date();

	private List<TipoEvento> listaTipoEvento = new ArrayList<TipoEvento>();
	private List<TipoEvento> listaTipoEventoSelecionado = new ArrayList<TipoEvento>();

	private TipoEvento tipoEventoSelecionado;

	private List<Funcionario> listaFuncionarios = new ArrayList<Funcionario>();

	@PostConstruct
	public void iniciar() {

		eventoSelecionado.setStatus(controladorStatus.inicializaStatusSolicitado());
		listaTipoEvento = controladorEvento.retornaTiposEvento();
		lojas = controladorLoja.listaLojasAtivas();
		listaFuncionarios = controladorFuncionario.retornaTodosFuncionarios();

	}

	public void incluirLoja() {
		if (lojaSelecionada == null) {
			controladorMensagens.addMsgAlerta("loja.nao.selecionada");

		} else {

			boolean contem = false;
			for (Loja loja : listaLojasSelecionadas) {
				if (loja.getCnpj().equals(lojaSelecionada.getCnpj())) {
					contem = true;
					controladorMensagens.addMsgAlerta("loja.contida");
					break;
				}
			}
			if (!contem) {
				listaLojasSelecionadas.add(lojaSelecionada);
			}
		}
		lojaSelecionada = new Loja();

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

	public String cancela() {
		return "consultaEventos?faces-redirect=true";
	}

	public String salva() {

		if (eventoSelecionado.getDataInicio().after(eventoSelecionado.getDataFim())
				|| eventoSelecionado.getDataInicio().before(dataMinima)
				|| eventoSelecionado.getDataFim().before(dataMinima)) {
			controladorMensagens.addMsgAlerta("data.incoerente");
		} else {

			eventoSelecionado.setListaLojas(listaLojasSelecionadas);
			eventoSelecionado.getListaTipoEvento().addAll(listaTipoEventoSelecionado);
			if (validaFormulario()) {
				controladorEvento.incluiEvento(eventoSelecionado);
				controladorMensagens.addMsgInfo("evento.inserido");
				return "consultaEventos.xhtml";

			} else {
			}

		}
		return null;
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

	public Evento getEventoSelecionado() {
		return eventoSelecionado;
	}

	public void setEventoSelecionado(Evento eventoSelecionado) {
		this.eventoSelecionado = eventoSelecionado;
	}

	public List<Loja> getLojas() {
		return lojas;
	}

	public void setLojas(List<Loja> lojas) {
		this.lojas = lojas;
	}

	public List<Loja> getListaLojasSelecionadas() {
		return listaLojasSelecionadas;
	}

	public void setListaLojasSelecionadas(List<Loja> listaLojasSelecionadas) {
		this.listaLojasSelecionadas = listaLojasSelecionadas;
	}

	public Loja getLojaSelecionada() {
		return lojaSelecionada;
	}

	public void setLojaSelecionada(Loja lojaSelecionada) {
		this.lojaSelecionada = lojaSelecionada;
	}

	public Loja getLojaExibicao() {
		return lojaExibicao;
	}

	public void setLojaExibicao(Loja lojaExibicao) {
		this.lojaExibicao = lojaExibicao;
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

	public List<Funcionario> getListaFuncionarios() {
		return listaFuncionarios;
	}

	public void setListaFuncionarios(List<Funcionario> listaFuncionarios) {
		this.listaFuncionarios = listaFuncionarios;
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
