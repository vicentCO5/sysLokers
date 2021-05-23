<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title></title>
</head>
<body>
<div class="col-sm-12">
		<div class="clearfix">
			<div class="pull-right tableTools-container"></div>
		</div>
		<div class="table-header">Transitos &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		</div>
		<div>
			<table class="table table-bordered" id="transito_table">
				<thead>
					<tr>
						<th class="center">#</th>
						<th class="center">NUMERO FOLIO</th>
						<th class="center">COD ALMACEN</th>						
						<th class="center">ALMACEN</th>						
						<th class="center">COD SKU</th>						
						<th class="center">DESCRIPCION</th>						
						<th class="center">CANTIDAD INICIAL</th>
						<th class="center">CANTIDAD FALTANTE</th>
						<th class="center">UNIDAD</th>						
						<th class="center">FECHA LLEGADA</th>											
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
		</div>
	</div>
	<script type="text/javascript">
	var transitotable;
	$(function(){									
		transitotable = $('#transito_table').DataTable({	
		               'ajax':{
						"type" : "POST",
						"url":"${pageContext.request.contextPath}/getdatatransito",						
						},
						 paging: true,
						 searching : true,
						"ordering" : false,
						 scrollY:  "380px",	
						 scrollX:  "380px",
						 "pageLength" : '50',
						 "scrollCollapse" : true,	 				     
						 info:true, 
						 "order": [[ 1, "asc" ]],
						 "columns" : [ 
								{"data" : "indice"}, 
								{"data" : "numeroFolio"}, 
								{"data" : "codalmacen"},
								{"data" : "nombreAlmacen"},								
								{"data" : "codSKU"},
								{"data" : "nombreSKU"},
								{"data" : "cantidadInicial"},
								{"data" : "cantidadFaltante"},
								{"data" : "unidad"},
								{"data" : "fechaLlegada"}															
							]
						
			});
		});
	

</script>

</body>
</html>