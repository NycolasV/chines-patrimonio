package br.info.pweb.chines.utils;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.info.pweb.chines.models.Usuario;

@Component
public class SessionUtils {

	// Insere a chave do usu�rio autenticado
	public static final String USUARIO_CHAVE = "usuarioLogado";
	
	// Injeta o HttpSession no SessionUtils
	@Autowired
	HttpSession session;
	
	// Retorna o usu�rio para sess�o
	public Usuario getLogin() {
		return (Usuario) session.getAttribute(USUARIO_CHAVE);
	}
	
	// Guarda o usu�rio autenticado na sess�o
	public void setLogin(Usuario usuarioLogado) {
		session.setAttribute(USUARIO_CHAVE, usuarioLogado);
	}
	
	// Confirma se a sess�o � nula
	public boolean isLogin() {
		return session.getAttribute(USUARIO_CHAVE) != null;
	}

	// Deleta o arquivo da sess�o
	public void closeSession() {
		session.invalidate();
	}
}
