<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:url value="/assets/imagens" var="imagensRoot" />
<c:url value="/" var="raizRoot" />
<c:url value="/app/sair" var="urlSair" />

<!DOCTYPE html>
<html>
<head>
	<c:import url="../templates/head.jsp"/>
</head>
<body>	
	<section>
		<h1 style="text-align: center; color: black; font-weight: bold; font-size: 40dp">
			Opa! ERROR 404 - Página não encontrada
		</h1>
		
		<div id="img-centro" style="text-align: center;">
			<img src="${imagensRoot}/gatinhoTriste.jpg" id="img-central" style="text-align: center;"/>
		</div>
		
		<div class="row">
			<div class="input-field col s6">
				<button class="btn waves-effect waves-light materialize-red" type="button" name="action">
					<a href="${raizRoot}" class="btn btn-red materialize-red">Retornar ao início</a> 
					
					<i class="material-icons left">arrow_back</i>
				</button>
			</div>
			
			<div class="input-field col s6 right-align">
				<c:if test="${usuarioLogado != null}">
					<button class="btn waves-effect waves-light materialize-red" type="button" name="action">
						<a href="${urlSair}" class="btn btn-red materialize-red">Sair da sessão</a> 
					
						<i class="material-icons right">close</i>
					</button>	
				</c:if>
			</div>
		</div>
	</section>
</body>
</html>