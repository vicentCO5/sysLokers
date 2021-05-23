<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<div class="row">
	<div class="col-xs-12">
		<div class="page-header">
			<h1>Marcas</h1>
		</div>
		<!-- /.page-header -->
		<div class="row">
			<div class="alert alert-info">
				<button class="btn btn-sm btn-primary" id="importMarcas">
					<i class="ace-icon glyphicon glyphicon-upload bigger-110"></i>
					Importar Catalogo de Marcas
				</button>
				<button class="btn btn-sm btn-primary" id="exportMarcas">
					<i class="ace-icon glyphicon glyphicon-download bigger-110"></i>
					Exportar
				</button>
				<button class="btn btn-sm btn-success" id="searchMarcas">
					<i class="ace-icon glyphicon glyphicon-search bigger-110"></i>
					Consultar
				</button>
				<button class="btn btn-sm btn-danger" id="deletesMarcas" onclick="deleteall()">
					<i class="ace-icon glyphicon glyphicon-trash bigger-110"></i>
					Eliminar
				</button>
				
			</div>
		</div>
	
<!-- 		<div class="hr hr-8 hr-dotted"></div> -->
		<!-- /.col -->
			<div class="row">
				<div class="col-xs-12" id="listamarcas"></div>
				<div class="col-sm-12">
				<div class="clearfix">
					<div class="pull-right tableTools-container"></div>
				</div>
				
				<div class="table-header">
					Lista de Marcas &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</div>
				<div>
					<table class="table  table-bordered table-hover" id="marcas-table">
						<thead>
							<tr>
								
								<th class="center">Empresa</th>
								<th class="center">Clave</th>
								<th class="center">Descripci&oacute;n</th>
								<th class="center">Clasificaci&oacute;n</th>
								<th class="center">Acciones</th>
							</tr>
						</thead>
						<tbody>
							
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<!-- /.col -->
</div>
<!-- /.row -->
</html>
<script>
var tablemarcas;
	$(function() {		
		$("#importMarcas").click(function() {

			$.ajax({
				url : '${pageContext.request.contextPath}/getimportmarcas',
				dataType : 'html',
				success : function(data) {
					$("#modal-body").html(data);
					$("#modal-header").html("<h4>Importar Marcas</h4>");
					$('#modalaps').modal('show'); 
				},
				error : function(data) {
					alert("Ocurrio un error");
				}

			});
		});
		
		$( "#searchMarcas" ).click(function() {
			tablemarcas = $('#marcas-table').DataTable({
		 		"ajax":"${pageContext.request.contextPath}/showmarcas",
		 		"destroy": true,
		 	    paging: false,
		 	    searching: true,
		 	    "ordering": false,
		 	    "scrollY": "380px",
		 	    "pageLength": '100',
		         "scrollCollapse": true,
		         "paging": true,
		 	    info:true,
		 	    "columns":[		
		 				    {"data":"empresa"},				
		 					{"data":"clave"}, 						  			       	
		 		       		{"data":"descripcion"},
		 		       		{"data":"clasificacion"},
		 		       		{"data":"accion"}
		 		       	],		 	   
		 	   				    
		 	    });
			});
		
	});
// 	function editalmacen(id){	
// 		console.log(id);
// 		$.ajax({
// 			url:'${pageContext.request.contextPath}/editalmacen',
// 			type: "post",
// 			dataType : 'html',
// 			data:{id:id},
// 			success : function(data) {
// 				$("#modal-body").html(data);
// 				$("#modal-header").html("<h4>Editar Almacen</h4>");
// 				$('#modalaps').modal('show'); 
// 				},
// 			});
// 		}
	function deletemarca(id){
		$.ajax({
			url:'${pageContext.request.contextPath}/deletemarca',
			type: "post",
			dataType : 'html',
			data:{id:id},
			}).done(function(data){		
				alert(data);
				tablemarcas.ajax.reload( null, false );	 							
		    });
		}
	function deleteall(){
		if(!confirm("Desea eliminar todas las marcas?"))
			return 
		$.ajax({
			url:'${pageContext.request.contextPath}/deleteall',
			type: "post",
			dataType : 'JSON',
			data:{},
			}).done(function(data){		
				if(data.exito=="true"){
					alert(data.mensaje);
					tablemarcas.ajax.reload( null, false );	
				}else{					
	 	 				for( i=0;i<data.error.length;i++){
	 	 					alert(data.error[i]);
	 	 	 				}
				}
		    });
		
	}
	
</script>