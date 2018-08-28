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
import br.com.ins.control.ControladorMensagens;
import br.com.ins.control.ControladorPerfil;
import br.com.ins.control.ControladorStatus;
import br.com.ins.control.ControladorUsuario;
import br.com.ins.core.Cliente;
import br.com.ins.core.TipoEvento;
import br.com.ins.core.Usuario;

@ViewScoped
@ManagedBean
public class CadastraClienteBean implements Serializable {

	@EJB
	ControladorEvento controladorEvento;
	@EJB
	ControladorStatus controladorStatus;
	@EJB
	ControladorCliente controladorCliente;
	@EJB
	ControladorUsuario controladorUsuario;
	@EJB
	ControladorPerfil controladorPerfil;
	@EJB
	ControladorMensagens controladorMensagens;

	private static final long serialVersionUID = 1L;

	private Cliente clienteSelecionado = new Cliente();
	private Usuario usuario = new Usuario();
	private List<TipoEvento> listaTiposEventoSelecionado = new ArrayList<TipoEvento>();
	private List<TipoEvento> listaTiposEvento = new ArrayList<TipoEvento>();
	private TipoEvento tipoEventoSelecionado;

	@PostConstruct
	public void iniciar() {

		listaTiposEvento = controladorEvento.retornaTiposEvento();
		clienteSelecionado.setStatus(controladorStatus.inicializaAtivo());

	}

	public void buscaCliente() {
		String idClienteString = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
				.get("ClienteId");

		if (idClienteString != null) {
			clienteSelecionado = controladorCliente.buscaClientePorEmail(idClienteString);
			listaTiposEventoSelecionado.addAll(clienteSelecionado.getListaTiposEvento());
		}

	}

	public String salva() {
		try {
			usuario.setPerfil(controladorPerfil.buscaPerfilCliente());
			usuario.setNameUser(clienteSelecionado.getEmail());
			usuario.setPassword(clienteSelecionado.getCpf().replace("-", "").replace(".", ""));
			controladorUsuario.insereUsuario(usuario);
			clienteSelecionado.setUsuario(usuario);
			clienteSelecionado.getListaTiposEvento().addAll(listaTiposEventoSelecionado);
			controladorCliente.insereCliente(clienteSelecionado);
			controladorMensagens.addMsgInfo("cliente.inserido");
		} catch (Exception e) {
			controladorMensagens.addMsgFatal("erro.cadastro");
			e.printStackTrace();
		}

		return "index.xhtml";

	}

	public void adicionarTipoEvento() {
		if (tipoEventoSelecionado == null) {
			controladorMensagens.addMsgAlerta("nenhum.tipo.selecionado");

		} else {

			boolean contem = false;
			for (TipoEvento tipoEvento : listaTiposEventoSelecionado) {
				if (tipoEventoSelecionado.getDescricao().equals(tipoEvento.getDescricao())) {
					contem = true;
					controladorMensagens.addMsgAlerta("erro.tipo.selecionado");
					break;
				}
			}
			if (!contem) {
				listaTiposEventoSelecionado.add(tipoEventoSelecionado);
			}
		}
		tipoEventoSelecionado = new TipoEvento();

	}

	public void excluiTipoEvento(TipoEvento tipoEvento) {
		listaTiposEventoSelecionado.remove(tipoEvento);
	}

	public Cliente getClienteSelecionado() {
		return clienteSelecionado;
	}

	public void setClienteSelecionado(Cliente clienteSelecionado) {
		this.clienteSelecionado = clienteSelecionado;
	}

	public List<TipoEvento> getListaTiposEventoSelecionado() {
		return listaTiposEventoSelecionado;
	}

	public void setListaTiposEventoSelecionado(List<TipoEvento> listaTiposEventoSelecionado) {
		this.listaTiposEventoSelecionado = listaTiposEventoSelecionado;
	}

	public List<TipoEvento> getListaTiposEvento() {
		return listaTiposEvento;
	}

	public void setListaTiposEvento(List<TipoEvento> listaTiposEvento) {
		this.listaTiposEvento = listaTiposEvento;
	}

	public TipoEvento getTipoEventoSelecionado() {
		return tipoEventoSelecionado;
	}

	public void setTipoEventoSelecionado(TipoEvento tipoEventoSelecionado) {
		this.tipoEventoSelecionado = tipoEventoSelecionado;
	}

}
