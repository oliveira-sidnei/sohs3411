package br.com.ins.bean;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import br.com.ins.control.ControladorFuncionario;
import br.com.ins.control.ControladorMensagens;
import br.com.ins.control.ControladorStatus;
import br.com.ins.control.ControladorUsuario;
import br.com.ins.core.CEP;
import br.com.ins.core.Funcionario;
import br.com.ins.core.Lojista;
import br.com.ins.core.Perfil;
import br.com.ins.core.Usuario;
import br.com.ins.services.Validador;

@ViewScoped
@ManagedBean
public class CadastraFuncionarioBean implements Serializable {

	private Funcionario funcionarioSelecionado;
	private Usuario usuarioSelecionado;
	private List<Perfil> listaPerfis = new ArrayList<Perfil>();
	private List<Lojista> lojistas = new ArrayList<Lojista>();
	private List<Funcionario> funcionarios = new ArrayList<Funcionario>();
	private List<String> cpfsConsulta = new ArrayList<String>();

	private CEP cepSelecionado;

	private Date dataMinima;
	private Date dataMaxima;

	private static final long serialVersionUID = 1L;

	@EJB
	ControladorFuncionario controladorFuncionario;

	@EJB
	ControladorUsuario controladorUsuario;

	@EJB
	ControladorMensagens controladorMensagens;

	@EJB
	ControladorStatus controladorStatus;

	@PostConstruct
	public void iniciar() {
		setDataMinMax();

		funcionarioSelecionado = new Funcionario();
		usuarioSelecionado = new Usuario();
		preencheCpfs();
		funcionarioSelecionado.setStatus(controladorStatus.inicializaAtivo());
		listaPerfis = controladorFuncionario.retornaPerfis();
	}

	private void preencheCpfs() {
		for (Lojista lojista : lojistas) {
			cpfsConsulta.add(lojista.getCpf());
		}

		for (Funcionario funcionario : funcionarios) {
			cpfsConsulta.add(funcionario.getCpf());
		}

	}

	private void setDataMinMax() {

		Calendar dataAuxiliar = Calendar.getInstance();
		dataAuxiliar.add(Calendar.YEAR, -65);
		dataMinima = dataAuxiliar.getTime();

		dataAuxiliar = Calendar.getInstance();
		dataAuxiliar.add(Calendar.YEAR, -18);
		dataMaxima = dataAuxiliar.getTime();

	}

	public void buscaCep() throws IOException, JAXBException {
		URL url = new URL("http://viacep.com.br/ws/" + funcionarioSelecionado.getCep().replace("-", "") + "/xml");
		URLConnection urlConnection = url.openConnection();
		InputStream is = urlConnection.getInputStream();

		JAXBContext jaxb = JAXBContext.newInstance(CEP.class);
		Unmarshaller unmarshaller = jaxb.createUnmarshaller();

		cepSelecionado = (CEP) unmarshaller.unmarshal(is);

		preencheCepFuncionario(cepSelecionado);

	}

	private void preencheCepFuncionario(CEP cep) {
		funcionarioSelecionado.setRua(cep.getLogradouro());
		funcionarioSelecionado.setCidade(cep.getLocalidade());
		funcionarioSelecionado.setEstado(cep.getUF());
		funcionarioSelecionado.setBairro(cep.getBairro());
		cepSelecionado = new CEP();

	}

	public void geraMatricula(String cpf) {
		funcionarioSelecionado.setMatricula(
				"F" + cpf.substring(0, 2) + new SimpleDateFormat("yyyy").format(new Date()) + cpf.substring(10, 11));

	}

	public String salva() {
		try {
			if (funcionarioSelecionado.getDataNascimento().after(dataMaxima)
					|| funcionarioSelecionado.getDataNascimento().before(dataMinima)) {
				controladorMensagens.addMsgAlerta("data.nascimento.errada");

			} else {
				if (usuarioSelecionado.getPerfil() == null) {
					controladorMensagens.addMsgErro("perfil.nao.selecionado");
				} else if (cpfsConsulta.contains(funcionarioSelecionado.getCpf())) {
					controladorMensagens.addMsgAlerta("cpf.cadastrado");

				} else {

					usuarioSelecionado.setNameUser(funcionarioSelecionado.getMatricula());
					usuarioSelecionado.setPassword(funcionarioSelecionado.getMatricula());
					controladorUsuario.insereUsuario(usuarioSelecionado);

					funcionarioSelecionado.setUsuario(usuarioSelecionado);
					controladorFuncionario.insereFuncionario(funcionarioSelecionado);

					controladorMensagens.addMsgInfo("funcionario.inserido");
					return "consultaFuncionarios.xhtml";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;

	}

	public void validaCPF() {
		String cpf = funcionarioSelecionado.getCpf().replace("-", "").replace(".", "");
		geraMatricula(cpf);
		if (!Validador.isCPF(cpf)) {
			System.out.println(cpf);
			System.out.println("SAINDO DO CAMPO CPF BLUR");
			controladorMensagens.addMsgErro("cpf.invalido");
		} else {
			controladorMensagens.addMsgInfo("cpf.valido");
		}
	}

	public String cancela() {
		return "consultaFuncionarios?faces-redirect=true";

	}

	public Funcionario getFuncionarioSelecionado() {
		return funcionarioSelecionado;
	}

	public void setFuncionarioSelecionado(Funcionario funcionarioSelecionado) {
		this.funcionarioSelecionado = funcionarioSelecionado;
	}

	public List<Perfil> getListaPerfis() {
		return listaPerfis;
	}

	public void setListaPerfis(List<Perfil> listaPerfis) {
		this.listaPerfis = listaPerfis;
	}

	public Usuario getUsuarioSelecionado() {
		return usuarioSelecionado;
	}

	public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
		this.usuarioSelecionado = usuarioSelecionado;
	}

	public Date getDataMinima() {
		return dataMinima;
	}

	public Date getDataMaxima() {
		return dataMaxima;
	}

	public CEP getCepSelecionado() {
		return cepSelecionado;
	}

}
