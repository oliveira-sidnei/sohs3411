package br.com.ins.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.ins.control.ControladorEvento;
import br.com.ins.control.ControladorMensagens;
import br.com.ins.core.Evento;
import br.com.ins.core.Loja;

@ViewScoped
@ManagedBean
public class ExcluiEventoBean implements Serializable {

	private Evento eventoSelecionado;
	private List<Loja> lojas = new ArrayList<Loja>();

	@EJB
	ControladorEvento controladorEvento;

	@EJB
	ControladorMensagens controladorMensagens;

	private static final long serialVersionUID = 1L;

	@PostConstruct
	public void iniciar() {
		String idEventoString = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
				.get("EventoId");

		eventoSelecionado = controladorEvento.buscaEvento(Integer.parseInt(idEventoString));

		lojas = controladorEvento.buscaLojasEvento(eventoSelecionado);
	}

	public String cancela() {
		return "consultaEventos?faces-redirect=true";

	}

	public String exclui() {
		try {
			controladorEvento.excluiEvento(eventoSelecionado);
			controladorMensagens.addMsgInfo("evento.excluido");
			return "consultaEventos.xhtml";

		} catch (Exception e) {

		}
		return null;

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

}
