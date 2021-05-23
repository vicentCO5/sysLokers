<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<div class="row">
	<div class="col-xs-12">
		<div class="page-header">
			<h1>Forecasting General</h1>
		</div>
		<!-- /.page-header -->
		<div class="row">
			<div class="alert alert-info">
				<button class="btn btn-sm btn-primary" id="ejecutarpycgen">
					<i class="ace-icon glyphicon glyphicon-play-circle bigger-110"></i>
					Ejecutar calculos
				</button>
				<button class="btn btn-sm btn-success" id="searchpycsku">
					<i class="ace-icon glyphicon glyphicon-align-right bigger-110"></i>
					Proyección Compuesta [SKU]
				</button>
				<button class="btn btn-sm btn-success" id="searchpycagrupada">
					<i class="ace-icon glyphicon glyphicon-align-left bigger-110"></i>
					Proyección Compuesta [Agrupada]
				</button>		
			</div>
		</div>
	
		<div class="hr hr-8 hr-dotted"></div>
<!-- 		.col -->
			<div class="row" id="idcalculospyc"></div>
	</div>
	<!-- /.col -->
</div>
<!-- /.row -->
</html>
<script>

	$(function() {

		$("#ejecutarpycgen").click(function() {
			$.ajax({
				url : '${pageContext.request.contextPath}/startcalculos',
				dataType : 'html',
				success : function(data) {
					$("#idcalculospyc").html(data); 
				},
				error : function(data) {
					alert("Ocurrio un error");
				}

			});
		});
		
		
	});

	
</script>