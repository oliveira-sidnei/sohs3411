package br.com.ins.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.ins.control.ControladorAluguel;
import br.com.ins.control.ControladorStatus;
import br.com.ins.core.Aluguel;
import br.com.ins.core.Status;

@ViewScoped
@ManagedBean
public class ConsultaAluguelBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	ControladorAluguel controladorAluguel;
	@EJB
	ControladorStatus controladorStatus;

	private List<Aluguel> alugueis = new ArrayList<Aluguel>();
	private Aluguel aluguelSelecionado;
	private List<Status> listaStatus = new ArrayList<Status>();

	@PostConstruct
	public void iniciar() {
		listaStatus = controladorStatus.listaStatusAluguel();
		alugueis = controladorAluguel.retornaTodosAlugueis();

	}

	public String excluirAluguel() {
		return "excluiAlugueis.xhtml";

	}
	
	public void detalhaAluguel(Aluguel aluguel) {
		aluguelSelecionado = aluguel;

	}

	public List<Aluguel> getAlugueis() {
		return alugueis;
	}

	public void setAlugueis(List<Aluguel> alugueis) {
		this.alugueis = alugueis;
	}

	public Aluguel getAluguelSelecionado() {
		return aluguelSelecionado;
	}

	public void setAluguelSelecionado(Aluguel aluguelSelecionado) {
		this.aluguelSelecionado = aluguelSelecionado;
	}

	public List<Status> getListaStatus() {
		return listaStatus;
	}
	
	

}
