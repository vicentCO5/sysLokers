<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

</head>
<body>
	<h4>Archivo con extensi&oacute;n .xls</h4>
	<div class="row">
		<div class="col-sm-12">
		<div class="alert alert-danger" id="errorsudm"  style="display:none" ></div>
			<form id="fileudmForm" enctype="multipart/form-data" name="fileudmForm">
				<input id="fileundm" type="file" name="fileundm" accept=".xls" required /> <br>

				<fieldset class="buttons">
					<div id="btnUploadudmed" class="btn btn-primary">Cargar</div>
				</fieldset>

			</form>		
		</div>
	</div>


</body>
</html>
<script type="text/javascript">
	$(function() {
		$('#fileundm').ace_file_input({
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
		$("#btnUploadudmed").click(function() {
			var oData = new FormData(document.forms.namedItem("fileudmForm"));
			var file = $('#fileundm').val();
			var xls ='';
			if(file){
				xls = file.split(".");
				
				if(xls[1]=='xls' || xls[1]=='XLS'){
					$.ajax({
				         url:"${pageContext.request.contextPath}/doUploadUndMed",
				         type:'POST',
				         data:oData,
				         dataType:'JSON',
				         processData: false,  
				         contentType: false ,
				         success:function (data) {
				        	 if(data.exito=="true"){
				        		 alert("Carga exitosa");
				        		 if ( ! $.fn.DataTable.isDataTable(tableudm ) ) {
				        			 tableudm = $('#udm-table').DataTable({
				        			 		"ajax":"${pageContext.request.contextPath}/showundmed",
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
					        			 	    	{"data":"codigo"}, 
					    		 		       		{"data":"descripcion"},
					    		 		       		{"data":"undmin"},
					    		 		       		{"data":"estatus"},
					    		 		       		{"data":"accion"}
				        			 		       	],		 	   
				        			 	   				    
				        			 	    });

				        		}else{
				        			tableudm.ajax.reload( null, false );
				        		}
				        		 $('#modalaps').modal('hide');
				        	 }else{
									$("#errorsudm").show();
		 	 	 	 				$("#errorsudm").html("");
		 	 	 	 				for( i=0;i<data.error.length;i++){
		 	 	 	 					$("#errorsudm").append("-"+data.error[i]+"<br/>");
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