package br.info.pweb.chines.controllers;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.info.pweb.chines.dao.AmbienteDAO;
import br.info.pweb.chines.dao.ItemPatrimonioDAO;
import br.info.pweb.chines.dao.PatrimonioDAO;
import br.info.pweb.chines.models.ItemPatrimonio;
import br.info.pweb.chines.models.Usuario;
import br.info.pweb.chines.utils.SessionUtils;

@Controller
@RequestMapping("/app")
public class ItemPatrimonioController {

	@Autowired
	private SessionUtils session;
	
	@Autowired
	private ItemPatrimonioDAO itemPatrimonioDAO;
	
	@Autowired
	private PatrimonioDAO patrimonioDAO;
	
	@Autowired
	private AmbienteDAO ambienteDAO;
	
	@GetMapping("/item-patrimonio")
	public String abrirListaItemPatrimonio(Model model) {
		
		model.addAttribute("itemPatrimonios", itemPatrimonioDAO.listar());
		model.addAttribute("patrimonios", patrimonioDAO.listar());
		
		return "item-patrimonio/lista";
	}
	
	@GetMapping("/item-patrimonio/novo")
	public String abrirFormItemPatrimonio(@RequestParam(name = "id", required = false) Long id, Model model) {
		model.addAttribute("itemPatrimonio", new ItemPatrimonio());			
		
		model.addAttribute("itemPatrimonios", itemPatrimonioDAO.listar());

		model.addAttribute("patrimonios", patrimonioDAO.listar());
		model.addAttribute("ambientes", ambienteDAO.listar());
		
		return "item-patrimonio/form";
	}

	@GetMapping("/adm/item-patrimonio/excluir")
	public String excluirItemPatrimonio(@RequestParam(name = "id", required = true) Long id) {
		ItemPatrimonio itemPatrimonioExcluido = itemPatrimonioDAO.buscar(id);
		itemPatrimonioDAO.excluir(itemPatrimonioExcluido);	
		
		return "redirect:/app/item-patrimonio";
	}
	
	@GetMapping("/item-patrimonio/alterar")
	public String alterarItemPatrimonio(@RequestParam(name = "id", required = true) Long id, Model model) {
		ItemPatrimonio itemPatrimonioAlterado = itemPatrimonioDAO.buscar(id);
		model.addAttribute("itemPatrimonio", itemPatrimonioAlterado);
		
		model.addAttribute("itemPatrimonios", itemPatrimonioDAO.listar());
		model.addAttribute("patrimonios", patrimonioDAO.listar());
		model.addAttribute("ambientes", ambienteDAO.listar());
		
		return "item-patrimonio/form";
	}

	@PostMapping("/item-patrimonio/salvar")
	public String salvarItemPatrimonio(@Valid ItemPatrimonio itemPatrimonio, BindingResult brItemPatrimonio, 
			@RequestParam(name = "id", required = false) Long id, Model model) {
		
		if (brItemPatrimonio.hasErrors()) 
		{
			return "item-patrimonio/form";
		}
		
		itemPatrimonio.setDataAtualizacao(new Date());
		
		if (itemPatrimonio.getId() == null) {
			Usuario usuarioLogado = session.getLogin();
			itemPatrimonio.setUsuario(usuarioLogado);
			
			itemPatrimonioDAO.persistir(itemPatrimonio);			
		} else {
			ItemPatrimonio itemPatrimonioAlterado = itemPatrimonioDAO.buscar(id);
			BeanUtils.copyProperties(itemPatrimonio, itemPatrimonioAlterado, "id", "usuario", "ambienteAtual");
			
			itemPatrimonioDAO.alterar(itemPatrimonioAlterado);
		}
		
		return "redirect:/app/item-patrimonio/novo";
	}
}
