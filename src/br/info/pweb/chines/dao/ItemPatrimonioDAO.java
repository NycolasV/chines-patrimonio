package br.info.pweb.chines.dao;

import java.util.List;

import br.info.pweb.chines.models.ItemPatrimonio;

public interface ItemPatrimonioDAO extends DAO<ItemPatrimonio> {
	
	public List<ItemPatrimonio> buscarPatrimonio(Long id); 
		
}
