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

import br.info.pweb.chines.dao.AmbienteDAO;
import br.info.pweb.chines.dao.ItemPatrimonioDAO;
import br.info.pweb.chines.dao.MovimentacaoDAO;
import br.info.pweb.chines.models.ItemPatrimonio;
import br.info.pweb.chines.models.Movimentacao;
import br.info.pweb.chines.models.Usuario;
import br.info.pweb.chines.utils.SessionUtils;

@Controller
@RequestMapping("/app")
public class MovimentacaoController {

	@Autowired
	private SessionUtils session;
	
	@Autowired
	private MovimentacaoDAO movimentacaoDAO;
	
	@Autowired
	private ItemPatrimonioDAO itemPatrimonioDAO;
	
	@Autowired
	private AmbienteDAO ambienteDAO;
	
	@GetMapping("/movimentacao-feita")
	public String abrirListaMovimentacao(Model model) {
		model.addAttribute("movimentacoes", movimentacaoDAO.listar());
		
		return "movimentacao/lista";
	}
	
	@GetMapping("/movimentacao/novo")
	public String abrirFormMovimentacao(Model model,
			@RequestParam(name = "id", required = true) Long id) {				
		ItemPatrimonio itemPatrimonio = itemPatrimonioDAO.buscar(id);  
		model.addAttribute("itemPatrimonio", itemPatrimonio);	
	
		model.addAttribute("ambienteOrigem", ambienteDAO.listar());		
		model.addAttribute("ambienteDestino", ambienteDAO.listar());
		
		return "movimentacao/form";
	}
	
	@PostMapping("/movimentacao/item")
	public String movimentarItemPatrimonio(@Valid ItemPatrimonio itemPatrimonio, BindingResult brItemMovimentado,
			@RequestParam(name = "id", required = true) Long id, Model model) {
		
		itemPatrimonio.setDataAtualizacao(new Date());
		ItemPatrimonio itemMovimentado = itemPatrimonioDAO.buscar(id);
		BeanUtils.copyProperties(itemPatrimonio, itemMovimentado, "id", "usuario", "patrimonio");
	
		Movimentacao movimentacao = new Movimentacao();
		movimentacao.setItem(itemMovimentado);
		movimentacao.setAmbienteOrigem(itemPatrimonio.getAmbienteAtual());		
		movimentacao.setAmbienteDestino(itemMovimentado.getAmbienteAtual());	
		movimentacao.setDataMovimentacao(new Date());
		
		Usuario usuarioLogado = session.getLogin();
		movimentacao.setUsuario(usuarioLogado);
		
		itemPatrimonioDAO.alterar(itemMovimentado);
		movimentacaoDAO.persistir(movimentacao);			
		
		return "redirect:/app/item-patrimonio";
	}
}
