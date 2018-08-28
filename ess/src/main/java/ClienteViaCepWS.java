import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import br.com.ins.core.CEP;

public class ClienteViaCepWS {

	public static void main(String[] args) throws IOException, JAXBException {
		buscaCep("21230500");
	}
	
	public static void buscaCep(String cep) throws IOException, JAXBException {
	    URL url = new URL("http://viacep.com.br/ws/"+ cep +"/xml");
	    URLConnection urlConnection = url.openConnection();
	    InputStream is = urlConnection.getInputStream();
	    
	    
	    
	    JAXBContext jaxb = JAXBContext.newInstance(CEP.class);
	    Unmarshaller unmarshaller = jaxb.createUnmarshaller();
	    
	    CEP objetoCep = (CEP)unmarshaller.unmarshal(is);
	    
	    System.out.println(objetoCep);

	}
}