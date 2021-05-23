<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
<div class="alert alert-danger" id="errorsclasificador"  style="display:none" ></div>
		<div id="create-clasificador"  >
			<form:form  method="post" id="formCrearClasificador">
				<fieldset class="form">
				<div class="form-group has-info">
						<label for = "codigo">Clave: </label>
						<form:input  path="codigo" class="form-control"/>
				</div>
				<div class="form-group has-info">
						<label for = "descripcion">Nombre: </label>
						<form:input  path="descripcion" type="text" class="form-control"/>
					</div>
				<div class="form-group has-info">
					<label for="nivel">Nivel: </label>
					<form:input path="nivel" type="text" class="form-control input-sm" maxlength='4' size='4'/>
				</div>
				<div class="form-group has-info">
					<label for="nivel">Nivel: </label>
					<select	id='idempresa' class='form-control'></select>
				</div>

			</fieldset>
				<fieldset>
				<br>
				<div class="col-sm-12">
					<button type="button" class="btn btn-primary btn-sm" id="crearEmpresa" title="Guardar">
					<i class="ace-icon fa fa-floppy-o bigger-160"></i>
					</button>
				</div>
				</fieldset>
			</form:form>
		</div>
</body>
<script>
$(function(){
	
$("#crearEmpresa").on("click", function(e){
    var formData = new FormData(document.getElementById("formCrearEmpresa"));
    
    $.ajax({
        url: '${pageContext.request.contextPath}/saveempresa',
        type: "post",
        dataType: "json",
        data: formData,
        cache: false,
        contentType: false,
        processData: false
    }).done(function(data){
    	console.log(data.exito);
    	if(data.exito=="true"){
    		jQuery.ajax({
    			type:'POST',
    			url:'${pageContext.request.contextPath}/getindexempresa',
    			beforeSend: function() {
    				
    			},success:function(data,textStatus){
    				jQuery('#divprincipal').html(data);
    				$("#modalaps").modal('hide');
    				},
    				
    			});
				
			}else{
	 			$("#errorsEmpresa").show();
	 			$("#errorsEmpresa").html("");
 	 			for( i=0;i<data.error.length;i++){
  	 				$("#errorsEmpresa").append("-"+data.error[i]+"<br/>");
  	 	 		}
			}
    });
});
});
</script>