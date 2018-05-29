package br.info.pweb.chines.controllers;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
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
import br.info.pweb.chines.dao.PatrimonioDAO;
import br.info.pweb.chines.models.Patrimonio;
import br.info.pweb.chines.models.Usuario;
import br.info.pweb.chines.utils.SessionUtils;

@Controller
@RequestMapping("/app")
public class PatrimonioController {

	@Autowired
	private PatrimonioDAO patrimonioDAO;
	
	@Autowired
	private CategoriaDAO categoriaDAO;
	
	@Autowired
	private SessionUtils session;
	
	@GetMapping("/patrimonio")
	public String abrirListaPatrimonio(Model model) {
		model.addAttribute("patrimonios", patrimonioDAO.listar());
		
		return "patrimonio/lista";
	}
	
	@GetMapping("/adm/patrimonio/novo")
	public String abrirFormAmbiente(@RequestParam(name = "id", required = false) Long id, Model model) {
		if (id != null) {
			model.addAttribute("patrimonio", patrimonioDAO.buscar(id));
		} else {
			model.addAttribute("patrimonio", new Patrimonio());			
		}
		
		model.addAttribute("patrimonios", patrimonioDAO.listar());
		
		model.addAttribute("categorias", categoriaDAO.listar());		
		
		return "patrimonio/form";
	}
	
	@GetMapping("/adm/patrimonio/excluir")
	public String excluirPatrimonio(@Valid Patrimonio patrimonio, BindingResult brPatrimonio,
			@RequestParam(name = "id", required = true) Long id, Model model) {
		
		try {
			Patrimonio patrimonioExcluido = patrimonioDAO.buscar(id);
			patrimonioDAO.excluir(patrimonioExcluido);	
		} catch (Exception e) {
			model.addAttribute("patrimonios", patrimonioDAO.listar());
			model.addAttribute("categorias", categoriaDAO.listar());		
			
			brPatrimonio.addError(new FieldError("patrimonio", "nome", 
					"Patrimônio não pode ser excluido! Itens existentes com este patrimônio"));
			
			return "patrimonio/form";
		}
				
		return "redirect:/app/patrimonio";
	}
	
	@GetMapping("/adm/patrimonio/alterar")
	public String alterarPatrimonio(@RequestParam(name = "id", required = true) Long id, Model model) {
		Patrimonio patrimonioAlterado = patrimonioDAO.buscar(id);
		model.addAttribute("patrimonio", patrimonioAlterado);
	
		model.addAttribute("patrimonios", patrimonioDAO.listar());
		model.addAttribute("categorias", categoriaDAO.listar());		
		
		return "patrimonio/form";
	}
	
	@PostMapping("/adm/patrimonio/salvar")
	public String salvarPatrimonio(@Valid Patrimonio patrimonio, BindingResult brPatrimonio, 
			@RequestParam(name = "id", required = false) Long id, Model model) {
		
		if (patrimonio.getId() == null && patrimonioDAO.buscarNome(patrimonio.getNome()) != null)
		{
			model.addAttribute("patrimonios", patrimonioDAO.listar());
			model.addAttribute("categorias", categoriaDAO.listar());		
			
			brPatrimonio.addError(new FieldError("patrimonio", "nome", "Patrimonio já cadastrado"));
		}
		
		if (brPatrimonio.hasErrors()) 
		{
			model.addAttribute("patrimonios", patrimonioDAO.listar());
			model.addAttribute("categorias", categoriaDAO.listar());		
			
			return "patrimonio/form";
		}
		
		if (patrimonio.getId() == null) {
			Usuario usuarioLogado = session.getLogin();
			patrimonio.setUsuario(usuarioLogado);
			patrimonio.setDataPatrimonio(new Date());
			
			patrimonioDAO.persistir(patrimonio);			
		} else {
			Patrimonio patrimonioAlterado = patrimonioDAO.buscar(id);
			BeanUtils.copyProperties(patrimonio, patrimonioAlterado, "id", "usuario", "dataPatrimonio");
			
			patrimonioDAO.alterar(patrimonioAlterado);
		}
		
		return "redirect:/app/adm/patrimonio/novo";
	}
}
