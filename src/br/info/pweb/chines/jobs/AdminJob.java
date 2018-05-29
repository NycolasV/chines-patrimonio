package br.info.pweb.chines.jobs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import br.info.pweb.chines.dao.UsuarioDAO;
import br.info.pweb.chines.models.TipoUsuario;
import br.info.pweb.chines.models.Usuario;

@Component
public class AdminJob implements ApplicationListener<ApplicationEvent>{

	@Autowired
	private UsuarioDAO usuarioDAO;
	
	@Override
	public void onApplicationEvent(ApplicationEvent appEvent) {		
		Usuario admin = new Usuario();
		admin.setNome("Administrador");
		admin.setSobrenome("da empresa Chinês Patrimonio");
		admin.setEmail("admin@teste.com");
		admin.setSenha("admin123");
		admin.setTipoUsuario(TipoUsuario.ADMINISTRADOR);
		
		admin.HashearSenha();

		if (usuarioDAO.buscarEmail(admin.getEmail()) == null) {
			usuarioDAO.persistir(admin);	
		}
	}

}
