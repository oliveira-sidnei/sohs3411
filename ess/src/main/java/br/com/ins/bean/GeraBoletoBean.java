package br.com.ins.bean;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.servlet.http.HttpSession;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.com.ins.control.ControladorAluguel;
import br.com.ins.control.ControladorEmail;
import br.com.ins.control.ControladorEvento;
import br.com.ins.control.ControladorLojista;
import br.com.ins.control.ControladorMensagens;
import br.com.ins.control.ControladorPerfil;
import br.com.ins.core.Aluguel;
import br.com.ins.core.Loja;
import br.com.ins.core.Lojista;
import br.com.ins.core.Usuario;
import br.com.ins.services.SessionUtils;

@ViewScoped
@ManagedBean
public class GeraBoletoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	ControladorAluguel controladorAluguel;
	@EJB
	ControladorMensagens controladorMensagens;
	@EJB
	ControladorLojista controladorLojista;
	@EJB
	ControladorEmail controladorEmail;
	@EJB
	ControladorEvento controladorEvento;
	@EJB
	ControladorPerfil controladorPerfil;

	private Integer idAluguelSelecionado = new Integer(0);

	private List<Aluguel> alugueis = new ArrayList<Aluguel>();

	private Aluguel aluguelSelecionado;

	private Lojista lojistaSelecionado;
	
	private Usuario usuarioSessao;

	HttpSession session = SessionUtils.getSession();
	private StreamedContent content;

	@PostConstruct
	public void iniciar() {

		 usuarioSessao = (Usuario) session.getAttribute("username");
		 
		 
		 
		lojistaSelecionado = controladorLojista.buscaLojistaPorUsuario(usuarioSessao);


	}

	public boolean verificaAcesso() {
		
		if (usuarioSessao.getPerfil().getDescricao().equals(controladorPerfil.buscaPerfilLojista().getDescricao())) {
			return true;
		} else {
			return false;
		}
	}

	public String retornaDataString(Date dataConsultada) {
		try {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		String dataString = sdf.format(dataConsultada);
		return dataString;
		}catch(Exception e) {
			return null;
		}
	}

//	public void downloadBoleto(Integer idAluguel) throws IOException {
//		Aluguel aluguelDownload = controladorAluguel.buscaAluguel(idAluguel);
//		ByteArrayInputStream inputStream = controladorAluguel.selecionaDocumento(aluguelDownload);
//		setContent(new DefaultStreamedContent(inputStream, "application/x-download", "boleto " + aluguelDownload.getLoja().getCnpj() + ".pdf"));
//		inputStream.close();
//
//	}

	public void detalhaHistoricoFinanceiro(Loja loja) {
		alugueis = controladorAluguel.buscaAluguelPorLoja(loja);
	}

	public void detalhaAluguel(Aluguel aluguel) {
		aluguelSelecionado = aluguel;
		System.out.println(aluguelSelecionado.getListaItensAluguel().size());

	}

	public void enviarEmail() {
		System.out.println("Enviando e-mail...");
		// controladorEmail.enviaEmail(controladorEvento.buscaEvento(1));

	}

	public void geraBoleto(Aluguel aluguelConsultado) {

		if (aluguelConsultado.getDocumento() == null) {
			content = controladorAluguel.gereBoleto(aluguelConsultado);
			controladorMensagens.addMsgInfo("boleto.gerado");

		} else {
			System.out.println("CAINDO AQUI");
			controladorMensagens.addMsgErro("boleto.existente");
		}

	}

	public String redireciona() {

		controladorMensagens.addMsgAlerta("boleto.existente");
		return "consultaFuncionarios.xhtml";

	}

	public Integer getIdAluguelSelecionado() {
		return idAluguelSelecionado;
	}

	public void setIdAluguelSelecionado(Integer idAluguelSelecionado) {
		this.idAluguelSelecionado = idAluguelSelecionado;
	}

	public Aluguel getAluguelSelecionado() {
		return aluguelSelecionado;
	}

	public void setAluguelSelecionado(Aluguel aluguelSelecionado) {
		this.aluguelSelecionado = aluguelSelecionado;
	}

	public StreamedContent getContent() {
		return content;
	}

	public void setContent(StreamedContent content) {
		this.content = content;
	}

	public List<Aluguel> getAlugueis() {
		return alugueis;
	}

	public void setAlugueis(List<Aluguel> alugueis) {
		this.alugueis = alugueis;
	}

	public Lojista getLojistaSelecionado() {
		return lojistaSelecionado;
	}

	public void setLojistaSelecionado(Lojista lojistaSelecionado) {
		this.lojistaSelecionado = lojistaSelecionado;
	}

	
	

}

