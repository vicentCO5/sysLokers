<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<div class="col-lg-12" >
	<div class="card">
	 	<div class="card-body" >	 	
	 	    <h4 class="card-title">Equipaje</h4>
			<h6 class="card-subtitle">Catalogo de equipaje</h6>
               <!-- Nav tabs -->
                <ul class="nav nav-tabs" role="tablist">
                    <li class="nav-item"> <a class="nav-link active" data-toggle="tab" href="#tabitems" role="tab"><span class="hidden-sm-up"><i class="ti-home"></i></span><i class="fa fa-codepen"> </i><span class="hidden-xs-down">Equipaje</span></a> </li>
                    <li class="nav-item"> <a class="nav-link" data-toggle="tab" id="listPreciosItems" href="#tabprecios" role="tab"><span class="hidden-sm-up"><i class="ti-user"></i></span><i class="fa fa-dollar"></i> <span class="hidden-xs-down">Precios</span></a> </li>
                </ul>
               <!-- Tab panes -->
               <!-- Tab content -->
               <div class="tab-content tabcontent-border">
               <div class="tab-pane p-20 active" id="tabitems" role="tabpanel">
						<div class="col-md-12">
							<div class="alert alert-info" style="padding: 0.75rem 1.25rem;">
								<button class="btn waves-effect waves-light btn-rounded btn-success" id="newItem">
									<i class="fa fa-plus"></i>
									Nuevo
								</button>
<!-- 								<button class="btn waves-effect waves-light btn-rounded btn-success" id="importArt"> -->
<!-- 									<i class="ace-icon glyphicon glyphicon-upload bigger-110"></i> -->
<!-- 									Importar xlx -->
<!-- 								</button> -->
								<button class="btn waves-effect waves-light btn-rounded btn-success" id="searchArticulos">
									<i class="fa fa-rotate-right"></i>
									Actualizar
								</button>								
							</div>
						</div>				
					<div class="table-responsive m-t-10" >
						 <table class="display nowrap table table-hover table-striped table-bordered" id="articulos-table">		
							<thead>
								<tr>
									<th>#</th>
									<th>Codigo</th>
									<th>Descripcion</th>
									<th>Unidad</th>					
									<th>Color</th>
									<th>Peso</th>
									<th>Volumen</th>					
									<th>Medidas</th>
									<th>Estatus</th>
									<th>Fecha creado</th>
									<th>Clasificación CVE</th>
									<th>Descripción</th>
									<th>Estatus</th>					
									<th>Acciones</th>
				
								</tr>
							</thead>
							<tbody>
							</tbody>
						</table>
					</div>
			 	</div>
			 	<div class="tab-pane  p-20" id="tabprecios" role="tabpanel" >
					<div id="articuloPrecios">
					</div>
			 	</div>
			 </div>
			 <!-- end Tab content -->
	 	</div>
	 </div>
</div>

                    
</html>
<script>
var tablearticulos;

	$(function() {
        /*rellenar la tabla de articulos */
        listaArticulos();
        
        $("#newItem").click(function(){
        	$('#modalaps').modal('show');
        	$("#modal-header").html("");
    		$("#modal-body-detalle").html("");	
    		
        	$.ajax({             	
   	   		 url:'${pageContext.request.contextPath}/formTemplateSKU',
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
        
		$("#importArt").click(function() {
			$.ajax({
				url : '${pageContext.request.contextPath}/getimportarticulos',
				dataType : 'html',
				success : function(data) {
					$("#modal-body").html(data);
					$("#modal-header").html("<h4>Importar Articulos</h4>");
					$('#modalaps').modal('show'); 
				},
				error : function(data) {
					msg_error("Ocurrio un error");
				}

			});
		});
		/*actualizar tablaa*/
	    $( "#searchArticulos" ).click(function() {
				listaArticulos();						
		});
		/*********************
		  Precios
		*******************/
		$("#listPreciosItems").click(function(){
			$.ajax({             	
	   	   		 url:'${pageContext.request.contextPath}/listPrecios',
	   	   			type: "post",			
	   	   	       }).done(function(data, textStatus, jqXHR  ) {
	   	   	           if ( console && console.log ) 
	   	   	           {			             			  
	   	   	        	$("#articuloPrecios").html(data);       	
	   	   	           }
	   	   	       }).fail(function( jqXHR, textStatus, errorThrown ) {
	   	                  if ( console && console.log ) {
	   	                     // function de diferentes errores index
	   	                     errorsAjax(jqXHR, textStatus);		                    
	   	                  }
	   	   	        }); // end .ajax
		});
	});
	function listaArticulos(){
		
	    tablearticulos = $('#articulos-table').DataTable({
		"ajax" : "${pageContext.request.contextPath}/getlistarticulos",
				"destroy" : true,				
				"ordering" : false,
				"scrollY" : "300px",
				"scrollX" : "380px",
				"scrollCollapse" : true,
				"paging" : false,
				info : true,
				"columns" : [ 
					{"data" : "indice"}, 
					{"data" : "numart"}, 
					{"data" : "nomart"},
					{"data" : "uniart"},				
					{"data" : "color"},
					{"data" : "peso"},
					{"data" : "volumen"},
					{"data" : "medidas"},
					{"data" : "estatus"},				
					{"data" : "timecreate"},
					{"data" : "clave"},
					{"data" : "descripcion"},
					{"data" : "estatusclass"},
					{"data" : "acciones"},
					],
					

			});
	}
	/*eliminar articulos*/
	function deleteArticulo(idclass){
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
	   		 url:'${pageContext.request.contextPath}/deleteArticulo',
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
	   	        
	    }); // en alert
	} // end function

	
</script>