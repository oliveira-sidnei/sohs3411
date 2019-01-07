package br.com.ins.core;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
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
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "ESS_ALUGUEL")
public class Aluguel implements Serializable {

	private static final long serialVersionUID = 1L;

	@XmlElement(name = "Id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@XmlElement(name = "dataVencimento")
	private Date dataVencimento;

	@XmlTransient
    @JsonBackReference
	@ManyToOne
	@JoinColumn(name = "idLoja")
	private Loja loja = new Loja();
	
	@Transient
	@XmlElement(name = "loja")
	private String nomeLoja;

	@XmlTransient
	@OneToMany(mappedBy = "aluguel", targetEntity = ItemAluguel.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
    @JsonManagedReference
	private List<ItemAluguel> listaItensAluguel = new ArrayList<ItemAluguel>();

	@XmlElement(name = "status")
    @JsonBackReference
	@ManyToOne
	@JoinColumn(name = "idStatus")
	private Status status;
	
	@JsonIgnore
	@XmlTransient
	private byte[] documento;
	
	@XmlElement(name = "dataPagamento")	
	private Date dataPagamento;


	public Aluguel() {
		this.nomeLoja = this.loja.getNome();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	@JsonIgnore
	public Loja getLoja() {
		return loja;
	}

	public void setLoja(Loja loja) {
		this.loja = loja;
	}

	@JsonIgnore
	public List<ItemAluguel> getListaItensAluguel() {
		return listaItensAluguel;
	}

	public void setListaItensAluguel(List<ItemAluguel> listaItensAluguel) {
		this.listaItensAluguel = listaItensAluguel;
	}

	@XmlElement(name = "valorTotal")
	public BigDecimal getValorTotal() {
		BigDecimal valorTotal = new BigDecimal(0).setScale(4, RoundingMode.HALF_EVEN);
		for (ItemAluguel itemAluguel : listaItensAluguel) {
			valorTotal = valorTotal.add(itemAluguel.getValor());
		}

		return valorTotal;
	}

	@JsonIgnore
	public byte[] getDocumento() {
		return documento;
	}

	public void setDocumento(byte[] documento) {
		this.documento = documento;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	@JsonIgnore
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Aluguel [id=" + id + ", dataVencimento=" + dataVencimento + ", status=" + status + ", dataPagamento="
				+ dataPagamento + "]";
	}
	
	
	

}
