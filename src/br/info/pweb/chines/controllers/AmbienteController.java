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

import br.info.pweb.chines.dao.AmbienteDAO;
import br.info.pweb.chines.models.Ambiente;
import br.info.pweb.chines.models.ItemPatrimonio;
import sun.util.BuddhistCalendar;

@Controller
@RequestMapping("/app")
public class AmbienteController {
	
	@Autowired
	private AmbienteDAO ambienteDAO;
	
	@GetMapping("/ambiente")
	public String abrirListaAmbiente(Model model) {
		model.addAttribute("ambientes", ambienteDAO.listar());
		
		return "ambiente/lista";
	}
	
	@GetMapping("/adm/ambiente/novo")
	public String abrirFormAmbiente(@RequestParam(name = "id", required = false) Long id, Model model) {
		if (id != null) {
			model.addAttribute("ambiente", ambienteDAO.buscar(id));
		} else {
			model.addAttribute("ambiente", new Ambiente());			
		}
		
		model.addAttribute("ambientes", ambienteDAO.listar());
		
		return "ambiente/form";
	}
	
	@GetMapping("/adm/ambiente/excluir")
	public String excluirAmbiente(@Valid Ambiente ambienteExcluido, BindingResult brAmbiente,
			@RequestParam(name = "id", required = true) Long id, Model model) {
		// busca itens que tenham esse ambiente como parametro
		// se não tiver nenhum item deixa remover
		// se não, avisa o usuário que possui item cadastrado na lista com esse ambiente		
		try {
			ambienteExcluido = ambienteDAO.buscar(id);
			ambienteDAO.excluir(ambienteExcluido);
		} catch (Exception e) {
			model.addAttribute("ambientes", ambienteDAO.listar());		
			
			brAmbiente.addError(new FieldError("ambiente", "nome", 
					"Ambiente não pode ser excluido! Itens existentes com este ambiente"));
			
			return "ambiente/form";
		}
		
		return "redirect:/app/adm/ambiente/novo";
	}
	
	@GetMapping("/adm/ambiente/alterar")
	public String alterarAmbiente(@RequestParam(name = "id", required = true) Long id, Model model) {
		Ambiente ambienteAlterado = ambienteDAO.buscar(id);
		model.addAttribute("ambiente", ambienteAlterado);
		
		model.addAttribute("ambientes", ambienteDAO.listar()); 
		
		return "ambiente/form";
	}

	@PostMapping("/adm/ambiente/salvar")
	public String salvarAmbiente(@Valid Ambiente ambiente, BindingResult brAmbiente, Model model) {
		
		if (ambiente.getId() == null && ambienteDAO.buscarNome(ambiente.getNome()) != null) 
		{
			model.addAttribute("ambientes", ambienteDAO.listar());		
			
			brAmbiente.addError(new FieldError("ambiente", "nome", "Ambiente já cadastrado"));
		}
		
		if (brAmbiente.hasErrors()) 
		{
			model.addAttribute("ambientes", ambienteDAO.listar());

			return "ambiente/form";
		}
		
		if (ambiente.getId() != null) {
			ambienteDAO.alterar(ambiente);
		} else {
			ambienteDAO.persistir(ambiente);
		}
		
		return "redirect:/app/adm/ambiente/novo";
	}

}
