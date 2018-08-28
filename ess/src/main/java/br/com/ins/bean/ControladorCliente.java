package br.com.ins.bean;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.ins.core.Cliente;
import br.com.ins.dao.ClienteDAO;

@Stateless
public class ControladorCliente implements Serializable {

	@EJB
	ClienteDAO daoCliente;

	private static final long serialVersionUID = 1L;

	public List<Cliente> retornaClientesAtivos() {
		List<Cliente> listaClientesAtivos = daoCliente.retornaClientesAtivos();
		return listaClientesAtivos;
	}

	public void insereCliente(Cliente clienteSelecionado) {
		daoCliente.insereCliente(clienteSelecionado);

	}

	public Cliente buscaClientePorEmail(String emailCliente) {
		Cliente cliente = daoCliente.buscaClientePorEmail(emailCliente);
		return cliente;
	}

}
