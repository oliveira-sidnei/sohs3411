package br.com.ins.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import br.com.ins.control.ControladorAluguel;
import br.com.ins.control.ControladorEmail;
import br.com.ins.control.ControladorEvento;
import br.com.ins.control.ControladorLoja;
import br.com.ins.control.ControladorStatus;
import br.com.ins.core.Aluguel;
import br.com.ins.core.Evento;
import br.com.ins.core.Loja;

@Singleton
@Startup
public class Agendador {

	@EJB
	ControladorEmail controladorEmail;
	@EJB
	ControladorEvento controladorEvento;
	@EJB
	ControladorStatus controladorStatus;
	@EJB
	ControladorAluguel controladorAluguel;
	@EJB
	ControladorLoja controladorLoja;

	@Schedule(hour = "07")
	public void divulgaEventos() {
		controladorEmail.enviaEventosParaClientes();

	}

	@Schedule(hour = "*", minute = "*", second = "*/30", persistent = false)
	public void modificaAceitoParaAtivo() throws ParseException {
		List<Evento> listaEventosAceitos = controladorEvento.retornaEventosAceitos();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String dataAtualString = sdf.format(new Date());
		Date dataAtual = sdf.parse(dataAtualString);
		for (Evento evento : listaEventosAceitos) {
			String dataInicioString = sdf.format(evento.getDataInicio());
			Date dataInicio = sdf.parse(dataInicioString);
			if (dataInicio.equals(dataAtual)) {

				System.out.println("ATIVO " + evento.getNome());
				evento.setStatus(controladorStatus.eventoAtivo());
				
				controladorEvento.atualizaEvento(evento);
			}else{
				System.out.println("ACEITO " + evento.getNome());
				evento.setStatus(controladorStatus.eventoAceito());
				
				controladorEvento.atualizaEvento(evento);
				
			}

		}
	}

	//
	@Schedule(hour = "*", minute = "*", second = "*/30", persistent = false)
	public void modificaAtivoParaFinalizado() throws ParseException {
		List<Evento> listaEventosAtivos = controladorEvento.retornaEventosAtivos();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String dataAtualString = sdf.format(new Date());
		Date dataAtual = sdf.parse(dataAtualString);

		for (Evento evento : listaEventosAtivos) {
			String dataFimString = sdf.format(evento.getDataFim());
			Date dataFim = sdf.parse(dataFimString);
			if (dataAtual.after(dataFim)) {
				System.out.println("TRUE " + evento.getNome());
				evento.setStatus(controladorStatus.eventoFinalizado());
				controladorEvento.atualizaEvento(evento);
			} else {
				System.out.println("FALSE" + evento.getNome());
				evento.setStatus(controladorStatus.eventoAtivo());
				controladorEvento.atualizaEvento(evento);
			}
		}

	}
	
	@Schedule(hour = "*", minute = "*", second = "*/30", persistent = false)
	public void modificaParaAtrasado() throws ParseException {
		List<Aluguel> listaAlugueisPendentes = controladorAluguel.listaAlugueisPendentes();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String dataAtualString = sdf.format(new Date());
		Date dataAtual = sdf.parse(dataAtualString);

		for (Aluguel aluguel : listaAlugueisPendentes) {
			String dataVencimentoString = sdf.format(aluguel.getDataVencimento());
			Date dataVencimento = sdf.parse(dataVencimentoString);
			if (dataAtual.after(dataVencimento) && aluguel.getDataPagamento() == null) {
				aluguel.setStatus(controladorStatus.aluguelAtrasado());
				controladorAluguel.atualizaAluguel(aluguel);
			} else {
				aluguel.setStatus(controladorStatus.inicializaStatusPagamento());
				controladorAluguel.atualizaAluguel(aluguel);
			}
		}

	}
	
	@Schedule(hour = "*", minute = "*", second = "*/30", persistent = false)
	public void modificaParaSuspenso() throws ParseException {
		List<Loja> listaLojasAtrasadas = controladorLoja.listaLojaComAluguelAtrasado();

		for (Loja loja : listaLojasAtrasadas) {
			loja.setStatus(controladorStatus.lojaSuspensa());
			controladorLoja.atualizaLoja(loja);

		}

	}


}
