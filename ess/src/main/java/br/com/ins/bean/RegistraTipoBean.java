package br.com.ins.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.ins.control.ControladorDespesa;
import br.com.ins.control.ControladorEvento;
import br.com.ins.control.ControladorMensagens;
import br.com.ins.core.Despesa;
import br.com.ins.core.TipoEvento;

@ViewScoped
@ManagedBean
public class RegistraTipoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	ControladorEvento controladorEvento;
	@EJB
	ControladorDespesa controladorDespesa;
	@EJB
	ControladorMensagens controladorMensagens;

	private TipoEvento tipoEventoSelecionado = new TipoEvento();
	private Despesa tipoDespesaSelecionada = new Despesa();

	private List<TipoEvento> listaTiposEvento = new ArrayList<TipoEvento>();
	private List<TipoEvento> listaTiposEventoExcluido = new ArrayList<TipoEvento>();

	private List<Despesa> listaDespesas = new ArrayList<Despesa>();
	private List<Despesa> listaDespesasExcluida = new ArrayList<Despesa>();
	
	private boolean listaEventoVazia = false;
	
	private boolean listaDespesaVazia = false;


	@PostConstruct
	public void iniciar() {
		listaTiposEvento = controladorEvento.retornaTiposEvento();
		listaDespesas = controladorDespesa.retornaDespesas();
		
		if(listaTiposEvento.isEmpty()){
			listaEventoVazia = true;
		}
		
		if(listaDespesas.isEmpty()){
			listaDespesaVazia = true;
		}

	}

	public void adicionaTipoEvento() {
		if(!(tipoEventoSelecionado.getDescricao().length() < 1)) {
		listaTiposEvento.add(tipoEventoSelecionado);
		}else {
			controladorMensagens.addMsgAlerta("tipo.nulo");
		}
		tipoEventoSelecionado = new TipoEvento();
	}

	public void adicionaDespesa() {
		if(!(tipoDespesaSelecionada.getDescricao().length() < 1)) {
		listaDespesas.add(tipoDespesaSelecionada);
		}else {
			controladorMensagens.addMsgAlerta("tipo.nulo");
		}
		tipoDespesaSelecionada = new Despesa();
	}

	public String salvar() {

		try {
			
			verificaIteracao();
			
			
			controladorEvento.atualizaListaTiposEvento(listaTiposEvento, listaTiposEventoExcluido);
			controladorDespesa.atualizaListaDespesas(listaDespesas, listaDespesasExcluida);
			controladorMensagens.addMsgInfo("tipo.registro.atualizado");
			return "registraTipos.xhtml";
			
		} catch (Exception e) {
			controladorMensagens.addMsgFatal("erro.banco");
			return null;
		}
			
	}
	
	public void verificaIteracao() {
		if(listaDespesaVazia){
			listaDespesasExcluida = new ArrayList<Despesa>();
		}
		
		if(listaEventoVazia){
			listaTiposEventoExcluido = new ArrayList<TipoEvento>();
		}

	}

	public void excluiDespesa(Despesa despesa) {

		listaDespesas.remove(despesa);
		listaDespesasExcluida.add(despesa);

	}

	public void excluiTipoEvento(TipoEvento tipoEvento) {

		listaTiposEvento.remove(tipoEvento);
		listaTiposEventoExcluido.add(tipoEvento);

	}

	public TipoEvento getTipoEventoSelecionado() {
		return tipoEventoSelecionado;
	}

	public void setTipoEventoSelecionado(TipoEvento tipoEventoSelecionado) {
		this.tipoEventoSelecionado = tipoEventoSelecionado;
	}

	public List<TipoEvento> getListaTiposEvento() {
		return listaTiposEvento;
	}

	public void setListaTiposEvento(List<TipoEvento> listaTiposEvento) {
		this.listaTiposEvento = listaTiposEvento;
	}

	public Despesa getTipoDespesaSelecionada() {
		return tipoDespesaSelecionada;
	}

	public void setTipoDespesaSelecionada(Despesa tipoDespesaSelecionada) {
		this.tipoDespesaSelecionada = tipoDespesaSelecionada;
	}

	public List<Despesa> getListaDespesas() {
		return listaDespesas;
	}

	public void setListaDespesas(List<Despesa> listaDespesas) {
		this.listaDespesas = listaDespesas;
	}

}
