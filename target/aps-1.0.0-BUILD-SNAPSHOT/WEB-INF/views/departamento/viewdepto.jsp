<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<div class="row">
	<div class="col-sm-12">
		<div class="tabbable">
			<ul class="nav nav-tabs" id="myTabDepto">
				<li class="active"><a data-toggle="tab" href="#depto"> <i
						class="blue ace-icon fa fa-folder-open bigger-120"></i> Departamento
				</a></li>

				<li><a data-toggle="tab" href="#Subdepto"> <i
						class="blue ace-icon fa fa-folder-open bigger-120"></i>Subdepartamento
				</a></li>
				

			</ul>

			<div class="tab-content">
				<div id="depto" class="tab-pane fade in active">
					<div class="row">
						<div class="col-xs-12">
							<!-- /.page-header -->
							<div class="row">
								<div class="alert alert-info">
									<button class="btn btn-sm btn-primary" id="importdepto">
										<i class="ace-icon glyphicon glyphicon-upload bigger-110"></i>
										Importar Catalogo de Departamentos
									</button>
									<button class="btn btn-sm btn-success" id="searchDeptos">
										<i class="ace-icon glyphicon glyphicon-search bigger-110"></i>
										Consultar
									</button>
									<button class="btn btn-sm btn-danger" id="deletesDeptos"
										onclick="deleteallDeptos()">
										<i class="ace-icon glyphicon glyphicon-trash bigger-110"></i>
										Eliminar
									</button>

								</div>
							</div>

							<!-- 		<div class="hr hr-8 hr-dotted"></div> -->
							<!-- /.col -->
							<div class="row">
								<div class="col-xs-12" id="listaDeptos"></div>
								<div class="col-sm-12">
									<div class="clearfix">
										<div class="pull-right tableTools-container"></div>
									</div>

									<div class="table-header">Lista de Departamentos
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
									<div>
										<table class="table  table-bordered table-hover"
											id="deptos-table">
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

				<div id="Subdepto" class="tab-pane fade">
					<div class="row">
						<div class="col-xs-12">
							<!-- /.page-header -->
							<div class="row">
								<div class="alert alert-info">
									<button class="btn btn-sm btn-primary" id="importSubdepto">
										<i class="ace-icon glyphicon glyphicon-upload bigger-110"></i>
										Importar Catalogo de SubDepartamentos
									</button>
									<button class="btn btn-sm btn-success" id="searchSubDeptos">
										<i class="ace-icon glyphicon glyphicon-search bigger-110"></i>
										Consultar
									</button>
									<button class="btn btn-sm btn-danger" id="deletesSubDeptos"
										onclick="deleteallSubDeptos()">
										<i class="ace-icon glyphicon glyphicon-trash bigger-110"></i>
										Eliminar
									</button>

								</div>
							</div>

							<!-- 		<div class="hr hr-8 hr-dotted"></div> -->
							<!-- /.col -->
							<div class="row">
								<div class="col-xs-12" id="listaSubdeptos"></div>
								<div class="col-sm-12">
									<div class="clearfix">
										<div class="pull-right tableTools-container"></div>
									</div>

									<div class="table-header">Lista de SubDepartamentos
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
									<div>
										<table class="table  table-bordered table-hover"
											id="subdeptos-table">
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
var tabledeptos;
var tablesubdeptos;
	$(function() {		
		$("#importdepto").click(function() {
			$.ajax({
				url : '${pageContext.request.contextPath}/getimportdeptos',
				dataType : 'html',
				success : function(data) {
					$("#modal-body").html(data);
					$("#modal-header").html("<h4>Importar Departamentos</h4>");
					$('#modalaps').modal('show'); 
				},
				error : function(data) {
					alert("Ocurrio un error");
				}

			});
		});
		
		$( "#searchDeptos" ).click(function() {
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
			});
		//Search subdepartamentos
		$( "#searchSubDeptos" ).click(function() {
			tablesubdeptos = $('#subdeptos-table').DataTable({
		 		"ajax":"${pageContext.request.contextPath}/showSubdeptos",
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
		
		/*import subdepartamentos*/
		$("#importSubdepto").click(function() {
			$.ajax({
				url : '${pageContext.request.contextPath}/getimportSubdeptos',
				dataType : 'html',
				success : function(data) {
					$("#modal-body").html(data);
					$("#modal-header").html("<h4>Importar SubDepartamentos</h4>");
					$('#modalaps').modal('show'); 
				},
				error : function(data) {
					alert("Ocurrio un error");
				}

			});
		});
		
		
		
		
		
	});

	function deletedepto(id,idempresa){
		$.ajax({
			url:'${pageContext.request.contextPath}/deletedepto',
			type: "post",
			dataType : 'html',
			data:{id:id,idempresa:idempresa},
			}).done(function(data){		
				alert(data);
				tabledeptos.ajax.reload( null, false );	 							
		    });
		}
	function deleteallDeptos(){
		if(!confirm("Desea eliminar todos los Departamentos?"))
			return 
		$.ajax({
			url:'${pageContext.request.contextPath}/deletealldeptos',
			type: "post",
			dataType : 'JSON',
			data:{},
			}).done(function(data){		
				if(data.exito=="true"){
					alert(data.mensaje);
					tabledeptos.ajax.reload( null, false );	
				}else{					
	 	 				for( i=0;i<data.error.length;i++){
	 	 					alert(data.error[i]);
	 	 	 				}
				}
		    });
		
	}
	///////////////////Subdepartamentos///////////////////////////////
	function deletesubdepto(id,idempresa){
		$.ajax({
			url:'${pageContext.request.contextPath}/deleteSubdepto',
			type: "post",
			dataType : 'html',
			data:{id:id,idempresa:idempresa},
			}).done(function(data){		
				alert(data);
				tablesubdeptos.ajax.reload( null, false );	 							
		    });
		}
	
	function deleteallSubDeptos(){
		if(!confirm("Desea eliminar todos los SubDepartamentos?"))
			return 
		$.ajax({
			url:'${pageContext.request.contextPath}/deleteallSubDeptos',
			type: "post",
			dataType : 'JSON',
			data:{},
			}).done(function(data){		
				if(data.exito=="true"){
					alert(data.mensaje);
					tablesubdeptos.ajax.reload( null, false );	
				}else{					
	 	 				for( i=0;i<data.error.length;i++){
	 	 					alert(data.error[i]);
	 	 	 				}
				}
		    });
		
	}
	
	
</script>