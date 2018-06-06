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
		
		ItemPatrimonio itemMovimentado = itemPatrimonioDAO.buscar(id);
	
		Movimentacao movimentacao = new Movimentacao();
		movimentacao.setItem(itemMovimentado);
		movimentacao.setAmbienteOrigem(itemMovimentado.getAmbienteAtual());		
		movimentacao.setAmbienteDestino(itemPatrimonio.getAmbienteAtual());	
		movimentacao.setDataMovimentacao(new Date());
		
		Usuario usuarioLogado = session.getLogin();
		movimentacao.setUsuario(usuarioLogado);
		
		itemMovimentado.setDataAtualizacao(new Date());
		itemMovimentado.setAmbienteAtual(itemPatrimonio.getAmbienteAtual());
		
		movimentacaoDAO.persistir(movimentacao);			
		itemPatrimonioDAO.alterar(itemMovimentado);
		
		return "redirect:/app/item-patrimonio";
	}
}
