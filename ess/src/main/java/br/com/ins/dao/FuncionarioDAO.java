package br.com.ins.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import br.com.ins.core.Funcionario;
import br.com.ins.core.Perfil;

@Stateless
public class FuncionarioDAO {

	@PersistenceContext(unitName = "dbsysess", type = PersistenceContextType.TRANSACTION)
	EntityManager em;

	public void insereFuncionario(Funcionario funcionario) {
		this.em.persist(funcionario);
	}

	public List<Perfil> selecionaPerfil() {
		return this.em.createQuery("select p from Perfil p where p not in(1,6,7)", Perfil.class).getResultList();

	}

	public List<Funcionario> retornaTodosFuncionarios() {
		return this.em.createQuery("select f from Funcionario f", Funcionario.class).getResultList();

	}

	public Funcionario retornaFuncionario(Integer idFuncionario) {
		return this.em.createQuery("select f from Funcionario f where id = :idFuncionario", Funcionario.class)
				.setParameter("idFuncionario", idFuncionario).getSingleResult();
	}

	public void removeFuncionario(Funcionario funcionarioSelecionado) {
		Funcionario funcionario = this.em.find(Funcionario.class, funcionarioSelecionado.getId());
		this.em.remove(funcionario);

	}

	public void editaFuncionario(Funcionario funcionarioEditado) {
		this.em.merge(funcionarioEditado);
	}

}
