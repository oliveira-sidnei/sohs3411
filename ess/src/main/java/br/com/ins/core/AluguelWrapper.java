package br.com.ins.core;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.Gson;

@XmlRootElement(name = "alugueis")
@XmlAccessorType (XmlAccessType.FIELD)
public class AluguelWrapper {
	
	public AluguelWrapper() {
		
	}

	@XmlElement(name = "aluguel")
	private List<Aluguel> listaAlugueis = new ArrayList<Aluguel>();

	public List<Aluguel> getListaAlugueis() {
		return listaAlugueis;
	}

	public void setListaAlugueis(List<Aluguel> listaAlugueis) {
		this.listaAlugueis = listaAlugueis;
	}
	
	public String toJson() {
		return new Gson().toJson(this);
	}
}
