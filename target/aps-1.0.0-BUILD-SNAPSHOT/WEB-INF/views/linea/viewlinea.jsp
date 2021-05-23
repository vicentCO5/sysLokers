<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<div class="row">
	<div class="col-sm-12">
		<div class="tabbable">
			<ul class="nav nav-tabs" id="myTab">
				<li class="active"><a data-toggle="tab" href="#linea"> <i
						class="blue ace-icon fa fa-folder-open bigger-120"></i> Linea
				</a></li>

				<li><a data-toggle="tab" href="#Sublinea"> <i
						class="blue ace-icon fa fa-folder-open bigger-120"></i>Sublinea
				</a></li>
				<li><a data-toggle="tab" href="#Sublineados"><i
						class="blue ace-icon fa fa-folder-open bigger-120"></i>
						Sublineados </a></li>

			</ul>

			<div class="tab-content">
				<div id="linea" class="tab-pane fade in active">
					<div class="row">
						<div class="col-xs-12">
							<!-- /.page-header -->
							<div class="row">
								<div class="alert alert-info">
									<button class="btn btn-sm btn-primary" id="importLinea">
										<i class="ace-icon glyphicon glyphicon-upload bigger-110"></i>
										Importar Catalogo de Lineas
									</button>
									<!-- 									<button class="btn btn-sm btn-primary" id="exportMarcas"> -->
									<!-- 										<i class="ace-icon glyphicon glyphicon-download bigger-110"></i> -->
									<!-- 										Exportar -->
									<!-- 									</button> -->
									<button class="btn btn-sm btn-success" id="searchLineas">
										<i class="ace-icon glyphicon glyphicon-search bigger-110"></i>
										Consultar
									</button>
									<button class="btn btn-sm btn-danger" id="deletesLineas"
										onclick="deleteallLineas()">
										<i class="ace-icon glyphicon glyphicon-trash bigger-110"></i>
										Eliminar
									</button>

								</div>
							</div>

							<!-- 		<div class="hr hr-8 hr-dotted"></div> -->
							<!-- /.col -->
							<div class="row">
								<div class="col-xs-12" id="listaLineas"></div>
								<div class="col-sm-12">
									<div class="clearfix">
										<div class="pull-right tableTools-container"></div>
									</div>

									<div class="table-header">Lista de Lineas
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
									<div>
										<table class="table  table-bordered table-hover"
											id="lineas-table">
											<thead>
												<tr>
													<th class="center">#</th>
													<th class="center">Clave</th>
													<th class="center">Descripci&oacute;n</th>
													<th class="center">Empresa</th>
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
				</div>

				<div id="Sublinea" class="tab-pane fade">
					<div class="row">
						<div class="col-xs-12">
							<!-- /.page-header -->
							<div class="row">
								<div class="alert alert-info">
									<button class="btn btn-sm btn-primary" id="importSubLinea">
										<i class="ace-icon glyphicon glyphicon-upload bigger-110"></i>
										Importar Catalogo de SubLineas
									</button>
									<button class="btn btn-sm btn-success" id="searchSubLineas">
										<i class="ace-icon glyphicon glyphicon-search bigger-110"></i>
										Consultar
									</button>
									<button class="btn btn-sm btn-danger" id="deletesSubLineas"
										onclick="deleteallSubLineas()">
										<i class="ace-icon glyphicon glyphicon-trash bigger-110"></i>
										Eliminar
									</button>

								</div>
							</div>

							<!-- 		<div class="hr hr-8 hr-dotted"></div> -->
							<!-- /.col -->
							<div class="row">
								<div class="col-xs-12" id="listaSubLineas"></div>
								<div class="col-sm-12">
									<div class="clearfix">
										<div class="pull-right tableTools-container"></div>
									</div>

									<div class="table-header">Lista de SubLineas
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
									<div>
										<table class="table  table-bordered table-hover"
											id="sublineas-table">
											<thead>
												<tr>
													<th class="center">#</th>
													<th class="center">Clave</th>
													<th class="center">Descripci&oacute;n</th>
													<th class="center">Empresa</th>
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

				</div>

				<div id="Sublineados" class="tab-pane fade">
					<div class="row">
						<div class="col-xs-12">
							<!-- /.page-header -->
							<div class="row">
								<div class="alert alert-info">
									<button class="btn btn-sm btn-primary" id="importSubLineados">
										<i class="ace-icon glyphicon glyphicon-upload bigger-110"></i>
										Importar Catalogo de SubLineaDos
									</button>
									<button class="btn btn-sm btn-success" id="searchSubLineados">
										<i class="ace-icon glyphicon glyphicon-search bigger-110"></i>
										Consultar
									</button>
									<button class="btn btn-sm btn-danger" id="deletesSubLineados"
										onclick="deleteallSubLineados()">
										<i class="ace-icon glyphicon glyphicon-trash bigger-110"></i>
										Eliminar
									</button>

								</div>
							</div>
							
							<!-- /.col -->
							<div class="row">
								<div class="col-xs-12" id="listaSubLineasdos"></div>
								<div class="col-sm-12">
									<div class="clearfix">
										<div class="pull-right tableTools-container"></div>
									</div>

									<div class="table-header">Lista de SubLineaDos
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
									<div>
										<table class="table  table-bordered table-hover"
											id="sublineasdos-table">
											<thead>
												<tr>
													<th class="center">#</th>
													<th class="center">Clave</th>
													<th class="center">Descripci&oacute;n</th>
													<th class="center">Empresa</th>
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
				</div>

			</div>
		</div>
	</div>
	<!-- /.col -->

</div>
<!-- /.row -->



</html>
<script>
var tablelineas;
var tablesublineas;
var tablesublineasdos;
	$(function() {		
		$("#importLinea").click(function() {
			$.ajax({
				url : '${pageContext.request.contextPath}/getimportlineas',
				dataType : 'html',
				success : function(data) {
					$("#modal-body").html(data);
					$("#modal-header").html("<h4>Importar Lineas</h4>");
					$('#modalaps').modal('show'); 
				},
				error : function(data) {
					alert("Ocurrio un error");
				}

			});
		});
		
		$( "#searchLineas" ).click(function() {
			tablelineas = $('#lineas-table').DataTable({
		 		"ajax":"${pageContext.request.contextPath}/showlineas",
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
			});
		//Search sublineas
		$( "#searchSubLineas" ).click(function() {
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
			});
		
		/*import sublinea*/
		$("#importSubLinea").click(function() {
			$.ajax({
				url : '${pageContext.request.contextPath}/getimportSublineas',
				dataType : 'html',
				success : function(data) {
					$("#modal-body").html(data);
					$("#modal-header").html("<h4>Importar SubLineas</h4>");
					$('#modalaps').modal('show'); 
				},
				error : function(data) {
					alert("Ocurrio un error");
				}

			});
		});
		//search sublineasdos///////////
		$( "#searchSubLineados" ).click(function() {
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
			});
		
		$("#importSubLineados").click(function() {
			$.ajax({
				url : '${pageContext.request.contextPath}/getimportSublineasDos',
				dataType : 'html',
				success : function(data) {
					$("#modal-body").html(data);
					$("#modal-header").html("<h4>Importar SubLineasDos</h4>");
					$('#modalaps').modal('show'); 
				},
				error : function(data) {
					alert("Ocurrio un error");
				}

			});
		});
		
		
	});

	function deletelinea(id){
		$.ajax({
			url:'${pageContext.request.contextPath}/deletelinea',
			type: "post",
			dataType : 'html',
			data:{id:id},
			}).done(function(data){		
				alert(data);
				tablelineas.ajax.reload( null, false );	 							
		    });
		}
	function deleteallLineas(){
		if(!confirm("Desea eliminar todas las Lineas?"))
			return 
		$.ajax({
			url:'${pageContext.request.contextPath}/deletealllineas',
			type: "post",
			dataType : 'JSON',
			data:{},
			}).done(function(data){		
				if(data.exito=="true"){
					alert(data.mensaje);
					tablelineas.ajax.reload( null, false );	
				}else{					
	 	 				for( i=0;i<data.error.length;i++){
	 	 					alert(data.error[i]);
	 	 	 				}
				}
		    });
		
	}
	///////////////////Sublineas///////////////////////////////
	function deletesublinea(id){
		$.ajax({
			url:'${pageContext.request.contextPath}/deleteSublinea',
			type: "post",
			dataType : 'html',
			data:{id:id},
			}).done(function(data){		
				alert(data);
				tablesublineas.ajax.reload( null, false );	 							
		    });
		}
	
	function deleteallSubLineas(){
		if(!confirm("Desea eliminar todas las SubLineas?"))
			return 
		$.ajax({
			url:'${pageContext.request.contextPath}/deleteallSublineas',
			type: "post",
			dataType : 'JSON',
			data:{},
			}).done(function(data){		
				if(data.exito=="true"){
					alert(data.mensaje);
					tablesublineas.ajax.reload( null, false );	
				}else{					
	 	 				for( i=0;i<data.error.length;i++){
	 	 					alert(data.error[i]);
	 	 	 				}
				}
		    });
		
	}
	/////////////Sublineados///////////////
		function deleteallSubLineados(){
		if(!confirm("Desea eliminar todo SubLineados?"))
			return 
		$.ajax({
			url:'${pageContext.request.contextPath}/deleteallSublineasDos',
			type: "post",
			dataType : 'JSON',
			data:{},
			}).done(function(data){		
				if(data.exito=="true"){
					alert(data.mensaje);
					tablesublineasdos.ajax.reload( null, false );	
				}else{					
	 	 				for( i=0;i<data.error.length;i++){
	 	 					alert(data.error[i]);
	 	 	 				}
				}
		    });
		
	}
		function deleteSublineados(id,idempresa){
			$.ajax({
				url:'${pageContext.request.contextPath}/deleteSublineaDos',
				type: "post",
				dataType : 'html',
				data:{id:id,idempresa:idempresa},
				}).done(function(data){		
					alert(data);
					tablesublineasdos.ajax.reload( null, false );	 							
			    });
			}
	
</script>