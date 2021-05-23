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
		<div class="alert alert-danger" id="errorsmarca"  style="display:none" ></div>
			<form id="fileMForm" enctype="multipart/form-data" name="filemarcaForm">
				<input id="filemarca" type="file" name="filemarca" accept=".xls" required /> <br>

				<fieldset class="buttons">
					<div id="btnUploadmarca" class="btn btn-primary">Cargar</div>
				</fieldset>

			</form>		
		</div>
	</div>


</body>
</html>
<script type="text/javascript">
	$(function() {
		$('#filemarca').ace_file_input({
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
		$("#btnUploadmarca").click(function() {
			var oData = new FormData(document.forms.namedItem("filemarcaForm"));
			var file = $('#filemarca').val();
			var xls ='';
			if(file){
				xls = file.split(".");
				
				if(xls[1]=='xls' || xls[1]=='XLS'){
					$.ajax({
				         url:"${pageContext.request.contextPath}/doUploadmarca",
				         type:'POST',
				         data:oData,
				         dataType:'JSON',
				         processData: false,  
				         contentType: false ,
				         success:function (data) {
				        	 if(data.exito=="true"){
				        		 alert("Carga exitosa");
				        		 if ( ! $.fn.DataTable.isDataTable(tablemarcas ) ) {
				        			 tablemarcas = $('#marcas-table').DataTable({
				        			 		"ajax":"${pageContext.request.contextPath}/showmarcas",
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
				        			 				    {"data":"empresa"},				
				        			 					{"data":"clave"}, 						  			       	
				        			 		       		{"data":"descripcion"},
				        			 		       		{"data":"clasificacion"},
				        			 		       		{"data":"accion"}
				        			 		       	],		 	   
				        			 	   				    
				        			 	    });

				        		}else{
				        		 tablemarcas.ajax.reload( null, false );
				        		}
				        		 $('#modalaps').modal('hide');
				        	 }else{
									$("#errorsmarca").show();
		 	 	 	 				$("#errorsmarca").html("");
		 	 	 	 				for( i=0;i<data.error.length;i++){
		 	 	 	 					$("#errorsmarca").append("-"+data.error[i]+"<br/>");
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