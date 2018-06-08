package br.info.pweb.chines.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.info.pweb.chines.dao.CategoriaDAO;
import br.info.pweb.chines.exceptions.MyEntityNotFoundException;
import br.info.pweb.chines.models.Categoria;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaDAO categoriaDAO; 
	
	public List<Categoria> listar() {
		return categoriaDAO.listar();
	}
	
	public Categoria buscar(Long id) throws MyEntityNotFoundException {
		Categoria categoriaBuscada = categoriaDAO.buscar(id);
		
		if (categoriaBuscada == null) 
		{
			throw new MyEntityNotFoundException();
		}
		
		return categoriaBuscada;
	}
	
}
