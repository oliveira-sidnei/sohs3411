package br.com.ins.control;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.ins.core.Evento;
import br.com.ins.core.Lojista;
import br.com.ins.core.Usuario;
import br.com.ins.dao.LojistaDAO;

@Stateless
public class ControladorLojista implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	LojistaDAO daoLojista;

	public List<Lojista> retornaTodosLojistas() {

		List<Lojista> listaLojistas = daoLojista.listaTodosLojistas();
		return listaLojistas;

	}

	public void insereLojista(Lojista lojistaSelecionado) {
		daoLojista.insereLojista(lojistaSelecionado);

	}

	public Lojista buscaLojista(Integer idLojista) {
		Lojista lojistaSelecionado = daoLojista.buscaLojista(idLojista);
		return lojistaSelecionado;
	}

	public void editaLojista(Lojista lojistaSelecionado) {
		daoLojista.editaLojista(lojistaSelecionado);
		
	}

	public void removeLojista(Lojista lojistaSelecionado) {
		daoLojista.removeLojista(lojistaSelecionado);
		
	}

	public Lojista buscaLojistaPorUsuario(Usuario usuarioSelecionado) {
			Lojista lojistaSelecionado = daoLojista.buscaLojistaPorUsuario(usuarioSelecionado);
		return lojistaSelecionado;
	}

	public List<Lojista> lojistasEvento(Evento evento) {
		List<Lojista> listaLojistas = daoLojista.buscaLojistasPorEvento(evento);
		return listaLojistas;
	}

}
