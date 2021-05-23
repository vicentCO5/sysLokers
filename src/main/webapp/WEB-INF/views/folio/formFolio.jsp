<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="ISO-8859-1">
</head>
<body>
<div class="col-lg-12">
     <div class="card">
         <div class="card-body">
             <h4 class="card-title"></h4>
             <h6 class="card-subtitle">Folios para control de movimientos</h6>
             <div class="col-lg-6 col-md-12"> 
             	<div class="alert alert-info alert-rounded" id="errorFolio" style="display:none;" > </div>
              </div>
             <form class="form-material m-t-10" id="formFolio">
              	<div class="form-group">
                     <label>Mostrador o taquilla</label>
                     <select class="form-control" id="mostrador" name="mostrador">
                        <option value="">-- Selecione -- </option>
                         <option value="1">1 - CENTRAL </option>
                         <option value="2">2 - CENTRO</option>                         
                     </select>
                 </div>
                 <div class="form-group">
                     <label>Serie <span class="help"> TK </span></label>
                     <input type="text" id="serie" name="serie" class="form-control form-control-line" value="" placeholder="TK"> </div>
                 <div class="form-group">
                     <label>Folio <span class="help"> 0 </span></label>
                     <input type="number" id="folio" name="folio" class="form-control form-control-line" value="0"> </div>
                 <div class="form-group">
                     <label>Documento</label>
                     <select class="form-control" id="documento" name="documento">
                         <option value="">-- Selecione -- </option>
                         <option value="TICKET">TICKET </option>
                         <option value="CORTE">CORTE</option> 
                         <option value="RETIRO">RETIRO</option>                         
                     </select>
                 </div> 
                 <div class="form-group">
                     <label>Estatus <span class="help"> ON </span></label>                     
                     <div class="switch">
                         <label>OFF
                             <input type="checkbox" checked="" id="estatus" name="estatus" ><span class="lever"></span>ON</label>
                      </div>
                   </div>                                                        
             </form>
             <button type="button" class="btn waves-effect waves-light btn-rounded btn-success" id="guardarFolio" title="Guardar">
				<i class="icon-Save-Window"></i>
				Guardar
			</button>
         </div>
     </div>           
</div>
</body>
<script type="text/javascript">
$(function(){
	/*guardar contenido*/
	$("#guardarFolio").on("click",function(){
		
		var formulario = document.forms['formFolio'];
		var formData = new FormData(document.getElementById("formFolio"));
		var form = jQuery("form").closest("form").serializeArray();
		console.log(form);
		var map = {};
		jQuery.each(form, function(index, input){
			map[input.name] = input.value
		});
		console.log(map);
		
		if(validaForm()){
			 $.ajax({             	
				 url:'${pageContext.request.contextPath}/guardarFolio',
					type: "post",
				 	dataType: "json",
			     	data: {formvalues :JSON.stringify(map)},
// 			     	cache: false,
// 			     	contentType: false,
// 			     	processData: false,		
			       }).done(function(data, textStatus, jqXHR  ) {
			           if ( console && console.log ) 
			           {	
			        	   if(data.exito=="true"){			        		   
			        		   msg_ok(data.error)
			        		   tablefolios.ajax.reload( null, false );			        		   
			        	   }else{
			        		   $("#errorFolio").show();
	 	 	 	 			   $("#errorFolio").html("");	 	 	 	 				
	 	 	 	 				for( i=0;i<data.error.length;i++){
	 	 	 	 					$("#errorFolio").append("-"+data.error[i]+"<br/>");
	 	 	 	 	 			}
			        	   }			           	 				            	  			           					           					            	  
			           }
			       }).fail(function( jqXHR, textStatus, errorThrown ) {
		               if ( console && console.log ) {
		                  // function de diferentes errores index
		                  errorsAjax(jqXHR, textStatus);		                    
		               }
			        }); // end .ajax
		}		
		
	});
	
});
function validaForm(){
    // Campos de texto
    var msg = "";
    if($("#mostrador").val() == ""){
    	msg = "El campo  mostrador no puede estar vacío.";
        msg_error(msg);
        $("#mostrador").focus();       // Esta función coloca el foco de escritura del usuario en el campo Nombre directamente.
        return false;
    }
    if($("#serie").val() == ""){
    	msg = ("El campo serie no puede estar vacío.");
    	msg_error(msg);
        $("#serie").focus();
        return false;
    }
    if($("#folio").val() == ""){
        msg = ("El campo folio no puede estar vacío.");
        msg_error(msg);
        $("#folio").focus();
        return false;
    }
    if($("#documento").val() == ""){
        msg = ("El campo documento no puede estar vacío.");
        msg_error(msg);
        $("#documento").focus();
        return false;
    }
    
    return true; // Si todo está correcto
}
</script>
</html>