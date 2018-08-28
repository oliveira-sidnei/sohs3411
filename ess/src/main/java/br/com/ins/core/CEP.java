package br.com.ins.core;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "xmlcep")
@XmlAccessorType(XmlAccessType.FIELD)
public class CEP {

	@XmlElement(name = "cep")
	private String cep;
	@XmlElement(name = "logradouro")
	private String logradouro;
	@XmlElement(name = "complemento")
	private String complemento;
	@XmlElement(name = "bairro")
	private String bairro;
	@XmlElement(name = "localidade")
	private String localidade;
	@XmlElement(name = "uf")
	private String UF;
	@XmlElement(name = "unidade")
	private String unidade;
	@XmlElement(name = "ibge")
	private String ibge;
	@XmlElement(name = "gia")
	private String gia;
	

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getUF() {
		return UF;
	}

	public void setUF(String uF) {
		UF = uF;
	}

	public String getUnidade() {
		return unidade;
	}

	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}

	public String getIbge() {
		return ibge;
	}

	public void setIbge(String ibge) {
		this.ibge = ibge;
	}

	public String getGia() {
		return gia;
	}

	public void setGia(String gia) {
		this.gia = gia;
	}

	@Override
	public String toString() {
		return "CEP [cep=" + cep + ", logradouro=" + logradouro + ", complemento=" + complemento + ", bairro=" + bairro
				+ ", localidade=" + localidade + ", UF=" + UF + ", unidade=" + unidade + ", ibge=" + ibge + ", gia="
				+ gia + "]";
	}
	
	

}
