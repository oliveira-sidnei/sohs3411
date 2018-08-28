package br.com.ins.bean;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.ins.control.ControladorAluguel;
import br.com.ins.core.Aluguel;

@ViewScoped
@ManagedBean
public class ExcluiAluguelBean implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	private Aluguel aluguelSelecionado;
	
	@EJB
	ControladorAluguel controladorAluguel;

	@PostConstruct
	public void iniciar() {
		String idAluguelString = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
				.get("AluguelId");

		aluguelSelecionado = controladorAluguel.buscaAluguel(Integer.parseInt(idAluguelString));

	}

	public Aluguel getAluguelSelecionado() {
		return aluguelSelecionado;
	}

	public void setAluguelSelecionado(Aluguel aluguelSelecionado) {
		this.aluguelSelecionado = aluguelSelecionado;
	}
	
	
	public String excluir() {
		controladorAluguel.excluiAluguel(aluguelSelecionado);
		return "consultaAlugueis.xhtml";
	}
	
	public String cancela() {
		return "consultaAlugueis?faces-redirect=true";
	}
}
