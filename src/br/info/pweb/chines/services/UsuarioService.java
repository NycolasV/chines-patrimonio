package br.info.pweb.chines.services;

import java.util.List;

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
	
	public List<Usuario> listar() {
		return usuarioDAO.listar();
	}
	
	public Usuario buscar(Long id) throws MyEntityNotFoundException {
		Usuario usuarioBuscado = usuarioDAO.buscar(id);
		
		if (usuarioBuscado == null) {
			throw new MyEntityNotFoundException();
		}
		
		return usuarioBuscado;
	}
	
}
