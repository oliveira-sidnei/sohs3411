package br.com.ins.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.ins.control.ControladorLojista;
import br.com.ins.core.Lojista;

@ViewScoped
@ManagedBean
public class ConsultaLojistaBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	ControladorLojista controladorLojista;

	private Lojista lojistaSelecionado;
	private List<Lojista> listaLojistas = new ArrayList<Lojista>();

	@PostConstruct
	public void iniciar() {
		listaLojistas = controladorLojista.retornaTodosLojistas();
		
	}

	public String cadastraLojista() {
		return "cadastraLojistas?faces-redirect=true";
	}

	public String excluirLojista() {
		return "excluiLojistas.xhtml";

	}

	public Lojista getLojistaSelecionado() {
		return lojistaSelecionado;
	}

	public void setLojistaSelecionado(Lojista lojistaSelecionado) {
		this.lojistaSelecionado = lojistaSelecionado;
	}

	public List<Lojista> getListaLojistas() {
		return listaLojistas;
	}

	public void setListaLojistas(List<Lojista> listaLojistas) {
		this.listaLojistas = listaLojistas;
	}

}
