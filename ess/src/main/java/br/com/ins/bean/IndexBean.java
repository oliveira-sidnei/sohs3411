package br.com.ins.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.ins.control.ControladorEmail;
import br.com.ins.control.ControladorEvento;
import br.com.ins.core.Cliente;
import br.com.ins.core.Evento;

@ViewScoped
@ManagedBean
public class IndexBean implements Serializable {

	@EJB
	ControladorEvento controladorEvento;
	@EJB 
	ControladorEmail controladorEmail;
	@EJB
	ControladorCliente controladorCliente;
	
	private static final long serialVersionUID = 1L;
	
	private List<Evento> listaEventosAtivos = new ArrayList<Evento>();
	
	private List<Cliente> listaClientesAtivos = new ArrayList<Cliente>();
	
	private Date dataExibicao = new Date();
	
	
	
	@PostConstruct
	public void iniciar() {
		listaClientesAtivos = controladorCliente.retornaClientesAtivos();
		listaEventosAtivos = controladorEvento.consultaTodosEventosAtivos();

	}
	
		public void enviarNewsFeed() {
//			controladorEmail.enviaEmail(listaClientesAtivos, listaEventosAtivos);
		}



	public List<Evento> getListaEventosAtivos() {
		return listaEventosAtivos;
	}

	public void setListaEventosAtivos(List<Evento> listaEventosAtivos) {
		this.listaEventosAtivos = listaEventosAtivos;
	}

	public Date getDataExibicao() {
		return dataExibicao;
	}
	
	

}
