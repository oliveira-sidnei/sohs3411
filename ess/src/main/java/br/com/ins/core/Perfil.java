package br.com.ins.core;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ESS_PERFIL")
public class Perfil {

	@Id
	private Integer id;
	private String descricao;
	
	@OneToMany(mappedBy = "perfil", targetEntity = Usuario.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Usuario> listaUsuarios = new ArrayList<Usuario>();	

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

	@Override
	public String toString() {
		return "Perfil [id=" + id + ", descricao=" + descricao + "]";
	}
	
	

}
