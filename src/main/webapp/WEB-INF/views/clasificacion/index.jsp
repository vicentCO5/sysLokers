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
	 	    <h4 class="card-title">Clasificación</h4>
			<h6 class="card-subtitle">Tipos de de tamaños</h6>
			<div class="row">
				<div class="col-md-12">
					<div class="alert alert-info">
						<button class="btn waves-effect waves-light btn-rounded btn-success" id="newCalsificacion">
							<i class="fa fa-plus"></i>
							Nuevo
						</button>
						<button class="btn waves-effect waves-light btn-rounded btn-success" id="updateClasificacion">
							<i class="fa fa-rotate-right"></i>
							Actualizar
						</button>
					</div>
				</div>				
			</div>
			<div class="table-responsive m-t-10" >
				<table class="display nowrap table table-hover table-striped table-bordered" id="clasificacion-table">
					<thead>
						<tr>						   											
							<th class="center">#</th>
							<th class="center">Clave</th>
							<th class="center">Descripcion</th>	
							<th class="center">Fecha creación</th>							
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
	var tableClasificacion = "";
	listaClasificacion();
	/* nueva clasificacion */
	$("#newCalsificacion").click(function(){
		
		$('#myModal-medium').modal('show');
		$("#modal-body").html("");
		$("#myModalLabel").html("");
		
		 $.ajax({             	
			 url:'${pageContext.request.contextPath}/newClasificacion',
				type: "post",
				dataType : 'html',
			   			
		       }).done(function(data, textStatus, jqXHR  ) {
		           if ( console && console.log ) 
		           {			             			  
		            	  $("#modal-body-medium").html(data);			            	
		           }
		       }).fail(function( jqXHR, textStatus, errorThrown ) {
	               if ( console && console.log ) {
	                  // function de diferentes errores index
	                  errorsAjax(jqXHR, textStatus);		                    
	               }
		        }); // end .ajax
	});
	$("#updateClasificacion").click(function(){
		listaClasificacion();
	});
	
	/*Modificar estatus*/
// 	$(".changeEstatusClass").click(function(){	
  $(document).on("click", ".changeEstatusClass", function(){
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
	    		 url:'${pageContext.request.contextPath}/changedEstatusClass',
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
function deleteClasificacion(idclass){
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
   		 url:'${pageContext.request.contextPath}/deleteClasificacion',
   			type: "post",			
   			data:{idclass:idclass},
   	       }).done(function(data, textStatus, jqXHR  ) {
   	           if ( console && console.log ) 
   	           {			             			  
   	        	   if(data.exito=="true"){	        		  
   	        		   msg_ok(data.error)
   	        		   // tableClasificacion.ajax.reload( null, false );
   	        		   listaClasificacion();
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
function listaClasificacion(){
    
	  tablefolios = $('#clasificacion-table').DataTable({
	 		"ajax":"${pageContext.request.contextPath}/listClasificacion",
	 		"destroy": true,
	 	    paging: false,
// 	 	    "scrollX": "300px",
	 	    "scrollY": "300px",
	         "scrollCollapse": true,
	         "paging": false,
	 	    info:true,
	 	    "columns":[ 
	 	    	        {"data":"id"},
	 	    	        {"data":"clave"},
	 				    {"data":"descripcion"},
	 				    {"data":"fechaCreacion"},	
	 					{"data":"estatus"}, 
	 		       		{"data":"accion"}
	 		       	],		 	   
	 	   				    
	 	    });
}
</script>
</html>