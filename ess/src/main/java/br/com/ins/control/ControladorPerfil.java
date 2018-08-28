package br.com.ins.control;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.ins.core.Perfil;
import br.com.ins.dao.PerfilDAO;

@Stateless
public class ControladorPerfil implements Serializable {
	
	@EJB
	PerfilDAO daoPerfil;

	
	private static final long serialVersionUID = 1L;
	
	

	public Perfil buscaPerfilLojista() {
		Perfil perfilLojista = daoPerfil.buscaPerfilLojista();
		return perfilLojista;
	}
	
	public Perfil buscaPerfilCliente() {
		Perfil perfilCliente = daoPerfil.buscaPerfilCliente();
		return perfilCliente;
	}

}
