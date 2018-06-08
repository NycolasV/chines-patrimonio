package br.info.pweb.chines.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.info.pweb.chines.dao.PatrimonioDAO;
import br.info.pweb.chines.exceptions.MyEntityNotFoundException;
import br.info.pweb.chines.models.Patrimonio;

@Service
public class PatrimonioService {

	@Autowired
	private PatrimonioDAO patrimonioDAO; 
	
	public List<Patrimonio> listar() {
		return patrimonioDAO.listar();
	}
	
	public Patrimonio buscar(Long id) throws MyEntityNotFoundException {
		Patrimonio patrimonioBuscado = patrimonioDAO.buscar(id);
		
		if (patrimonioBuscado == null) {
			throw new MyEntityNotFoundException();
		}
		
		return patrimonioBuscado;
	}
	
}
