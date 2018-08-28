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

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "ESS_ALUGUEL")
public class Aluguel implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Date dataVencimento;

	@ManyToOne
	@JoinColumn(name = "idLoja")
	private Loja loja;

	@OneToMany(mappedBy = "aluguel", targetEntity = ItemAluguel.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
	private List<ItemAluguel> listaItensAluguel = new ArrayList<ItemAluguel>();

	@ManyToOne
	@JoinColumn(name = "idStatus")
	private Status status;
	
	private byte[] documento;
	
	private Date dataPagamento;

	public Aluguel() {
		
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

	public Loja getLoja() {
		return loja;
	}

	public void setLoja(Loja loja) {
		this.loja = loja;
	}

	public List<ItemAluguel> getListaItensAluguel() {
		return listaItensAluguel;
	}

	public void setListaItensAluguel(List<ItemAluguel> listaItensAluguel) {
		this.listaItensAluguel = listaItensAluguel;
	}

	public BigDecimal getValorTotal() {
		BigDecimal valorTotal = new BigDecimal(0).setScale(4, RoundingMode.HALF_EVEN);
		for (ItemAluguel itemAluguel : listaItensAluguel) {
			valorTotal = valorTotal.add(itemAluguel.getValor());
		}

		return valorTotal;
	}

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

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	

}
