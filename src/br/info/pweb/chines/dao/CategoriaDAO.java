package br.info.pweb.chines.dao;

import br.info.pweb.chines.models.Categoria;

public interface CategoriaDAO extends DAO<Categoria> {

	public Categoria buscarNome(String nome);
	
}
