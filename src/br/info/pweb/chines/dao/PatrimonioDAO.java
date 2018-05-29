package br.info.pweb.chines.dao;

import br.info.pweb.chines.models.Patrimonio;

public interface PatrimonioDAO extends DAO<Patrimonio>{

	public Patrimonio buscarNome(String nome);
		
}
