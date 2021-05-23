<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
<div class="alert alert-danger" id="errorsEmpresa"  style="display:none" ></div>
		<div id="create-empresa"  >
			<form:form  method="post" id="formCrearEmpresa">
				<fieldset class="form">
				<div class="form-group has-info">
						<label for = "clave">Clave: </label>
						<form:input  path="clave" class="form-control"/>
				</div>
				<div class="form-group has-info">
						<label for = "name">Nombre: </label>
						<form:input  path="nombre" type="text" class="form-control"/>
					</div>
					<div class="form-group has-info">
						<label for = "pais">Pais: </label>
						<form:input path="pais" type="text" class="form-control"/>
					</div>
					<div class="form-group has-info">
						<label for ="ruc">Ruc:</label>
						<form:input path="ruc" type="text" class="form-control"/>
					</div>
<!-- 					<div class="form-group"> -->
<!-- 						<label for = "logo">Logo: </label> -->
<%-- 						<form:input path="logo" type="file" class="form-control" onChange="cambiodeImagen(this);"/> --%>
<!-- 					</div> -->
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
function cambiodeImagen(input){
	 if (input.files && input.files[0]) {
		    var reader = new FileReader();
		    reader.onload = function (e) {
		      $("#imagen").html('<img src="'+e.target.result+'" alt="logo"width="300">');
		    };
		    reader.readAsDataURL(input.files[0]);
		  }
	 }
$("#crearEmpresa").on("click", function(e){
    var formData = new FormData(document.getElementById("formCrearEmpresa"));
    
//     if($("#logo").val()==""){
//         console.log("ssese");
//     	formData.append("logoVacio",true);
//         }else{
//         	formData.append("logoVacio",false);
//         }
    
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
</script>