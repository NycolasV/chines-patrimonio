package br.info.pweb.chines.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.info.pweb.chines.dao.ItemPatrimonioDAO;
import br.info.pweb.chines.exceptions.MyEntityNotFoundException;
import br.info.pweb.chines.models.ItemPatrimonio;

@Service
public class ItemPatrimonioService {

	@Autowired
	private ItemPatrimonioDAO itemPatrimonioDAO;
	
	public List<ItemPatrimonio> listar() {
		return itemPatrimonioDAO.listar();
	}
	
	public ItemPatrimonio buscar(Long id) 
			throws MyEntityNotFoundException {
		ItemPatrimonio itemBuscado = itemPatrimonioDAO.buscar(id);
		
		if (itemBuscado == null) {
			throw new MyEntityNotFoundException();
		}
		
		return itemBuscado;
	}
	
}
