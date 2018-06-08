package br.info.pweb.chines.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import br.info.pweb.chines.dao.ItemPatrimonioDAO;
import br.info.pweb.chines.dao.MovimentacaoDAO;
import br.info.pweb.chines.exceptions.MyEntityNotFoundException;
import br.info.pweb.chines.exceptions.MyValidationException;
import br.info.pweb.chines.models.ItemPatrimonio;
import br.info.pweb.chines.models.Movimentacao;
import br.info.pweb.chines.models.Usuario;

@Service
public class MovimentacaoService {

	@Autowired
	private MovimentacaoDAO movimentacaoDAO;
	
	@Autowired
	private ItemPatrimonioDAO itemPatrimonioDAO;
	
	@Autowired
	private ItemPatrimonioService itemPatrimonioService;
	
	public List<Movimentacao> listar() {
		return movimentacaoDAO.listar();
	}
	
	public Movimentacao buscar(Long id) 
			throws MyEntityNotFoundException {
		
		Movimentacao movimentacaoBuscada = movimentacaoDAO.buscar(id);
		
		if (movimentacaoBuscada == null) {
			throw new MyEntityNotFoundException();
		}
		
		return movimentacaoBuscada;
	}
	
	public ItemPatrimonio movimentar(Long id, ItemPatrimonio itemPatrimonio,
			BindingResult brItemPatrimonio) throws MyValidationException, MyEntityNotFoundException  {

		if (brItemPatrimonio.hasErrors()) {			
			throw new MyValidationException();
		}
		
		ItemPatrimonio itemMovimentado = itemPatrimonioService.buscar(id);
		
		Movimentacao movimentacao = new Movimentacao();
		movimentacao.setItem(itemMovimentado);	
		movimentacao.setDataMovimentacao(new Date());
		
		Usuario usuarioLogado = (Usuario) SecurityContextHolder.getContext().getAuthentication();
		movimentacao.setUsuario(usuarioLogado);
		
		if (itemMovimentado.getAmbienteAtual().getId() == itemPatrimonio.getAmbienteAtual().getId()) {
			brItemPatrimonio.addError(new FieldError("itemPatrimonio", "ambienteAtual", 
					"Item já está neste ambiente: " + itemMovimentado.getAmbienteAtual().getNome()));
			
			throw new MyValidationException();
		} else {			
			movimentacao.setAmbienteOrigem(itemMovimentado.getAmbienteAtual());		
			movimentacao.setAmbienteDestino(itemPatrimonio.getAmbienteAtual());			
		}
		
		itemMovimentado.setDataAtualizacao(new Date());
		itemMovimentado.setAmbienteAtual(itemPatrimonio.getAmbienteAtual());
		
		movimentacaoDAO.persistir(movimentacao);
		itemPatrimonioDAO.alterar(itemMovimentado);
		
		return itemMovimentado;
	}
}
