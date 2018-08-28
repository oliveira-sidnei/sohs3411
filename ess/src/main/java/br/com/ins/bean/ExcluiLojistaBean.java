package br.com.ins.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.ins.control.ControladorLojista;
import br.com.ins.control.ControladorMensagens;
import br.com.ins.core.Lojista;

@ViewScoped
@ManagedBean
public class ExcluiLojistaBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Lojista lojistaSelecionado;
	@EJB
	ControladorLojista controladorLojista;
	@EJB
	ControladorMensagens controladorMensagens;

	@PostConstruct
	public void iniciar() {
		String idLojistaString = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
				.get("LojistaId");

		lojistaSelecionado = controladorLojista.buscaLojista(Integer.parseInt(idLojistaString));

	}
	
	public String exclui() {
		controladorLojista.removeLojista(lojistaSelecionado);
		controladorMensagens.addMsgInfo("lojista.excluido");
		return "consultaLojistas.xhtml";

	}
	
	public String cancela() {
		return "consultaLojistas?faces-redirect=true" ;

	}


	public Lojista getLojistaSelecionado() {
		return lojistaSelecionado;
	}

	public void setLojistaSelecionado(Lojista lojistaSelecionado) {
		this.lojistaSelecionado = lojistaSelecionado;
	}

}
