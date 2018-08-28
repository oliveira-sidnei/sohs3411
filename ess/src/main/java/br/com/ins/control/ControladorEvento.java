package br.com.ins.control;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.ins.core.Cliente;
import br.com.ins.core.Evento;
import br.com.ins.core.Loja;
import br.com.ins.core.Status;
import br.com.ins.core.TipoEvento;
import br.com.ins.dao.EventoDAO;
import br.com.ins.dao.TipoEventoDAO;

@Stateless
public class ControladorEvento implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
	TipoEventoDAO daoTipoEvento;
	@EJB
	EventoDAO daoEvento;

	public List<TipoEvento> retornaTiposEvento() {

		List<TipoEvento> tiposEvento = daoTipoEvento.retornaTiposEvento();
		return tiposEvento;
	}

	public void incluiEvento(Evento eventoSelecionado) {
		daoEvento.incluiEvento(eventoSelecionado);

	}

	public List<Evento> retornaEventos() {
		List<Evento> eventos = daoEvento.retornaTodosEventos();

		return eventos;
	}

	public Evento buscaEvento(Integer idEvento) {

		Evento eventoSelecionado = daoEvento.buscaEvento(idEvento);

		return eventoSelecionado;
	}

	public List<Loja> buscaLojasEvento(Evento eventoSelecionado) {
		List<Loja> lojasDoEvento = daoEvento.buscaLojasEvento(eventoSelecionado);
		return lojasDoEvento;
	}

	public void atualizaEvento(Evento eventoSelecionado) {
		daoEvento.atualizaEvento(eventoSelecionado);
	}

	public void excluiEvento(Evento eventoSelecionado) {
		daoEvento.excluiEvento(eventoSelecionado);
	}

	public List<Evento> consultaTodosEventosAtivos() {
		List<Evento> listaEventosAtivos = daoEvento.consultaTodosEventosAtivos();
		return listaEventosAtivos;
	}

	public void atualizaListaTiposEvento(List<TipoEvento> listaTiposEvento, List<TipoEvento> listaTiposEventoExcluido) {
		daoTipoEvento.atualizaListaTiposEvento(listaTiposEvento, listaTiposEventoExcluido);

	}

	public List<Evento> retornaEventosPorCliente(Cliente cliente) {
		List<Evento> listaEventos = daoEvento.retornaEventosPorCliente(cliente);
		return listaEventos;
	}

	public List<Evento> eventosPorLoja(Loja lojaSelecionada) {
		List<Evento> listaEventos = daoEvento.eventosPorLojaValor(lojaSelecionada);
		return listaEventos;
	}

	public List<Evento> retornaEventosPorStatus(Status status) {
		List<Evento> listaEventos = daoEvento.retornaEventosPorStatus(status);
		return listaEventos;
	}

	public List<Evento> retornaEventosAceitos() {
		List<Evento> listaEventos = daoEvento.retornaEventosPorAceitos();
		return listaEventos;
	}

	public List<Evento> retornaEventosAtivos() {
		List<Evento> listaEventos = daoEvento.retornaEventosAtivos();
		return listaEventos;
	}
}
