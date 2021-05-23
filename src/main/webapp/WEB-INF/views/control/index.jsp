<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="utf-8">
</head>
<body>
	<!-- ============================================================== -->
	<!-- Start Page Content -->
	<!-- ============================================================== -->
	<!-- Validation wizard -->
	<div class="row" id="validation">
		<div class="col-12">
			<div class="card wizard-content">
				<div class="card-body" style="padding-top: .5rem;">
					<h4 class="card-title" style="    margin-bottom: 0.15rem;">Control de equipaje</h4>					
					<form action="#" class="validation-wizard wizard-circle" id="formControl">
						<!-- Step 1 -->
						<h6>Datos personales</h6>
						<section>
							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<label for="clnombre">Nombre : <span class="danger">*</span>
										</label> <input type="text" value="demo" class="form-control required"
											id="clnombre" name="clnombre"
											style="text-transform: uppercase">
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<label for="clapelidos"> Apellidos : <span
											class="danger">*</span>
										</label> <input type="text" value="demo" class="form-control required"
											id="clapelidos" name="clapellidos"
											style="text-transform: uppercase">
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<label for="clemail"> Email : </label> <input type="email"
											class="form-control" id="clemail" name="clemail">
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<label for="cltelefono"> Número teléfono :</label> <input
											type="tel" class="form-control" id="cltelefono"
											name="cltelefono">
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<label for="cltipoidentificacion"> Tipo de
											identificación : <span class="danger">*</span>
										</label> <select class="custom-select form-control required"
											id="cltipoidentificacion" name="cltipoidentificacion">
											<option value="">-- Seleccione --</option>
											<option value="RFCRUC">RFC - RUC</option>
											<option value="INE">INE</option>
											<option value="LICENCIMAMANEJO">LICENCIA DE MANEJO</option>
											<option value="CREDENCIALESCOLAR">CREDENCIAL DE
												ESCUELA</option>
										</select>
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<label for="clnumidentificacion"> Número
											identificación : <span class="danger">*</span>
										</label> <input type="text" value="12345"
											class="form-control required" id="clnumidentificacion"
											name="clnumidentificacion">
									</div>
								</div>
							</div>
						</section>
						<!-- Step 2 -->
						<h6>Equipaje</h6>
						<section>
							<a href="#"
								class="btn waves-effect waves-light btn-rounded btn-xs btn-info" onclick="clagregarequipaje(this)" id="clagregarequipaje" data-number="1" ><i class="fa fa-plus-square"></i> Agregar </a>
							<div class="row" id="clTableEquipaje">
								<div class="row" id="1">
									<div class="col-md-1 estyletable">
										<div class="form-group">
											<label for="clarticulo1"># : </label> <input type="number" value="1" class="form-control verificarcontador" id="clcontador1" name="clcontador1" readonly>
										</div>
									</div>
									<div class="col-md-2 styletable">
										<div class="form-group">
											<label for="clarticulo1">Equipaje : <span
												class="danger">*</span>
											</label> <input type="text" class="form-control seleccionarArticulo required" data-contador="1" id="clarticulo1" placeholder="Click" name="clarticulo1" readonly>
										</div>
									</div>
									<div class="col-md-1 styletable">
										<div class="form-group">
											<label for="clcodigosku1"> Codigo: <span class="danger">*</span> </label> <input type="text" class="form-control required" id="clcodigosku1" name="clcodigosku1" readonly>
										</div>
									</div>
									<div class="col-md-2 styletable">
										<div class="form-group">
											<label for="tipoClass">Tamaño :</label> <select
												class="custom-select form-control clselectclass required"
												onchange="seleccionarPrecio(this)" data-contador="1" id="tipoClass1" name="tipoClass1" data-placeholder="Type to search prices" >
												<option value="">--Seleccione--</option>
											</select>
										</div>
									</div>
									<div class="col-md-2 styletable">
										<div class="form-group">
											<label for="clcolor1">Color : <span class="danger">*</span>
											</label> <input type="text" class="form-control required"
												id="clcolor1" name="clcolor1"
												style="text-transform: uppercase">
										</div>
									</div>
									<div class="col-md-2 styletable">
										<div class="form-group">
											<label for="preciohr1">Precio X Hora :</label> <input
												type="number" value="0" class="form-control required"
												id="preciohr1" name="preciohr1" readonly>
										</div>
									</div>
									<div class="col-md-1 styletable">
										<div class="form-group">
											<label for="clcantidad">Cantidad :</label> <input
												type="number" value="1" class="form-control required"
												id="clcantidad1" name="clcantidad1">
										</div>
									</div>

									<div class="col-md-1 styletable">
										-
									</div>
								</div>
								<!--  segundo divrow -->
							</div>
							<!-- observacion -->
							<div class="row">
								<div class="col-md-12">
									<div class="form-group">
										<label for="shortDescription"> Description :</label>
										<textarea name="shortDescription" id="shortDescription"
											rows="2" class="form-control"></textarea>
									</div>
								</div>
							</div>
						</section>
					
						<!-- Step 3 -->
						<h6>Confirmar</h6>
						<section>
							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<a href="#" class="btn btn-rounded btn-block btn-info" onclick="confirmarDatos(this)"><i class="fa fa-check"></i>Confirmar </a>
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">									 
										<input
											type="text" value="" class="form-control required" name="statusconfirmar" id="statusconfirmar" placeholder="Estatus" readonly />		
									</div>									
								</div>
							</div>
							<div class="row" >
								<div class="col-md-12" id="datosConfirmacion">
                                
                                    <div class="ribbon ribbon-success">Para proseguir confirme ..!<i class="fa fa-level-up" style="font-size: 14px;"></i></div>                                   
                               	</div>                           
							</div>
						</section>
					</form>						
				</div>
			</div>
		</div>
	</div>

	<!-- ============================================================== -->
	<!-- End PAge Content -->
	<!-- ============================================================== -->
	<div  class="card">
		<div class="card-body" id="boletoPrint">
			
		</div>
	</div>
	<!-- Sweet-Alert formulario control  -->
	<%--     <script src="${pageContext.request.contextPath}/resources/js/steps.js"></script> --%>
	<style>
.styletable {
	padding-right: 3px;
	padding-left: 3px;
}
</style>

<script type="text/javascript">
var contador = 1;
var listaOptions = "";
$(function(){
	// actualizar lista de opciones de clasificacion 
	var numElemento = 1;
	OptionsClasificacion( numElemento );
	OptionsIdentificacion();
	
	$(document).on("click",".seleccionarArticulo",function(){
		$('#modalaps').modal('show');
    	$("#modal-header").html("");
		$("#modal-body-detalle").html("");	
		var contador = $(this).data("contador");		
		
		console.log(contador);
		$.ajax({             	
  	   		 url:'${pageContext.request.contextPath}/cllistArticulos',
  	   			type: "post",	
  	   			data:{contador:contador},
  	   	       }).done(function(data, textStatus, jqXHR  ){
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
	
	/**selecionar el precio en base al parametro
	seleccionado */
	$(".tab-wizard").steps({
	    headerTag: "h6"
	    , bodyTag: "section"
	    , transitionEffect: "fade"
	    , titleTemplate: '<span class="step">#index#</span> #title#'
	    , labels: {
	        finish: "Submit"
	    }
	    , onFinished: function (event, currentIndex) {
	       swal("Form Submitted!", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed lorem erat eleifend ex semper, lobortis purus sed.");
	            
	    }
	});


	var form = $(".validation-wizard").show();

	$(".validation-wizard").steps({
	    headerTag: "h6"
	    , bodyTag: "section"
	    , transitionEffect: "fade"
	    , titleTemplate: '<span class="step">#index#</span> #title#'
	    , labels: {
	        finish: "Submit"
	    }
	    , onStepChanging: function (event, currentIndex, newIndex) {
	        return currentIndex > newIndex || !(3 === newIndex && Number($("#age-2").val()) < 18) && (currentIndex < newIndex && (form.find(".body:eq(" + newIndex + ") label.error").remove(), form.find(".body:eq(" + newIndex + ") .error").removeClass("error")), form.validate().settings.ignore = ":disabled,:hidden", form.valid())
	    }
	    , onFinishing: function (event, currentIndex) {
	        return form.validate().settings.ignore = ":disabled", form.valid()
	    }
	    , onFinished: function (event, currentIndex) {
	         
// 	    	swal("Form Submitted!", 
// 	         	"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed lorem erat eleifend ex semper, lobortis purus sed.");
	         // function confirmar datos una vez confimado prosige a guardar para posteriormente generar los tk
	         if(confirmarDatos()){
	         	msg_ok("Procesando ..!");
	         	guardarDatos();
	         };

	    }
	}), $(".validation-wizard").validate({
	    ignore: "input[type=hidden]"
	    , errorClass: "text-danger"
	    , successClass: "text-success"
	    , highlight: function (element, errorClass) {
	        $(element).removeClass(errorClass)
	    }
	    , unhighlight: function (element, errorClass) {
	        $(element).removeClass(errorClass)
	    }
	    , errorPlacement: function (error, element) {
	        error.insertAfter(element)
	    }
	    , rules: {
	        email: {
	            email: !0
	        }
	    }
	})
}); // end jquery $
function guardarDatos(){
	var i=0;  var j=0; 
	var estatusConfir = $("#statusconfirmar").val();
	/***************************************/
	 var JSONObject          =  new Object;
      JSONObject.PEDIDO      = "NUEVO";
      JSONObject.SERIE       ='N';
      JSONObject.NOMBRE      = $("#clnombre").val();
      JSONObject.APELLIDOS   = $("#clapelidos").val();
      JSONObject.EMAIL       = $("#clemail").val();
      JSONObject.TELEFONO    = $("#cltelefono").val();
      JSONObject.IDENTIFICACION        = $("#cltipoidentificacion").val();
      JSONObject.NUMEROIDENTIFICACION   = $("#clnumidentificacion").val();
      JSONObject.DESCRIPCION   = $("#shortDescription").val();
      JSONObject.ESTATUS    = $("[id='statusconfirmar']").val();

      JSONObject.NEWPEDIDO = new Array;	      
      $(".verificarcontador").each(function(){
          var id = $(this).val();
           console.log(id);
            JSONObject.NEWPEDIDO[i]           = new Object;
            JSONObject.NEWPEDIDO[i].CONTADOR    = $("[id='clcontador"+id+"']").val();
            JSONObject.NEWPEDIDO[i].NOMBRESKU   = $("[id='clarticulo"+id+"']").val();
            JSONObject.NEWPEDIDO[i].CODIGOSKU  = $("[id='clcodigosku"+id+"']").val();
            JSONObject.NEWPEDIDO[i].TIPOCLASS  = $("[id='tipoClass"+id+"']").val();
            JSONObject.NEWPEDIDO[i].COLOR      = $("[id='clcolor"+id+"']").val();
            JSONObject.NEWPEDIDO[i].PRECIO      = $("[id='preciohr"+id+"']").val();
            JSONObject.NEWPEDIDO[i].CANTIDAD    = $("[id='clcantidad"+id+"']").val();

            i++;                      
    });
    JSONstring = JSON.stringify(JSONObject);

    if( i > 0 )
    {

        $.ajax({             	
  		    url:'${pageContext.request.contextPath}/clguardarEquipajex',
  			type: "post",
  			data:{varjson:JSONstring},  			
  	       }).done(function(data, textStatus, jqXHR  ){
  	           if ( console && console.log ) 
  	           {			             			  	        		  
	        	$("#divprincipal").html(data);    
				console.log(data);
  	           }
  	       }).fail(function( jqXHR, textStatus, errorThrown ) {
                 if ( console && console.log ) {
                    // function de diferentes errores index
                    errorsAjax(jqXHR, textStatus);		                    
                 }
  	    }); // end .ajax

    }else{
    	msg_error("No existe equipaje a guardar ..!");
    }
     

}
/*confirmar datos */
function confirmarDatos(){
	 
	var i=0;  var j=0; 
	var estatusConfir = $("#statusconfirmar").val();
	/***************************************/
		 var JSONObject        =  new Object;
	      JSONObject.PEDIDO     = "NUEVO";
	      JSONObject.SERIE      ='N';
	      JSONObject.NOMBRE      = $("#clnombre").val();
	      JSONObject.APELLIDOS   = $("#clapelidos").val();
	      JSONObject.EMAIL       = $("#clemail").val();
	      JSONObject.TELEFONO    = $("#cltelefono").val();
	      JSONObject.IDENTIFICACION        = $("#cltipoidentificacion").val();
	      JSONObject.NUMEROIDENTIFICACION   = $("#clnumidentificacion").val();
	      JSONObject.DESCRIPCION   = $("#shortDescription").val();
	      JSONObject.ESTATUS    = $("[id='statusconfirmar']").val();

	      var tableHtml ="<div class='table-responsive'><table class'table table-striped'>";
	       tableHtml += "<tr><th>Cliente </th> <td>"+$("#clnombre").val()+" "+ $("#clapelidos").val()+"</td></tr>";
	       tableHtml += "<tr><th>Email : </th> <td>"+$("#clemail").val()+"</td> <td>Telefono</td><td>"+$("#cltelefono").val()+"</td></tr>";
	       tableHtml += "<tr><th>Idetificación  </th><td>"+$("#cltipoidentificacion").val()+" &nbsp;&nbsp;&nbsp;&nbsp; Codigo : ["+ $("#clnumidentificacion").val()+"]</td></tr>";

	      var tableHtmlBody = "<table class='table'><tr> <th>#</th><th>NOMBRE</th><th>CODIGO</th><th>TIPO</th><th>COLOR</th><th>PRECIO</th><th>CANTIDAD<th></tr> </th>";
	      JSONObject.NEWPEDIDO = new Array;	      
	      $(".verificarcontador").each(function(){
	          var id = $(this).val();
	          console.log(id);
	            JSONObject.NEWPEDIDO[i]           = new Object;
	            JSONObject.NEWPEDIDO[i].CONTADOR    = $("[id='clcontador"+id+"']").val();
	            JSONObject.NEWPEDIDO[i].NOMBRESKU   = $("[id='clarticulo"+id+"']").val();
	            JSONObject.NEWPEDIDO[i].CODIGOSKU  = $("[id='clcodigosku"+id+"']").val();
	            JSONObject.NEWPEDIDO[i].TIPOCLASS  = $("[id='tipoClass"+id+"']").val();
	            JSONObject.NEWPEDIDO[i].COLOR      = $("[id='clcolor"+id+"']").val();
	            JSONObject.NEWPEDIDO[i].PRECIO      = $("[id='preciohr"+id+"']").val();
	            JSONObject.NEWPEDIDO[i].CANTIDAD    = $("[id='clcantidad"+id+"']").val();

	          tableHtmlBody += "<tr> <th>"+$("[id='clcontador"+id+"']").val()+"</th>";
	          tableHtmlBody += "<th>"+$("[id='clarticulo"+id+"']").val()+"</th>";
	          tableHtmlBody += "<th>"+$("[id='clcodigosku"+id+"']").val()+"</th>";
	          tableHtmlBody +="<th>"+$("[id='tipoClass"+id+"']").val()+"</th>"
	          tableHtmlBody +="<th>"+$("[id='clcolor"+id+"']").val()+"</th>";
	          tableHtmlBody +="<th>"+$("[id='preciohr"+id+"']").val()+"</th>";
	          tableHtmlBody +="<th>"+$("[id='clcantidad"+id+"']").val()+"</th> </tr>";                         
	            i++;                      
	    });
	    JSONstring = JSON.stringify(JSONObject);
	    tableHtml += "<tr><td colspan='2'>"+tableHtmlBody+"</td></tr>";
	    tableHtml += "<tr><td>Descripción General  : </td><td>"+$("#shortDescription").val()+" </td></tr>";
	    tableHtml += "</table></div>";
	/***************************************/
	if( estatusConfir =="" ){
		$("#datosConfirmacion").html(tableHtml);
        $("#statusconfirmar").attr('value','CONFIRMADO');
        return false;
	}else{       
      return true;
	}
}

function OptionsClasificacion( numElemento ){
	$.ajax({             	
  		 url:'${pageContext.request.contextPath}/cloptionClasificacion',
  			type: "post",	  			
  	       }).done(function(data, textStatus, jqXHR  ){
  	           if ( console && console.log ) 
  	           {			             			  	        		  
	        	$("#tipoClass"+numElemento).html(data);        		 
  	           }
  	       }).fail(function( jqXHR, textStatus, errorThrown ) {
                 if ( console && console.log ) {
                    // function de diferentes errores index
                    errorsAjax(jqXHR, textStatus);		                    
                 }
  	    }); // end .ajax
}


function OptionsIdentificacion(){
	$.ajax({             	
  		 url:'${pageContext.request.contextPath}/cloptionIdentificacion',
  			type: "post",	  			
  	       }).done(function(data, textStatus, jqXHR  ){
  	           if ( console && console.log ) 
  	           {			             			  	        		  
	        	$("#cltipoidentificacion").html(data);        		 
  	           }
  	       }).fail(function( jqXHR, textStatus, errorThrown ) {
                 if ( console && console.log ) {
                    // function de diferentes errores index
                    errorsAjax(jqXHR, textStatus);		                    
                 }
  	    }); // end .ajax
}

function seleccionarPrecio(thisx){
	var contador = $(thisx).data("contador");
	var idselect = $(thisx).id;
	var valoroption = $(thisx).val();		
	var numart = $("#clcodigosku"+contador).val();
	
	console.log(contador + " "+ numart+" "+valoroption);
	$.ajax({             	
	   		 url:'${pageContext.request.contextPath}/cloptionskuprecio',
	   			type: "post",	
	   			data:{numart:numart,valoroption:valoroption},
	   	       }).done(function(data, textStatus, jqXHR  ){
	   	           if ( console && console.log ) 
	   	           {			             			  
	   	        		console.log(data);  
	  	   	      		if(data.exito=="true"){	        		  
		        		   msg_ok(data.error)	
		        		   $("#preciohr"+contador).attr("value",data.error );
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
}
function cleliminarElemento(idelement){
	$("#"+idelement).remove();
	var numerodelementos = document.getElementsByClassName("verificarcontador").length;
	$("#clagregarequipaje").data('number', numerodelementos );
}

function clagregarequipaje(thisx){
	var contador = $(thisx).data('number');
	contador = contador +1; 
	console.log($(thisx).data('number') +" "+ contador);
	$(thisx).data('number',contador);
	
 var htmlfilas = '<div class="row" id="'+contador+'">'
		+'<div class="col-md-1 estyletable">'
		+'	<div class="form-group">'
		+'		<label for="clarticulo'+contador+'"># : </label> <input type="number" value="'+contador+'" class="form-control verificarcontador" id="clcontador'+contador+'" name="clcontador'+contador+'">'
	   +'	</div>'
		+'</div>'
		+'<div class="col-md-2 styletable">'
		+'<div class="form-group">'
		+' <label for="clarticulo'+contador+'">Equipaje : <span class="danger">*</span> </label> <input type="text" class="form-control seleccionarArticulo required" data-contador="'+contador+'" id="clarticulo'+contador+'" placeholder="Click" name="clarticulo'+contador+'" readonly>'
		+'</div>'
		+'</div>'
		+'<div class="col-md-1 styletable">'
		+'<div class="form-group">'
		+'<label for="clcodigosku'+contador+'"> Codigo: <span class="danger">*</span> </label> <input type="text" class="form-control required" id="clcodigosku'+contador+'" name="clcodigosku'+contador+'" readonly> </div>'
		+'</div>'
		+'<div class="col-md-2 styletable">'
		+'<div class="form-group">'
		+'<label for="tipoClass'+contador+'">Tamaño :</label> <select class="custom-select form-control clselectclass required" onchange="seleccionarPrecio(this)" data-contador="'+contador+'" id="tipoClass'+contador+'" name="tipoClass'+contador+'" data-placeholder="Type to search prices" name="wintType1">'
		+'<option value="">--Seleccione--</option>'
		+'</select>'
		+'</div>'
		+'</div>'
		+'<div class="col-md-2 styletable">'
		+'<div class="form-group">'
		+'<label for="clcolor'+contador+'">Color : <span class="danger">*</span> </label> <input type="text" class="form-control required" id="clcolor'+contador+'" name="clcolor'+contador+'" style="text-transform: uppercase">'
		+'</div>'
		+'</div>'
		+'<div class="col-md-2 styletable">'
		+'<div class="form-group">'
		+'<label for="preciohr'+contador+'">Precio X Hora :</label> <input type="number" value="0" class="form-control required" id="preciohr'+contador+'" name="preciohr'+contador+'" readonly>'
		+'</div>'
		+'</div>'
		+'<div class="col-md-1 styletable">'
		+'<div class="form-group">'
		+'<label for="clcantidad'+contador+'">Cantidad :</label> <input type="number" value="1" class="form-control required" id="clcantidad'+contador+'" name="clcantidad'+contador+'">'
		+'</div>'
		+'</div>'
		+'<div class="col-md-1 styletable">'
		+'<br> <a href="#" class="btn waves-effect waves-light btn-rounded btn-xs btn-danger" onclick="cleliminarElemento('+contador+')" data-number="'+contador+'"><i></i>Eliminar </a>'
		+'</div>'
		+'</div>';

	$("#clTableEquipaje").prepend(htmlfilas);
	// agregar opcion clasificaciones 
	OptionsClasificacion( contador );
}

</script>
</body>
</html>