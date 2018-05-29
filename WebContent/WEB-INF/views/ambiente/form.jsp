<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<c:url value="/assets/imagens" var="imagensRoot" />
<c:url value="/app/adm/ambiente/alterar" var="urlAlterarAmbiente" />
<c:url value="/app/adm/ambiente/salvar" var="urlSalvarAmbiente" />
<c:url value="/app/adm/ambiente/excluir" var="urlExcluirAmbiente" />

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
	<h1>Cadastro de ambientes</h1>

	<section class="row">
		<div class="input-field col s6">
			<img alt="Logo do ambiente" src="${imagensRoot}/logo-ambiente.jpg" />
		</div>

		<div class="input-field col s6">
			<form:form modelAttribute="ambiente" action="${urlSalvarAmbiente}" method="post">
				<form:hidden path="id" />

				<div class="row">
					<form:input path="nome" id="nome" class="validate" />
					<label for="nome">Nome do ambiente</label>

					<form:errors path="nome" cssClass="error" />
				</div>

				<div class="row">
					<div class="input-field col s6">
						<button class="btn waves-effect waves-light materialize-red" type="submit" name="action">
							Salvar <i class="material-icons right">add_box</i>
						</button>
					</div>
				
					<c:if test="${empty ambiente.id}">				
						<div class="input-field col s6">
							<button class="btn waves-effect waves-light materialize-red" type="reset" name="action">
								Limpar dados <i class="material-icons right">clear</i>
							</button>
						</div>
					</c:if>

					<c:if test="${not empty ambiente.id}">
						<div class="input-field col s6">
							<button class="btn waves-effect waves-light materialize-red" type="reset" name="action">
								<a href="${urlExcluirAmbiente}?id=${ambiente.id}" class="btn btn-red materialize-red">
									Excluir</a> <i class="material-icons right">delete</i>
							</button>
						</div>
					</c:if>
				</div>
			</form:form>
		</div>
	</section>

	<section>
		<h2>Tabela do administrador</h2>

		<table class="responsive-table highlight centered">
			<thead>
				<tr>
					<th>ID</th>
					<th>Nome do ambiente</th>
				</tr>
			</thead>

			<tbody>
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
			</tbody>
		</table>
	</section>
	</main>
</body>
</html>