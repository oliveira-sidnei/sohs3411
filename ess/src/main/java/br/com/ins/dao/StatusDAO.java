package br.com.ins.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import br.com.ins.core.Status;

@Stateless
public class StatusDAO {

	@PersistenceContext(unitName = "dbsysess", type = PersistenceContextType.TRANSACTION)
	EntityManager em;

	public Status inicializaStatusAtivo() {

		Status statusInicial = this.em.createQuery("select s from Status s where s.id = 10", Status.class)
				.getSingleResult();

		return statusInicial;
	}

	public Status inicializaStatusPagamento() {

		Status statusInicial = this.em.createQuery("select s from Status s where s.id = 40", Status.class)
				.getSingleResult();

		return statusInicial;
	}

	public Status inicializaStatusSolicitado() {
		Status statusInicial = this.em.createQuery("select s from Status s where s.id = 60 ", Status.class)
				.getSingleResult();
		return statusInicial;

	}

	public List<Status> listaStatusLoja() {
		List<Status> listaStatus = this.em
				.createQuery("select s from Status s where s.id in(10, 35, 45) ", Status.class).getResultList();
		return listaStatus;
	}

	public List<Status> listaStatusFuncionario() {
		List<Status> listaStatus = this.em.createQuery("select s from Status s where s.id in(10, 20, 30)", Status.class)
				.getResultList();
		return listaStatus;
	}

	public List<Status> listaStatusAluguel() {
		List<Status> listaStatus = this.em.createQuery("select s from Status s where s.id in(40, 50, 55)", Status.class)
				.getResultList();
		return listaStatus;
	}

	public List<Status> listaStatusEvento() {
		List<Status> listaStatus = this.em
				.createQuery("select s from Status s where s.id in (10,15,60,70)", Status.class).getResultList();
		return listaStatus;
	}

	public Status eventoAceito() {
		Status eventoAceito = this.em.createQuery("select s from Status s where s.id = 65 ", Status.class)
				.getSingleResult();
		return eventoAceito;
	}

	public Status eventoRecusado() {
		Status eventoRecusado = this.em.createQuery("select s from Status s where s.id = 75 ", Status.class)
				.getSingleResult();
		return eventoRecusado;
	}

	public Status eventoAtivo() {
		Status eventoAtivo = this.em.createQuery("select s from Status s where s.id = 10 ", Status.class)
				.getSingleResult();
		return eventoAtivo;
	}

	public Status eventoFinalizado() {
		Status eventoFinalizado = this.em.createQuery("select s from Status s where s.id = 70 ", Status.class)
				.getSingleResult();
		return eventoFinalizado;
	}

	public Status aluguelAtrasado() {
		Status aluguelAtrasado = this.em.createQuery("select s from Status s where s.id = 55 ", Status.class)
				.getSingleResult();
		return aluguelAtrasado;
	}

	public Status lojaSuspensa() {
		Status lojaSuspensa = this.em.createQuery("select s from Status s where s.id = 45 ", Status.class)
				.getSingleResult();
		return lojaSuspensa;
	}
}
