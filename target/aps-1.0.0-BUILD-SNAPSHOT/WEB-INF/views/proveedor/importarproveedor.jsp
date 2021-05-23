<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

</head>
<body>
	<h4>Archivo con extensi&oacute;n .xls</h4>
	<div class="row">
		<div class="col-sm-12">
		<div class="alert alert-danger" id="errorsProveedor"  style="display:none" ></div>
			<form id="fileprovForm" enctype="multipart/form-data" name="fileprovForm">
				<input id="fileproveedor" type="file" name="fileproveedor" accept=".xls" required /> <br>

				<fieldset class="buttons">
					<div id="btnUploadProveedor" class="btn btn-primary">Cargar</div>
				</fieldset>

			</form>		
		</div>
	</div>


</body>
</html>
<script type="text/javascript">
	$(function() {
		$('#fileproveedor').ace_file_input({
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
		$("#btnUploadProveedor").click(function() {
			var oData = new FormData(document.forms.namedItem("fileprovForm"));
			var file = $('#fileproveedor').val();
			var xls ='';
			if(file){
				xls = file.split(".");
				
				if(xls[1]=='xls' || xls[1]=='XLS'){
					$.ajax({
				         url:"${pageContext.request.contextPath}/doUploadProveedor",
				         type:'POST',
				         data:oData,
				         dataType:'JSON',
				         processData: false,  
				         contentType: false ,
				         success:function (data) {
				        	 if(data.exito=="true"){
				        		 alert("Carga exitosa");
				        		 if ( ! $.fn.DataTable.isDataTable(tableproveedores ) ) {
				        			 tableproveedores = $('#proveedores-table').DataTable({
				        			 		"ajax":"${pageContext.request.contextPath}/showproveedores",
				        			 		"destroy": true,
				        			 	    paging: false,
				        			 	    searching: true,
				        			 	    "ordering": false,
				        			 	    "scrollY": "380px",
				        			 	    "pageLength": '100',
				        			        "scrollCollapse": true,
				        			        "paging": true,
				        			 	    info:true,
				        			 	  	 "columns" : [ 
				        						{"data" : "indice"},
				        						{"data" : "clave"},
				        						{"data" : "descripcion"},
				        						{"data" : "empresa"},
				        						{"data" : "tipo"},
				        						{"data" : "direccion"},
				        						{"data" : "telefono"},
				        						{"data" : "celular"},
				        						{"data" : "mail"},
				        						{"data" : "pais"}, 
				        						{"data" : "razonsocial"},
				        						{"data" : "estatus"},
				        						{"data" : "accion"}
				        						],		 	   
				        			 	   				    
				        			 	    });

				        		}else{
				        			tableproveedores.ajax.reload( null, false );
				        		}
				        		 $('#modalaps').modal('hide');
				        	 }else{
									$("#errorsProveedor").show();
		 	 	 	 				$("#errorsProveedor").html("");
		 	 	 	 				for( i=0;i<data.error.length;i++){
		 	 	 	 					$("#errorsProveedor").append("-"+data.error[i]+"<br/>");
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