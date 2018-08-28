package br.com.ins.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import br.com.ins.core.Perfil;

@Stateless
public class PerfilDAO {
	
	@PersistenceContext(unitName = "dbsysess", type = PersistenceContextType.TRANSACTION)
	EntityManager em;

	public Perfil buscaPerfilLojista() {
		Perfil perfilLojista = this.em.createQuery("select p from Perfil p where p.id = 6", Perfil.class)
				.getSingleResult();
		return perfilLojista;
	}

	public Perfil buscaPerfilCliente() {
		Perfil perfilCliente = this.em.createQuery("select p from Perfil p where p.id = 7", Perfil.class)
				.getSingleResult();
		return perfilCliente;
	}

}
