package br.com.ins.core;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "ESS_TIPOEVENTO")
public class TipoEvento implements Serializable {

	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String descricao;
	private Integer ocorrencia;


	public TipoEvento() {

	}

	
	public TipoEvento(String tipoEventoString) {
		this.descricao = tipoEventoString;
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


	public Integer getOcorrencia() {
		return ocorrencia;
	}


	public void setOcorrencia(Integer ocorrencia) {
		this.ocorrencia = ocorrencia;
	}
	

	
}
