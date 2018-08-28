package br.com.ins.dao;

import java.math.BigInteger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import br.com.ins.core.Perfil;

@Stateless
public class AcessosDAO {

	@PersistenceContext(unitName = "dbsysess", type = PersistenceContextType.TRANSACTION)
	EntityManager em;

	public boolean verificaAcesso(Perfil perfil, String urlRequisitada) {
		try {
			 this.em
					.createNativeQuery(
							"select permissao from ess_acessos a inner join ess_pagina p on a.idPagina = p.id where a.idPerfil = :perfil and (p.pagina = ?2 or p.paginaMapeada = ?2)")
					.setParameter("perfil", perfil.getId()).setParameter(2, urlRequisitada).getSingleResult();

			
				return true;
			
		} catch (NoResultException nre) {

			return false;
		}
	}

}
