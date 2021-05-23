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
<h1>imprimiento boleto ..! ${idtramite}</h1>
<input type="hidden" id="selectContador" value="${idtramite}">
 <embed src="forecastpdf?idtramite=${idtramite}" type="application/pdf" width="80%" height="500" href="forecastpdf?idtramite=${idtramite}"></embed>";
</body>
</html>