<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@page import="java.util.Map"%>
 <%@page import="java.util.HashMap"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
</head>
<body>
<c:if test="${accion=='false'}">
 	<h1> ${mensaje}</h1>
</c:if>
<c:if test="${accion=='true'}"> 	
	<input type="hidden" id="selectContador" value="${idtramite}">
 	<embed src="comprobaantePayUppdf?idtramite=${idtramite}" type="application/pdf" width="98%" height="500" href="comprobaantePayUppdf?idtramite=${idtramite}"></embed>
</c:if>
</body>
</html>