package br.com.ins.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import br.com.ins.core.Cliente;
import br.com.ins.core.Evento;
import br.com.ins.core.Loja;
import br.com.ins.core.Status;

@Stateless
public class EventoDAO {

	@PersistenceContext(unitName = "dbsysess", type = PersistenceContextType.TRANSACTION)
	EntityManager em;

	public void incluiEvento(Evento evento) {
		List<Loja> lojas = new ArrayList<Loja>();
		for (Loja loja : evento.getListaLojas()) {
			loja = this.em.find(Loja.class, loja.getId());
			lojas.add(loja);
		}
		evento.setListaLojas(lojas);
		this.em.persist(evento);

	}

	public List<Evento> retornaTodosEventos() {
		List<Evento> listaEventos = this.em.createQuery("select e from Evento e ", Evento.class).getResultList();
		return listaEventos;
	}

	public Evento buscaEvento(Integer idEvento) {
		Evento evento = this.em.find(Evento.class, idEvento);
		return evento;
	}

	@SuppressWarnings("unchecked")
	public List<Loja> buscaLojasEvento(Evento eventoSelecionado) {

		List<Loja> lojas = this.em.createNativeQuery(
				"select lj.ID, " + " lj.CNPJ," + " lj.DATAFIM," + " lj.DATAINICIO," + " lj.EMAIL," + " lj.IDLOJISTA,"
						+ " lj.NOME," + " lj.NOME," + " lj.NUMERO," + " lj.IDSTATUS," + " lj.TELEFONE"
						+ " from ess_evento_loja el inner join ess_loja lj on el.idLoja = lj.id where el.idEvento = :idEvento",
				Loja.class).setParameter("idEvento", eventoSelecionado.getId()).getResultList();

		return lojas;
	}

	public void atualizaEvento(Evento eventoSelecionado) {
		this.em.merge(eventoSelecionado);
	}

	public void excluiEvento(Evento eventoSelecionado) {
		Evento eventoExcluido = this.em.find(Evento.class, eventoSelecionado.getId());
		this.em.remove(eventoExcluido);

	}

	public List<Evento> consultaTodosEventosAtivos() {
		List<Evento> listaEventosAtivos = this.em
				.createQuery("select e from Evento e where e.status = 10", Evento.class).getResultList();
		return listaEventosAtivos;
	}

	@SuppressWarnings("unchecked")
	public List<Evento> retornaEventosPorCliente(Cliente cliente) {
		List<Evento> listaEventos = this.em.createNamedQuery("eventosPorCliente")
				.setParameter("idCliente", cliente.getId()).getResultList();
		return listaEventos;
	}

	public List<Evento> eventosPorLojaValor(Loja loja) {
		List<Evento> listaEventos = this.em.createNamedQuery("eventosPorLojaValor", Evento.class)
				.setParameter("idLoja", loja.getId()).getResultList();
		return listaEventos;
	}

	public List<Evento> retornaEventosPorStatus(Status status) {
		List<Evento> listaEventos = this.em.createQuery("select e from Evento e where e.status = :status", Evento.class)
				.setParameter("status", status).getResultList();
		return listaEventos;
	}

	public List<Evento> retornaEventosPorAceitos() {
			List<Evento> listaEventos = this.em.createQuery("select e from Evento e where e.status = 65", Evento.class).getResultList();
			return listaEventos;
	}
	public List<Evento> retornaEventosAtivos() {
		List<Evento> listaEventos = this.em.createQuery("select e from Evento e where e.status = 10", Evento.class).getResultList();
		return listaEventos;
}
}
