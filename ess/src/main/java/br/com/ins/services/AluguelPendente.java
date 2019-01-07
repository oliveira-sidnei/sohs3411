package br.com.ins.services;

import java.io.StringWriter;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import br.com.ins.control.ControladorAluguel;
import br.com.ins.core.AluguelWrapper;

@Path("alugueisPendentes")
public class AluguelPendente {
	
	@EJB
	ControladorAluguel controladorAluguel;
	
	@GET
    @Produces(MediaType.APPLICATION_XML)
	public String listaAlugueisPendentes() throws JAXBException {
//		JAXBContext jcContext = JAXBContext.newInstance(AluguelWrapper.class);
//		Marshaller marshall = jcContext.createMarshaller();

//		marshall.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

		AluguelWrapper alugueis = new AluguelWrapper();
//		
		alugueis.setListaAlugueis( controladorAluguel.listaAlugueisPendentes());
//		StringWriter writer = new StringWriter();
//		
//			marshall.marshal(alugueis, writer);
//
//		
//		System.out.println(writer);
//		return writer.toString();
		
		return alugueis.toJson();
		
		

	}

}
