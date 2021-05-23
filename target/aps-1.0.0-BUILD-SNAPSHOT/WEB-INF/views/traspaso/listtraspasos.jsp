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
		<div class="table-header">Traspasos &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		</div>
		<div>
			<table class="table table-bordered" id="traspaso_table">
				<thead>
					<tr>
						<th class="center">#</th>
						<th class="center">NUMERO FOLIO</th>						
						<th class="center">ALMACEN ORIGEN</th>
						<th class="center">ALMACEN DESTINO</th>
						<th class="center">SKU</th>						
						<th class="center">DESCRIPCION</th>						
						<th class="center">CANTIDAD</th>
						<th class="center">UNIDAD</th>						
						<th class="center">FECHA MOVIMIENTO</th>
						<th class="center">ESTATUS</th>
						<th class="center">AGENTE</th>						
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
		</div>
	</div>
	<script type="text/javascript">
	var traspasotable;
	$(function(){									
		traspasotable = $('#traspaso_table').DataTable({	
		               'ajax':{
						"type" : "POST",
						"url":"${pageContext.request.contextPath}/getdatatraspaso",						
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
								{"data" : "almacenOrigen"},
								{"data" : "almacenDestino"},
								{"data" : "codSKU"},
								{"data" : "nombreSKU"},
								{"data" : "cantidad"},
								{"data" : "unidad"},
								{"data" : "fechaMovimiento"},
								{"data" : "estatus"},
								{"data" : "agente"},								
							]
						
			});
		});
	

</script>
</body>
</html>