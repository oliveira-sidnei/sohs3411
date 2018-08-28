package br.com.ins.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ESS_LOJA")
public class Loja implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private String cnpj;
	private String telefone;
	private String email;
	@ManyToOne
	@JoinColumn(name = "idLojista")
	private Lojista lojista;
	private Date dataInicio;
	private Date dataFim;
	
	@ManyToOne
	@JoinColumn(name = "idStatus")
	private Status status;
	
	private Integer numero;
	
	
	@ManyToMany(cascade = CascadeType.MERGE)
	@JoinTable(
	        name = "ess_loja_tipoevento", 
	        joinColumns = { @JoinColumn(name = "idLoja") }, 
	        inverseJoinColumns = { @JoinColumn(name = "idTipoEvento") }
	    )
	  private Set<TipoEvento>listaTiposEvento = new HashSet<TipoEvento>();

	@ManyToMany(mappedBy = "listaLojas", fetch = FetchType.EAGER,  cascade = CascadeType.MERGE)
	private List<Evento> listaEventos = new ArrayList<Evento>();




	public Loja(){
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Lojista getLojista() {
		return lojista;
	}

	public void setLojista(Lojista lojista) {
		this.lojista = lojista;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}


	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	@Override
	public String toString() {
		return "Loja [id=" + id + ", nome=" + nome + ", cnpj=" + cnpj + ", numero=" + numero + "]";
	}

	public Set<TipoEvento> getListaTiposEvento() {
		return listaTiposEvento;
	}

	public void setListaTiposEvento(Set<TipoEvento> listaTiposEvento) {
		this.listaTiposEvento = listaTiposEvento;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	

}
