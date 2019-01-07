package br.com.ins.dao;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import br.com.ins.core.Aluguel;
import br.com.ins.core.Despesa;
import br.com.ins.core.ItemAluguel;
import br.com.ins.core.Loja;
import br.com.ins.core.Lojista;

@Stateless
public class AluguelDAO {
	@PersistenceContext(unitName = "dbsysess", type = PersistenceContextType.TRANSACTION)
	EntityManager em;

	public List<Despesa> listaDespesas() {

		return this.em.createQuery("select d from Despesa d", Despesa.class).getResultList();
	}

	public void insereAluguel(Aluguel aluguelSelecionado) {
		System.out.println(aluguelSelecionado.getDataVencimento());
		this.em.persist(aluguelSelecionado);
	}

	public void insereItensAluguel(List<ItemAluguel> listaItensAluguel, Aluguel aluguelSelecionado) {
		Aluguel aluguelConsultado = this.em.find(Aluguel.class, aluguelSelecionado.getId());

		for (ItemAluguel itemAluguel : listaItensAluguel) {
			itemAluguel.setAluguel(aluguelConsultado);
			this.em.persist(itemAluguel);
		}

	}

	public List<Aluguel> retornaTodosAlugueis() {
		List<Aluguel> listaAlugueis = this.em.createQuery("select a from Aluguel a", Aluguel.class).getResultList();
		return listaAlugueis;
	}

	public Aluguel buscaAluguel(Integer idAluguel) {
		Aluguel aluguel = this.em.find(Aluguel.class, idAluguel);
		return aluguel;
	}

	public List<ItemAluguel> listaItensAluguel(Aluguel aluguelSelecionado) {
		List<ItemAluguel> listaItensAluguel = this.em.createQuery(
				"select ia from ItemAluguel ia JOIN FETCH ia.aluguel where ia.aluguel = :aluguelSelecionado",
				ItemAluguel.class).setParameter("aluguelSelecionado", aluguelSelecionado).getResultList();
		return listaItensAluguel;
	}

	public void atualizaItensAluguel(List<ItemAluguel> listaItensAluguel) {

		for (ItemAluguel itemAluguel : listaItensAluguel) {
			this.em.merge(itemAluguel);
		}

	}

	public List<Aluguel> buscaAluguelPorLojista(Lojista lojistaSelecionado) {

		List<Aluguel> listaAlugueis = this.em
				.createQuery("select a from Aluguel a inner join a.loja lj where lj.lojista = :lojista", Aluguel.class)
				.setParameter("lojista", lojistaSelecionado).getResultList();

		return listaAlugueis;
	}

	public void excluiAluguel(Aluguel aluguelSelecionado) {
		Aluguel aluguelExcluido = this.em.find(Aluguel.class, aluguelSelecionado.getId());
		this.em.remove(aluguelExcluido);

	}

	public void insereDocumento(Aluguel aluguelSelecionado) {
		this.em.merge(aluguelSelecionado);

	}

	public List<Aluguel> buscaAluguelPorLoja(Loja lojaSelecionada) {
		List<Aluguel> listaAlugueis = this.em
				.createQuery("select a from Aluguel a where a.loja = :lojaSelecionada", Aluguel.class)
				.setParameter("lojaSelecionada", lojaSelecionada).getResultList();

		return listaAlugueis;
	}

	public List<Aluguel> lojasInadimplentes(Date dataInicio, Date dataFim) {
		List<Aluguel> listaAlugueis = this.em
				.createQuery("select a from Aluguel a where (a.dataVencimento between :dataInicio and :dataFim) and a.status = 55",
						Aluguel.class)
				.setParameter("dataInicio", dataInicio).setParameter("dataFim", dataFim).getResultList();
		return listaAlugueis;
	}

	public List<Aluguel> listaAlugueisPendentes() {
		List<Aluguel> listaAlugueis = this.em
				.createQuery("select al from Aluguel al where al.status in (40,55)", Aluguel.class).getResultList();
		return listaAlugueis;
	}

	public void atualizaAluguel(Aluguel aluguelSelecionado) {
		this.em.merge(aluguelSelecionado);
		
	}
}
