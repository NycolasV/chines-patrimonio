package br.info.pweb.chines.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import br.info.pweb.chines.models.Usuario;
import br.info.pweb.chines.utils.JwtUtils;

@Component
public class JwtFilter extends GenericFilterBean {

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) req;
		
		String authorization = request.getHeader("Authorization");
		
		if (authorization != null) {
			if (authorization.matches("(Bearer )(\\w|\\.|\\-)+")) {
				String token = authorization.split(" ")[1];
				
				try {
					JwtUtils.validateToken(token);
					
					Usuario usuarioToken = JwtUtils.extractUser(token);
					
					SecurityContextHolder.getContext().setAuthentication(usuarioToken);
				} catch (Exception e) {
					System.out.println("ERROR - Token inválido");
				}
			} else {
				System.out.println("ERROR - Bearer inválido");
			}
		} else {
			System.out.println("ERROR - Authorization is null");
		}
		
		chain.doFilter(req, resp);
	}

}
