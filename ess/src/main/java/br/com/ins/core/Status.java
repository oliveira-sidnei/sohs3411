package br.com.ins.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ESS_STATUS")
public class Status implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer id;
	private String descricao;
	
	@OneToMany(mappedBy = "status", targetEntity = Loja.class, cascade = CascadeType.ALL)
	private List<Loja> lojas = new ArrayList<Loja>();
	
	@OneToMany(mappedBy = "status", targetEntity = Funcionario.class, cascade = CascadeType.ALL)
	private List<Funcionario> funcionarios = new ArrayList<Funcionario>();
	
	@OneToMany(mappedBy = "status", targetEntity = Aluguel.class, cascade = CascadeType.ALL)
	private List<Aluguel> alugueis = new ArrayList<Aluguel>();
	
	@OneToMany(mappedBy = "status", targetEntity = Evento.class, cascade = CascadeType.ALL)
	private List<Evento> eventos = new ArrayList<Evento>();
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
	
	

}
