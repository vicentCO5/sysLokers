<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<div class="row">
	<div class="col-xs-12">
		<div class="page-header"><h1>Configuraci&oacute;n de Almacenes</h1>
		</div>
		<!-- /.page-header -->
		<div class="row">
		<div class="col-sm-12">
		<div class="clearfix">
			<div class="pull-right tableTools-container"></div>
		</div>
		
		<div class="table-header">
			Almacenes &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		</div>
		<div>
			<table class="table  table-bordered table-hover" id="almacenconfig-table">
				<thead>
					<tr>
						<th class="center">#</th>
						<th class="center">Clave</th>
						<th class="center">Nombre</th>
						<th class="center">Forecasting</th>
						<th class="center">Forecasting PV</th>
						<th class="center">Reabasto</th>
						<th class="center">Resurtido</th>
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
	var almacenconfig;
	$(function() {
		almacenconfig = $('#almacenconfig-table').DataTable({	
			"ajax":"${pageContext.request.contextPath}/configalmacenes",
	 		"destroy": true,
	 	    paging: false,
	 	    searching: true,
	 	    "ordering": false,
	 	    "scrollY": "380px",
	        "scrollCollapse": true,
	        "paging": true,
	 	    info:true,
	 	    "columns":[		
	 				   			
			 	    	{"data":"indice"},		
			 	    	{"data":"clave"}, 						  			       	
	 		       		{"data":"nombre"},
	 		       		{"data":"forecast"},
	 		       		{"data":"forecastpv"},
	 		      		{"data":"reabasto"},
	 		      		{"data":"resurtido"}
	 		       	],
	 		 		  "columnDefs": [
	 	 		          { className: "alinear", "targets": [3, 4, 5 , 6] },
	 	 		        
	 	 		   ],	
				
	});
		
		 $(document).on('click','.updatemodulo',function(){
				
		        var id=	$(this).data('id');
		        var clave = $(this).data('clave');
		        var oldvalue = $(this).data('value');
		        var modulo = $(this).data('modulo'); 
		      
			        $.ajax({
						url:'${pageContext.request.contextPath}/updatemodulo',
						type: "post",
						dataType : 'html',
						data:{clave:clave,oldvalue:oldvalue,modulo:modulo},
						}).done(function(data){		
							console.log(data);
							almacenconfig.ajax.reload( null, false );	 							
					    });
		      });

	});
</script>