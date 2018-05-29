<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<c:url value="/assets/imagens" var="imagensRoot" />
<c:url value="/app/adm/usuario/cadastro" var="urlNovoUsuario"/>
<c:url value="/app/adm/usuario/alterar" var="urlAlterarUsuario"/>

<!DOCTYPE html>
<html>
<head>
<c:import url="../templates/head.jsp" />
</head>
<body>
	<header>
		<c:import url="../templates/nav.jsp" />
	</header>

	<main>
	<h1>Lista de usuários</h1>
	
	<section style="text-align: center;">
		<button class="btn waves-effect waves-light materialize-red" type="reset" name="action">
			<a href="${urlNovoUsuario}" class="btn btn-red materialize-red">Adicionar novo usuário</a> 
			
			<i class="material-icons right">person_add</i>
		</button>	
	</section>
	
	<section>
		<div class="row">
			<div class="col s12 m7">
				<c:forEach items="${usuarios}" var="usuario">
					<div class="card">
						<c:if test="${usuario.email != 'admin@teste.com'}">
							<div class="input-field col s6">
								<div class="card-image">
									<c:if test="${not empty usuario.caminhoFoto}">
										<img alt="Foto do usuário" src="${usuario.caminhoFoto}">
									</c:if>
	
									<c:if test="${empty usuario.caminhoFoto}">
										<img alt="Foto do usuário nula" src="${imagensRoot}/imagem-usuario.png" 
											style="width: 30%; height: 30%"/>
									</c:if>
								</div>
							</div>
							
							<div class="input-field col s6">
								<div class="card-content">
									<p>Nome: ${usuario.nome} ${usuario.sobrenome}</p>
									<p>Email: ${usuario.email}</p>
									
									<c:if test="${usuario.tipoUsuario eq 'ADMINISTRADOR'}">
										<p>Usuário administrador</p>	
									</c:if>
									<c:if test="${usuario.tipoUsuario eq 'COMUM'}">
										<p>Usuário comum</p>	
									</c:if>
								</div>
								
								<div class="card-action">
									<a href="${urlAlterarUsuario}?id=${usuario.id}">Alterar usuário</a>
								</div>
							</div>
						</c:if>
					</div>
				</c:forEach>
			</div>
		</div>
	</section>
	</main>
</body>
</html>