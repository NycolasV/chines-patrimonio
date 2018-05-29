package br.info.pweb.chines.interceptors;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import br.info.pweb.chines.models.Usuario;
import br.info.pweb.chines.utils.SessionUtils;

public class Interceptor extends HandlerInterceptorAdapter{

	@Autowired
	private SessionUtils session;
	
	@Autowired
	private ServletContext servletContext;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, 
			Object handler) throws Exception {
		
		boolean autenticacao = request.getRequestURI().contains("/app");
		boolean autenticacaoAdmin = request.getRequestURI().contains("/adm");
		
		Usuario usuarioLogado = (Usuario) session.getLogin();
		boolean usuarioNaSessao = usuarioLogado != null;
		
		if (autenticacao && !usuarioNaSessao) {
			response.sendRedirect(servletContext.getContextPath());
			return false;
		} else if (autenticacaoAdmin && !usuarioLogado.getAdministrador()) {
			response.sendRedirect(servletContext.getContextPath() + "/app/");
			return false;
		} else {
			return true;
		}
	}
	
}
