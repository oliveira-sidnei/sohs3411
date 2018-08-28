package br.com.ins.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import br.com.ins.core.Evento;
import br.com.ins.core.Lojista;
import br.com.ins.core.Usuario;

@Stateless
public class LojistaDAO {

	@PersistenceContext(unitName = "dbsysess", type = PersistenceContextType.TRANSACTION)
	EntityManager em;

	public void insereLojista(Lojista lojista) {
		this.em.persist(lojista);

	}

	public List<Lojista> listaTodosLojistas() {
		return this.em.createQuery("select l from Lojista l", Lojista.class).getResultList();
	}

	public Lojista buscaLojista(Integer idLojista) {
		Lojista lojista = this.em.find(Lojista.class, idLojista);
		return lojista;
	}

	public void editaLojista(Lojista lojistaSelecionado) {
		this.em.merge(lojistaSelecionado);
	}

	public void removeLojista(Lojista lojistaSelecionado) {
		Lojista lojistaExcluido = this.em.find(Lojista.class, lojistaSelecionado.getId());
		this.em.remove(lojistaExcluido);

	}

	public Lojista buscaLojistaPorUsuario(Usuario usuarioSelecionado) {
		try{
		Lojista lojistaSelecionado = this.em
				.createQuery("select l from Lojista l where l.usuario = :usuarioSelecionado", Lojista.class)
				.setParameter("usuarioSelecionado", usuarioSelecionado).getSingleResult();
		return lojistaSelecionado;
		}catch(NoResultException nre){
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Lojista> buscaLojistasPorEvento(Evento evento) {
		List<Lojista> listaLojistas = this.em.createNamedQuery("lojistasPorEvento").getResultList();
		return listaLojistas;
	}
}
