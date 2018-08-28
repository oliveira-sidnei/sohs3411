package br.com.ins.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.ins.control.ControladorFuncionario;
import br.com.ins.control.ControladorMensagens;
import br.com.ins.control.ControladorStatus;
import br.com.ins.core.Funcionario;
import br.com.ins.core.Status;

@ViewScoped
@ManagedBean
public class ConsultaFuncionarioBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	ControladorFuncionario controladorFuncionario;
	@EJB
	ControladorMensagens controladorMensagens;
	@EJB
	ControladorStatus controladorStatus;

	private List<Funcionario> funcionarios = new ArrayList<Funcionario>();
	private Funcionario funcionarioSelecionado;
	private List<Status> listaStatus = new ArrayList<Status>();

	@PostConstruct
	public void iniciar() {
		listaStatus = controladorStatus.listaStatusFuncionario();
		funcionarios = controladorFuncionario.retornaTodosFuncionarios();
	}

	public String excluirFuncionario() {
		return "excluiFuncionarios.xhtml";

	}

	public String cadastrarFuncionario() {
		return "cadastraFuncionarios?faces-redirect=true";
	}

	public String editarFuncionario() {
		return "editaFuncionarios.xhtml";

	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public Funcionario getFuncionarioSelecionado() {
		return funcionarioSelecionado;
	}

	public void setFuncionarioSelecionado(Funcionario funcionarioSelecionado) {
		this.funcionarioSelecionado = funcionarioSelecionado;
	}

	public List<Status> getListaStatus() {
		return listaStatus;
	}

}
