<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<div class="row">
	<div class="col-xs-12">
		<div class="page-header">
			<h1>Configuraci&oacute;n Periodos</h1>
		</div>
		<!-- /.page-header -->
		<div class="row">
			<div class="col-sm-12">
				<div class="tabbable">
					<ul class="nav nav-tabs" id="myTabPeriodos">
						<li class="active"><a data-toggle="tab" href="#periodos-rb"> <i
								class="blue ace-icon fa fa-folder-open bigger-120"></i> Periodos-Reabasto
						</a></li>

						<li><a data-toggle="tab" href="#periodos-re"> <i
								class="blue ace-icon fa fa-folder-open bigger-120"></i>Periodos-Resurtido
						</a></li>
						<li><a data-toggle="tab" href="#periodos-pvs"> <i
								class="blue ace-icon fa fa-folder-open bigger-120"></i>Periodos-Almacen
						</a></li>
					</ul>

					<div class="tab-content">
						<div id="periodos-rb" class="tab-pane fade in active">
							<div class="row">
								<div class="col-xs-12">
									<div class="clearfix">
										<div class="pull-right tableTools-container"></div>
									</div>

									
									<div class="form-group has-info">
										<div class="col-sm-6">
											<div class="table-header">Periodos Extracción
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
											<div>
											<table class="table  table-bordered table-hover" id="periodosconfig-table">
												<thead>
													<tr>
														<th class="center">#</th>
														<th class="center">Nombre</th>
														<th class="center">Periodo</th>
													</tr>
												</thead>
												<tbody>
													
												</tbody>
											</table>
										</div>

											<div class="hr hr-16 hr-dotted"></div>
										</div>
										<div class="col-sm-6">
											<div class="table-header">Periodos a considerar PMS Reabasto
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
											<div>
											<table class="table  table-bordered table-hover" id="periodosselected-table">
												<thead>
													<tr>
														<th class="center">#</th>
														<th class="center">Nombre</th>
														<th class="center">Periodo</th>
													</tr>
												</thead>
												<tbody>
													
												</tbody>
											</table>
										</div>

											<div class="hr hr-16 hr-dotted"></div>
										</div>
									</div>
								</div>
								<!-- /.col -->
							</div>
							<!-- /.row -->
						</div>

						<div id="periodos-re" class="tab-pane fade">
							
								<jsp:include page="${request.contextPath}/configperiodosRe"></jsp:include>
								
							

						</div>
						<div id="periodos-pvs" class="tab-pane fade">
							
								<jsp:include page="${request.contextPath}/configperiodosPv"></jsp:include>
							

						</div>
					</div>
				</div>
			</div>
		</div>



	</div>
	<!-- /.col -->
</div>
<!-- /.row -->
</html>
<script>
var periodosextrac;
var periodosselected;
$(function() {
	periodosextrac = $('#periodosconfig-table').DataTable({	
		'ajax':{
			"type" : "POST",
			"url":"${pageContext.request.contextPath}/configperiodos",
			"data":function(d){
					d.almacen="GENERAL"
					
			}
			},
			
 		"destroy": true,
 	    searching: true,
 	    "ordering": false,
 	    "scrollY": "380px",
        "scrollCollapse": true,
        "paging": false,
 	    info:true,
 	    "columns":[		
 				   			
		 	    	{"data":"indice"}, 						  			       	
 		       		{"data":"nombre"},
 		       		{"data":"periodo"},
 		       	],	
	});
	
	periodosselected = $('#periodosselected-table').DataTable({	
		'ajax':{
			"type" : "POST",
			"url":"${pageContext.request.contextPath}/periodosselected",
			"data":function(d){
					d.almacen="GENERAL"
					
			}
			},
		"destroy": true,
 	    searching: true,
 	    "ordering": false,
 	    "scrollY": "380px",
        "scrollCollapse": true,
        "paging": false,
 	    info:true,
 	    "columns":[		
 				   			
		 	    	{"data":"indice"}, 						  			       	
 		       		{"data":"nombre"},
 		       		{"data":"periodo"},
 		       	],	
	});	
	

	$('#periodosconfig-table').on( 'dblclick', 'tr', function () {
        $(this).toggleClass('selected');
        var id = $(this).find("td:eq(0)").text();
        var almacen = $(this).find("td:eq(1)").text();
        var periodo = $(this).find("td:eq(2)").text();
		console.log(periodo+":"+id+":"+almacen);
		loader_open();
		$.ajax({
			url:'${pageContext.request.contextPath}/addperiodo',
			type: "post",
			dataType : 'html',
			data:{id:id,almacen:almacen,periodo:periodo},
			}).done(function(data){		
				alert(data);
				periodosselected.ajax.reload( null, false );
				loader_close();
		    });
    });
	
	$('#periodosselected-table').on( 'dblclick', 'tr', function () {
        $(this).toggleClass('selected');
        var id = $(this).find("td:eq(0)").text();
        var almacen = $(this).find("td:eq(1)").text();
        var periodo = $(this).find("td:eq(2)").text();
		console.log(periodo+":"+id+":"+almacen);
		if(id<4){
			alert("Los 3 primeros periodos no se pueden eliminar");	
		}else{
			loader_open();
			$.ajax({
				url:'${pageContext.request.contextPath}/deleteperiodo',
				type: "post",
				dataType : 'html',
				data:{id:id,almacen:almacen,periodo:periodo},
				}).done(function(data){		
					alert(data);
					periodosselected.ajax.reload( null, false );
					loader_close();
			    });
		}
    });
});
	
	
</script>