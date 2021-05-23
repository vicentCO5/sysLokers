<div class="col-xs-12" id="listarticulos"></div>


		<table class="display nowrap table table-hover table-striped table-bordered" id="articulos-table">		
			<thead>
				<tr>
					<th>#</th>
					<th>Codigo</th>
					<th>Descripcion</th>
					<th>Unidad</th>					
					<th>Color</th>
					<th>Peso</th>
					<th>Volumen</th>					
					<th>Medidas</th>
					<th>Estatus</th>
					<th>Fecha creado</th>
					<th>Clasificación CVE</th>
					<th>Descripción</th>
					<th>Estatus</th>					
					<th>Acciones</th>

				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>

<script>
jQuery(function($) {
$(document).ready(function() {
	
	var tablearticulos;
    tablearticulos = $('#articulos-table').DataTable({
	"ajax" : "${pageContext.request.contextPath}/getlistarticulos",
			"destroy" : true,
	
			searching : true,
			"ordering" : false,
			"scrollY" : "300px",
			"scrollX" : "380px",
			"scrollCollapse" : true,
			"paging" : false,
			info : true,
			"columns" : [ 
				{"data" : "indice"}, 
				{"data" : "numart"}, 
				{"data" : "nomart"},
				{"data" : "uniart"},				
				{"data" : "color"},
				{"data" : "peso"},
				{"data" : "volumen"},
				{"data" : "medidas"},
				{"data" : "estatus"},				
				{"data" : "timecreate"},
				{"data" : "clave"},
				{"data" : "descripcion"},
				{"data" : "estatusclass"},
				{"data" : "acciones"},
				],
				

		});
    
});
});

</script>