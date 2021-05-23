<html>
<head>
<meta charset="ISO-8859-1">
<!-- <script -->
<%-- 	src="${pageContext.request.contextPath}/resources/js/jquery.ajaxForm.js"></script> --%>
</head>
<body>
	<h4>Archivo con extensi&oacute;n .xls</h4>
	<div class="row">
		<div class="col-sm-12">
		<div class="alert alert-danger" id="errorsTraspaso"  style="display:none" ></div>
			<form id="filetraspasoForm" enctype="multipart/form-data" name="filetraspasoForm">
				<input id="filetraspaso" type="file" name="filetraspaso" accept=".xls" required /> <br>

				<fieldset class="buttons">
					<div id="btnUploadtraspaso" class="btn btn-primary">Cargar</div>
				</fieldset>

			</form>		
		</div>
	</div>
	<div class="row"> 
		<p>Estructura xls</p>
		<div class="col-sm-12">
		 <table class="table table-bordered" id="">
				<thead>
					<tr>					
						<th class="center">NUMERO FOLIO</th>						
						<th class="center">COD ALMACEN ORIGEN</th>
						<th class="center">COD ALMACEN DESTINO</th>
						<th class="center">COD SKU</th>																	
						<th class="center">CANTIDAD</th>
						<th class="center">UNIDAD</th>						
						<th class="center">FECHA MOVIMIENTO</th>
						<th class="center">ESTATUS</th>											
					</tr>
				</thead>			
			</table>
		</div>
	</div>
</body>
</html>
<script>
$(function() {
	$('#filetraspaso').ace_file_input({
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
	
	$("#btnUploadtraspaso").click(function() {
		var oData = new FormData(document.forms.namedItem("filetraspasoForm"));
		var file = $('#filetraspaso').val();
		var xls ='';
		if(file){
			xls = file.split(".");
			
			if(xls[1]=='xls' || xls[1]=='XLS'){
				$.ajax({
			         url:"${pageContext.request.contextPath}/doUploadtraspaso",
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
								$("#errorsTraspaso").show();
	 	 	 	 				$("#errorsTraspaso").html("");
	 	 	 	 				for( i=0;i<data.error.length;i++){
	 	 	 	 					$("#errorsTraspaso").append("-"+data.error[i]+"<br/>");
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