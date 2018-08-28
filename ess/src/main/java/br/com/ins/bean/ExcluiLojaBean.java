package br.com.ins.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.ins.control.ControladorEvento;
import br.com.ins.control.ControladorLoja;
import br.com.ins.control.ControladorMensagens;
import br.com.ins.core.Loja;
import br.com.ins.core.TipoEvento;

@ViewScoped
@ManagedBean
public class ExcluiLojaBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	ControladorLoja controladorLoja;

	@EJB
	ControladorEvento controladorEvento;

	@EJB
	ControladorMensagens controladorMensagens;

	private Loja lojaSelecionada;

	private List<TipoEvento> listaTipoEvento = new ArrayList<TipoEvento>();

	@PostConstruct
	public void iniciar() {
		String idLojaString = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
				.get("LojaId");
		lojaSelecionada = controladorLoja.buscaLoja(Integer.parseInt(idLojaString));

		listaTipoEvento = controladorEvento.retornaTiposEvento();

	}

	public String exclui() {

		try {
			controladorLoja.excluiLoja(lojaSelecionada);
			controladorMensagens.addMsgInfo("loja.excluida");
			return "consultaLojas.xhtml";

		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}
	}

	public String cancela() {

		return "consultaLojas?faces-redirect=true";

	}

	public Loja getLojaSelecionada() {
		return lojaSelecionada;
	}

	public void setLojaSelecionada(Loja lojaSelecionada) {
		this.lojaSelecionada = lojaSelecionada;
	}

	public List<TipoEvento> getListaTipoEvento() {
		return listaTipoEvento;
	}

	public void setListaTipoEvento(List<TipoEvento> listaTipoEvento) {
		this.listaTipoEvento = listaTipoEvento;
	}

}
