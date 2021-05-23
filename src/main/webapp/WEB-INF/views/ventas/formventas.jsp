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
		<div class="alert alert-danger" id="errorsVtas"  style="display:none" ></div>
			<form id="filevtasForm" enctype="multipart/form-data" name="filevtasForm">
				<input id="fileventas" type="file" name="fileventas" accept=".xls" required /> <br>

				<fieldset class="buttons">
					<div id="btnUploadvtas" class="btn btn-primary">Cargar</div>
				</fieldset>

			</form>		
		</div>
	</div>


</body>
</html>

<script>
$(function() {
	$('#fileventas').ace_file_input({
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
	
	$("#btnUploadvtas").click(function() {
		var oData = new FormData(document.forms.namedItem("filevtasForm"));
		var file = $('#fileventas').val();
		var xls ='';
		if(file){
			xls = file.split(".");
			
			if(xls[1]=='xls' || xls[1]=='XLS'){
				$.ajax({
			         url:"${pageContext.request.contextPath}/doUploadventas",
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
								$("#errorsVtas").show();
	 	 	 	 				$("#errorsVtas").html("");
	 	 	 	 				for( i=0;i<data.error.length;i++){
	 	 	 	 					$("#errorsVtas").append("-"+data.error[i]+"<br/>");
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