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
		<div class="alert alert-danger" id="errorsdepto"  style="display:none" ></div>
			<form id="fileLForm" enctype="multipart/form-data" name="filedeptoForm">
				<input id="filedepto" type="file" name="filedepto" accept=".xls" required /> <br>

				<fieldset class="buttons">
					<div id="btnUploaddepto" class="btn btn-primary">Cargar</div>
				</fieldset>

			</form>		
		</div>
	</div>


</body>
</html>
<script type="text/javascript">
	$(function() {
		$('#filedepto').ace_file_input({
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
		$("#btnUploaddepto").click(function() {
			var oData = new FormData(document.forms.namedItem("filedeptoForm"));
			var file = $('#filedepto').val();
			var xls ='';
			if(file){
				xls = file.split(".");
				
				if(xls[1]=='xls' || xls[1]=='XLS'){
					$.ajax({
				         url:"${pageContext.request.contextPath}/doUploadDepto",
				         type:'POST',
				         data:oData,
				         dataType:'JSON',
				         processData: false,  
				         contentType: false ,
				         success:function (data) {
				        	 if(data.exito=="true"){
				        		 alert("Carga exitosa");
				        		 if ( ! $.fn.DataTable.isDataTable(tabledeptos ) ) {
				        			 tabledeptos = $('#deptos-table').DataTable({
				        			 		"ajax":"${pageContext.request.contextPath}/showdeptos",
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
				        			tabledeptos.ajax.reload( null, false );
				        		}
				        		 $('#modalaps').modal('hide');
				        	 }else{
									$("#errorsdepto").show();
		 	 	 	 				$("#errorsdepto").html("");
		 	 	 	 				for( i=0;i<data.error.length;i++){
		 	 	 	 					$("#errorsdepto").append("-"+data.error[i]+"<br/>");
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