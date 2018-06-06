package br.info.pweb.chines.ws.rest.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.info.pweb.chines.exceptions.MyEntityNotFoundException;
import br.info.pweb.chines.exceptions.MyValidationException;
import br.info.pweb.chines.models.Usuario;
import br.info.pweb.chines.services.UsuarioService;
import br.info.pweb.chines.utils.JwtUtils;
import br.info.pweb.chines.utils.MapUtils;

@RestController
@RequestMapping("/rest/auth")
public class AuthRestController {

	@Autowired
	private UsuarioService usuarioService;
	
	@PostMapping("/jwt")
	public ResponseEntity<Object> criarJWT(@RequestBody @Valid
			Usuario usuario, BindingResult brUsuario) {
		
		try {
			Usuario usuarioLogado = usuarioService.buscarEmailSenha(usuario, brUsuario);
			
			Map<String, String> mapToken = new HashMap<>();
			mapToken.put("token", JwtUtils.createToken(usuarioLogado));
			
			return ResponseEntity.ok(mapToken);
		} catch (MyValidationException e) {
			return ResponseEntity.unprocessableEntity()
					.body(MapUtils.mapFrom(brUsuario));
		} catch (MyEntityNotFoundException e) {
			return ResponseEntity.notFound()
					.header("X-Reason", "Usuário não encontrado").build();
		} catch (Exception e) {
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
	}
	
}
