package br.info.pweb.chines.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.info.pweb.chines.dao.AmbienteDAO;
import br.info.pweb.chines.exceptions.MyEntityNotFoundException;
import br.info.pweb.chines.models.Ambiente;

@Service
public class AmbienteService {

	@Autowired
	private AmbienteDAO ambienteDAO;
	
	public List<Ambiente> listar() {
		return ambienteDAO.listar();
	}
	
	public Ambiente buscar(Long id) throws MyEntityNotFoundException {
		Ambiente ambienteBuscado = ambienteDAO.buscar(id);
		
		if (ambienteBuscado == null) {
			throw new MyEntityNotFoundException();
		}
		
		return ambienteBuscado;
	}
	
}
