package br.com.ins.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.ins.control.ControladorAluguel;
import br.com.ins.control.ControladorMensagens;
import br.com.ins.control.ControladorStatus;
import br.com.ins.core.Aluguel;
import br.com.ins.core.Despesa;
import br.com.ins.core.ItemAluguel;
import br.com.ins.core.Loja;
import br.com.ins.core.Status;

@ViewScoped
@ManagedBean
public class EditaAluguelBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Loja lojaSelecionada;
	private List<Despesa> listaDespesasSelecionada = new ArrayList<Despesa>();
	private List<Despesa> listaDespesas = new ArrayList<Despesa>();
	private ItemAluguel itemAluguelSelecionado = new ItemAluguel();
	private Despesa despesaSelecionada;
	private Aluguel aluguelSelecionado;
	private BigDecimal valorSelecionado;
	private List<ItemAluguel> listaItensAluguel = new ArrayList<ItemAluguel>();
	private List<Status> listaStatus = new ArrayList<Status>();

	@EJB
	ControladorAluguel controladorAluguel;
	@EJB
	ControladorStatus controladorStatus;
	@EJB
	ControladorMensagens controladorMensagens;

	@PostConstruct
	public void iniciar() {

		String idAluguelString = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
				.get("AluguelId");

		aluguelSelecionado = controladorAluguel.buscaAluguel(Integer.parseInt(idAluguelString));

		listaStatus = controladorStatus.listaStatusAluguel();

		listaItensAluguel = controladorAluguel.listaItensAluguel(aluguelSelecionado);

		listaDespesas = controladorAluguel.retornaTodasDespesas();

	}

	public void incluiDespesa() {
		try {
			itemAluguelSelecionado.setDespesa(despesaSelecionada);
			itemAluguelSelecionado.setValor(valorSelecionado);
			itemAluguelSelecionado.setAluguel(aluguelSelecionado);
		} catch (Exception e) {
			e.printStackTrace();
		}

		listaItensAluguel.add(itemAluguelSelecionado);

		itemAluguelSelecionado = new ItemAluguel();
		valorSelecionado = new BigDecimal(0);

	}

	public String salva() {
		if (aluguelSelecionado.getDataPagamento() != null) {
			if (aluguelSelecionado.getDataPagamento().after(aluguelSelecionado.getDataVencimento())) {
				controladorMensagens.addMsgAlerta("data.incoerente2");

			}
		} else if (aluguelSelecionado.getLoja() == null) {
			controladorMensagens.addMsgAlerta("loja.nao.selecionada");

		} else if (listaItensAluguel.size() < 1) {
			controladorMensagens.addMsgAlerta("item.nao.selecionado");
		} else {
			controladorAluguel.atualizaAluguel(aluguelSelecionado);
			controladorAluguel.atualizaItensAluguel(listaItensAluguel);
			controladorMensagens.addMsgAlerta("aluguel.atualizado");
			return "consultaAlugueis.xhtml";
		}
		return null;
	}

	public String cancela() {
		return "consultaAlugueis?faces-redirect=true";

	}

	public void removeDespesa(ItemAluguel itemAluguel) {
		listaItensAluguel.remove(itemAluguel);
	}

	public Loja getLojaSelecionada() {
		return lojaSelecionada;
	}

	public void setLojaSelecionada(Loja lojaSelecionada) {
		this.lojaSelecionada = lojaSelecionada;
	}

	public List<Despesa> getListaDespesas() {
		return listaDespesas;
	}

	public void setListaDespesas(List<Despesa> listaDespesas) {
		this.listaDespesas = listaDespesas;
	}

	public List<Despesa> getListaDespesasSelecionada() {
		return listaDespesasSelecionada;
	}

	public void setListaDespesasSelecionada(List<Despesa> listaDespesasSelecionada) {
		this.listaDespesasSelecionada = listaDespesasSelecionada;
	}

	public Despesa getDespesaSelecionada() {
		return despesaSelecionada;
	}

	public void setDespesaSelecionada(Despesa despesaSelecionada) {
		this.despesaSelecionada = despesaSelecionada;
	}

	public Aluguel getAluguelSelecionado() {
		return aluguelSelecionado;
	}

	public void setAluguelSelecionado(Aluguel aluguelSelecionado) {
		this.aluguelSelecionado = aluguelSelecionado;
	}

	public BigDecimal getValorSelecionado() {
		return valorSelecionado;
	}

	public void setValorSelecionado(BigDecimal valorSelecionado) {
		this.valorSelecionado = valorSelecionado;
	}

	public ItemAluguel getItemAluguelSelecionado() {
		return itemAluguelSelecionado;
	}

	public void setItemAluguelSelecionado(ItemAluguel itemAluguelSelecionado) {
		this.itemAluguelSelecionado = itemAluguelSelecionado;
	}

	public List<ItemAluguel> getListaItensAluguel() {
		return listaItensAluguel;
	}

	public void setListaItensAluguel(List<ItemAluguel> listaItensAluguel) {
		this.listaItensAluguel = listaItensAluguel;
	}

	public List<Status> getListaStatus() {
		return listaStatus;
	}

	public void setListaStatus(List<Status> listaStatus) {
		this.listaStatus = listaStatus;
	}

}
