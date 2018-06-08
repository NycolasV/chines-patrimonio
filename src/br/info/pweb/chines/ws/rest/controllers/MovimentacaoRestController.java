package br.info.pweb.chines.ws.rest.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.info.pweb.chines.exceptions.MyEntityNotFoundException;
import br.info.pweb.chines.exceptions.MyValidationException;
import br.info.pweb.chines.models.ItemPatrimonio;
import br.info.pweb.chines.services.MovimentacaoService;
import br.info.pweb.chines.utils.MapUtils;

@RestController
@RequestMapping("/rest/movimentacaos")
public class MovimentacaoRestController {

	@Autowired
	private MovimentacaoService movimentacaoService;
	
	@GetMapping
	public ResponseEntity<Object> listar() {
		try {
			return ResponseEntity.ok(movimentacaoService.listar());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> buscar(@PathVariable Long id) {
		try {
			return ResponseEntity.ok(movimentacaoService.buscar(id));
		} catch (MyEntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<Object> alterar(@PathVariable Long id, @RequestBody @Valid 
			ItemPatrimonio itemPatrimonio, BindingResult brItemPatrimonio) {

		try {
			return ResponseEntity.ok(movimentacaoService.movimentar(id, itemPatrimonio, brItemPatrimonio));
		} catch (MyValidationException e) {
			return ResponseEntity.unprocessableEntity().body(MapUtils.mapFrom(brItemPatrimonio));
		}catch (MyEntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}
	