<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:url value="/assets/imagens" var="imagensRoot" />
<c:url value="/app/movimentacao/item" var="urlMovimentarItem" />
<c:url value="/app/item-patrimonio" var="urlListaItemPatrimonio" />

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
		<h1>Tabela de movimentações</h1>
	
		<section style="text-align: center;">
			<button class="btn waves-effect waves-light materialize-red" type="reset" name="action">
				<a href="${urlListaItemPatrimonio}" class="btn btn-red materialize-red">Registrar nova movimentação</a> 
				
				<i class="material-icons right">near_me</i>
			</button>	
		</section>
	
		<section>
			<table class="responsive-table highlight centered">
				<thead>
					<tr>
						<th>ID da movimentação</th>
						<th>ID do item movimentado</th>
						<th>Tipo de patrimônio</th>
						<th>Ambiente de origem</th>
						<th>Ambiente de destino</th>
						<th>Data de movimentação</th>
						<th>Emissor</th>
					</tr>
				</thead>
		
				<tbody>
					<c:forEach items="${movimentacoes}" var="movimentacao">
						<tr>
							<td>${movimentacao.id}</td>			
							<td>${movimentacao.item.id}</td>
							<td>${movimentacao.item.patrimonio.nome}</td>
							<td>${movimentacao.ambienteOrigem.nome}</td>
							<td>${movimentacao.ambienteDestino.nome}</td>
							
							<td>
								<fmt:formatDate value="${movimentacao.dataMovimentacao}" pattern="dd/MM/yyyy HH:mm:ss"/>
							</td>
							
							<td>${movimentacao.usuario.nome}</td>
					</c:forEach>
				</tbody>
			</table>
		</section>
	</main>
</body>
</html>