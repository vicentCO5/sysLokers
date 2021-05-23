<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<div class="row">
	<div class="col-xs-12">
		<div class="page-header">
			<h1>Proveedores</h1>
		</div>
		<!-- /.page-header -->
		<div class="row">
			<div class="alert alert-info">
				<button class="btn btn-sm btn-primary" id="importProv">
					<i class="ace-icon glyphicon glyphicon-upload bigger-110"></i>
					Importar Catalogo Proveedores
				</button>
				<!-- 				<button class="btn btn-sm btn-primary" id="exportAlm"> -->
				<!-- 					<i class="ace-icon glyphicon glyphicon-download bigger-110"></i> -->
				<!-- 					Exportar -->
				<!-- 				</button> -->
				<button class="btn btn-sm btn-success" id="searchProv">
					<i class="ace-icon glyphicon glyphicon-search bigger-110"></i>
					Consultar
				</button>
				<button class="btn btn-sm btn-danger" id="deletesProveedores"
					onclick="deleteallProveedores()">
					<i class="ace-icon glyphicon glyphicon-trash bigger-110"></i>
					Eliminar
				</button>
			</div>
		</div>
		<!-- 		<div class="hr hr-8 hr-dotted"></div> -->
		<!-- /.col -->
		<div class="row">
			<div class="col-xs-12" id="listprovs"></div>
			<div class="col-sm-12">
				<div class="clearfix">
					<div class="pull-right tableTools-container"></div>
				</div>

				<div class="table-header">Lista de Proveedores
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
				<div>
					<table class="table  table-bordered table-hover"
						id="proveedores-table">
						<thead>
							<tr>
								<th class="center">#</th>
								<th class="center">Clave</th>
								<th class="center">Descripci&oacute;n</th>
								<th class="center">Empresa</th>
								<th class="center">Tipo</th>
								<th class="center">Direcci&oacute;n</th>
								<th class="center">Tel&eacute;fono</th>
								<th class="center">Celular</th>
								<th class="center">Mail</th>
								<th class="center">Pa&iacute;s</th>
								<th class="center">Raz&oacute;n Social</th>
								<th class="center">Estatus</th>
								<th class="center">Acciones</th>
							</tr>
						</thead>
						<tbody>

						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<!-- /.col -->
</div>
<!-- /.row -->
</html>
<script>
	var tableproveedores;
	$(function() {
		$("#importProv").click(function() {

			$.ajax({
				url : '${pageContext.request.contextPath}/getimportprov',
				dataType : 'html',
				success : function(data) {
					$("#modal-body").html(data);
					$("#modal-header").html("<h4>Importar Proveedores</h4>");
					$('#modalaps').modal('show');
				},
				error : function(data) {
					alert("Ocurrio un error");
				}

			});
		});

		$("#searchProv").click(function() {
			tableproveedores = $('#proveedores-table').DataTable({
				"ajax" : "${pageContext.request.contextPath}/showproveedores",
				"destroy" : true,
				paging : false,
				searching : true,
				"ordering" : false,
				"scrollY" : "380px",
				"pageLength" : '100',
				"scrollCollapse" : true,
				"paging" : true,
				info : true,
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
		});

	});
	
	function deleteallProveedores(){
		if(!confirm("Desea eliminar todos los Proveedores?"))
			return 
		$.ajax({
			url:'${pageContext.request.contextPath}/deleteallProveedores',
			type: "post",
			dataType : 'JSON',
			data:{},
			}).done(function(data){		
				if(data.exito=="true"){
					alert(data.mensaje);
					tableproveedores.ajax.reload( null, false );	
				}else{					
	 	 				for( i=0;i<data.error.length;i++){
	 	 					alert(data.error[i]);
	 	 	 				}
				}
		    });
		
	}
	function deleteprov(id,idempresa){
		$.ajax({
			url:'${pageContext.request.contextPath}/deleteProveedor',
			type: "post",
			dataType : 'html',
			data:{id:id,idempresa:idempresa},
			}).done(function(data){		
				alert(data);
				tableproveedores.ajax.reload( null, false );	 							
		    });
		}
</script>