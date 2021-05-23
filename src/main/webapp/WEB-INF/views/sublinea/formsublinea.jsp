<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

</head>
<body>
	<h4>Archivo con extensi&oacute;n .xls</h4>
	<div class="row">
		<div class="col-sm-12">
		<div class="alert alert-danger" id="errorsSublinea"  style="display:none" ></div>
			<form id="fileSubLForm" enctype="multipart/form-data" name="filesublineaForm">
				<input id="fileSublinea" type="file" name="fileSublinea" accept=".xls" required /> <br>

				<fieldset class="buttons">
					<div id="btnUploadSublinea" class="btn btn-primary">Cargar</div>
				</fieldset>

			</form>		
		</div>
	</div>


</body>
</html>
<script type="text/javascript">
	$(function() {
		$('#fileSublinea').ace_file_input({
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
		$("#btnUploadSublinea").click(function() {
			var oData = new FormData(document.forms.namedItem("filesublineaForm"));
			var file = $('#fileSublinea').val();
			var xls ='';
			if(file){
				xls = file.split(".");
				
				if(xls[1]=='xls' || xls[1]=='XLS'){
					$.ajax({
				         url:"${pageContext.request.contextPath}/doUploadSubLinea",
				         type:'POST',
				         data:oData,
				         dataType:'JSON',
				         processData: false,  
				         contentType: false ,
				         success:function (data) {
				        	 if(data.exito=="true"){
				        		 alert("Carga exitosa");
				        		 if ( ! $.fn.DataTable.isDataTable(tablesublineas ) ) {
				        			 tablesublineas = $('#sublineas-table').DataTable({
				        			 		"ajax":"${pageContext.request.contextPath}/showSublineas",
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
				        			tablesublineas.ajax.reload( null, false );
				        		}
				        		 $('#modalaps').modal('hide');
				        	 }else{
									$("#errorsSublinea").show();
		 	 	 	 				$("#errorsSublinea").html("");
		 	 	 	 				for( i=0;i<data.error.length;i++){
		 	 	 	 					$("#errorsSublinea").append("-"+data.error[i]+"<br/>");
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