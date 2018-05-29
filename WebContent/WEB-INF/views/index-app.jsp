<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<c:url value="/assets/imagens" var="imagensRoot" />
<c:url value="/app/sair" var="urlSair" />
<c:url value="/app/usuario/perfil" var="urlPerfil" />
<c:url value="/app/adm/usuario/cadastro" var="urlCadastroUsuario" />
<c:url value="/app/adm/usuario" var="urlListaUsuario" />
<c:url value="/app/adm/ambiente/novo" var="urlNovoAmbiente" />
<c:url value="/app/ambiente" var="urlListaAmbiente" />
<c:url value="/app/adm/categoria" var="urlMenuCategoria" />
<c:url value="/app/patrimonio" var="urlListaPatrimonio" />
<c:url value="/app/adm/patrimonio/novo" var="urlNovoPatrimonio" />
<c:url value="/app/item-patrimonio" var="urlListaItemPatrimonio" />
<c:url value="/app/item-patrimonio/novo" var="urlNovoItemPatrimonio" />
<c:url value="/app/movimentacao-feita" var="urlListaMovimentacao" />

<!DOCTYPE html>
<html>
<head>
	<c:import url="templates/head.jsp"/>
</head>
<body>
	<header> 
		<c:import url="templates/nav.jsp" />
	</header>

	<main>
	<h1>Página inicial, bem-vindo ${usuarioLogado.nome}</h1>

	<section>	
		<c:if test="${usuarioLogado.tipoUsuario eq 'ADMINISTRADOR'}">
			<div class="row collection with-header">
				<h3>Usuários</h3>
			
				<c:if test="${usuarioLogado.email != 'admin@teste.com'}">
<!-- 
					<div class="input-field col s6 center-align">
						<button class="btn waves-effect waves-light materialize-red" type="reset" name="action">
							<a href="${urlPerfil}?id=${usuarioLogado.id}" class="btn btn-red materialize-red">Perfil do ${usuarioLogado.nome}</a> 
						
							<i class="material-icons right">edit</i>
						</button>	
					</div>
 -->				
					<div class="input-field col s12 center-align">
						<button class="btn waves-effect waves-light materialize-red" type="button" name="action">
							<a href="${urlSair}" class="btn btn-red materialize-red">Sair da sessão</a> 
							
							<i class="material-icons right">exit_to_app</i>
						</button>
					</div>
				</c:if>
		
				<div class="row">
					<div class="input-field col s6 center-align">
						<button class="btn waves-effect waves-light materialize-red" type="reset" name="action">
							<a href="${urlCadastroUsuario}" class="btn btn-red materialize-red">Adicionar novo usuário</a> 
							
							<i class="material-icons right">person_add</i>
						</button>	
					</div>
					
					<div class="input-field col s6 center-align">
						<button class="btn waves-effect waves-light materialize-red" type="reset" name="action">
							<a href="${urlListaUsuario}" class="btn btn-red materialize-red">Lista de usuários</a> 
							
							<i class="material-icons right">group</i>
						</button>	
					</div>
				</div>
			</div>
			
			<div class="row collection with-header">
				<h3>Itens</h3>
			
				<div class="input-field col s6 center-align">
					<button class="btn waves-effect waves-light materialize-red" type="reset" name="action">
						<a href="${urlNovoitemPatrimonio}" class="btn btn-red materialize-red">Adicionar novo item</a> 
						
						<i class="material-icons right">system_update_alt</i>
					</button>	
				</div>
				
				<div class="input-field col s6 center-align">
					<button class="btn waves-effect waves-light materialize-red" type="reset" name="action">
						<a href="${urlListaItemPatrimonio}" class="btn btn-red materialize-red">Lista de itens</a> 
						
						<i class="material-icons right">devices</i>
					</button>	
				</div>
			</div>
			
			<div class="row collection with-header">
				<h3>Movimentações</h3>
				
				<div class="input-field col s6 center-align">
					<button class="btn waves-effect waves-light materialize-red" type="reset" name="action">
						<a href="${urlListaItemPatrimonio}" class="btn btn-red materialize-red">Registrar nova movimentação</a> 
						
						<i class="material-icons right">add_location</i>
					</button>	
				</div>
				
				<div class="input-field col s6 center-align">
					<button class="btn waves-effect waves-light materialize-red" type="reset" name="action">
						<a href="${urlListaMovimentacao}" class="btn btn-red materialize-red">Histórico de movimentações</a> 
						
						<i class="material-icons right">location_on</i>
					</button>	
				</div>
			</div>
			
			<div class="row collection with-header">
				<h3>Ambientes</h3>
			
				<div class="input-field col s6 center-align">
					<button class="btn waves-effect waves-light materialize-red" type="reset" name="action">
						<a href="${urlNovoAmbiente}" class="btn btn-red materialize-red">Adicionar novo ambiente</a> 
						
						<i class="material-icons right">library_add</i>
					</button>	
				</div>
				
				<div class="input-field col s6 center-align">
					<button class="btn waves-effect waves-light materialize-red" type="reset" name="action">
						<a href="${urlListaAmbiente}" class="btn btn-red materialize-red">Lista de ambientes</a> 
						
						<i class="material-icons right">account_balance</i>
					</button>	
				</div>
			</div>
			
			<div class="row collection with-header">
				<h3>Patrimônios</h3>
				
				<div class="input-field col s6 center-align">
					<button class="btn waves-effect waves-light materialize-red" type="reset" name="action">
						<a href="${urlNovoPatrimonio}" class="btn btn-red materialize-red">Adicionar novo patrimônio</a> 
						
						<i class="material-icons right">add_to_queue</i>
					</button>	
				</div>
				
				<div class="input-field col s6 center-align">
					<button class="btn waves-effect waves-light materialize-red" type="reset" name="action">
						<a href="${urlListaPatrimonio}" class="btn btn-red materialize-red">Lista de patrimônios</a> 
						
						<i class="material-icons right">desktop_windows</i>
					</button>	
				</div>
				
				<div class="input-field col s12 center-align">
					<button class="btn waves-effect waves-light materialize-red" type="reset" name="action">
						<a href="${urlMenuCategoria}" class="btn btn-red materialize-red">Menu de categorias</a> 
						
						<i class="material-icons right">keyboard</i>
					</button>
				</div>
			</div>
		</c:if>
	</section>
	
	<section>
		<c:if test="${usuarioLogado.tipoUsuario eq 'COMUM'}">
			<div class="row collection with-header">
				<h3>Usuários</h3>
<!-- 			
				<div class="input-field col s6 center-align">
					<button class="btn waves-effect waves-light materialize-red" type="reset" name="action">
						<a href="${urlPerfil}?id=${usuarioLogado.id}" class="btn btn-red materialize-red">Perfil do ${usuarioLogado.nome}</a> 
					
						<i class="material-icons right">edit</i>
					</button>	
				</div>
 -->				
				<div class="input-field col s12 center-align">
					<button class="btn waves-effect waves-light materialize-red" type="button" name="action">
						<a href="${urlSair}" class="btn btn-red materialize-red">Sair da sessão</a> 
						
						<i class="material-icons right">exit_to_app</i>
					</button>
				</div>
			</div>
			
			<div class="row collection with-header">
				<h3>Itens</h3>
			
				<div class="input-field col s6 center-align">
					<button class="btn waves-effect waves-light materialize-red" type="reset" name="action">
						<a href="${urlNovoitemPatrimonio}" class="btn btn-red materialize-red">Adicionar novo item</a> 
						
						<i class="material-icons right">system_update_alt</i>
					</button>	
				</div>
				
				<div class="input-field col s6 center-align">
					<button class="btn waves-effect waves-light materialize-red" type="reset" name="action">
						<a href="${urlListaItemPatrimonio}" class="btn btn-red materialize-red">Lista de itens</a> 
						
						<i class="material-icons right">devices</i>
					</button>	
				</div>
			</div>
			
			<div class="row collection with-header">
				<h3>Movimentações</h3>
				
				<div class="input-field col s6 center-align">
					<button class="btn waves-effect waves-light materialize-red" type="reset" name="action">
						<a href="${urlListaItemPatrimonio}" class="btn btn-red materialize-red">Registrar nova movimentação</a> 
						
						<i class="material-icons right">add_location</i>
					</button>	
				</div>
				
				<div class="input-field col s6 center-align">
					<button class="btn waves-effect waves-light materialize-red" type="reset" name="action">
						<a href="${urlListaMovimentacao}" class="btn btn-red materialize-red">Histórico de movimentações</a> 
						
						<i class="material-icons right">location_on</i>
					</button>	
				</div>
			</div>
			
			<div class="row collection with-header">
				<h3>Ambientes</h3>
			
				<div class="input-field col s12 center-align">
					<button class="btn waves-effect waves-light materialize-red" type="reset" name="action">
						<a href="${urlListaAmbiente}" class="btn btn-red materialize-red">Lista de ambientes</a> 
						
						<i class="material-icons right">account_balance</i>
					</button>	
				</div>
			</div>
			
			<div class="row collection with-header">
				<h3>Patrimônios</h3>
				
				<div class="input-field col s12 center-align">
					<button class="btn waves-effect waves-light materialize-red" type="reset" name="action">
						<a href="${urlListaPatrimonio}" class="btn btn-red materialize-red">Lista de patrimônios</a> 
						
						<i class="material-icons right">desktop_windows</i>
					</button>	
				</div>
			</div>
		</c:if>
	</section>
	</main>
</body>
</html>