package br.com.ins.core;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "ESS_CLIENTE")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String cpf;
	private String nome;
	private String email;
	private String sexo;


	@ManyToOne
	@JoinColumn(name = "idStatus")
	private Status status;

	private Date dataNascimento;
	@ManyToOne
	@JoinColumn(name = "idUsuario")
	private Usuario usuario;

	@ManyToMany(cascade = CascadeType.MERGE)
	@JoinTable(name = "ess_cliente_tipoevento", joinColumns = {
			@JoinColumn(name = "idCliente") }, inverseJoinColumns = { @JoinColumn(name = "idTipoEvento") })
	private Set<TipoEvento> listaTiposEvento = new HashSet<TipoEvento>();

	public Cliente() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}


	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}


	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Set<TipoEvento> getListaTiposEvento() {
		return listaTiposEvento;
	}

	public void setListaTiposEvento(Set<TipoEvento> listaTiposEvento) {
		this.listaTiposEvento = listaTiposEvento;
	}

}
