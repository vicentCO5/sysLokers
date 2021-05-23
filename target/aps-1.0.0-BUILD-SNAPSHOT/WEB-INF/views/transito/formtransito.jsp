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
		<div class="alert alert-danger" id="errorsTransito"  style="display:none" ></div>
			<form id="filetransitoForm" enctype="multipart/form-data" name="filetransitoForm">
				<input id="filetransito" type="file" name="filetransito" accept=".xls" required /> <br>

				<fieldset class="buttons">
					<div id="btnUploadtransito" class="btn btn-primary">Cargar</div>
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
					<th class="center">COD ALMACEN </th>						
					<th class="center">COD SKU</th>																	
					<th class="center">CANTIDAD INICIAL</th>
					<th class="center">CANTIDAD FINAL</th>
					<th class="center">UNIDAD</th>						
					<th class="center">FECHA LLEGADA</th>																
				</tr>
			</thead>			
		</table>
		</div>
	</div>
</body>
<script type="text/javascript">
	$(function(){
		$('#filetransito').ace_file_input({
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
		// importar o cargar archivo excel 
		$("#btnUploadtransito").click(function() {
			var oData = new FormData(document.forms.namedItem("filetransitoForm"));
			var file = $('#filetransito').val();
			var xls ='';
			if(file){
				xls = file.split(".");
				
				if(xls[1]=='xls' || xls[1]=='XLS'){
					$.ajax({
				         url:"${pageContext.request.contextPath}/doUploadtransito",
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
									$("#errorsTransito").show();
		 	 	 	 				$("#errorsTransito").html("");
		 	 	 	 				for( i=0;i<data.error.length;i++){
		 	 	 	 					$("#errorsTransito").append("-"+data.error[i]+"<br/>");
		 	 	 	 	 				}
				            }

				         }
			         });
					
				}else 
					alert("El tipo de archivo debe ser XLS");	
				
			}else
				alert("NO existe archivo");
		});
		
	})
</script>
</html>