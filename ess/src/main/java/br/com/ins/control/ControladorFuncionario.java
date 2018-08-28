package br.com.ins.control;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.ins.core.Funcionario;
import br.com.ins.core.Perfil;
import br.com.ins.dao.FuncionarioDAO;

@Stateless
public class ControladorFuncionario implements Serializable {

	@EJB
	FuncionarioDAO daoFuncionario;

	private static final long serialVersionUID = 1L;

	public void insereFuncionario(Funcionario funcionario) {
		// funcionario.setPerfil(daoFuncionario.selecionaPerfil());

		daoFuncionario.insereFuncionario(funcionario);

	}

	public Funcionario retornaFuncionario(Integer idFuncionario) {
		Funcionario funcionario = daoFuncionario.retornaFuncionario(idFuncionario);
		return funcionario;
	}

	public void removeFuncionario(Funcionario funcionarioSelecionado) {
		daoFuncionario.removeFuncionario(funcionarioSelecionado);

	}

	public void editaFuncionario(Funcionario funcionarioEditado) {
		daoFuncionario.editaFuncionario(funcionarioEditado);
	}

	public List<Funcionario> retornaTodosFuncionarios() {
		List<Funcionario> listaFuncionarios = daoFuncionario.retornaTodosFuncionarios();
		return listaFuncionarios;
	}

	public List<Perfil> retornaPerfis() {
		List<Perfil> perfis = daoFuncionario.selecionaPerfil();
		return perfis;

	}

}
