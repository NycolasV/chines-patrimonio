package br.info.pweb.chines.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.info.pweb.chines.dao.CategoriaDAO;
import br.info.pweb.chines.models.Categoria;

@Controller
@RequestMapping("/app/adm")
public class CategoriaController {
	
	@Autowired
	private CategoriaDAO categoriaDAO;
	
	@GetMapping("/categoria")
	public String abrirMenuCategoria(@RequestParam(name = "id", required = false) Long id, Model model) {
		if (id != null) {
			model.addAttribute("categoria", categoriaDAO.buscar(id));
		} else {
			model.addAttribute("categoria", new Categoria());			
		}
		
		model.addAttribute("categorias", categoriaDAO.listar());
		
		return "categoria/menu";
	}
	
	@GetMapping("/categoria/excluir")
	public String excluirCategoria(@Valid Categoria categoria, BindingResult brCategoria,
			@RequestParam(name = "id", required = true) Long id, Model model) {
		
		try {
			Categoria categoriaExcluida = categoriaDAO.buscar(id);
			categoriaDAO.excluir(categoriaExcluida);	
		} catch (Exception e) {
			model.addAttribute("categorias", categoriaDAO.listar());
			
			brCategoria.addError(new FieldError("categoria", "nome", 
					"Categoria não pode ser excluída! Patrimônios existentes com esta categoria"));
			
			return "categoria/form";
		}
		
		return "redirect:/app/adm/categoria";
	}
	
	@PostMapping("/categoria/salvar")
	public String salvarCategoria(@Valid Categoria categoria, BindingResult brCategoria,
			@RequestParam(name = "id", required = false) Long id, Model model) {
		
		if (categoria.getId() == null && categoriaDAO.buscarNome(categoria.getNome()) != null) 
		{
			model.addAttribute("categorias", categoriaDAO.listar());
			
			brCategoria.addError(new FieldError("categoria", "nome", "Categoria já cadastrada"));
		}
		
		if (brCategoria.hasErrors()) 
		{
			model.addAttribute("categorias", categoriaDAO.listar());
			
			return "categoria/menu";
		}
		
		if (categoria.getId() != null) {
			categoriaDAO.alterar(categoria);	
		} else {			
			categoriaDAO.persistir(categoria);
		}
		
		return "redirect:/app/adm/categoria";
	}
}
