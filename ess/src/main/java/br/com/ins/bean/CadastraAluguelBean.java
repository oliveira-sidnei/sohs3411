package br.com.ins.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.ins.control.ControladorAluguel;
import br.com.ins.control.ControladorLoja;
import br.com.ins.control.ControladorMensagens;
import br.com.ins.control.ControladorStatus;
import br.com.ins.core.Aluguel;
import br.com.ins.core.Despesa;
import br.com.ins.core.ItemAluguel;
import br.com.ins.core.Loja;
import br.com.ins.core.Status;

@ViewScoped
@ManagedBean
public class CadastraAluguelBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Aluguel aluguelSelecionado;
	private ItemAluguel itemAluguelSelecionado = new ItemAluguel();
	private List<Despesa> listaDespesas = new ArrayList<Despesa>();
	private List<Despesa> listaDespesasSelecionada = new ArrayList<Despesa>();
	private List<Loja> lojas = new ArrayList<Loja>();
	private Loja lojaSelecionada;
	private Despesa despesaSelecionada;
	private List<ItemAluguel> listaItensAluguel = new ArrayList<ItemAluguel>();
	private BigDecimal valorSelecionado;
	private Date dataAtual = new Date();
	private String dataMinima;
	private Status statusInicial;

	@EJB
	ControladorAluguel controladorAluguel;
	@EJB
	ControladorLoja controladorLoja;
	@EJB
	ControladorStatus controladorStatus;
	@EJB
	ControladorMensagens controladorMensagens;

	@PostConstruct
	public void iniciar() {
		aluguelSelecionado = new Aluguel();
		statusInicial = controladorStatus.inicializaStatusPagamento();
		aluguelSelecionado.setStatus(statusInicial);
		dataMinima = new SimpleDateFormat("dd/MM/yyyy").format(dataAtual);
		lojas = controladorLoja.listaLojas();
		listaDespesas = controladorAluguel.retornaTodasDespesas();
	}

	public void incluiDespesa() {
		if (despesaSelecionada == null || valorSelecionado == null) {
			controladorMensagens.addMsgAlerta("despesa.nao.selecionada");
		} else {
			try {
				itemAluguelSelecionado.setDespesa(despesaSelecionada);
				itemAluguelSelecionado.setValor(valorSelecionado);
				itemAluguelSelecionado.setAluguel(aluguelSelecionado);
			} catch (Exception e) {
				e.printStackTrace();
			}

			listaItensAluguel.add(itemAluguelSelecionado);
		}

		itemAluguelSelecionado = new ItemAluguel();
		despesaSelecionada = new Despesa();
		valorSelecionado = new BigDecimal(0);

	}

	public void removeDespesa(ItemAluguel itemAluguel) {
		listaItensAluguel.remove(itemAluguel);

	}

	public String salva() {
		if (aluguelSelecionado.getLoja() == null) {
			controladorMensagens.addMsgAlerta("loja.nao.selecionada");
		} else if (listaItensAluguel.size() < 1) {
			controladorMensagens.addMsgAlerta("item.nao.selecionado");
		} else if (aluguelSelecionado.getDataVencimento().before(dataAtual)) {
				controladorMensagens.addMsgAlerta("erro.data.vencimento");
		} else {

			controladorAluguel.insereAluguel(aluguelSelecionado);
			controladorAluguel.insereItensAluguel(listaItensAluguel, aluguelSelecionado);
			
			controladorMensagens.addMsgInfo("aluguel.inserido");

			return "consultaAlugueis.xhtml";
		}

		return null;

	}

	public String cancela() {
		return "consultaAlugueis?faces-redirect=true";

	}

	public Aluguel getAluguelSelecionado() {
		return aluguelSelecionado;
	}

	public void setAluguelSelecionado(Aluguel aluguelSelecionado) {
		this.aluguelSelecionado = aluguelSelecionado;
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

	public List<Loja> getLojas() {
		return lojas;
	}

	public void setLojas(List<Loja> lojas) {
		this.lojas = lojas;
	}

	public Loja getLojaSelecionada() {
		return lojaSelecionada;
	}

	public void setLojaSelecionada(Loja lojaSelecionada) {
		this.lojaSelecionada = lojaSelecionada;
	}

	public Despesa getDespesaSelecionada() {
		return despesaSelecionada;
	}

	public void setDespesaSelecionada(Despesa despesaSelecionada) {
		this.despesaSelecionada = despesaSelecionada;
	}

	public List<ItemAluguel> getListaItensAluguel() {
		return listaItensAluguel;
	}

	public void setListaItensAluguel(List<ItemAluguel> listaItensAluguel) {
		this.listaItensAluguel = listaItensAluguel;
	}

	public ItemAluguel getItemAluguelSelecionado() {
		return itemAluguelSelecionado;
	}

	public void setItemAluguelSelecionado(ItemAluguel itemAluguelSelecionado) {
		this.itemAluguelSelecionado = itemAluguelSelecionado;
	}

	public BigDecimal getValorSelecionado() {
		return valorSelecionado;
	}

	public void setValorSelecionado(BigDecimal valorSelecionado) {
		this.valorSelecionado = valorSelecionado;
	}

	public String getDataMinima() {
		return dataMinima;
	}

}
