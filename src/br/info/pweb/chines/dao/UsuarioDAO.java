package br.info.pweb.chines.dao;

import br.info.pweb.chines.models.Usuario;

public interface UsuarioDAO extends DAO<Usuario>{
	
	public Usuario buscarEmail(String email);
	
	public Usuario buscarEmailSenha(String email, String senha);
}
