package br.com.ins.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.ins.control.ControladorFuncionario;
import br.com.ins.control.ControladorMensagens;
import br.com.ins.core.Funcionario;

@ViewScoped
@ManagedBean
public class ExcluiFuncionarioBean implements Serializable {

	/**
	 * 
	 */
	@EJB
	ControladorFuncionario controladorFuncionario;

	@EJB
	ControladorMensagens controladorMensagens;

	private static final long serialVersionUID = 1L;

	private Funcionario funcionarioSelecionado;

	@PostConstruct
	public void iniciar() {
		String idFuncionarioString = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
				.get("FuncionarioId");

		funcionarioSelecionado = controladorFuncionario.retornaFuncionario(Integer.parseInt(idFuncionarioString));

	}

	public String cancela() {
		return "consultaFuncionarios?faces-redirect=true";

	}

	public String exclui() {
		controladorFuncionario.removeFuncionario(funcionarioSelecionado);
		controladorMensagens.addMsgInfo("funcionario.excluido");
		return "consultaFuncionarios.xhtml";

	}

	public Funcionario getFuncionarioSelecionado() {
		return funcionarioSelecionado;
	}

	public void setFuncionarioSelecionado(Funcionario funcionarioSelecionado) {
		this.funcionarioSelecionado = funcionarioSelecionado;
	}

}
