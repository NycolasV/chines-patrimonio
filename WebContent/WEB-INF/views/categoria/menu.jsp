<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<c:url value="/assets/imagens" var="imagensRoot" />
<c:url value="/app/adm/categoria" var="urEditarCategoria" />
<c:url value="/app/adm/categoria/salvar" var="urlSalvarCategoria" />
<c:url value="/app/adm/categoria/excluir" var="urlExcluirCategoria" />

<!DOCTYPE html>
<html>
<head>
	<c:import url="../templates/head.jsp"/>
</head>
<body>
	<header> 
		<c:import url="../templates/nav.jsp" />
	</header>
	
	<main>
	<h1>Categoria de patrimônios</h1>
	
	<section class="row">
		<div class="input-field col s6">	
			<form:form modelAttribute="categoria" action="${urlSalvarCategoria}" method="post">
				<form:hidden path="id" />
				
				<div class="row">
					<form:input path="nome" id="nome" class="validate" />
					<label for="nome">Nome da categoria</label>
		
					<form:errors path="nome" cssClass="error" />
				</div>
				
				<div class="row">
					<div class="input-field col s6">
						<button class="btn waves-effect waves-light materialize-red" type="submit" name="action">
							Salvar <i class="material-icons right">add_box</i>
						</button>
					</div>
				
					<c:if test="${empty categoria.id}">				
						<div class="input-field col s6">
							<button class="btn waves-effect waves-light materialize-red" type="reset" name="action">
								Limpar dados <i class="material-icons right">clear</i>
							</button>
						</div>
					</c:if>

					<c:if test="${not empty categoria.id}">
						<div class="input-field col s6">
							<button class="btn waves-effect waves-light materialize-red" type="reset" name="action">
								<a href="${urlExcluirCategoria}?id=${categoria.id}" class="btn btn-red materialize-red">
									Excluir</a> <i class="material-icons right">delete</i>
							</button>
						</div>
					</c:if>
				</div>
			</form:form>
		</div>
	
		<div class="input-field col s6">	
			<table class="responsive-table highlight centered">
				<thead>
					<tr>
						<th>ID</th>
						<th>Nome da categoria</th>
					</tr>
				</thead>
	
				<tbody>
					<c:forEach items="${categorias}" var="categoria">
						<tr>
							<td>
								<a href="${urEditarCategoria}?id=${categoria.id}">
									${categoria.id}
								</a>
							</td>
						
							<td>
								<a href="${urEditarCategoria}?id=${categoria.id}">
									${categoria.nome}
								</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</section>
	</main>
</body>
</html>