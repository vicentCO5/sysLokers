<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="utf-8">
</head>
<body>

<div class="col-lg-12" >
	<div class="card">
	 	<div class="card-body" >
	 	    <h4 class="card-title">Cliente</h4>
			<h6 class="card-subtitle">Lista de clientes registrados</h6>
			<div class="row">
				<div class="col-md-12">
					<div class="alert alert-info">
						<button class="btn waves-effect waves-light btn-rounded btn-success" id="newCliente">
							<i class="fa fa-plus"></i>
							Nuevo
						</button>
						<button class="btn waves-effect waves-light btn-rounded btn-success" id="refreshCliente">
							<i class="fa fa-rotate-right"></i>
							Actualizar
						</button>
					</div>
				</div>
				
			</div>
			<div class="table-responsive m-t-10" >
				<table class="display nowrap table table-hover table-striped table-bordered" id="table-cliente">
					<thead>
						<tr>						   											
							<th class="center">#</th>
							<th class="center">Clave</th>
							<th class="center">Nombre</th>							
							<th class="center">Direccion</th>
							<th class="center">Colonia</th>
							<th class="center">Telefono</th>
							<th class="center">E-mail</th>							
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
</body>
<script type="text/javascript">
var tableCliente ="";
$(function(){
	// recargar datos
	listaClientesdb();
	// actualizar lista 
	$("#refreshCliente").click(function(){
		// recargar datos
		listaClientesdb();
	});
	/*agregar un cliente */
	  $("#newCliente").click(function(){
      	$('#modalaps').modal('show');
      	$("#modal-header").html("");
  		$("#modal-body-detalle").html("");	
  		
      	$.ajax({             	
 	   		 url:'${pageContext.request.contextPath}/formClientes',
 	   			type: "post",			
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
})
function listaClientesdb(){
	 tablefolios = $('#table-cliente').DataTable({
	 		"ajax":"${pageContext.request.contextPath}/dataClientes",
	 		"destroy": true,
	 	    paging: false,
// 	 	    "scrollX": "300px",
	 	    "scrollY": "380px",
	         "scrollCollapse": true,
	         "paging": false,
	 	    info:true,
	 	    "columns":[ 
	 	    	        {"data":"indice"},
	 	    	        {"data":"codigo"},
	 				    {"data":"nombrecompleto"},				
	 					{"data":"direccion"}, 						  			       	
	 					{"data":"colonia"},
	 					{"data":"telefono"},
	 		       		{"data":"email"},
	 		       		{"data":"acciones"}
	 		       	],		 	   
	 	   				    
	 	    });
}
function deleteCliente(idx){
	 swal({   
         title: "Confirme !",   
         text: "Para eliminar",   
         type: "warning",   
         showCancelButton: true,   
         confirmButtonColor: "#DD6B55",   
         confirmButtonText: "Aceptar",   
         closeOnConfirm: true 
     }, function(){ 
    	 $.ajax({             	
    		 url:'${pageContext.request.contextPath}/deleteClientes',
    			type: "post",    			
    			data:{idx:idx},
    	       }).done(function(data, textStatus, jqXHR  ) {	    	                		  
    	        	msg_ok(data)	 
    	        	tableCliente.ajax.reload( null, false );	
    	       }).fail(function( jqXHR, textStatus, errorThrown ) {
                   if ( console && console.log ) {
                      // function de diferentes errores index
                      errorsAjax(jqXHR, textStatus);		                    
                   }
    	        }); // end .ajax
     });
}
</script>
</html>