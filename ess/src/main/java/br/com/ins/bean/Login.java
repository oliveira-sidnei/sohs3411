package br.com.ins.bean;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.ins.control.ControladorMensagens;
import br.com.ins.control.ControladorUsuario;
import br.com.ins.core.Usuario;
import br.com.ins.services.SessionUtils;

@ManagedBean
@SessionScoped
public class Login implements Serializable {

	@EJB
	ControladorMensagens controladorMensagens;
	@EJB
	ControladorUsuario controladorUsuario;

	private static final long serialVersionUID = 1094801825228386363L;

	private String msg;

	private Usuario user = new Usuario();

	private boolean perfilCliente = false;

	private boolean perfilDiretor = false;

	public void verificaPerfil() {
		try {
			if (user.getPerfil().getId().equals(7)) {
				perfilCliente = true;
			} else if (user.getPerfil().getId().equals(2) || user.getPerfil().getId().equals(1)) {
				perfilDiretor = true;
			} else {
				perfilCliente = false;
				perfilDiretor = false;
			}
		} catch (Exception e) {
			perfilCliente = false;
			perfilDiretor = false;
		}

	}

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public boolean verificaAcesso() {
		try {
			System.out.println(user);

			if (user.getPerfil().getDescricao().equals("ADMIN")) {
				return true;
			} else {
				return false;
			}
		} catch (NullPointerException npe) {
			return false;
		}

	}

	// validate login
	public String validateUsernamePassword() {

		if (efetuaLogin()) {
			user = controladorUsuario.buscaUsuario(user.getNameUser());
			HttpSession session = SessionUtils.getSession();
			session.setAttribute("username", user);
			controladorMensagens.addMsgInfo("login.efetuado");
			verificaPerfil();
			if (perfilCliente) {
				return "index";
			} else {

				return "intranetIndex";
			}
		} else {

			controladorMensagens.addMsgErro("login.incorreto");
			user = new Usuario();
			return "login";
		}
	}

	private boolean efetuaLogin() {
		boolean loginResposta = controladorUsuario.validaLogin(user);
		return loginResposta;

	}

	// logout event, invalidate session
	public String logout() {
		HttpSession session = SessionUtils.getSession();
		user = new Usuario();
		session.invalidate();
		return "login?faces-redirect=true";
	}

	public boolean isPerfilCliente() {
		return perfilCliente;
	}

	public boolean isPerfilDiretor() {
		return perfilDiretor;
	}

}