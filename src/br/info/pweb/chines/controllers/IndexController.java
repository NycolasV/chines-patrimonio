package br.info.pweb.chines.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.info.pweb.chines.models.Usuario;
import br.info.pweb.chines.utils.SessionUtils;

@Controller
public class IndexController {
	
	@Autowired
	private SessionUtils session;
	
	@RequestMapping("/")
	public String abrirIndex(Model model) {
	if (session.getLogin() != null) 
	{
		return "redirect:/app";
	}
	
	model.addAttribute("usuario", new Usuario());
	return "index";
	}
	
	@RequestMapping("/erro/404")
	public String abrir404() {
		return "erro/404";
	}
	
}
