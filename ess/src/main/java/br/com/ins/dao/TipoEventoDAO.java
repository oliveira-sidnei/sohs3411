package br.com.ins.dao;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import br.com.ins.core.TipoEvento;

@Stateless
public class TipoEventoDAO {

	@PersistenceContext(unitName = "dbsysess", type = PersistenceContextType.TRANSACTION)
	EntityManager em;

	public List<TipoEvento> retornaTiposEvento() {
		List<TipoEvento> listaTiposEvento = this.em.createQuery("select tp from TipoEvento tp", TipoEvento.class)
				.getResultList();
		return listaTiposEvento;
	}

	public void atualizaListaTiposEvento(List<TipoEvento> listaTiposEvento, List<TipoEvento> listaTiposEventoExcluido) {
		for (TipoEvento tipoEvento : listaTiposEvento) {
			this.em.merge(tipoEvento);
		}
		
		for(TipoEvento tipoEvento : listaTiposEventoExcluido){
			TipoEvento tipoEventoExcluido = this.em.find(TipoEvento.class, tipoEvento.getId());
			this.em.remove(tipoEventoExcluido);
		}

	}

	@SuppressWarnings("unchecked")
	public List<TipoEvento> tipoEventoMaisProcurados(Date dataInicio, Date dataFim) {
		List<TipoEvento> listaTipoEvento = this.em.createNamedQuery("tipoEventoMaisProcurado")
				.setParameter("dataInicio", dataInicio).setParameter("dataFim", dataFim).getResultList();
		return listaTipoEvento;
	}

}
