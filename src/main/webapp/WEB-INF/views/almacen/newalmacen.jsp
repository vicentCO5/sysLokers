<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
	<div class="alert alert-danger" id="errorsAlms" style="display: none"></div>

	<div id="">
		<form:form  method="post" class="form-horizontal" id="formNewAlmacen" name="formNewAlmacen" >
				<div class="form-group row">
					<label for="empresa_id"
						class="col-sm-3 text-right control-label col-form-label">Codigo
						Empresa *: </label>
					<div class="col-sm-9">
						<input type="number" value="1" id="empresa_id"  name="empresa_id" class="form-control" style="text-transform: uppercase" readonly requiered />
					</div>
				</div>
				<div class="form-group row">
					<label for="clave"
						class="col-sm-3 text-right control-label col-form-label">Clave*:
					</label>
					<div class="col-sm-9">
						<input value="" id="clave" name="clave" class="form-control" style="text-transform: uppercase" requiered  />
					</div>
				</div>
				<div class="form-group row">
					<label for="name"
						class="col-sm-3 text-right control-label col-form-label">Nombre*:
					</label>
					<div class="col-sm-9">
						<input id="nombre"  name="nombre" value="" type="text" class="form-control" style="text-transform: uppercase" requiered />
					</div>
				</div>
				<div class="form-group row">
					<label for="codigopostal"
						class="col-sm-3 text-right control-label col-form-label">Codigo Postal*:
					</label>
					<div class="col-sm-9">
						<input id="codigopostal" name="codigopostal" type="number" maxlength='5' required data-validation-required-message="Dato requerido" aria-invalid="false"  class="form-control" />
					</div>
				</div>
				<div class="form-group row">
					<label for="direccion"
						class="col-sm-3 text-right control-label col-form-label">Direccion:
					</label>
					<div class="col-sm-9">
						<input id="direccion" name="direccion" type="text" class="form-control" style="text-transform: uppercase" />
					</div>
				</div>

				<div class="form-group row">
					<label for="colonia"
						class="col-sm-3 text-right control-label col-form-label">Colonia:
					</label>
					<div class="col-sm-9">
						<input id="colonia" name="colonia" type="text" class="form-control" style="text-transform: uppercase" />
					</div>
				</div>

				<div class="form-group row">
					<label for="numExterior"
						class="col-sm-3 text-right control-label col-form-label">Num.Exterior:
					</label>
					<div class="col-sm-9">
						<input id="numExterior"  name="numExterior" maxlength='6' type="text" class="form-control" />
					</div>
				</div>

				<div class="form-group row">
					<label for="numInterior"
						class="col-sm-3 text-right control-label col-form-label">Num.Interior:
					</label>
					<div class="col-sm-9">
						<input id="numInterior" name="numInterior" maxlength='6' type="text" class="form-control" />
					</div>
				</div>
				<div class="form-group row">
					<label for="pais"
						class="col-sm-3 text-right control-label col-form-label">Pais:
					</label>
					<div class="col-sm-9">
						<input id="pais" name="pais" type="text" class="form-control" style="text-transform: uppercase" />
					</div>
				</div>
		
				<div class="col-sm-4">
					<button type="button" class="btn waves-effect waves-light btn-rounded btn-success"
						id="guardarAlmacen" title="Guardar">
						<i class="icon-Save-Window"></i>
						Guardar
					</button>
				</div>
		</form:form>
	</div>
</body>
<script>
$(function(){
	$("#guardarAlmacen").click(function(e){
		
		if(validaForm()){
			 var formData = new FormData(document.getElementById("formNewAlmacen"));
		
			 console.log( formData );
			 $.ajax({             	
				 url:'${pageContext.request.contextPath}/guardaralmacen',
					type: "post",		
					 dataType: "json",
				     data: formData,
				     cache: false,
				     contentType: false,
				     processData: false,			
			       }).done(function(data, textStatus, jqXHR  ) {
			           if ( console && console.log ) 
			           {			             			  		            				            	 
		                  console.log(data);  
		  	   	      		if(data.exito=="true"){	        		  
			        		   msg_ok(data.error)				        		   
			        	   }else{    	        		  	 	 	 	 						 	 	 				
			 	 	 			msg_error( data.error );
			 	 	 	 		
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
	
	function validaForm(){
	    // Campos de texto
	    var msg = "";
	    if($("#empresa_id").val() == ""){
	    	msg = "El campo id empresa no puede estar vacío.";
	        msg_error(msg);
	        $("#empresa_id").focus();       // Esta función coloca el foco de escritura del usuario en el campo Nombre directamente.
	        return false;
	    }
	    if($("#clave").val() == ""){
	    	msg = ("El campo clave no puede estar vacío.");
	    	msg_error(msg);
	        $("#clave").focus();
	        return false;
	    }
	    if($("#nombre").val() == ""){
	        msg = ("El campo nombre no puede estar vacío.");
	        msg_error(msg);
	        $("#nombre").focus();
	        return false;
	    }
	    if($("#codigopostal").val() == ""){
	        msg = ("El campo codigo postal no puede estar vacío.");
	        msg_error(msg);
	        $("#nombre").focus();
	        return false;
	    }
	    
	    return true; // Si todo está correcto
	}
	
})
</script>
</html>