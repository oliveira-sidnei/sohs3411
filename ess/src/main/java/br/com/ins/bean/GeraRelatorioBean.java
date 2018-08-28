package br.com.ins.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.ins.control.ControladorAluguel;
import br.com.ins.control.ControladorLoja;
import br.com.ins.control.ControladorMensagens;
import br.com.ins.core.Aluguel;
import br.com.ins.core.Loja;

@ViewScoped
@ManagedBean
public class GeraRelatorioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	ControladorAluguel controladorAluguel;
	@EJB
	ControladorLoja controladorLoja;
	@EJB
	ControladorMensagens controladorMensagens;

	private List<Aluguel> alugueis = new ArrayList<Aluguel>();
	private List<Loja> lojas = new ArrayList<Loja>();
	private Date dataInicio;
	private Date dataFim;
	private Date dataInicioAluguel;
	private Date dataFimAluguel;
	private Loja lojaSelecionada;

	@PostConstruct
	public void iniciar() {
		alugueis = controladorAluguel.retornaTodosAlugueis();
		lojas = controladorLoja.listaLojas();
	}

	public void geraRelatorio() {

	}

	public void validaDatas(Date dataInicio, Date dataFim) {
		if (dataInicio.after(dataFim)) {
			controladorMensagens.addMsgErro("erro.data.incoerente");
		}

	}

	public List<Aluguel> getAlugueis() {
		return alugueis;
	}

	public void setAlugueis(List<Aluguel> alugueis) {
		this.alugueis = alugueis;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public List<Loja> getLojas() {
		return lojas;
	}

	public Loja getLojaSelecionada() {
		return lojaSelecionada;
	}

	public void setLojaSelecionada(Loja lojaSelecionada) {
		this.lojaSelecionada = lojaSelecionada;
	}

	public Date getDataInicioAluguel() {
		return dataInicioAluguel;
	}

	public void setDataInicioAluguel(Date dataInicioAluguel) {
		this.dataInicioAluguel = dataInicioAluguel;
	}

	public Date getDataFimAluguel() {
		return dataFimAluguel;
	}

	public void setDataFimAluguel(Date dataFimAluguel) {
		this.dataFimAluguel = dataFimAluguel;
	}

	// try {
	// System.out.println("Método Relatório CHAMADO 2 ");
	// JasperCompileManager.compileReportToFile("boletos_nao_pagos.jrxml");
	// } catch (JRException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	// }

}
