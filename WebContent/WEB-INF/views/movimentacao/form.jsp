<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:url value="/assets/imagens" var="imagensRoot" />
<c:url value="/app/item/lista" var="urlNovaMovimentacao" />
<c:url value="/app/movimentacao/item" var="urlMovimentarItem" />

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
	<h1>Movimentação de itens</h1>

	<section class="row">
		<form:form modelAttribute="itemPatrimonio" action="${urlMovimentarItem}" method="post">
			<form:hidden path="id"/>
			
			<div class="input-field col s6">
				<div class="row">
					<form:input path="id" value="ID: ${itemPatrimonio.id}" disabled="true"/>
				</div>
				
				<div class="row">
					<input id="itemTipo" name="itemTipo" type="text" disabled="true"
						value="Tipo: ${itemPatrimonio.patrimonio.nome}">
				</div>
			</div>
			
			
			<div class="input-field col s6">
				<div class="row">
					<form:select path="ambienteAtual.id" items="${ambienteOrigem}" itemLabel="nome" itemValue="id" disabled="true"/>
				</div>
				
				<div class="row">
					<form:select path="ambienteAtual.id" items="${ambienteDestino}" itemLabel="nome" itemValue="id"/>
				</div>
			</div>

			<div class="row">
				<div class="input-field col s6">
					<button class="btn waves-effect waves-light materialize-red" type="submit" name="action">
						Registrar movimentação <i class="material-icons right">add_box</i>
					</button>
				</div>
			</div>
		</form:form>
	</section>
	
	<c:if test="${usuarioLogado.tipoUsuario eq 'ADMINISTRADOR'}">
		<section>
			<h2>Tabela do administrador</h2>
		
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
	</c:if>
	</main>
</body>
</html>