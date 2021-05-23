<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="utf-8">
</head>
<body>
<div class="row">

<!--  precios options -->
  <div class="col-md-12">
	<div class="alert alert-info" style="padding: 0.75rem 1.25rem;">
		<button class="btn waves-effect waves-light btn-rounded btn-success" id="newPrecio">
			<i class="fa fa-plus"></i>
			Nuevo
		</button>
		<button class="btn waves-effect waves-light btn-rounded btn-success" id="searchArticulos">
			<i class="fa fa-rotate-right "></i>
			Actualizar
		</button>								
	</div>
</div>					
<!--  end precios options -->
<!-- 					home table -->
<div class="table-responsive m-t-10" id="tablePrecios" >
	 <table class="display nowrap table table-hover table-striped table-bordered" id="precio-table">		
		<thead>
			<tr>
				<th>#</th>
				<th>Codigo</th>
				<th>Descripcion</th>
				<th>Unidad</th>					
				<th>Clave</th>
				<th>Descripción</th>
				<th>No Precio</th>
				<th>$ Precio</th>
				<th>Estatus</th>
			</tr>
		</thead>
		<tbody>
		</tbody>
	</table>
</div>
<!-- 					end table -->
</div>
</body>
<script type="text/javascript">
$(function(){
	// Carga lista de articulos
	listaPreciosArticulos();
	// actualizar lista de articulos
	$("#searchArticulos").click(function(){
		// Carga lista de articulos
		listaPreciosArticulos();
	})

		/* cambiar de estatus */
	$(document).on("click", ".changedestatusPrecio", function(){
		
			var idchanged =  $(this).data("idchaged"); 
			var skuchaged =  $(this).data("skuchaged");
			var numprechaged =  $(this).data("numprechaged");
			var estatus = $(this).prop('checked');
			console.log(idchanged + "---"+ skuchaged+ "---"+ numprechaged +" "+estatus );
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
		    		 url:'${pageContext.request.contextPath}/changedEstatusPrecio',
		    			type: "post",			
		    			data:{idchanged:idchanged,skuchaged:skuchaged,numprechaged:numprechaged,estatus:estatus },
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
	
	  /**modificar precio */
	  $(document).on("change", ".changedPrecio",function(){
		var idchanged =  $(this).data("idchaged"); 
		var skuchaged =  $(this).data("skuchaged");
		var numprechaged =  $(this).data("numprechaged");
		var newprice =  $(this).val();
			
			console.log(idchanged + "---"+ skuchaged+ "---"+ numprechaged +" "+newprice );

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
		    		 url:'${pageContext.request.contextPath}/changePrecio',
		    			type: "post",			
		    			data:{idchanged:idchanged,skuchaged:skuchaged,numprechaged:numprechaged,newprice:newprice },
		    	       }).done(function(data, textStatus, jqXHR  ) {	    	                		  
		    	        	msg_ok(data)	    	        		             		    	           
		    	       }).fail(function( jqXHR, textStatus, errorThrown ) {
		                   if ( console && console.log ) {
		                      // function de diferentes errores index
		                      errorsAjax(jqXHR, textStatus);		                    
		                   }
		    	        }); // end .ajax
		     });
	  })

	
})
function listaPreciosArticulos(){
	 tablearticulos = $('#precio-table').DataTable({
			"ajax" : "${pageContext.request.contextPath}/datalistprecios",
					"destroy" : true,	
					searching : true,
					"ordering" : true,
					"scrollY" : "300px",
					"scrollX" : "auto",
					"scrollCollapse" : true,
					"paging" : false,
					info : true,
					"columns" : [ 
						{"data" : "indice"}, 
						{"data" : "numart"}, 
						{"data" : "nomart"},
						{"data" : "uniart"},				
						{"data" : "clave"},
						{"data" : "descripcion"},
						{"data" : "numprecio"},
						{"data" : "precio"},
						{"data" : "estatusprecio"},
						],
						

				});
}
</script>
</html>