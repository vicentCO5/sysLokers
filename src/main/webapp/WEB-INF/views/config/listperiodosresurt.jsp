<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
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
					<table class="table  table-bordered table-hover"
						id="periodosconfigre">
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
				<div class="table-header">Periodos a considerar PMS Resurtido
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
				<div>
					<table class="table  table-bordered table-hover"
						id="periodosselectedre">
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
</div>
</html>
<script>
var periodosconfigre;
var periodosselectedre;
$(function() {
	periodosconfigre = $('#periodosconfigre').DataTable({	
		'ajax':{
			"type" : "POST",
			"url":"${pageContext.request.contextPath}/configperiodos",
			"data":function(d){
					d.almacen="RESURTIDO"
					
			}
			},
			
 		"destroy": true,
 	    searching: true,
 	    "ordering": false,
 	   "scrollY": "350px",
     
        "scrollCollapse": true,
        "paging": false,
 	    info:true,
 	    "columns":[		
 				   			
		 	    	{"data":"indice"}, 						  			       	
 		       		{"data":"nombre"},
 		       		{"data":"periodo"},
 		       	],	
	});
	
	periodosselectedre = $('#periodosselectedre').DataTable({	
		'ajax':{
			"type" : "POST",
			"url":"${pageContext.request.contextPath}/periodosselected",
			"data":function(d){
					d.almacen="RESURTIDO"
					
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
	$('#periodosconfigre').on( 'dblclick', 'tr', function () {
		loader_open();
        $(this).toggleClass('selected');
        var id = $(this).find("td:eq(0)").text();
        var almacen = $(this).find("td:eq(1)").text();
        var periodo = $(this).find("td:eq(2)").text();
		console.log(periodo+":"+id+":"+almacen);
		$.ajax({
			url:'${pageContext.request.contextPath}/addperiodo',
			type: "post",
			dataType : 'html',
			data:{id:id,almacen:almacen,periodo:periodo},
			}).done(function(data){		
				alert(data);
				periodosselectedre.ajax.reload( null, false );
				loader_close();
		    });
    });
	$('#periodosselectedre').on( 'dblclick', 'tr', function () {
		
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
					periodosselectedre.ajax.reload( null, false );
					loader_close();
			    });
		}
    });
});

</script>