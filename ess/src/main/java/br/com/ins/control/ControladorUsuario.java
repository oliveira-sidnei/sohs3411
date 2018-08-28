package br.com.ins.control;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.ins.core.Usuario;
import br.com.ins.dao.UsuarioDAO;

@Stateless
public class ControladorUsuario implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@EJB
	UsuarioDAO daoUsuario;
	
	public void insereUsuario(Usuario usuario) {
		daoUsuario.insereUsuario(usuario);

	}

	public boolean validaLogin(Usuario user) {
		boolean loginValidado = daoUsuario.buscaUsuario(user);
			return loginValidado;
	}

	public Usuario buscaUsuario(String nomeUsuario) {
		Usuario usuario = daoUsuario.buscaUsuarioPorLogin(nomeUsuario);
		return usuario;
	}

}
