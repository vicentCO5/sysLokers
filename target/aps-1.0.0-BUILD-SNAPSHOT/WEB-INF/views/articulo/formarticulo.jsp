<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- <script -->
<%-- 	src="${pageContext.request.contextPath}/resources/js/jquery.ajaxForm.js"></script> --%>

</head>
<body>
<div class="col-lg-12" >
	<div class="card">
	 	<div class="card-body" >
	 	    <h4 class="card-title">Equipaje</h4>
			<h6 class="card-subtitle">Equipajes y clasificaciones</h6>
			<div class="row">
				<div class="col-md-12">
				
					<div class="alert alert-info">					
							<form id="filearticuloForm" enctype="multipart/form-data" name="filearticuloForm">
								<input id="filearticulo" type="file" name="filearticulo" accept=".xls" required /> <br>				
								<fieldset class="buttons">
									<div id="btnUploadarticulo" class="btn btn-primary">Cargar</div>
								</fieldset>				
							</form>								
						<button class="btn waves-effect waves-light btn-rounded btn-success" id="newCalsificacion">
							<i class="ace-icon glyphicon glyphicon-upload bigger-110"></i>
							Nuevo
						</button>
						<button class="btn waves-effect waves-light btn-rounded btn-success" id="updateClasificacion">
							<i class="ace-icon glyphicon glyphicon-search bigger-110"></i>
							Actualizar
						</button>
					</div>
					<div class="alert alert-danger" id="errorsArticulo"  style="display:none" ></div>
				</div>				
			</div>
			<div class="table-responsive m-t-10" >
				<table class="display nowrap table table-hover table-striped table-bordered" id="clasificacion-table">
					<thead>
						<tr>						   											
							<th class="center">#</th>
							<th class="center">Clave</th>
							<th class="center">Descripcion</th>	
							<th class="center">Fecha creación</th>							
							<th class="center">Estatus</th>							
							<th class="center">Acciones</th>
						</tr>
					</thead>
					<tbody>
						
					</tbody>
				</table>
			</div>
	 	</div>
	 </div>
</div>

</body>
</html>

<script>
$(function() {
	$('#filearticulo').ace_file_input({
		no_file:'No File ...',
		btn_choose:'Choose',
		btn_change:'Change',
		droppable:false,
		onchange:null,
		thumbnail:false //| true | large
		//whitelist:'gif|png|jpg|jpeg'
		//blacklist:'exe|php'
		//onchange:''
		//
	});
	
	$("#btnUploadarticulo").click(function() {
		var oData = new FormData(document.forms.namedItem("filearticuloForm"));
		var file = $('#filearticulo').val();
		var xls ='';
		if(file){
			xls = file.split(".");
			
			if(xls[1]=='xls' || xls[1]=='XLS'){
				$.ajax({
			         url:"${pageContext.request.contextPath}/doUploadarticulo",
			         type:'POST',
			         data:oData,
			         dataType:'JSON',
			         processData: false,  
			         contentType: false ,
			         success:function (data) {
			        	 if(data.exito=="true"){
			        		 alert("Carga exitosa");
			        		 $('#modalaps').modal('hide');
			        	 }else{
								$("#errorsArticulo").show();
	 	 	 	 				$("#errorsArticulo").html("");
	 	 	 	 				for( i=0;i<data.error.length;i++){
	 	 	 	 					$("#errorsArticulo").append("-"+data.error[i]+"<br/>");
	 	 	 	 	 				}
			            }

			         }
		         });
				
			}else 
				alert("El tipo de archivo debe ser XLS");	
			
		}else
			alert("NO existe archivo");
	});
});

	
</script>