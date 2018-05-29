<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<c:url value="/assets/imagens" var="imagensRoot" />
<c:url value="/app/" var="urlIndexApp" />
<c:url value="/app/sair" var="urlSair" />
<c:url value="/app/usuario/perfil" var="urlPerfil" />
<c:url value="/app/ambiente" var="urlListaAmbiente" />
<c:url value="/app/patrimonio" var="urlListaPatrimonio" />
<c:url value="/app/item-patrimonio" var="urlListaItemPatrimonio" />
<c:url value="/app/movimentacao-feita" var="urlListaMovimentacao" />
<c:url value="/app/adm/categoria" var="urlMenuCategoria" />
<c:url value="/app/adm/usuario" var="urlListaUsuario" />

<nav class="materialize-red">
	<div class="nav-wrapper" style="margin-left: 2%; margin-right: 2%">
    	<a href="${urlIndexApp}" class="brand-logo">Chinês patrimônio</a>

    	<ul id="nav-mobile" class="right hide-on-med-and-down">
			<li><a href="${urlIndexApp}">Página inicial</a></li>
			<li><a href="${urlListaAmbiente}">Ambiente</a></li>
			<li><a href="${urlListaPatrimonio}">Patrimônios</a></li>
			<li><a href="${urlListaItemPatrimonio}">Itens</a></li>
			<li><a href="${urlListaMovimentacao}">Movimentações</a></li>
			
			<c:if test="${usuarioLogado.tipoUsuario eq 'ADMINISTRADOR'}">
				<li><a href="${urlMenuCategoria}">Categorias</a></li>
				<li><a href="${urlListaUsuario}">Usuários</a></li>
			</c:if>
<!-- 			
			<c:if test="${usuarioLogado.email != 'admin@teste.com'}">	
	
				<li><a class="dropdown-trigger" href="#!" data-target="dropdown1">Perfil
					<i class="material-icons right">arrow_drop_down</i>
				</a></li>
				
				<ul id="dropdown1" class="dropdown-content">
					<li><a href="${urlPerfil}">Ver perfil</a></li>
				  	<li class="divider"></li>
					<li><a href="${urlAlterarPerfil}">Alterar perfil</a></li>
				</ul>

				 
				<li><a href="${urlPerfil}?id=${usuarioLogado.id}">Ver perfil</a></li>
			</c:if>
-->			
			<li><a href="${urlSair}">Sair</a></li>
		</ul>
	</div>
</nav>