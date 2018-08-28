package br.com.ins.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.ins.control.ControladorLoja;
import br.com.ins.control.ControladorStatus;
import br.com.ins.core.Loja;
import br.com.ins.core.Status;

@ViewScoped
@ManagedBean
public class ConsultaLojaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Loja> lojas = new ArrayList<Loja>();
	private List<Status> listaStatus = new ArrayList<Status>();
	
	@EJB
	ControladorLoja controladorLoja;
	@EJB
	ControladorStatus controladorStatus;

	@PostConstruct
	public void iniciar() {
		
		listaStatus = controladorStatus.listaStatusLoja();
		lojas = controladorLoja.listaLojas();

	}

	public String excluirLoja() {
		return "excluiLojas.xhtml";

	}

	public List<Loja> getLojas() {
		return lojas;
	}

	public void setLojas(List<Loja> lojas) {
		this.lojas = lojas;
	}

	public List<Status> getListaStatus() {
		return listaStatus;
	}
	
	

}
