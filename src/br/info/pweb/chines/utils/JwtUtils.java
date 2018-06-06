package br.info.pweb.chines.utils;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import br.info.pweb.chines.models.Usuario;

public class JwtUtils {

	public static String createToken(Usuario usuario)
			throws IllegalArgumentException, JWTCreationException, UnsupportedEncodingException {
		
		return JWT.create()
				.withIssuer("Chinês Patrimônio")
				.withIssuedAt(new Date())
				.withSubject("Autenticação")
				.withClaim("id", usuario.getId())
				.withClaim("nome", usuario.getNome())
				.withClaim("sobrenome", usuario.getSobrenome())
				.sign(Algorithm.HMAC512("78F1A00295A50C5F069389EA6BCDBC661C50FBF27AB84EEBA20FE00D57D6E88D91610F98529877424767BCDB5B28E05EED3E9754B0627033C44E68F268DAA470"));
	}
	
	public static Usuario extractUser(String token) {
		Usuario usuario = new Usuario();
		
		DecodedJWT decodedJWT = JWT.decode(token);
		usuario.setId(decodedJWT.getClaim("id").asLong());
		usuario.setNome(decodedJWT.getClaim("nome").asString());
		usuario.setSobrenome(decodedJWT.getClaim("sobrenome").asString());
		
		return usuario;
	}
	
	public static void validateToken(String token) 
			throws JWTVerificationException, IllegalArgumentException, UnsupportedEncodingException {
		
		JWT.require(Algorithm
				.HMAC512("78F1A00295A50C5F069389EA6BCDBC661C50FBF27AB84EEBA20FE00D57D6E88D91610F98529877424767BCDB5B28E05EED3E9754B0627033C44E68F268DAA470"))
				.build()
				.verify(token);
	}
}
