package br.com.ins.services;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.ins.core.Usuario;
import br.com.ins.dao.AcessosDAO;

public class AuthorizationFilter implements Filter {
	
	@EJB
	AcessosDAO daoAcesso;

	public AuthorizationFilter() {
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		try {

			HttpServletRequest reqt = (HttpServletRequest) request;
			HttpServletResponse resp = (HttpServletResponse) response;
			HttpSession ses = reqt.getSession(false);
			
			Usuario usuario = (Usuario) ses.getAttribute("username");

			if (usuario != null ) {
				System.out.println("PERFIL USADO:" + usuario.getPerfil() );
				System.out.println("PAGINA REQUISITADA: " + reqt.getServletPath());
				if(daoAcesso.verificaAcesso(usuario.getPerfil(), reqt.getServletPath())){
				chain.doFilter(request, response);
				}else{
					resp.sendError(401, "Você não é autorizado.");
				}
			} else {
				resp.sendRedirect(reqt.getContextPath() + "/login");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void destroy() {

	}
}