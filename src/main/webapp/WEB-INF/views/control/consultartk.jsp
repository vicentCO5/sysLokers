<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="utf-8">
</head>
<body>
<div class="col-12">
	<div class="card" style ="margin-bottom: 10px; ">
		<div class="card-body" style=" padding: 0.5rem;">
		<h5 class="card-title">Consultar Equipaje</h5>			
                <input type="text" class="form-control" id="clbuscarTK" placeholder="Buscar">          		
		</div>
	</div>
	<div class="row">
		<div class="col-12">
			<div class="card">
				<div class="card-body" id="datosTK">
					 <!-- datos de tiket -->					   
				</div>
			</div>
		</div>
	</div>

</div>
</body>
<style>
	table th, .table td {
    padding: 0.12rem;
    }
</style>
<script type="text/javascript">
document.getElementById("clbuscarTK").focus();
$(function(){
// 	 al realizar enter buscar
    $("#clbuscarTK").keypress(function(event){
    	var keycode = (event.keyCode ? event.keyCode : event.which);
        if(keycode == '13'){
        	var idtramite  = $("#clbuscarTK").val();
        	$.ajax({             	
     	   		 url:'${pageContext.request.contextPath}/clSearchTK',
     	   			type: "post",	
     	   			data:{idtramite:idtramite},
     	   	       }).done(function(data, textStatus, jqXHR  ){
     	   	           if ( console && console.log ) 
     	   	           {			             			  
     	   	        	$("#datosTK").html(data);       	
     	   	           }
     	   	       }).fail(function( jqXHR, textStatus, errorThrown ) {
     	                  if ( console && console.log ) {
     	                     // function de diferentes errores index
     	                     errorsAjax(jqXHR, textStatus);		                    
     	                  }
     	   	    }); // end .ajax   
        }
    })
});
/*eventoo */
 function payUp(idtramite){
	 swal({   
         title: "Confirme !",   
         text: "Confirme para modificar estatus",   
         type: "warning",   
         showCancelButton: true,   
         confirmButtonColor: "#DD6B55",   
         confirmButtonText: "Aceptar",   
         closeOnConfirm: true 
     }, function(){ 
    	$('#modalaps').modal('show');
     	$("#modal-header").html("");
 		$("#modal-body-detalle").html("");
    	 $.ajax({             	
    		 url:'${pageContext.request.contextPath}/formPayUp',
    			type: "post",			
    			data:{idtramite:idtramite},
    	       }).done(function(data, textStatus, jqXHR  ) {	    	                		  
    	    	   $("#modal-body").html(data);    	        		             		    	           
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