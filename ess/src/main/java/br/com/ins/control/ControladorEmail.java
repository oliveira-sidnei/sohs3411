package br.com.ins.control;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import br.com.ins.bean.ControladorCliente;
import br.com.ins.core.Cliente;
import br.com.ins.core.Evento;
import br.com.ins.core.Lojista;

@Stateless
public class ControladorEmail implements Serializable {
	
	@EJB
	ControladorCliente controladorCliente;
	@EJB
	ControladorEvento controladorEvento;

	private static final long serialVersionUID = 1L;

	public void enviaEventosParaClientes() {

		List<Cliente> listaClientesAtivos = controladorCliente.retornaClientesAtivos();
		List<Evento> listaEventos = new ArrayList<Evento>();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		DecimalFormatSymbols dFS = ((DecimalFormat) nf).getDecimalFormatSymbols();
		dFS.setCurrencySymbol("");
		((DecimalFormat) nf).setDecimalFormatSymbols(dFS);

		Properties props = new Properties();
		/** Parâmetros de conexão com servidor Gmail */
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("easyshoppf@gmail.com", "easyshop2018");
			}
		});

		/** Ativa Debug para sessão */
		session.setDebug(true);

		try {
			for (Cliente cliente : listaClientesAtivos) {
				listaEventos = controladorEvento.retornaEventosPorCliente(cliente);
				System.out.println(cliente.getEmail() + "-------- " +listaEventos.size());
				if(listaEventos.isEmpty()){
					continue;
				}
				
				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress("easyshoppf@gmail.com")); // Remetente

				Address[] toUser = InternetAddress // Destinatário(s)
						.parse(cliente.getEmail());

				message.setRecipients(Message.RecipientType.TO, toUser);
				message.setSubject("Feed de Eventos do Shopping");// Assunto

				StringBuilder sb = new StringBuilder();

		
				for (Evento evento : listaEventos) {
					String mensagem = 

					"Nome do Evento: " + evento.getNome() + "\nLocal do evento: " + evento.getLocal()
							+ "\nValor do Evento: " + "R$" + nf.format(evento.getValorIngresso()) + "\nData de Inicio do Evento: "
							+ sdf.format(evento.getDataInicio()) + "\nData de fim do Evento: "
							+ sdf.format(evento.getDataFim()) + "\n";

					sb.append(mensagem).append(System.lineSeparator());

				}
				
				message.setText(sb.toString());

				/** Método para enviar a mensagem criada */
				Transport.send(message);

				System.out.println("Feito!!!");
			}
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	public void enviaEmailLojistas(List<Lojista> listaLojistas, Evento evento) {



		Properties props = new Properties();
		/** Parâmetros de conexão com servidor Gmail */
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("easyshoppf@gmail.com", "easyshop2018");
			}
		});

		/** Ativa Debug para sessão */
		session.setDebug(true);

		try {
			for (Lojista lojista : listaLojistas) {
				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress("easyshoppf@gmail.com")); // Remetente

				Address[] toUser = InternetAddress // Destinatário(s)
						.parse(lojista.getEmail());

				message.setRecipients(Message.RecipientType.TO, toUser);
				message.setSubject("Notificação de solitação de evento");// Assunto


				message.setText("Prezado lojista " + lojista.getNome() + " o status do seu evento " + evento.getNome() + " foi alterado para " + evento.getStatus().getDescricao());

				/** Método para enviar a mensagem criada */
				Transport.send(message);

				System.out.println("Feito!!!");
			}
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		
	}
}
