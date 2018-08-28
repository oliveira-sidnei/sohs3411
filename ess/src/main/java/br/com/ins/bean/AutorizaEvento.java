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

@ViewScoped
@ManagedBean
public class AutorizaEvento implements Serializable {

	@EJB
	ControladorStatus controladorStatus;
	@EJB
	ControladorEvento controladorEvento;
	@EJB
	ControladorMensagens controladorMensagens;
	@EJB
	ControladorLojista controladorLojista;
	@EJB
	ControladorEmail controladorEmail;

	private List<Evento> listaEventosSolicitados = new ArrayList<Evento>();

	private static final long serialVersionUID = 1L;

	@PostConstruct
	public void iniciar() {
		listaEventosSolicitados = controladorEvento
				.retornaEventosPorStatus(controladorStatus.inicializaStatusSolicitado());

	}
	
	public void feedClientes(){
		controladorEmail.enviaEventosParaClientes();
	}

	public void autorizaEventos(Evento evento) {
		evento.setStatus(controladorStatus.eventoAceito());
		notificaLojistas(evento);
		controladorEvento.atualizaEvento(evento);
		controladorMensagens.addMsgInfo("evento.autorizado");
		listaEventosSolicitados.remove(evento);

	}
	
	public void recusaEventos(Evento evento) {
		evento.setStatus(controladorStatus.eventoRecusado());
		notificaLojistas(evento);
		controladorEvento.atualizaEvento(evento);
		controladorMensagens.addMsgInfo("evento.autorizado");
		listaEventosSolicitados.remove(evento);

	}

	private List<Lojista> notificaLojistas(Evento evento) {
		List<Lojista> listaLojistas = controladorLojista.lojistasEvento(evento);
		controladorEmail.enviaEmailLojistas(listaLojistas, evento);
		return listaLojistas;

	}

	public List<Evento> getListaEventosSolicitados() {
		return listaEventosSolicitados;
	}
	
	
}