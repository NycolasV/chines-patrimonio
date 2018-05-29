<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<c:url value="/app/adm/ambiente/novo" var="urlNovoAmbiente" />
<c:url value="/app/adm/ambiente/alterar" var="urlAlterarAmbiente" />

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
	<h1>Lista do ambiente</h1>
	
	<section style="text-align: center;">
		<c:if test="${usuarioLogado.tipoUsuario eq 'ADMINISTRADOR'}">
			<button class="btn waves-effect waves-light materialize-red" type="reset" name="action">
				<a href="${urlNovoAmbiente}" class="btn btn-red materialize-red">Adicionar novo ambiente</a> 
				
				<i class="material-icons right">account_balance</i>
			</button>	
		</c:if>
	</section>
	
	<section>
		<table class="responsive-table highlight centered">
			<thead>
				<tr>
					<th>ID</th>
					<th>Nome do ambiente</th>
				</tr>
			</thead>

			<tbody>
				<c:if test="${usuarioLogado.tipoUsuario eq 'COMUM'}">
					<c:forEach items="${ambientes}" var="ambiente">
						<tr>
							<td>${ambiente.id}</td>
							<td>${ambiente.nome}</td>
						</tr>
					</c:forEach>
				</c:if>

				<c:if test="${usuarioLogado.tipoUsuario eq 'ADMINISTRADOR'}">
					<c:forEach items="${ambientes}" var="ambiente">
						<tr>
							<td>
								<a href="${urlAlterarAmbiente}?id=${ambiente.id}">
									${ambiente.id}
								</a>			
							</td>
	
							<td>
								<a href="${urlAlterarAmbiente}?id=${ambiente.id}">
									${ambiente.nome} 
								</a>
							</td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
		</table>
	</section>
	</main>
</body>
</html>