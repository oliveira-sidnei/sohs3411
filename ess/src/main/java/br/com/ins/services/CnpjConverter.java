package br.com.ins.services;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "cnpjConverter")
public class CnpjConverter implements Converter {

	
	public Object getAsObject(FacesContext context, UIComponent component, String value)throws ConverterException {

		/*
		 * método que converte cnpj formatado para valor sem pontos 
		 * Ex.: 01.125.456/0001-00 para 01125456000100
		 */
		String cnpj = value;
		if (!value.equals("") && value != null)

			cnpj = value.replaceAll("\\.", "").replaceAll("\\-", "").replaceAll("/", "");
			
			return cnpj;
	
	

	}

	public String getAsString(FacesContext context, UIComponent component, Object value) throws ConverterException{
		
		/*
		 * método que converte cnpj sem pontos para valor formatado
		 * Ex.: 01125456000100 para 01.125.456/0001-00
		 * */
		String cnpj = (String)value;
		if(cnpj != null && cnpj.length() == 14)
			cnpj = cnpj.substring(0, 2) + "."+ cnpj.substring(2, 5)+"."+cnpj.substring(5, 8)+"/"+cnpj.substring(8, 12)+"-"+cnpj.substring(12, 14);
		return cnpj;
	}

}
