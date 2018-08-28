package br.com.ins.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import br.com.ins.core.Loja;
import br.com.ins.core.TipoEvento;

@Stateless
public class LojaDAO {

	@PersistenceContext(unitName = "dbsysess", type = PersistenceContextType.TRANSACTION)
	EntityManager em;

	public List<Loja> listaLojas() {
		return this.em.createQuery("select lj from Loja lj", Loja.class).getResultList();
	}

	public void insereLoja(Loja lojaSelecionada) {

		Set<TipoEvento> listaTipoEventos = new HashSet<TipoEvento>();

		for (TipoEvento tipoEvento : lojaSelecionada.getListaTiposEvento()) {
			TipoEvento tipoEventoSelecionado = this.em.find(TipoEvento.class, tipoEvento.getId());
			listaTipoEventos.add(tipoEventoSelecionado);
		}
		lojaSelecionada.setListaTiposEvento(listaTipoEventos);

		this.em.persist(lojaSelecionada);
	}

	public Loja buscaLoja(Integer idLojaSelecionada) {
		Loja lojaSelecionada = this.em.find(Loja.class, idLojaSelecionada);
		return lojaSelecionada;
	}

	public void atualizaLoja(Loja lojaSelecionada) {
		this.em.merge(lojaSelecionada);

	}

	public void excluiLoja(Loja lojaSelecionada) {
		Loja lojaExcluida = this.em.find(Loja.class, lojaSelecionada.getId());
		System.out.println(lojaExcluida);
		this.em.remove(lojaExcluida);

	}

//	@SuppressWarnings("unchecked")
//	public List<Loja> listaLojasParaEventos() {
//		List<Loja> listaLojas = this.em.createNamedQuery("lojasSemAtraso").getResultList();
//		return listaLojas;
//	}

	public List<Loja> listaLojasAtivas() {
		List<Loja> listaLojas = this.em.createQuery("select lj from Loja lj where lj.status = 10", Loja.class)
				.getResultList();
		return listaLojas;
	}

	public List<Loja> listaLojaComAluguelAtrasado() {
		List<Loja> listaLojas = this.em.createNamedQuery("lojasComAtraso", Loja.class).getResultList();
		return listaLojas;
	}
}
