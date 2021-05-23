<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<div class="col-12" >
	<div class="card">
	 	<div class="card-body"  >
	 		
			<h4 class="card-title">Mostradores</h4>
			<h6 class="card-subtitle">Lista de taquillas guarda equipaje</h6>
			<div class="row">
				<div class="col-md-12">
					<div class="alert alert-info">
						<button class="btn waves-effect waves-light btn-rounded btn-success" id="newAlmacen">
							<i class="fa fa-plus"></i>
							Nuevo
						</button>
<!-- 						importAlm -->						
						<button class="btn waves-effect waves-light btn-rounded btn-success" id="searchAlms">
							<i class="fa fa-rotate-right"></i>
							Actualizar
						</button>
					</div>
				</div>
				
			</div>
			<div class="table-responsive m-t-10" >
				<table class="display nowrap table table-hover table-striped table-bordered" id="almacen-table">
					<thead>
						<tr>
						    <th class="center">Empresa</th>							
							<th class="center">Clave</th>
							<th class="center">Nombre</th>
							<th class="center">CP</th>
							<th class="center">Dirección</th>							
							<th class="center">Pais</th>
							<th class="center">Estatus</th>
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

</html>
<script>
var tablealmacenes;
	$(function() {
		// cargar lista de almacenes 
		listaAlmacenes();
		
		$("#newAlmacen").on("click",function(){

			$("#modal-body").html("");
			$("#modal-header").html("<h4>Nuevo Mostrador</h4>");
			$('#modalaps').modal('show');
			
			 $.ajax({             	
				 url:'${pageContext.request.contextPath}/newalmacen',
					type: "post",
					dataType : 'html',
				   			
			       }).done(function(data, textStatus, jqXHR  ) {
			           if ( console && console.log ) 
			           {			             			  
			            	  $("#modal-body").html(data);			            	
			           }
			       }).fail(function( jqXHR, textStatus, errorThrown ) {
		               if ( console && console.log ) {
		                  // function de diferentes errores index
		                  errorsAjax(jqXHR, textStatus);		                    
		               }
			        }); // end .ajax			        
		});
		
		
		$("#importAlm").click(function() {

			$.ajax({
				url : '${pageContext.request.contextPath}/getimportalms',
				dataType : 'html',
				success : function(data) {
					$("#modal-body").html(data);
					$("#modal-header").html("<h4>Importar Mostrador</h4>");
					$('#modalaps').modal('show'); 
				},
				error : function(data) {
					alert("Ocurrio un error");
				}

			});
		});
		
		$( "#searchAlms" ).click(function() {
		     listaAlmacenes();
	    });
		
	});
	function listaAlmacenes(){
		tablealmacenes = $('#almacen-table').DataTable({
	 		"ajax":"${pageContext.request.contextPath}/showalms",
	 		"destroy": true,
	 	    paging: false,

	 	    "scrollY": "380px",
	         "scrollCollapse": true,
	         "paging": false,
	 	    info:true,
	 	    "columns":[
	 	    	        {"data":"empresa_id"},
	 				    {"data":"clave"},				
	 					{"data":"nombre"}, 						  			       	
	 					{"data":"codigoPostal"},
	 					{"data":"direccion"},
	 		       		{"data":"pais"},
	 		       	    {"data":"estatus"},
	 		       		{"data":"accion"}
	 		       	],		 	   
	 	   				    
	 	    });
	}
	function editalmacen(id){	
		console.log(id);
		$.ajax({
			url:'${pageContext.request.contextPath}/editalmacen',
			type: "post",
			dataType : 'html',
			data:{id:id},
			success : function(data) {
				$("#modal-body").html(data);
				$("#modal-header").html("<h4>Editar Mostrador</h4>");
				$('#modalaps').modal('show'); 
				},
			});
		}
	function deletealmacen(id){	
		console.log(id);
		$.ajax({
			url:'${pageContext.request.contextPath}/deletealmacen',
			type: "post",
			dataType : 'html',
			data:{id:id},
			}).done(function(data){		
				alert(data);
				tablealmacenes.ajax.reload( null, false );	 							
		    });
		}
	function editalmacen(id){
		msg_ok("sin accion");
	}
</script>