<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:url value="/assets/imagens" var="imagensRoot" />
<c:url value="/app/adm/patrimonio/alterar" var="urlAlterarPatrimonio" />
<c:url value="/app/adm/patrimonio/salvar" var="urlSalvarPatrimonio" />
<c:url value="/app/adm/patrimonio/excluir" var="urlExcluirPatrimonio" />

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
	<h1>Cadastro de patrimônios</h1>
	
	<section class="row">
		<div class="input-field col s6">
			<img alt="Logo do patrimônio" src="${imagensRoot}/logo-patrimonio.jpg" />
		</div>

		<div class="input-field col s6">
			<form:form modelAttribute="patrimonio" action="${urlSalvarPatrimonio}" method="post">
				<form:hidden path="id" />
		
				<div class="row">
					<form:input path="nome" id="nome" class="validate" />
					<label for="nome">Nome do patrimonio</label>
		
					<form:errors path="nome" cssClass="error" />
				</div>
				
				<div class="row">
					<form:select path="categoria.id" items="${categorias}" itemLabel="nome" itemValue="id"/>
				</div>
				
				<div class="row">
					<div class="input-field col s6">
						<button class="btn waves-effect waves-light materialize-red" type="submit" name="action">
							Salvar <i class="material-icons right">add_box</i>
						</button>
					</div>
				
					<c:if test="${empty patrimonio.id}">				
						<div class="input-field col s6">
							<button class="btn waves-effect waves-light materialize-red" type="reset" name="action">
								Limpar dados <i class="material-icons right">clear</i>
							</button>
						</div>
					</c:if>

					<c:if test="${not empty patrimonio.id}">
						<div class="input-field col s6">
							<button class="btn waves-effect waves-light materialize-red" type="reset" name="action">
								<a href="${urlExcluirPatrimonio}?id=${patrimonio.id}" class="btn btn-red materialize-red">
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
					<th>Nome do patrimônio</th>
					<th>Categoria</th>
					<th>Data de cadastro</th>
				</tr>
			</thead>

			<tbody>
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
								<fmt:formatDate value="${patrimonio.dataPatrimonio}" pattern="dd/MM/yyyy HH:mm:ss"/>
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