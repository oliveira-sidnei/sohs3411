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
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ESS_DESPESA")
public class Despesa implements Serializable{


	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String descricao;
	
	@OneToMany(mappedBy = "despesa", targetEntity = ItemAluguel.class, fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	private List<ItemAluguel> listaItensAluguel = new ArrayList<ItemAluguel>();
	
	
	public Despesa(){
	}

	public Despesa(String despesaString) {
			this.descricao = despesaString;
	}

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
