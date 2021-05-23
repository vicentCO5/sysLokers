<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<meta charset="ISO-8859-1">
</head>
<body>
<div class="col-md-12">
        <div class="card card-body">
            <h3 class="box-title m-b-0">Nuevo Clasificación</h3>
            <p class="text-muted m-b-30 font-13"> Diferentes items </p>
            <form class="form-horizontal" id="formClasificacion">
                <div class="form-group row">
                    <label for="clave" class="col-sm-4 text-right control-label col-form-label">Clave *</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" id="clave" name="clave" placeholder="G">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="descripcion" class="col-sm-4 text-right control-label col-form-label">Descripción*</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" id="descripcion" name="descripcion" placeholder="GRANDE">
                    </div>
                </div>
               
                <div class="form-group row">                                        
                    <label for="estatus" class="col-sm-4 text-right control-label col-form-label" >Estatus <span class="help"></span></label>                     
                     <div class="switch">
                         <label>OFF
                           <input type="checkbox" checked="" id="estatus" name="estatus" ><span class="lever"></span>ON</label>
                     </div>
                </div>
                
                <div class="form-group m-b-0">
                    <div class="offset-sm-3 col-sm-9">                        
                        <button type="button" class="btn waves-effect waves-light btn-rounded btn-success" id="guardarClasificacion" title="Guardar">
							<i class="icon-Save-Window"></i>
							Guardar
						</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</body>
<script type="text/javascript">
$(function(){
	/*guardar contenido*/
	$("#guardarClasificacion").on("click",function(){
		
		var formulario = document.forms['formClasificacion'];
		var formData = new FormData(document.getElementById("formClasificacion"));
		var form = jQuery("form").closest("form").serializeArray();
		
		var map = {};
		jQuery.each(form, function(index, input){
			map[input.name] = input.value
		});
		console.log(map);
		
		if(validaForm()){
			 $.ajax({             	
				 url:'${pageContext.request.contextPath}/guardarClasificacion',
					type: "post",
				 	dataType: "json",
			     	data: {formvalues :JSON.stringify(map)},	
			       }).done(function(data, textStatus, jqXHR  ) {
			           if ( console && console.log ) 
			           {	
			        	   if(data.exito=="true"){			        		   
			        		   msg_ok(data.error)
// 			        		   tableClasificacion.ajax.reload( null, false );
			        		   /* lista de clasifiacion */
			        		   listaClasificacion();
			        	   }else{	 	 	 	 				
	 	 	 	 				for( i=0;i<data.error.length;i++){
	 	 	 	 				  msg_error(data.error[i] );
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

   /*validar formulario */
   function validaForm(){
    // Campos de texto
    var msg = "";
    if($("#clave").val() == ""){
    	msg = "El campo  clave no puede estar vacío.";
        msg_error(msg);
        $("#clave").focus();       // Esta función coloca el foco de escritura del usuario en el campo Nombre directamente.
        return false;
    }
    if($("#descripcion").val() == ""){
    	msg = ("El campo descripcion no puede estar vacío.");
    	msg_error(msg);
        $("#descripcion").focus();
        return false;
    }    
    
    return true; // Si todo está correcto
}

</script>

</html>