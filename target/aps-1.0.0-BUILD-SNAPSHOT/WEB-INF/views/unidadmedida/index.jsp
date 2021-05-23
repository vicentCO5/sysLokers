<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<div class="row">
	<div class="col-sm-12">
		<div class="tabbable">
			<ul class="nav nav-tabs" id="myTabDepto">
				<li class="active"><a data-toggle="tab" href="#undm"> <i
						class="blue ace-icon fa fa-folder-open bigger-120"></i> Unidad de Medida
				</a></li>

				<li><a data-toggle="tab" href="#eudm"> <i
						class="blue ace-icon fa fa-folder-open bigger-120"></i>Equivalencia Unidad de Medida
				</a></li>
				

			</ul>

			<div class="tab-content">
				<div id="undm" class="tab-pane fade in active">
					<div class="row">
						<div class="col-xs-12">
							<!-- /.page-header -->
							<div class="row">
								<div class="alert alert-info">
									<button class="btn btn-sm btn-primary" id="importundm">
										<i class="ace-icon glyphicon glyphicon-upload bigger-110"></i>
										Importar Catalogo de Unidades de Medida
									</button>
									<button class="btn btn-sm btn-success" id="searchUdM">
										<i class="ace-icon glyphicon glyphicon-search bigger-110"></i>
										Consultar
									</button>
									<button class="btn btn-sm btn-danger" id="deletesund"
										onclick="deleteallUnidadesmed()">
										<i class="ace-icon glyphicon glyphicon-trash bigger-110"></i>
										Eliminar
									</button>

								</div>
							</div>

							
							<div class="row">
								<div class="col-xs-12" id="listaUndm"></div>
								<div class="col-sm-12">
									<div class="clearfix">
										<div class="pull-right tableTools-container"></div>
									</div>

									<div class="table-header">Lista Unidades de Medida
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
									<div>
										<table class="table  table-bordered table-hover"
											id="udm-table">
											<thead>
												<tr>
													<th class="center">#</th>
													<th class="center">C&oacute;digo</th>
													<th class="center">Descripci&oacute;n</th>
													<th class="center">Codigo Unidad Minima</th>
													<th class="center">Estatus</th>
													<th class="center">Accion</th>
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

				<div id="eudm" class="tab-pane fade">
					<div class="row">
						<div class="col-xs-12">
							<!-- /.page-header -->
							<div class="row">
								<div class="alert alert-info">
									<button class="btn btn-sm btn-primary" id="importeudm">
										<i class="ace-icon glyphicon glyphicon-upload bigger-110"></i>
										Importar Catalogo de Equivalencias
									</button>
									<button class="btn btn-sm btn-success" id="searcheudm">
										<i class="ace-icon glyphicon glyphicon-search bigger-110"></i>
										Consultar
									</button>
									<button class="btn btn-sm btn-danger" id="deleteseudm"
										onclick="deletealleudm()">
										<i class="ace-icon glyphicon glyphicon-trash bigger-110"></i>
										Eliminar
									</button>

								</div>
							</div>

							
							<div class="row">
								<div class="col-xs-12" id="listaeudm"></div>
								<div class="col-sm-12">
									<div class="clearfix">
										<div class="pull-right tableTools-container"></div>
									</div>

									<div class="table-header">Lista de Equivalencias
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
									<div>
										<table class="table  table-bordered table-hover"
											id="eudm-table">
											<thead>
												<tr>
													<th class="center">#</th>
													<th class="center">Codigo Unidad Origen</th>
													<th class="center">Codigo Unidad Destino</th>
													<th class="center">Factor</th>
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
var tableudm;
var tableeudm;
	$(function() {		
		$("#importundm").click(function() {
			$.ajax({
				url : '${pageContext.request.contextPath}/getimportunidadmedida',
				dataType : 'html',
				success : function(data) {
					$("#modal-body").html(data);
					$("#modal-header").html("<h4>Importar Unidades de Medida</h4>");
					$('#modalaps').modal('show'); 
				},
				error : function(data) {
					alert("Ocurrio un error");
				}

			});
		});
		
		$( "#searchUdM" ).click(function() {
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
			});
		//Search subdepartamentos
		$( "#searcheudm" ).click(function() {
			tableeudm = $('#eudm-table').DataTable({
		 		"ajax":"${pageContext.request.contextPath}/showeundmed",
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
				 	    	{"data":"codori"}, 						  			       	
		 		       		{"data":"coddes"},
		 		         	{"data":"factor"},
		 		       		{"data":"accion"}
		 		       	],		 	   
		 	   				    
		 	    });
			});
		
		/*import euivalencias*/
		$("#importeudm").click(function() {
			$.ajax({
				url : '${pageContext.request.contextPath}/getimportequivalencias',
				dataType : 'html',
				success : function(data) {
					$("#modal-body").html(data);
					$("#modal-header").html("<h4>Importar Equivalencias Unidad de Medida</h4>");
					$('#modalaps').modal('show'); 
				},
				error : function(data) {
					alert("Ocurrio un error");
				}

			});
		});
		
	});
	function deleteUndMedida(id){
		$.ajax({
			url:'${pageContext.request.contextPath}/deleteundMed',
			type: "post",
			dataType : 'html',
			data:{id:id},
			}).done(function(data){		
				alert(data);
				tableudm.ajax.reload( null, false );	 							
		    });
		}
	
	function deleteallUnidadesmed(){
		if(!confirm("Desea eliminar todas las Unidades de Medida?"))
			return 
		$.ajax({
			url:'${pageContext.request.contextPath}/deleteallundmed',
			type: "post",
			dataType : 'JSON',
			data:{},
			}).done(function(data){		
				if(data.exito=="true"){
					alert(data.mensaje);
					tableudm.ajax.reload( null, false );	
				}else{					
	 	 				for( i=0;i<data.error.length;i++){
	 	 					alert(data.error[i]);
	 	 	 				}
				}
		    });
		
	}
	///////////////////Equivalencias///////////////////////////////
	function deleteEquivUndMedida(codori,coddes){
		$.ajax({
			url:'${pageContext.request.contextPath}/deleteEquivundMed',
			type: "post",
			dataType : 'html',
			data:{codori:codori,coddes:coddes},
			}).done(function(data){		
				alert(data);
				tableeudm.ajax.reload( null, false );	 							
		    });
		}
	
	function deletealleudm(){
		if(!confirm("Desea eliminar todas las Equivalencias?"))
			return 
		$.ajax({
			url:'${pageContext.request.contextPath}/deleteallEquivalencias',
			type: "post",
			dataType : 'JSON',
			data:{},
			}).done(function(data){		
				if(data.exito=="true"){
					alert(data.mensaje);
					tableeudm.ajax.reload( null, false );	
				}else{					
	 	 				for( i=0;i<data.error.length;i++){
	 	 					alert(data.error[i]);
	 	 	 				}
				}
		    });
		
	}
	
	
</script>