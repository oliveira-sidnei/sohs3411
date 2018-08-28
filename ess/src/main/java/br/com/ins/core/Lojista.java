package br.com.ins.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "ESS_LOJISTA")
public class Lojista implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private String cpf;
	private String email;
	private String telefone;
	private String celular;
	private String matricula;
	@ManyToOne
	@JoinColumn(name = "idUsuario")
	private Usuario usuario;

	@OneToMany(mappedBy = "lojista", targetEntity = Loja.class, fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	private List<Loja> lojas = new ArrayList<Loja>();

	public Integer getId() {
		return id;
	}

	public Lojista() {

	}

	public Lojista(int i) {
		this.id = 1;
		this.nome = "lojista 1";
		this.cpf = "111111";
		this.email = "email@dominio.com";
		this.telefone = "23546333";
	}

	public Lojista(String nome) {
		this.nome = "lojista 1";
		this.cpf = "111111";
		this.email = "email@dominio.com";
		this.telefone = "23546333";
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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public List<Loja> getLojas() {
		return lojas;
	}

	public void setLojas(List<Loja> lojas) {
		this.lojas = lojas;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

}
