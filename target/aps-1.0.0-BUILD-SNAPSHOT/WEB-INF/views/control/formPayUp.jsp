<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="utf-8">
</head>
<body>
<div class="row h-100 justify-content-center align-items-center">
	<div class="card">
		<div class="card-body">
			 <form class="form-horizontal form-bordered m-t-5">
			  <div class="form-body">
			  	<div class="form-group row has-success">
	                  <label class="control-label text-right col-md-4" for="totalPayup"><h4 class="font-medium">Total</h4></label>
	                  <div class="col-md-8">
	                  		<input type="text" value="${total}"  data-idtramite="${idtramite}" class="form-control form-control-success" id="totalPayup" required data-validation-required-message="Cantidad a pagar">
	                  </div>	                  
	           </div>
	           <div class="form-group row has-warning">
	                  <label class="control-label text-right col-md-4" for="montoPayUp"><h4 class="font-medium">Monto</h4></label>
	                  <div class="col-md-8">
	                  	<input type="number" class="form-control form-control-warning" id="montoPayUp" onKeyUp="validarPago(event)" required data-validation-required-message="Cantidad que recibe para pago">
	                  </div>
	           </div>
	           <div class="form-group row has-danger">
	                  <label class="control-label text-right col-md-4" for="cambio"><h4 class="font-medium">Cambio</h4></label>
	                  <div class="col-md-8">
	                     <h2 class="font-medium" id="cambio">0.0</h2>	                  	
	                  </div>
	           </div>
			  </div>
	          <div class="form-actions">
	          	<div class="row">
	          		<div class="col-md-12">
	          			<div class="row">
	          				<div class="offset-sm-3 col-md-9">
	          				   <button type="button" class="btn btn-success" id="cobrarPayUp" onclick="cobrarPayUpNow()" ><i class="fa  fa-money"></i> Cobrar</button>
	          				</div>	          			
	          			</div>
	          		</div>
	          	</div>
	          </div>    
	           	              
          </form>
		</div>
	</div>
</div>
<script type="text/javascript">

 document.getElementById("montoPayUp").focus();
 // agregar titulo al titulo de modal
 $(function(){
	 $("#modal-header").html("<i class=''></i>Realizar pago");	 
 });
 
 function playPayUp(){
	 var idtramite  = $("#totalPayup").data("idtramite");
	 var totalPayup = $("#totalPayup").val();	
	 var montoPago = $("#montoPayUp").val();
	 var cambio    =  $("#cambio").text();
	 console.log( idtramite + " "+ totalPayup +" "+ montoPago +" "+ cambio);
	 
	 $.ajax({             	
   		 url:'${pageContext.request.contextPath}/clplayPayUp',
   			type: "post",	
   			data:{idtramite:idtramite,totalPayup:totalPayup,montoPago:montoPago,cambio:cambio},
   	       }).done(function(data, textStatus, jqXHR  ){
   	           if ( console && console.log ) 
   	           {
   	        	$("#modal-header").html("<i class=''></i>Imprimir comprobante");	 
   	        	$("#modal-body").html(data);     	        	
   	           }
   	       }).fail(function( jqXHR, textStatus, errorThrown ) {
                  if ( console && console.log ) {
                     // function de diferentes errores index
                     errorsAjax(jqXHR, textStatus);		                    
                  }
   	    }); // end .ajax 
 }
 
 function cobrarPayUpNow(){
	 var totalPayup = $("#totalPayup").val();	
	 var montoPago = $("#montoPayUp").val();  
	 if( montoPago >= totalPayup  ){ 
 		playPayUp();        		
 	}else{
 		msg_error("Cantidad invalida "+ montoPago );
 	}
 }

 function validarPago(event){
	 var keycode = (event.keyCode ? event.keyCode : event.which);
	 console.log(keycode);
	 var totalPayup = $("#totalPayup").val();
	 var idtramite = $("#totalPayup").data("idtramite");
	 var montoPago = $("#montoPayUp").val();
	 
	 if(montoPago > 0){
		 var cambio = parseFloat(montoPago) - parseFloat(totalPayup) ;
		  cambio = cambio.toFixed(2);
	 }else{
		 var cambio = 0 ;
	 }
	 $("#cambio").html(cambio);
	 if( montoPago >= totalPayup ){
		 $("#cobrarPayUp").removeAttr("disabled");
	 }else{
		 $("#cobrarPayUp").attr("disabled", "disabled");
	 }
        if(keycode == '13'){        	
        	if( montoPago >= totalPayup  ){ 
        		playPayUp();        		
        	}else{
        		msg_error("Cantidad invalida "+ montoPago );
        	}
        }
 }
 
 setTimeout(colocarFocus,1500);
 function colocarFocus(){
	 document.getElementById("montoPayUp").focus();
 }
 
</script>
</body>
</html>