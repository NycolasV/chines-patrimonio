package br.info.pweb.chines.dao;

import br.info.pweb.chines.models.Ambiente;

public interface AmbienteDAO extends DAO<Ambiente> {
	
	public Ambiente buscarNome(String nome);
	
}
