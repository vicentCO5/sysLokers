<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
<div class="alert alert-danger" id="errorsAlms"  style="display:none" ></div>
		<div id="edit-almacen"  >
			<form:form  method="post" id="formeditAlmacen">
				<fieldset class="form">
				<form:hidden path="clave" />
				<div class="row">
				<div class="col-sm-6">
					<div class="form-group">
						<label for = "clave">Clave: </label>
						<form:input  path="clave" class="form-control" disabled="true"/>
					</div>
				</div>
				<div class="col-sm-6">
					<div class="form-group">
							<label for = "empresa_id">Codigo Empresa: </label>
							<form:input  path="empresa_id" class="form-control" disabled="true"/>
					</div>
				</div>
				</div>
				<div class="row">
				<div class="col-sm-6">
					<div class="form-group">
						<label for = "name">Nombre: </label>
						<form:input  path="nombre" type="text" class="form-control"/>
					</div>
				</div>
				<div class="col-sm-6">
				<div class="form-group">
						<label for = "pais">Pais: </label>
						<form:input path="pais" type="text" class="form-control"/>
					</div>
				</div>
				</div>
				 <div class="form-group">
						<label for = "direccion">Direccion: </label>
						<form:input path="direccion" type="text" class="form-control"/>
					</div>
				<div class="row">
				<div class="col-sm-3">
					<div class="form-group">
						<label for = "colonia">Colonia: </label>
						<form:input path="colonia" type="text" class="form-control"/>
					</div>
				</div>
				<div class="col-sm-3">
				<div class="form-group">
						<label for = "numExterior">Num.Exterior: </label>
						<form:input path="numExterior" type="text" class="form-control"/>
					</div>
				</div>
				<div class="col-sm-3">
				<div class="form-group">
						<label for = "numInterior">Num.Interior: </label>
						<form:input path="numInterior" type="text" class="form-control"/>
					</div>
				</div>
				</div>
					
				</fieldset>
				<fieldset>
				<br>
				<div class="col-sm-12">
					<button type="button" class="btn btn-primary btn-sm" id="editalmacen" title="Guardar">
					<i class="ace-icon fa fa-floppy-o bigger-160"></i>
					</button>
				</div>
				</fieldset>
			</form:form>
		</div>
</body>
<script>
$("#editalmacen").on("click", function(e){
    var formData = new FormData(document.getElementById("formeditAlmacen"));

    $.ajax({
        url: '${pageContext.request.contextPath}/updatealmacen',
        type: "post",
        dataType: "json",
        data: formData,
        cache: false,
        contentType: false,
        processData: false
    }).done(function(data){
    	console.log(data.exito);
    	if(data.exito=="true"){
    		tablealmacenes.ajax.reload( null, false );	
    		$("#modalaps").modal('hide');
			}else{
	 			$("#errorsAlms").show();
	 			$("#errorsAlms").html("");
 	 			for( i=0;i<data.error.length;i++){
  	 				$("#errorsAlms").append("-"+data.error[i]+"<br/>");
  	 	 		}
			}
    });
});
</script>