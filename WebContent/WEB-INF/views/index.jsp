<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<c:url value="/assets/imagens" var="imagensRoot" />
<c:url value="/usuario/logar" var="urlLogarUsuario" />

<!DOCTYPE html>
<html>
<head>
<c:import url="templates/head.jsp" />
</head>
<body>
	<section style="text-align: center;">
		<h1>Bem-vindo ao Chinês Patrimônio!</h1>

		<img alt="Logo do Chinês Patrimônio" src="${imagensRoot}/logo-chines.png" style="text-align: center;"/>
	</section>

	<section>
		<form:form modelAttribute="usuario" action="${urlLogarUsuario}" method="post">
			<div class="row">
				<div class="input-field col s6">
					<form:input path="email" /> 
					<label for="email">E-mail</label>
					
					<form:errors path="email" cssClass="error" />
				</div>

				<div class="input-field col s6">
					<form:password path="senha" id="senha" class="validate" />
					<label for="senha">Senha</label>

					<form:errors path="senha" cssClass="error" />
				</div>
			</div>
			
			<button class="btn waves-effect waves-light materialize-red" name="action" style="text-align: center;">
				Login
				<i class="material-icons right">power_settings_new</i> 
			</button>
		</form:form>
	</section>
</body>
</html>