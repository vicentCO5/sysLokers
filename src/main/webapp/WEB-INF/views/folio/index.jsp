<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="ISO-8859-1">

</head>
<body>
<div class="col-lg-12" >
	<div class="card">
	 	<div class="card-body" >
	 	    <h4 class="card-title">Folios</h4>
			<h6 class="card-subtitle">Lista de folios ingresados</h6>
			<div class="row">
				<div class="col-md-12">
					<div class="alert alert-info">
						<button class="btn waves-effect waves-light btn-rounded btn-success" id="newFolio">
							<i class="fa fa-plus"></i>
							Nuevo
						</button>
						<button class="btn waves-effect waves-light btn-rounded btn-success" id="searchFolio">
							<i class="fa fa-rotate-right"></i>
							Actualizar
						</button>
					</div>
				</div>
				
			</div>
			<div class="table-responsive m-t-10" >
				<table class="display nowrap table table-hover table-striped table-bordered" id="folio-table">
					<thead>
						<tr>						   											
							<th class="center">Almacen</th>
							<th class="center">Serie</th>
							<th class="center">Folio</th>							
							<th class="center">Folio inicial</th>
							<th class="center">Documento</th>
							<th class="center">Fecha Creacion
							<th class="center">Fecha Modificacion</th>
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

</body>
<script type="text/javascript">
$(function(){
	var tablefolios = "";
	/**lista de folios */
	listaFolios();
	
	$("#newFolio").click(function(){
		$("#modal-body").html("");
		$("#modal-header").html("<h4>Nuevo Folio</h4>");
		$('#modalaps').modal('show');
		
		 $.ajax({             	
			 url:'${pageContext.request.contextPath}/newFolio',
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
	/* actualizar lista de folios */
	$("#searchFolio").click(function(){
		listaFolios();
	});
	/* cambiar de estatus */
	$(document).on("click", ".changedestatus", function(){
		var idchanged =  $(this).data("idchaged");
		var estatus = $(this).prop('checked');
		console.log(idchanged + "---"+ estatus );
		if(estatus == true){
			estatus = 0;
		}else{
			estatus = 1;
		}
		 swal({   
	         title: "Confirme !",   
	         text: "Confirme para modificar estatus",   
	         type: "warning",   
	         showCancelButton: true,   
	         confirmButtonColor: "#DD6B55",   
	         confirmButtonText: "Aceptar",   
	         closeOnConfirm: true 
	     }, function(){ 
	    	 $.ajax({             	
	    		 url:'${pageContext.request.contextPath}/changedEstatus',
	    			type: "post",			
	    			data:{idchanged:idchanged,estatus:estatus },
	    	       }).done(function(data, textStatus, jqXHR  ) {	    	                		  
	    	        	msg_ok(data)	    	        		             		    	           
	    	       }).fail(function( jqXHR, textStatus, errorThrown ) {
	                   if ( console && console.log ) {
	                      // function de diferentes errores index
	                      errorsAjax(jqXHR, textStatus);		                    
	                   }
	    	        }); // end .ajax
	     });
	});
	
});
 function listaFolios(){
	        
	  tablefolios = $('#folio-table').DataTable({
	 		"ajax":"${pageContext.request.contextPath}/listFolios",
	 		"destroy": true,
	 	    paging: false,
	 	    "scrollX": "300px",
	 	    "scrollY": "380px",
	         "scrollCollapse": true,
	         "paging": false,
	 	    info:true,
	 	    "columns":[ 
	 	    	        {"data":"nombreAlmacen"},
	 	    	        {"data":"serie"},
	 				    {"data":"folio"},				
	 					{"data":"folioInicial"}, 						  			       	
	 					{"data":"documento"},
	 					{"data":"fechaCreacion"},
	 		       		{"data":"fechaModificacion"},
	 		       	    {"data":"estatus"},
	 		       		{"data":"accion"}
	 		       	],		 	   
	 	   				    
	 	    });
 }
 function deleteFolio(idFolio){
	 // nuevo alert 
	 
	 swal({   
         title: "Confirme !",   
         text: "Confirme para eliminar",   
         type: "warning",   
         showCancelButton: true,   
         confirmButtonColor: "#DD6B55",   
         confirmButtonText: "Aceptar",   
         closeOnConfirm: false 
     }, function(){   
 
         swal({   
             title: "",   
             text: "Procesado ..!", 
             type: "success",  
             timer: 1200,   
             showConfirmButton: true 
         });
         
         $.ajax({             	
    		 url:'${pageContext.request.contextPath}/deleteFolio',
    			type: "post",			
    			data:{idfolio:idFolio},
    	       }).done(function(data, textStatus, jqXHR  ) {
    	           if ( console && console.log ) 
    	           {			             			  
    	        	   if(data.exito=="true"){	        		  
    	        		   msg_ok(data.error)
    	        		   tablefolios.ajax.reload( null, false );			        		   
    	        	   }else{    	        		  	 	 	 	 			
    	 	 	 				for( i=0;i<data.error.length;i++){
    	 	 	 					msg_error( data.error[i] );
    	 	 	 	 			}
    	        	   }		            	
    	           }
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