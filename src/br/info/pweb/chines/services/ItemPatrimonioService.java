package br.info.pweb.chines.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import br.info.pweb.chines.dao.ItemPatrimonioDAO;
import br.info.pweb.chines.exceptions.MyEntityNotFoundException;
import br.info.pweb.chines.exceptions.MyValidationException;
import br.info.pweb.chines.models.Ambiente;
import br.info.pweb.chines.models.ItemPatrimonio;
import br.info.pweb.chines.models.Patrimonio;
import br.info.pweb.chines.models.Usuario;

@Service
public class ItemPatrimonioService {

	@Autowired
	private ItemPatrimonioDAO itemPatrimonioDAO;
	
	@Autowired
	private PatrimonioService patrimonioService;
	
	@Autowired
	private AmbienteService ambienteService;
	
	public List<ItemPatrimonio> listar() {
		return itemPatrimonioDAO.listar();
	}
	
	public ItemPatrimonio buscar(Long id) 
			throws MyEntityNotFoundException {
		
		ItemPatrimonio itemBuscado = itemPatrimonioDAO.buscar(id);
		
		if (itemBuscado == null) 
		{
			throw new MyEntityNotFoundException();
		}
		
		return itemBuscado;
	}
	
	public List<ItemPatrimonio> buscarPatrimonio(Long id) 
			throws MyEntityNotFoundException {		
		
		ItemPatrimonio itemBuscado = buscar(id);
		
		return itemPatrimonioDAO.buscarPatrimonio(itemBuscado.getId());				
	}

	public ItemPatrimonio persistir(ItemPatrimonio itemPatrimonio, 
			BindingResult brItemPatrimonio)	throws MyValidationException, MyEntityNotFoundException {

		if (brItemPatrimonio.hasErrors()) 
		{			
			throw new MyValidationException();
		}	
		
		Ambiente ambiente = ambienteService.buscar(itemPatrimonio.getAmbienteAtual().getId());
		
		Patrimonio patrimonio = patrimonioService.buscar(itemPatrimonio.getPatrimonio().getId());
		
		itemPatrimonio.setDataAtualizacao(new Date());
		
		Usuario usuarioLogado = (Usuario) SecurityContextHolder.getContext().getAuthentication();
		itemPatrimonio.setUsuario(usuarioLogado);
		
		itemPatrimonioDAO.persistir(itemPatrimonio);
		
		return itemPatrimonio;
	}
	
//	public ItemPatrimonio alterar(Long id, ItemPatrimonio itemPatrimonio,
//			BindingResult brItemPatrimonio) throws MyValidationException, MyEntityNotFoundException  {
//		
//		if (brItemPatrimonio.hasErrors()) 
//		{			
//			throw new MyValidationException();
//		}
//			
//		ItemPatrimonio itemPatrimonioAlterado = buscar(id);
//		itemPatrimonioAlterado.setDataAtualizacao(new Date());
//		BeanUtils.copyProperties(itemPatrimonio, itemPatrimonioAlterado, "id", "usuario", "ambienteAtual");
//		
//		itemPatrimonioDAO.alterar(itemPatrimonioAlterado);
//		
//		return itemPatrimonioAlterado;
//	}
	
}
