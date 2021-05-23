<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- <script -->
<%-- 	src="${pageContext.request.contextPath}/resources/js/jquery.ajaxForm.js"></script> --%>

</head>
<body>
	<h4>Archivo con extensi&oacute;n .xls</h4>
	<div class="row">
		<div class="col-sm-12">
		<div class="alert alert-danger" id="errorsAlmacen"  style="display:none" ></div>
			<form id="fileForm" enctype="multipart/form-data" name="fileForm">
				<input id="filealm" type="file" name="filealm" accept=".xls" required /> <br>

				<fieldset class="buttons">
					<div id="btnUpload" class="btn btn-primary">Cargar</div>
				</fieldset>

			</form>		
		</div>
	</div>


</body>
</html>
<script type="text/javascript">
	$(function() {
		$('#filealm').ace_file_input({
			no_file : 'No File ...',
			btn_choose : 'Choose',
			btn_change : 'Change',
			droppable : false,
			onchange : null,
			thumbnail : false
		//| true | large
		//whitelist:'gif|png|jpg|jpeg'
		//blacklist:'exe|php'
		//onchange:''
		//
		});
		$("#btnUpload").click(function() {
			var oData = new FormData(document.forms.namedItem("fileForm"));
			var file = $('#filealm').val();
			var xls ='';
			if(file){
				xls = file.split(".");
				
				if(xls[1]=='xls' || xls[1]=='XLS'){
					$.ajax({
				         url:"${pageContext.request.contextPath}/doUploadalm",
				         type:'POST',
				         data:oData,
				         dataType:'JSON',
				         processData: false,  
				         contentType: false ,
				         success:function (data) {
				        	 if(data.exito=="true"){
				        		 alert("Carga exitosa");
				        		 tablealmacenes.ajax.reload( null, false );	
				        		 $('#modalaps').modal('hide');
				        	 }else{
									$("#errorsAlmacen").show();
		 	 	 	 				$("#errorsAlmacen").html("");
		 	 	 	 				for( i=0;i<data.error.length;i++){
		 	 	 	 					$("#errorsAlmacen").append("-"+data.error[i]+"<br/>");
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