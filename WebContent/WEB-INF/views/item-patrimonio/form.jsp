<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:url value="/assets/imagens" var="imagensRoot" />
<c:url value="/app/item-patrimonio/alterar" var="urlAlterarItemPatrimonio" />
<c:url value="/app/item-patrimonio/salvar" var="urlSalvarItemPatrimonio" />
<c:url value="/app/adm/item-patrimonio/excluir" var="urlExcluirItemPatrimonio" />
<c:url value="/app/movimentacao/novo" var="urlMovimentarItemPatrimonio" />

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
	<h1>Categoria dos itens do patrimônio</h1>

	<section class="row">
		<form:form modelAttribute="itemPatrimonio" action="${urlSalvarItemPatrimonio}" method="post">
			<form:hidden path="id"/>
		
			<div class="row">	
				<div class="input-field col s6">
					<form:select path="patrimonio.id" items="${patrimonios}" itemLabel="nome" itemValue="id"/>
				</div>
				
				<c:if test="${empty itemPatrimonio.id}">
					<div class="input-field col s6">
						<form:select path="ambienteAtual.id" items="${ambientes}" itemLabel="nome" itemValue="id"/>
					</div>
				</c:if>
				
				<c:if test="${not empty itemPatrimonio.id}">
					<div class="input-field col s6">
						<form:select path="ambienteAtual.id" items="${ambientes}" itemLabel="nome" itemValue="id" disabled="true"/>
					</div>
				</c:if>
			</div>

			<div class="row">
				<div class="input-field col s6">
					<button class="btn waves-effect waves-light materialize-red" type="submit" name="action">
						Salvar <i class="material-icons right">add_box</i>
					</button>
				</div>
			
				<c:if test="${empty itemPatrimonio.id}">
					<div class="input-field col s6">
						<button class="btn waves-effect waves-light materialize-red" type="reset" name="action">
							Limpar dados <i class="material-icons right">clear</i>
						</button>
					</div>
				</c:if>
				
				<c:if test="${not empty itemPatrimonio.id}">
					<div class="input-field col s6 right-align">
						<button class="btn waves-effect waves-light materialize-red" type="button" name="action">
							<a href="${urlExcluirItemPatrimonio}?id=${itemPatrimonio.id}" class="btn btn-red materialize-red">
								Excluir</a> <i class="material-icons right">delete</i>
						</button>
					</div>
				
					<div class="input-field col s12 center-align">
						<button class="btn waves-effect waves-light materialize-red" type="button" name="action">
							<a href="${urlMovimentarItemPatrimonio}?id=${itemPatrimonio.id}" class="btn btn-red materialize-red">
								Registrar movimentação</a> <i class="material-icons right">place</i>
						</button>
					</div>
				</c:if>
			</div>
		</form:form>
	</section>
	
	<c:if test="${usuarioLogado.tipoUsuario eq 'ADMINISTRADOR'}">
		<section>
			<h2>Tabela do administrador</h2>
		
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
	</c:if>
	</main>
</body>
</html>