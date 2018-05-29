<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:url value="/assets/imagens" var="imagensRoot" />
<c:url value="/assets/css" var="cssRoot" />
<c:url value="/assets/js" var="jsRoot" />

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Chinês patrimônio</title>

<link rel="icon" type="image/ico" sizes="16x16" href="${imagensRoot}/icone-chines.ico">

<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">

<link rel="stylesheet" href="${cssRoot}/materialize.min.css" />
<link rel="stylesheet" href="${cssRoot}/ui.css" />

<script src="${jsRoot}/jquery-3.2.1.min.js"></script>
<script src="${jsRoot}/materialize.js"></script>

<script>

$(document).ready(function(){
	$("select").material_select();
})

document.addEventListener('DOMContentLoaded', function() {
	var elems = document.querySelectorAll('.sidenav');
	var instances = M.Sidenav.init(elems, options);
});

</script>
