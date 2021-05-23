<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
</head>
<body>
	<div class="col-xs-12" id="listaventas"></div>
	<div class="col-sm-12">
		<div class="clearfix">
			<div class="pull-right tableTools-container"></div>
		</div>

		<div class="table-header">Ventas &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		</div>
		<div>
			<table class="table table-bordered" id="ventas-table">
				<thead>
					<tr>
						<th class="center">#</th>
						<th class="center">COD ALMACEN</th>
						<th class="center">NOMBRE ALMACEN</th>
						<th class="center">SKU</th>
						<th class="center">DESCRIPCION</th>
						<th class="center">TIPO MOVIMIENTO</th>
						<th class="center">VENTA</th>
						<th class="center">UNIDAD</th>
						<th class="center">PERIODO DIA</th>
						<th class="center">CLIENTE</th>
						<th class="center">CANAL CLIENTE</th>
						<th class="center">PRECIO</th>
						<th class="center">% DESCUENTO</th>
						<th class="center">DESCUENTO</th>
						<th class="center">PRECIO NETO</th>
						<th class="center">IEPS</th>
						<th class="center">PRECIO BRUTO</th>
						<th class="center">IVA</th>
						<th class="center">PRECIO FINAL</th>
						<th class="center">FACTURA</th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
		</div>
	</div>
<script type="text/javascript">
var ventastable;
$(function(){			
	var opalmacen = $("#almacen_imp").val();
	var finicio = $("#finicio_imp").val();
	var ffin    = $("#ffin_imp").val();
	//console.log(finicio+":"+ffin);
	
	ventastable = $('#ventas-table').DataTable({	
	              'ajax':{
					"type" : "POST",
					"url":"${pageContext.request.contextPath}/getdataventas",
					"data":function(d){
							d.almacen=opalmacen
							d.finicio=finicio
							d.ffin=ffin
							
					}
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
							{"data" : "codalmacen"}, 
							{"data" : "nomalmacen"},
							{"data" : "numart"},
							{"data" : "descripcion"},
							{"data" : "tipomov"},
							{"data" : "venta"},
							{"data" : "unidad"},
							{"data" : "periododia"},
							{"data" : "cliente"},
							{"data" : "canalcliente"},
							{"data" : "precio"},
							{"data" : "pdescuento"},
							{"data" : "descuento"},
							{"data" : "precioneto"},
							{"data" : "ieps"},
							{"data" : "preciobruto"},
							{"data" : "iva"},
							{"data" : "preciofinal"},
							{"data" : "factura"}
							]
					
		});
	});

</script>
</body>
</html>