package br.info.pweb.chines.ws.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.info.pweb.chines.exceptions.MyEntityNotFoundException;
import br.info.pweb.chines.services.PatrimonioService;

@RestController
@RequestMapping("/rest/patrimonios")
public class PatrimonioRestController {

	@Autowired
	private PatrimonioService patrimonioService;
	
	@GetMapping
	public ResponseEntity<Object> listar() {
		try {
			return ResponseEntity.ok(patrimonioService.listar());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> buscar(@PathVariable Long id) {
		try {
			return ResponseEntity.ok(patrimonioService.buscar(id));
		} catch (MyEntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
}
