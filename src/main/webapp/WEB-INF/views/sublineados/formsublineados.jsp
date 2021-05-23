<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

</head>
<body>
	<h4>Archivo con extensi&oacute;n .xls</h4>
	<div class="row">
		<div class="col-sm-12">
		<div class="alert alert-danger" id="errorsSublineados"  style="display:none" ></div>
			<form id="fileSubLDosForm" enctype="multipart/form-data" name="filesublineadosForm">
				<input id="fileSublineados" type="file" name="fileSublineados" accept=".xls" required /> <br>

				<fieldset class="buttons">
					<div id="btnUploadSublineados" class="btn btn-primary">Cargar</div>
				</fieldset>

			</form>		
		</div>
	</div>


</body>
</html>
<script type="text/javascript">
	$(function() {
		$('#fileSublineados').ace_file_input({
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
		$("#btnUploadSublineados").click(function() {
			var oData = new FormData(document.forms.namedItem("filesublineadosForm"));
			var file = $('#fileSublineados').val();
			var xls ='';
			if(file){
				xls = file.split(".");
				
				if(xls[1]=='xls' || xls[1]=='XLS'){
					$.ajax({
				         url:"${pageContext.request.contextPath}/doUploadSubLineados",
				         type:'POST',
				         data:oData,
				         dataType:'JSON',
				         processData: false,  
				         contentType: false ,
				         success:function (data) {
				        	 if(data.exito=="true"){
				        		 alert("Carga exitosa");
				        		 if ( ! $.fn.DataTable.isDataTable(tablesublineasdos ) ) {
				        			 tablesublineasdos = $('#sublineasdos-table').DataTable({
				        			 		"ajax":"${pageContext.request.contextPath}/showSublineasDos",
				        			 		"destroy": true,
				        			 	    paging: false,
				        			 	    searching: true,
				        			 	    "ordering": false,
				        			 	    "scrollY": "380px",
				        			 	    "pageLength": '100',
				        			         "scrollCollapse": true,
				        			         "paging": true,
				        			 	    info:true,
				        			 	    "columns":[		
					        			 	    	{"data":"indice"}, 
					        			 	    	{"data":"clave"}, 
					    		 		       		{"data":"descripcion"},
					    		 		       		{"data":"empresa"},
					    		 		       		{"data":"accion"}
				        			 		       	],		 	   
				        			 	   				    
				        			 	    });

				        		}else{
				        			tablesublineasdos.ajax.reload( null, false );
				        		}
				        		 $('#modalaps').modal('hide');
				        	 }else{
									$("#errorsSublineados").show();
		 	 	 	 				$("#errorsSublineados").html("");
		 	 	 	 				for( i=0;i<data.error.length;i++){
		 	 	 	 					$("#errorsSublineados").append("-"+data.error[i]+"<br/>");
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