package br.com.ins.services;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//@WebFilter(urlPatterns = "/login.xhtml")
public class LoginFilter implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException,
			ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		HttpSession ses = request.getSession(false);

			System.out.println(request.getServletPath());
			System.out.println(request.getRequestURL());
			System.out.println(request.getRequestURI());
			System.out.println(ses.getAttribute("username"));
			System.out.println(ses.getAttribute("username") != null);

			if (ses.getAttribute("username") != null) {
				System.out.println("AQUI 1"	);
				response.sendRedirect(request.getContextPath() + "/index"); // Redirect
																			// to
																			// home
																			// page.
			} else {
				System.out.println("AQUI 2");
				chain.doFilter(req, res); // User is not logged-in, so just
											// continue request.
			}
	
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	// Add/generate init() and destroy() with NOOP.
}