package br.com.ins.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.ins.control.ControladorEmail;
import br.com.ins.control.ControladorEvento;
import br.com.ins.control.ControladorLojista;
import br.com.ins.control.ControladorMensagens;
import br.com.ins.control.ControladorStatus;
import br.com.ins.core.Evento;
import br.com.ins.core.Lojista;
import br.com.ins.core.Status;

@ViewScoped
@ManagedBean
public class ConsultaEventoBean implements Serializable {

	@EJB
	ControladorEvento controladorEvento;
	@EJB
	ControladorStatus controladorStatus;
	@EJB
	ControladorEmail controladorEmail;
	@EJB
	ControladorLojista controladorLojista;
	@EJB
	ControladorMensagens controladorMensagens;
	
	
	private Evento eventoSelecionado;
	private List<Evento> eventos = new ArrayList<Evento>();
	private List<Status> listaStatus = new ArrayList<Status>();

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@PostConstruct
	public void iniciar() {
		eventos = controladorEvento.retornaEventos();
		listaStatus = controladorStatus.listaStatusEvento();

	}
	
	public String excluirEvento() {
		return "excluiEventos.xhtml";

	}
	
	public void autorizaEventos(Evento evento) {
		evento.setStatus(controladorStatus.eventoAceito());
		notificaLojistas(evento);
		controladorEvento.atualizaEvento(evento);
		controladorMensagens.addMsgInfo("evento.autorizado");

	}
	
	private List<Lojista> notificaLojistas(Evento evento) {
		List<Lojista> listaLojistas = controladorLojista.lojistasEvento(evento);
		controladorEmail.enviaEmailLojistas(listaLojistas, evento);
		return listaLojistas;
	}

	public void insereEvento() {
		controladorEvento.incluiEvento(eventoSelecionado);
		System.out.println("Persist efetuado");

	}

	public List<Evento> getEventos() {
		return eventos;
	}

	public void setEventos(List<Evento> eventos) {
		this.eventos = eventos;
	}

	public void actionButton(Evento evento) {
		System.out.println(evento);
		System.out.println("Botão acionado!");
		eventoSelecionado = evento;

		// return "detalhaEventos.xhtml";

	}

	public void mostrarLista() {
		for (Evento e : eventos) {
			System.out.println(e.getNome());
		}
	}

	public Evento getEventoSelecionado() {
		return eventoSelecionado;
	}

	public void setEventoSelecionado(Evento eventoSelecionado) {
		this.eventoSelecionado = eventoSelecionado;
	}

	public List<Status> getListaStatus() {
		return listaStatus;
	}
	
	

}
