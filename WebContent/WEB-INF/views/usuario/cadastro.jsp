<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<c:url value="/app/adm/usuario/salvar" var="urlSalvarUsuario" />
<c:url value="/app/adm/usuario/excluir" var="urlExcluirUsuario" />

<!DOCTYPE html>
<html>
<head>
	<c:import url="../templates/head.jsp" />
</head>
<body>
	<header>
		<c:import url="../templates/nav.jsp" />
	</header>

	<main class="row">
	<h1>Cadastro de usuários</h1>

	<form:form modelAttribute="usuario" action="${urlSalvarUsuario}" method="post">
		<form:hidden path="id" />

		<div class="row">
			<div class="input-field col s6">
				<form:input path="nome" id="nome" class="validate" />
				<label for="nome">Nome</label>

				<form:errors path="nome" cssClass="error" />
			</div>

			<div class="input-field col s6">
				<form:input path="sobrenome" id="sobrenome" class="validate" />
				<label for="sobrenome">Sobrenome</label>

				<form:errors path="sobrenome" cssClass="error" />
			</div>
		</div>

		<c:if test="${empty usuario.id}">
			<div class="row">
				<div class="input-field col s12">
					<form:input path="email" id="email" class="validate" />
					<label for="email">E-mail</label>

					<form:errors path="email" cssClass="error" />
				</div>
			</div>

			<div class="row">
				<div class="input-field col s6">
					<form:password path="senha" id="senha" class="validate" />
					<label for="senha">Senha</label>

					<form:errors path="senha" cssClass="error" />
				</div>

				<div class="input-field col s6">
					<input id="confirmacaoSenha" name="confirmacaoSenha" type="password" class="validate">
					<label for="confirmacaoSenha">Confirmar senha</label>
				</div>
			</div>
		</c:if>
		
     	<ul class="collection with-header">
        	<li class="collection-header">
				<label for="inputFoto">Foto de Perfil</label>
				<br><br>
				
				<input type="file" name="foto action" class="btn waves-effect waves-light materialize-red"/>
			</li>
		</ul>
 
 		<div class="row switch" style="margin-left: 2%">
    		<label>Administrador</label><br>
    		
			<c:if test="${not empty usuario.id && usuario.tipoUsuario eq 'ADMINISTRADOR'}">
	    		<label> 
	    		Off
	      			<input type="checkbox" name="administrador" checked="checked"/>
	      			<span class="lever"></span>
	      		On 
	      		</label>
      		</c:if>
      		
      		<c:if test="${empty usuario.id || usuario.tipoUsuario eq 'COMUM'}">
      			<label> 
	    		Off
	      			<input type="checkbox" name="administrador"/>
	      			<span class="lever"></span>
	      		On 
	      		</label>
	      	</c:if>
	  	</div>
 		
		<div class="row">
			<div class="input-field col s6">
				<button class="btn waves-effect waves-light materialize-red" type="submit" name="action">
					Salvar <i class="material-icons right">add_box</i>
				</button>
			</div>
			
			<c:if test="${empty usuario.id}">				
				<div class="input-field col s6">
					<button class="btn waves-effect waves-light materialize-red" type="reset" name="action">
						Limpar dados <i class="material-icons right">clear</i>
					</button>
				</div>
			</c:if>

			<c:if test="${not empty usuario.id}">
				<div class="input-field col s6">
					<button class="btn waves-effect waves-light materialize-red" name="action">
						<a href="${urlExcluirUsuario}?id=${usuario.id}" class="btn btn-red materialize-red">Excluir</a>
					
						<i class="material-icons right">delete</i>
					</button> 
				</div>
			</c:if>
		</div>
	</form:form> 
	</main>
</body>
</html>