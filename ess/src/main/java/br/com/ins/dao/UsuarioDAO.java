package br.com.ins.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import br.com.ins.core.Usuario;

@Stateless
public class UsuarioDAO {

	@PersistenceContext(unitName = "dbsysess", type = PersistenceContextType.TRANSACTION)
	EntityManager em;

	public void insereUsuario(Usuario usuario) {
		this.em.persist(usuario);

	}

	public boolean buscaUsuario(Usuario user) {
		List<Usuario> usuarios = this.em
				.createQuery("select u from Usuario u where u.nameUser = :nomeUsuario and u.password = :senha", Usuario.class)
				.setParameter("nomeUsuario", user.getNameUser()).setParameter("senha", user.getPassword())
				.getResultList();

		if (usuarios.size() != 0) {
			return true;
		} else {
			return false;
		}
	}

	public Usuario buscaUsuarioPorLogin(String nomeUsuario) {
		Usuario usuarioLogado = this.em.createQuery("select u from Usuario u where u.nameUser = :nomeUsuario", Usuario.class)
				.setParameter("nomeUsuario", nomeUsuario).getSingleResult();
		return usuarioLogado;
	}
}
