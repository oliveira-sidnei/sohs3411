package br.com.ins.bean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.ins.control.ControladorLojista;
import br.com.ins.control.ControladorMensagens;
import br.com.ins.control.ControladorPerfil;
import br.com.ins.control.ControladorUsuario;
import br.com.ins.core.Funcionario;
import br.com.ins.core.Lojista;
import br.com.ins.core.Usuario;
import br.com.ins.services.Validador;

@ViewScoped
@ManagedBean
public class CadastraLojistaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Lojista lojistaSelecionado;

	private Usuario usuarioSelecionado;

	private List<Lojista> lojistas = new ArrayList<Lojista>();
	private List<Funcionario> funcionarios = new ArrayList<Funcionario>();
	private List<String> cpfsConsulta = new ArrayList<String>();

	@EJB
	ControladorLojista controladorLojista;

	@EJB
	ControladorUsuario controladorUsuario;

	@EJB
	ControladorPerfil controladorPerfil;

	@EJB
	ControladorMensagens controladorMensagem;

	@PostConstruct
	public void iniciar() {
		lojistaSelecionado = new Lojista();
		usuarioSelecionado = new Usuario();
		lojistas = controladorLojista.retornaTodosLojistas();

		preencheCpfs();

		usuarioSelecionado.setPerfil(controladorPerfil.buscaPerfilLojista());

	}

	private void preencheCpfs() {
		for (Lojista lojista : lojistas) {
			cpfsConsulta.add(lojista.getCpf());
		}

		for (Funcionario funcionario : funcionarios) {
			cpfsConsulta.add(funcionario.getCpf());
		}

	}

	public void geraMatricula(String cpf) {
		lojistaSelecionado.setMatricula(
				"L" + cpf.substring(0, 2) + new SimpleDateFormat("yyyy").format(new Date()) + cpf.substring(10, 11));

	}

	public void validaCPF() {
		String cpf = lojistaSelecionado.getCpf().replace("-", "").replace(".", "");
		geraMatricula(cpf);
		if (!Validador.isCPF(cpf)) {
			System.out.println(cpf);
			System.out.println("SAINDO DO CAMPO CPF BLUR");
			controladorMensagem.addMsgErro("cpf.invalido");
		} else {
			controladorMensagem.addMsgInfo("cpf.valido");
		}
	}

	public String salva() {
		if (cpfsConsulta.contains(lojistaSelecionado.getCpf())) {
			controladorMensagem.addMsgAlerta("cpf.cadastrado");
			return null;
		} else {
			usuarioSelecionado.setNameUser(lojistaSelecionado.getMatricula());
			usuarioSelecionado.setPassword(lojistaSelecionado.getMatricula());

			controladorUsuario.insereUsuario(usuarioSelecionado);
			lojistaSelecionado.setUsuario(usuarioSelecionado);

			controladorLojista.insereLojista(lojistaSelecionado);

			controladorMensagem.addMsgInfo("lojista.inserido");
			return "consultaLojistas.xhtml";
		}
	}

	public String cancela() {
		return "consultaLojistas?faces-redirect=true";
	}

	public Lojista getLojistaSelecionado() {
		return lojistaSelecionado;
	}

	public void setLojistaSelecionado(Lojista lojistaSelecionado) {
		this.lojistaSelecionado = lojistaSelecionado;
	}

}
