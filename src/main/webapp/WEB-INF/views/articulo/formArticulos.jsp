<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@page import="java.util.List"%>
    <%@page import="java.util.Map"%>
    <%@page import="java.util.HashMap"%>
    <%@page import="java.util.ArrayList"%>

<html>
<head>
</head>
<body>
<div class="row">
	<div class="col-md-12" >
       <div class="card card-body" style="padding: 0.5rem; ">
           <h3 class="box-title m-b-0">Nuevo Equipaje</h3>
           <p class="text-muted m-b-30 font-13"> Complete la información requerida </p>
           <div class="row">
             <form class="form-horizontal" id="formdataArticulos">
				<div class="row"> 
					<div class="col-md-6">
						<div class="form-group row">
	                          <label for="codigo" class="col-sm-3 text-right control-label col-form-label">Código*</label>
	                          <div class="col-sm-9">
	                              <input type="text" class="form-control" id="codigo" name="codigo" placeholder="SKU1">
	                          </div>
	                      </div>
					</div>
					<div class="col-md-6">
					    <div class="form-group row">
	                          <label for="nombre" class="col-sm-3 text-right control-label col-form-label">Nombre*</label>
	                          <div class="col-sm-9">
	                              <input type="text" class="form-control" id="nombre" name="nombre" placeholder="MALETA">
	                          </div>
	                      </div>
					</div>
				</div>
<!-- 				2 -->
				<div class="row"> 
					<div class="col-md-6">
					 <div class="form-group row">
                          <label for="unidad" class="col-sm-3 text-right control-label col-form-label">Unidad Base*</label>
                          <div class="col-sm-9">
                              <input type="text" class="form-control" id="unidad" name="unidad" placeholder="CAJA">
                          </div>
                      </div>
					</div>
					<div class="col-md-6">
					 <div class="form-group row">
                          <label for="color" class="col-sm-3 text-right control-label col-form-label">Color</label>
                          <div class="col-sm-9">
                              <input type="text" class="form-control" id="color" name="color" placeholder="">
                          </div>
                      </div>
					</div>
				</div>
<!-- 				3 -->
				<div class="row"> 
					<div class="col-md-6">
                      <div class="form-group row">
                          <label for="peso" class="col-sm-3 text-right control-label col-form-label">Peso</label>
                          <div class="col-sm-9">
                              <input type="number" value ="0" class="form-control" id="peso" name="peso" placeholder="0">
                          </div>
                      </div>					 
					</div>
					<div class="col-md-6">
                      <div class="form-group row">
                          <label for="volumen" class="col-sm-3 text-right control-label col-form-label">Volumen</label>
                          <div class="col-sm-9">
                              <input type="number" value ="0" class="form-control" id="volumen"  name= "volumen" placeholder="0">
                          </div>
                      </div>					 
					</div>
				</div>
<!-- 				4 -->
				<div class="row"> 
					<div class="col-md-6">
						 <div class="form-group row">
	                       <label for="alto" class="col-sm-3 text-right control-label col-form-label">Alto</label>
	                       <div class="col-sm-9">
	                           <input type="number" value ="0" class="form-control" id="alto" name="alto" placeholder="0">
	                       </div>
	                   </div>
					</div>
					<div class="col-md-6">
	                   <div class="form-group row">
	                       <label for="largo" class="col-sm-3 text-right control-label col-form-label">Largo</label>
	                       <div class="col-sm-9">
	                           <input type="number" value ="0" class="form-control" id="largo" name="largo" placeholder="0">
	                       </div>
	                   </div>					 
					</div>
				</div>
<!-- 				5 -->
				<div class="row"> 
					<div class="col-md-6">
	                   <div class="form-group row">
	                       <label for="ancho" class="col-sm-3 text-right control-label col-form-label">Ancho</label>
	                       <div class="col-sm-9">
	                           <input type="number" value="0" class="form-control" id="ancho" name="ancho"  placeholder="0">
	                       </div>
	                   </div>						
					</div>
					<div class="col-md-6">
	                   <div class="form-group row">
	                       <label for="clasificacionSKU" class="col-sm-3 text-right control-label col-form-label">Clasificación*</label>
	                       <div class="col-sm-9">                          
	                           <select class="form-control" id="clasificacionSKU" name="clasificacionSKU">
	                           <option value=""> --Seleccione--</option>
								 <%
		   				         Map<String,Map<String,Object>> mapa =  (Map<String,Map<String,Object>>)request.getAttribute("clasificaciones");                    		    
	                    		   Map<String,Object> datos = null;								   								 								   
									   for(String clave : mapa.keySet()) {									   
										   datos = mapa.get(clave); 																		  									   
										   out.println("<option value='"+datos.get("id")+"'>"+datos.get("clave")+ " - "+ datos.get("descripcion")+"</option>"); 									  									   
									   }
								                                  
								 %>
								
	                           </select>
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
	                          <button type="button" class="btn waves-effect waves-light btn-rounded btn-success" id="guardarArticulo" title="Guardar">
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
	/*guardar contenido*/
	$("#guardarArticulo").on("click",function(){
		
		var formulario = document.forms['formFolio'];
		var formData = new FormData(document.getElementById("formdataArticulos"));
		
		var form = jQuery("form").closest("form").serializeArray();
		console.log(form);
		var map = {};
		jQuery.each(form, function(index, input){
			map[input.name] = input.value
		});
		console.log(map);
		
		if(validaForm()){
			 $.ajax({             	
				 url:'${pageContext.request.contextPath}/guardarArticulo',
					type: "post",
				 	dataType: "json",
			     	data: {formvalues :JSON.stringify(map)},	
			       }).done(function(data, textStatus, jqXHR  ) {
		           if ( console && console.log ) 
		           {	
		        	   if(data.exito=="true"){			        		   
		        		   msg_ok(data.error)
		        		   /*rellenar la tabla de articulos */
		        	        listaArticulos();		        		   
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
		}		
		
	});
	
});

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
    if($("#unidad").val() == ""){
        msg = ("El campo unidad no puede estar vacío.");
        msg_error(msg);
        $("#unidad").focus();
        return false;
    }
    if($("#clasificacionSKU").val() == ""){
        msg = ("El campo clasificación no puede estar vacío.");
        msg_error(msg);
        $("#clasificacionSKU").focus();
        return false;
    }
    
    return true; // Si todo está correcto
}
</script>
</html>