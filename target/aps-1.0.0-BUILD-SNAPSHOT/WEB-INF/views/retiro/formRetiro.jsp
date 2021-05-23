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
         <div class="col-sm-12 col-xs-12">
             <form>
                 <div class="form-group">
                     <label for="serie">Serie</label>
                     <input type="text" class="form-control" value="${serie}" id="serie"  name="serie" placeholder="Serie" readonly>
                 </div>
                 <div class="form-group">
                     <label for="importe">Importe</label>
                     <input type="number" class="form-control" id="importe" name="importe" placeholder="0">
                 </div>
                 <div class="form-group">
                     <label for="consepto">Consepto</label>
                       <input type="text"  maxlength="150" class="form-control" id="consepto" name=consepto placeholder="">
                       <%--                       ${options}  --%>
<!--                      <select class="form-control" id="consepto"  name="consepto" ></select> -->
                 </div>
                 <div class="form-group">
                     <label for="receptor">Receptor</label>
                     <input type="text" class="form-control" id="receptor"  name="receptor" placeholder="Nombre receptor">
                 </div>                 
                 <button id="guardarRetiro" class="btn btn-success waves-effect waves-light m-r-10">Aplicar</button>                 
             </form>
         </div>
     </div>
</body>
<script type="text/javascript">
 $(function(){
	 $("#guardarRetiro").click(function(){
		 var form = jQuery("form").closest("form").serializeArray();
			console.log(form);
			var map = {};
			jQuery.each(form, function(index, input){
				map[input.name] = input.value
			});
			console.log(map);
			
			if(validaForm()){
				 $.ajax({             	
					 url:'${pageContext.request.contextPath}/guardarRetiro',
						type: "post",
					 	dataType: "json",
				     	data: {formvalues :JSON.stringify(map)},	
				       }).done(function(data, textStatus, jqXHR  ) {
			           if ( console && console.log ) 
			           {	
			        	   if(data.exito=="true"){			        		   
			        		   msg_ok(data.error)			        		           		   
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
		return false;
	 });
 });
 function validaForm(){
	    // Campos de texto
	    var msg = "";
	    if($("#serie").val() == "" || $("#serie").val() == "NO EXISTE SERIE"){
	    	msg = "El campo  serie no puede estar vacío.";
	        msg_error(msg);
	        $("#serie").focus();       // Esta función coloca el foco de escritura del usuario en el campo Nombre directamente.
	        return false;
	    }
	    if($("#importe").val() == ""){
	    	msg = ("El campo importe no puede estar vacío.");
	    	msg_error(msg);
	        $("#importe").focus();
	        return false;
	    }
	    if($("#consepto").val() == ""){
	        msg = ("El campo consepto no puede estar vacío.");
	        msg_error(msg);
	        $("#consepto").focus();
	        return false;
	    }
	    if($("#receptor").val() == ""){
	        msg = ("El campo receptor no puede estar vacío.");
	        msg_error(msg);
	        $("#receptor").focus();
	        return false;
	    }
	    
	    return true; // Si todo está correcto
	}
 
</script>
</html>