package br.com.ins.control;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.ins.core.Loja;
import br.com.ins.dao.LojaDAO;

@Stateless
public class ControladorLoja implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	LojaDAO daoLoja;

	public List<Loja> listaLojas() {

		List<Loja> listaLojas = daoLoja.listaLojas();
		return listaLojas;
	}

	public void insereLoja(Loja lojaSelecionada) {
		daoLoja.insereLoja(lojaSelecionada);

	}

	public Loja buscaLoja(Integer idLojaSelecionada) {
		Loja lojaSelecionada = daoLoja.buscaLoja(idLojaSelecionada);
		return lojaSelecionada;

	}

	public void atualizaLoja(Loja lojaSelecionada) {

		daoLoja.atualizaLoja(lojaSelecionada);
	}

	public void excluiLoja(Loja lojaSelecionada) {
		daoLoja.excluiLoja(lojaSelecionada);

	}
//
//	public List<Loja> listaLojaSemAtraso() {
//		List<Loja> listaLojas = daoLoja.listaLojasParaEventos();
//		return listaLojas;
//	}

	public List<Loja> listaLojasAtivas() {
		List<Loja> listaLojas = daoLoja.listaLojasAtivas();
		return listaLojas;
	}

	public List<Loja> listaLojaComAluguelAtrasado() {
	List<Loja> listaLojas = daoLoja.listaLojaComAluguelAtrasado();
		return listaLojas;
	}
}
