package br.com.ins.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import br.com.ins.core.Cliente;

@Stateless
public class ClienteDAO {

	@PersistenceContext(unitName = "dbsysess", type = PersistenceContextType.TRANSACTION)
	EntityManager em;

	public List<Cliente> retornaClientesAtivos() {
		List<Cliente> clientesAtivos = this.em.createQuery("select c from Cliente c where c.status = 10", Cliente.class)
				.getResultList();
		return clientesAtivos;

	}

	public void insereCliente(Cliente clienteSelecionado) {
		if (clienteSelecionado.getId() != null) {
			this.em.merge(clienteSelecionado);
		} else {
			this.em.persist(clienteSelecionado);
		}

	}

	public Cliente buscaClientePorEmail(String emailCliente) {
		Cliente cliente = this.em.createQuery("select c from Cliente c where c.email = :emailCliente ", Cliente.class)
				.setParameter("emailCliente", emailCliente).getSingleResult();
		return cliente;
	}

}
