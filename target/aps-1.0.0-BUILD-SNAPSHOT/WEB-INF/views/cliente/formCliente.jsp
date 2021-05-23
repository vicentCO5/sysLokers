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
	<div class="col-md-12" >
       <div class="card card-body" style="padding: 0.5rem; ">
           <h3 class="box-title m-b-0">Nuevo Cliente</h3>
           <p class="text-muted m-b-10 font-12"> Complete la información requerida </p>
           <div class="row">
             <form class="form-horizontal" id="formdataArticulos" >
				<div class="row"> 
					<div class="col-md-6">
						<div class="form-group row">
	                          <label for="codigo" class="col-sm-3 text-right control-label col-form-label">Código*</label>
	                          <div class="col-sm-9">
	                              <input type="text" class="form-control" id="codigo" name="codigo" placeholder="C001">
	                          </div>
	                      </div>
					</div>
					<div class="col-md-6">
					    <div class="form-group row">
	                          <label for="nombre" class="col-sm-3 text-right control-label col-form-label">Nombre*</label>
	                          <div class="col-sm-9">
	                              <input type="text" class="form-control" id="nombre" name="nombre" placeholder="Nombre">
	                          </div>
	                      </div>
					</div>
				</div>
<!-- 				2 -->
				<div class="row"> 
					<div class="col-md-6">
					 <div class="form-group row">
                          <label for="unidad" class="col-sm-3 text-right control-label col-form-label">Apellidos*</label>
                          <div class="col-sm-9">
                              <input type="text" class="form-control" id="apellidos" name="apellidos" placeholder="Apellidos">
                          </div>
                      </div>
					</div>
					<div class="col-md-6">
					 <div class="form-group row">
                          <label for="color" class="col-sm-3 text-right control-label col-form-label">Dirección</label>
                          <div class="col-sm-9">
                              <input type="text" class="form-control" id="direccion" name="direccion" placeholder="">
                          </div>
                      </div>
					</div>
				</div>
<!-- 				3 -->
				<div class="row"> 
					<div class="col-md-6">
                      <div class="form-group row">
                          <label for="peso" class="col-sm-3 text-right control-label col-form-label">Colonia</label>
                          <div class="col-sm-9">
                              <input type="text" value ="" class="form-control" id="colonia" name="colonia" placeholder="0">
                          </div>
                      </div>					 
					</div>
					<div class="col-md-6">
                      <div class="form-group row">
                          <label for="volumen" class="col-sm-3 text-right control-label col-form-label">Municipio</label>
                          <div class="col-sm-9">
                              <input type="text" value ="" class="form-control" id="municipio"  name= "municipio" placeholder="0">
                          </div>
                      </div>					 
					</div>
				</div>
<!-- 				4 -->
				<div class="row"> 
					<div class="col-md-6">
						 <div class="form-group row">
	                       <label for="alto" class="col-sm-3 text-right control-label col-form-label">Estado</label>
	                       <div class="col-sm-9">
	                           <input type="text" value ="" class="form-control" id="estado" name="estado" placeholder="0">
	                       </div>
	                   </div>
					</div>
					<div class="col-md-6">
	                   <div class="form-group row">
	                       <label for="largo" class="col-sm-3 text-right control-label col-form-label">Pais</label>
	                       <div class="col-sm-9">
	                           <input type="text" value ="0" class="form-control" id="pais" name="pais" placeholder="0">
	                       </div>
	                   </div>					 
					</div>
				</div>
<!-- 				5 -->
				<div class="row"> 
					<div class="col-md-6">
	                   <div class="form-group row">
	                       <label for="ancho" class="col-sm-3 text-right control-label col-form-label">Telefono</label>
	                       <div class="col-sm-9">
	                           <input type="text" value="" class="form-control" id="telefono" name="telefono"  placeholder="0">
	                       </div>
	                   </div>						
					</div>
					<div class="col-md-6">
	                   <div class="form-group row">
	                       <label for="clasificacionSKU" class="col-sm-3 text-right control-label col-form-label">E-mail</label>
	                       <div class="col-sm-9">                          
	                           <input type="email" value="" class="form-control" id="email" name="email"  placeholder="cliente@solutions.com">
	                       </div>
	                   </div> 	                 					 
					</div>
				</div>
<!-- 				6 -->
				<div class="row"> 
					<div class="col-md-6"> -
					</div>
					<div class="col-md-6">
	                   <div class="form-group m-b-0">
	                       <div class="offset-sm-3 col-sm-9">
	                          <button type="button" class="btn waves-effect waves-light btn-rounded btn-success" id="guardarCliente" title="Guardar">
									<i class="icon-Save-Window"></i>
									Guardar
								</button>
	                       </div>
	                   </div>	                				 
					</div>
				</div>
           	</form>
           	</div>
<!--            	end formulario -->
           </div>
       </div>
</div>
</body>
<script type="text/javascript">
	$(function(){
		$("#guardarCliente").click(function(){
			var form = jQuery("form").closest("form").serializeArray();
			console.log(form);
			var map = {};
			jQuery.each(form, function(index, input){
				map[input.name] = input.value
			});
			console.log(map);
			
			if(validaForm()){
				 $.ajax({             	
					 url:'${pageContext.request.contextPath}/guardarCliente',
						type: "post",
					 	dataType: "json",
				     	data: {formvalues :JSON.stringify(map)},	
				       }).done(function(data, textStatus, jqXHR  ) {
			           if ( console && console.log ) 
			           {	
			        	   if(data.exito=="true"){			        		   
			        		   msg_ok(data.error)
			        		   /*rellenar la tabla  */
			        			listaClientesdb();	        		   
			        	   }else{		        		 	 	 	 	 				
	 	 	 	 				for( i=0;i<data.error.length;i++){
	 	 	 	 				msg_error("-"+data.error[i]+"");
	 	 	 	 	 			}
			        	   }			           	 				            	  			           					           					            	  
			           }
			       }).fail(function( jqXHR, textStatus, errorThrown ) {
		               if ( console && console.log ) {
		                  // function de diferentes errores index
		                  errorsAjax(jqXHR, textStatus);		                    
		               }
			        }); // end .ajax
			}		// end if
		});
	})
	
function validaForm(){
    // Campos de texto
    var msg = "";
    if($("#codigo").val() == ""){
    	msg = "El campo  codigo no puede estar vacío.";
        msg_error(msg);
        $("#codigo").focus();       // Esta función coloca el foco de escritura del usuario en el campo Nombre directamente.
        return false;
    }
    if($("#nombre").val() == ""){
    	msg = ("El campo serie no puede estar vacío.");
    	msg_error(msg);
        $("#nombre").focus();
        return false;
    }
    if($("#apellidos").val() == ""){
        msg = ("El campo apellidos no puede estar vacío.");
        msg_error(msg);
        $("#apellidos").focus();
        return false;
    }
    
    return true; // Si todo está correcto
}
</script>
</html>