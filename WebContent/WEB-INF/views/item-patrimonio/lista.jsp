<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:url value="/app/item-patrimonio/novo" var="urlNovoItemPatrimonio" />
<c:url value="/app/item-patrimonio/alterar" var="urlAlterarItemPatrimonio" />

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
	<h1>Lista dos itens do patrimônio</h1>
	
	<section style="text-align: center;">
		<button class="btn waves-effect waves-light materialize-red" type="reset" name="action">
			<a href="${urlNovoItemPatrimonio}" class="btn btn-red materialize-red">Adicionar novo item</a> 
			
			<i class="material-icons right">devices</i>
		</button>	
	</section>
	
	<section>
		<table class="responsive-table highlight centered">
			<thead>
				<tr>
					<th>ID</th>
					<th>Tipo de patrimônio</th>
					<th>Ambiente atual</th>
					<th>Data de cadastro</th>
					<th>Emissor</th>
				</tr>
			</thead>
	
			<tbody>
				<c:forEach items="${itemPatrimonios}" var="itemPatrimonio">
					<tr>
						<td>
							<a href="${urlAlterarItemPatrimonio}?id=${itemPatrimonio.id}">
								${itemPatrimonio.id}
							</a>
						</td>			
						
						<td>
							<a href="${urlAlterarItemPatrimonio}?id=${itemPatrimonio.id}">
								${itemPatrimonio.patrimonio.nome}
							</a>
						</td>
							
						<td>
							<a href="${urlAlterarItemPatrimonio}?id=${itemPatrimonio.id}">
								${itemPatrimonio.ambienteAtual.nome}
							</a>
						</td>
							
						<td>
							<a href="${urlAlterarItemPatrimonio}?id=${itemPatrimonio.id}">
								<fmt:formatDate value="${itemPatrimonio.dataAtualizacao}" pattern="dd/MM/yyyy HH:mm:ss"/>
							</a>
						</td>
							
						<td>
							<a href="${urlAlterarItemPatrimonio}?id=${itemPatrimonio.id}">
								${itemPatrimonio.usuario.nome}
							</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</section>
	</main>
</body>
</html>