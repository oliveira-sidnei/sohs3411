package br.com.ins.control;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.ins.core.Status;
import br.com.ins.dao.StatusDAO;

@Stateless
public class ControladorStatus implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	StatusDAO daoStatus;

	public Status inicializaAtivo() {
		Status statusInicialAtivo = daoStatus.inicializaStatusAtivo();

		return statusInicialAtivo;
	}

	public Status inicializaStatusPagamento() {
		Status statusInicialPagamento = daoStatus.inicializaStatusPagamento();

		return statusInicialPagamento;
	}

	public Status inicializaStatusSolicitado() {
		Status statusInicialSolicitado = daoStatus.inicializaStatusSolicitado();

		return statusInicialSolicitado;
	}

	public List<Status> listaStatusLoja() {
		List<Status> listaStatus = daoStatus.listaStatusLoja();
		return listaStatus;
	}

	public List<Status> listaStatusFuncionario() {
		List<Status> listaStatus = daoStatus.listaStatusFuncionario();
		return listaStatus;
	}

	public List<Status> listaStatusAluguel() {
		List<Status> listaStatus = daoStatus.listaStatusAluguel();

		return listaStatus;
	}

	public List<Status> listaStatusEvento() {
		List<Status> listaStatus = daoStatus.listaStatusEvento();
		return listaStatus;
	}

	public Status eventoAceito() {
		Status eventoAceito = daoStatus.eventoAceito();
		return eventoAceito;
	}

	public Status eventoRecusado() {
		Status eventoRecusado = daoStatus.eventoRecusado();
		return eventoRecusado;
	}

	public Status eventoAtivo() {
		Status eventoAtivo = daoStatus.eventoAtivo();
		return eventoAtivo;
	}

	public Status eventoFinalizado() {
		Status eventoFinalizado = daoStatus.eventoFinalizado();
		return eventoFinalizado;
	}

	public Status aluguelAtrasado() {
		Status aluguelAtrasado = daoStatus.aluguelAtrasado();
		return aluguelAtrasado;
	}

	public Status lojaSuspensa() {
		Status lojaSuspensa = daoStatus.lojaSuspensa();
		return lojaSuspensa;
	}
}
