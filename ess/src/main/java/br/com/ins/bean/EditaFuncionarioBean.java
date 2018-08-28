package br.com.ins.bean;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import br.com.ins.control.ControladorFuncionario;
import br.com.ins.control.ControladorMensagens;
import br.com.ins.control.ControladorPerfil;
import br.com.ins.control.ControladorStatus;
import br.com.ins.core.CEP;
import br.com.ins.core.Funcionario;
import br.com.ins.core.Perfil;
import br.com.ins.core.Status;

@ViewScoped
@ManagedBean
public class EditaFuncionarioBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Funcionario funcionarioSelecionado;

	private List<Perfil> listaPerfis = new ArrayList<Perfil>();

	private List<Status> listaStatus = new ArrayList<Status>();

	private Date dataMinima;

	private Date dataMaxima;

	private CEP cepSelecionado;

	@EJB
	ControladorFuncionario controladorFuncionario;
	@EJB
	ControladorMensagens controladorMensagens;
	@EJB
	ControladorPerfil controladorPerfil;
	@EJB
	ControladorStatus controladorStatus;

	@PostConstruct
	public void iniciar() {

		String idFuncionarioString = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
				.get("FuncionarioId");

		listaPerfis = controladorFuncionario.retornaPerfis();

		listaStatus = controladorStatus.listaStatusFuncionario();

		setDataMinMax();

		funcionarioSelecionado = controladorFuncionario.retornaFuncionario(Integer.parseInt(idFuncionarioString));
		System.out.println(funcionarioSelecionado.getUsuario());

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

	public String salva() {
		try {
			if (funcionarioSelecionado.getDataNascimento().after(dataMaxima)
					|| funcionarioSelecionado.getDataNascimento().before(dataMinima)) {
				controladorMensagens.addMsgAlerta("data.nascimento.errada");
			} else {
				controladorFuncionario.editaFuncionario(funcionarioSelecionado);
				controladorMensagens.addMsgInfo("funcionario.editado");
				return "consultaFuncionarios.xhtml";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;

	}

	public void resetaSenha() {
		funcionarioSelecionado.getUsuario().setPassword(funcionarioSelecionado.getUsuario().getNameUser());
		controladorMensagens.addMsgAlerta("senha.resetada");
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

	public List<Status> getListaStatus() {
		return listaStatus;
	}

	public void setListaStatus(List<Status> listaStatus) {
		this.listaStatus = listaStatus;
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

	public void setCepSelecionado(CEP cepSelecionado) {
		this.cepSelecionado = cepSelecionado;
	}

}
