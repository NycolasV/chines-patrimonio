package br.info.pweb.chines.dao;

import java.util.List;

import br.info.pweb.chines.models.ItemPatrimonio;
import br.info.pweb.chines.models.Patrimonio;

public interface ItemPatrimonioDAO extends DAO<ItemPatrimonio> {
	
	public List<ItemPatrimonio> buscarPatrimonio(Patrimonio patrimonio); 
		
}
