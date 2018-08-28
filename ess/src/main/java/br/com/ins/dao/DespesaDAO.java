package br.com.ins.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import br.com.ins.core.Despesa;

@Stateless
public class DespesaDAO {

	@PersistenceContext(unitName = "dbsysess", type = PersistenceContextType.TRANSACTION)
	EntityManager em;

	public void insereDespesa(Despesa despesaSelecionada) {
		this.em.persist(despesaSelecionada);

	}

	public void atualizaListaDespesas(List<Despesa> listaDespesas, List<Despesa> listaDespesaExcluida) {
		for (Despesa despesa : listaDespesas) {

			this.em.merge(despesa);
		}

		for (Despesa despesa : listaDespesaExcluida) {
			Despesa despesaExcluida = this.em.find(Despesa.class, despesa.getId());
			this.em.remove(despesaExcluida);
		}

	}

	public List<Despesa> retornaDespesas() {
		List<Despesa> listaDespesas = this.em.createQuery("select d from Despesa d", Despesa.class).getResultList();
		return listaDespesas;
	}

}
