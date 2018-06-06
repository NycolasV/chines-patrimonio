package br.info.pweb.chines.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import br.info.pweb.chines.dao.UsuarioDAO;
import br.info.pweb.chines.exceptions.MyEntityNotFoundException;
import br.info.pweb.chines.exceptions.MyValidationException;
import br.info.pweb.chines.models.Usuario;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioDAO usuarioDAO;
	
	public Usuario buscarEmailSenha(Usuario usuario, BindingResult brUsuario) 
			throws MyValidationException, MyEntityNotFoundException {
		
		if (brUsuario.hasFieldErrors("email") || brUsuario.hasFieldErrors("senha")) {
			throw new MyValidationException();
		}
		
		usuario.HashearSenha();
		Usuario usuarioLogado = usuarioDAO.buscarEmailSenha(usuario.getEmail(), usuario.getSenha());
		
		if (usuarioLogado == null) {
			throw new MyEntityNotFoundException();
		}
		
		return usuarioLogado;
	}
	
}
