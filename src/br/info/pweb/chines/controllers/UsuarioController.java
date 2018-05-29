package br.info.pweb.chines.controllers;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import br.info.pweb.chines.dao.UsuarioDAO;
import br.info.pweb.chines.models.TipoUsuario;
import br.info.pweb.chines.models.Usuario;
import br.info.pweb.chines.utils.EmailUtils;
import br.info.pweb.chines.utils.SessionUtils;
import br.info.pweb.chines.utils.StorageUtils;

@Controller
public class UsuarioController {

	@Autowired
	private SessionUtils session;
	
	@Autowired
	private StorageUtils storage;
	
	@Autowired
	private UsuarioDAO usuarioDAO;
	
	@GetMapping("/app/adm/usuario")
	public String abrirListaUsuario(Model model) {
		// buscar todos os usuários do banco
		model.addAttribute("usuarios", usuarioDAO.listar());		
		
		return "usuario/lista";
	}
/*	
	@GetMapping("/app/adm/usuario/perfil")
	public String perfilUsuario(@RequestParam(name = "id", required = true) Long id, Model model) {	
		Usuario perfilUsuario = usuarioDAO.buscar(id);
		model.addAttribute(perfilUsuario);
		
		return "usuario/perfil";
	}
	
	@GetMapping("/app/usuario/alterar-perfil")
	public String alterarPerfilUsuario(@Valid Usuario usuario, BindingResult brUsuario,
			@RequestParam(name = "id", required = true) Long id,
			@RequestParam(name = "antigaSenha", required = false) String antigaSenha,
			@RequestParam(name = "administrador", required = false) Boolean administrador,
			@RequestParam(name = "fotoPerfil", required = false) MultipartFile fotoPerfil) {	
		
		usuario = usuarioDAO.buscar(id);
		
		// verificando se o arquivo da foto foi encontrada
		if (fotoPerfil != null) {
			System.out.println("FOTO ENCONTRADA");
		} else {
			System.out.println("FOTO NÃO ENCOTRADA");
		}
		
		// verificando se a senha pode ser alterada 
		usuario.HashearSenha();
		if (!antigaSenha.equals(usuario.getSenha())) 
		{
			brUsuario.addError(new FieldError("usuario", "senha", "A senha não está correta"));
		}
		
		// verificando se o email do novo usuário não se repete banco de dados
		if (usuario.getId() == null && usuarioDAO.buscarEmail(usuario.getEmail()) != null) 
		{
			brUsuario.addError(new FieldError("usuario", "email", "O email utilizado já está em uso"));
		}
		
		// verificando se os dados inseridos do novo usuário não possuem erros
		if (usuario.getId() == null && brUsuario.hasErrors()) 
		{
			return "usuario/cadastro";
		}
		
		// verificando se os dados inseridos do usuário alterado não possuem erros
		if (usuario.getId() != null && brUsuario.hasFieldErrors("nome") || brUsuario.hasFieldErrors("sobrenome")) 
		{
			return "usuario/form";
		}
		
		// verificando se o usuário é ou não administrador
		if (administrador != null) {
			usuario.setTipoUsuario(TipoUsuario.ADMINISTRADOR);
		} else {
			usuario.setTipoUsuario(TipoUsuario.COMUM);
		}

		// verificando se deve adicionar novo usuário ou alterá-lo 
		if (usuario.getId() == null) {
			usuario.HashearSenha();
			
			usuarioDAO.persistir(usuario);
		} else {
			Usuario usuarioAlterado = usuarioDAO.buscar(usuario.getId());	
			BeanUtils.copyProperties(usuario, usuarioAlterado, "id", "email", "senha");
			
			usuarioDAO.alterar(usuarioAlterado);
		}
	
		try {
			storage.armazenamentoFotoPerfil("foto_" + usuario.getId(), fotoPerfil.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "usuario/alterar-perfil";
	}
*/	
	@GetMapping("/app/adm/usuario/cadastro")
	public String abrirCadastroUsuario(Model model) {
		model.addAttribute("usuario", new Usuario());
		
		return "usuario/cadastro";
	}
	
	@GetMapping("/app/adm/usuario/excluir")
	public String excluirUsuario(@Valid Usuario usuario, BindingResult brUsuario,
			@RequestParam(name = "id", required = true) Long id) {
		
		try {
			Usuario usuarioExcluido = usuarioDAO.buscar(id);
			usuarioDAO.excluir(usuarioExcluido);	
		} catch (Exception e) {
			brUsuario.addError(new FieldError("usuario", "nome", 
					"Usuário não pode ser excluído! Cadastros foram feitos em este nome"));
			
			return "usuario/form";
		}
		
		return "redirect:/app/adm/usuario";
	}
	
	@GetMapping("/app/adm/usuario/alterar")
	public String alterarUsuario(@RequestParam(name = "id", required = true) Long id, Model model) {	
		Usuario usuarioAlterado = usuarioDAO.buscar(id);
		model.addAttribute(usuarioAlterado);
		
		return "usuario/cadastro";
	}
	
	@PostMapping("/app/adm/usuario/salvar")
	public String salvarUsuario(@Valid Usuario usuario, BindingResult brUsuario,
			@RequestParam(name = "confirmacaoSenha", required = false) String confirmacaoSenha,
			@RequestParam(name = "administrador", required = false) Boolean administrador,
			@RequestParam(name = "fotoPerfil", required = false) MultipartFile fotoPerfil) {		

		// verificando se o arquivo da foto foi encontrada
		if (fotoPerfil != null) {
			System.out.println("FOTO ENCONTRADA");
		} else {
			System.out.println("FOTO NÃO ENCOTRADA");
		}
		
		// verificando se as senhas do novo usuário coincidem 
		if (usuario.getId() == null && !confirmacaoSenha.equals(usuario.getSenha())) 
		{
			brUsuario.addError(new FieldError("usuario", "senha", "As senhas não coincidem"));
		}
		
		// verificando se o email do novo usuário não se repete banco de dados
		if (usuario.getId() == null && usuarioDAO.buscarEmail(usuario.getEmail()) != null) 
		{
			brUsuario.addError(new FieldError("usuario", "email", "O email utilizado já está em uso"));
		}
		
		// verificando se os dados inseridos do novo usuário não possuem erros
		if (usuario.getId() == null && brUsuario.hasErrors()) 
		{
			return "usuario/cadastro";
		}
		
		// verificando se os dados inseridos do usuário alterado não possuem erros
		if (usuario.getId() != null && brUsuario.hasFieldErrors("nome") || brUsuario.hasFieldErrors("sobrenome")) 
		{
			return "usuario/form";
		}
		
		// verificando se o usuário é ou não administrador
		if (administrador != null) {
			usuario.setTipoUsuario(TipoUsuario.ADMINISTRADOR);
		} else {
			usuario.setTipoUsuario(TipoUsuario.COMUM);
		}
		
		// verificando se deve adicionar novo usuário ou alterá-lo 
		if (usuario.getId() == null) {
			usuario.HashearSenha();
			
			usuarioDAO.persistir(usuario);
/*			
			String titulo = "Cadastro feito com sucesso!";
			String corpo = "Bem-vindo " + usuario.getNome() + "ao Chinês Patrimônio! "
					+ "Acesse: http://localhost:8080/chines-patrimonio/";
			
			try {
				EmailUtils.enviarMensagem(titulo, corpo, usuario.getEmail());
			} catch (AddressException e) {
				e.printStackTrace();
			} catch (MessagingException e) {
				e.printStackTrace();
			}
*/
		} else {
			Usuario usuarioAlterado = usuarioDAO.buscar(usuario.getId());	
			BeanUtils.copyProperties(usuario, usuarioAlterado, "id", "email", "senha");
			
			usuarioDAO.alterar(usuarioAlterado);
		}
/*	
		try {
			storage.armazenamentoFotoPerfil("foto_" + usuario.getId(), fotoPerfil.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
*/	
		return "redirect:/app/adm/usuario";
	}	
	
	@PostMapping("/usuario/logar")
	public String autenticarUsuario(@Valid Usuario usuario, BindingResult brUsuario) {
		if (brUsuario.hasFieldErrors("email") || brUsuario.hasFieldErrors("senha")) 
		{	
			return "index";
		}
		
		usuario.HashearSenha();
		Usuario usuarioLogado = usuarioDAO.buscarEmailSenha(usuario.getEmail(), usuario.getSenha());
		
		if (usuarioLogado == null) 
		{
			brUsuario.addError(new FieldError("usuario", "email", "E-mail ou senha inválido"));
			return "index";
		}
		
		session.setLogin(usuarioLogado);
		return "redirect:/app/";
	}
	
	@GetMapping({"/app/sair"})
	public String sairUsuario() {
		session.closeSession();
		
		return "redirect:/";
	}
}
