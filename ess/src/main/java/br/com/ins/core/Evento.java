package br.com.ins.core;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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
@Table(name = "ESS_EVENTO")
public class Evento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	 @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String nome;

	@ManyToMany(cascade = CascadeType.MERGE)
	@JoinTable(
			name = "ess_evento_tipoevento", 
			joinColumns = { @JoinColumn(name = "idEvento") }, 
			inverseJoinColumns = { @JoinColumn(name = "idTipoEvento") })
	private Set<TipoEvento> listaTipoEvento = new HashSet<TipoEvento>();

	@ManyToOne
	@JoinColumn(name = "idStatus")
	private Status status;
	
	@Column(name = "valor")
	private BigDecimal valorIngresso;
	private String local;
	private Date dataInicio;
	private Date dataFim;
	private Date horario;
	private String descricao;

	@ManyToOne
	@JoinColumn(name = "idFuncionario")
	private Funcionario funcionario;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinTable(
			name = "ess_evento_loja",
			joinColumns = { @JoinColumn(name = "idEvento") }, 
			inverseJoinColumns = { @JoinColumn(name = "idLoja") })	
	private List<Loja> listaLojas = new ArrayList<Loja>();

	public Evento() {
		
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


	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public BigDecimal getValorIngresso() {
		return valorIngresso;
	}

	public void setValorIngresso(BigDecimal valorIngresso) {
		this.valorIngresso = valorIngresso;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
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

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Set<TipoEvento> getListaTipoEvento() {
		return listaTipoEvento;
	}

	public void setListaTipoEvento(Set<TipoEvento> listaTipoEvento) {
		this.listaTipoEvento = listaTipoEvento;
	}

	public List<Loja> getListaLojas() {
		return listaLojas;
	}

	public void setListaLojas(List<Loja> listaLojas) {
		this.listaLojas = listaLojas;
	}

	public Date getHorario() {
		return horario;
	}

	public void setHorario(Date horario) {
		this.horario = horario;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	

}
