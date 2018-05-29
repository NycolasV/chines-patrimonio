<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:url value="/app/adm/patrimonio/novo" var="urlNovoPatrimonio" />
<c:url value="/app/adm/patrimonio/alterar" var="urlAlterarPatrimonio" />

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
	<h1>Lista de patrimônio</h1>
	
	<section style="text-align: center;">
		<c:if test="${usuarioLogado.tipoUsuario eq 'ADMINISTRADOR'}">
			<button class="btn waves-effect waves-light materialize-red" type="reset" name="action">
				<a href="${urlNovoPatrimonio}" class="btn btn-red materialize-red">Adicionar novo patrimônio</a> 
				
				<i class="material-icons right">desktop_windows</i>
			</button>	
		</c:if>
	</section>
	
	<section>
		<table class="responsive-table highlight centered">
			<thead>
				<tr>
					<th>ID</th>
					<th>Nome do patrimônio</th>
					<th>Categoria</th>
					<th>Data de cadastro</th>
				</tr>
			</thead>

			<tbody>
				<c:if test="${usuarioLogado.tipoUsuario eq 'COMUM'}">
					<c:forEach items="${patrimonios}" var="patrimonio">			
						<tr>
							<td>${patrimonio.id}</td>
							<td>${patrimonio.nome}</td>
							<td>${patrimonio.categoria.nome}</td>
							
							<td>
								<fmt:formatDate value="${patrimonio.dataPatrimonio}" pattern="dd/MM/yyyy HH:mm:ss" />
							</td>
						</tr>
					</c:forEach>
				</c:if>
							
				<c:if test="${usuarioLogado.tipoUsuario eq 'ADMINISTRADOR'}">
					<c:forEach items="${patrimonios}" var="patrimonio">
						<tr>
							<td>
								<a href="${urlAlterarPatrimonio}?id=${patrimonio.id}">
									${patrimonio.id} 
								</a>
							</td>
	
							<td>
								<a href="${urlAlterarPatrimonio}?id=${patrimonio.id}">
									${patrimonio.nome} 
								</a>
							</td>
	
							<td>
								<a href="${urlAlterarPatrimonio}?id=${patrimonio.id}">
									${patrimonio.categoria.nome} 
								</a>
							</td>
	
							<td>
								<a href="${urlAlterarPatrimonio}?id=${patrimonio.id}">
									<fmt:formatDate value="${patrimonio.dataPatrimonio}" pattern="dd/MM/yyyy HH:mm:ss" />
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