<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="utf-8">
</head>
<body>
<div class="row">
   <div class="col-md-12">
    <div class="card">
	    <input type="text" id="selectContador" value="${contador}">
	   <div class="table-responsive m-t-10" id="tablePrecios" >
		 <table class="display nowrap table table-hover table-striped table-bordered" id="clarticulo-table">		
			<thead>
				<tr>
					<th>#</th>
					<th>Codigo</th>
					<th>Descripcion</th>
					<th>Unidad</th>									
					<th>Acción</th>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
	  </div>
     </div>
   </div>
</div>
<script type="text/javascript">

$(document).ready(function() {
	
	var tablecontrolsku;
	tablecontrolsku = $('#clarticulo-table').DataTable({
	"ajax" : "${pageContext.request.contextPath}/getlistarticuloscl",
			"destroy" : true,	
			searching : true,
			"ordering" : true,
			"scrollY" : "280px",
			"scrollCollapse" : true,
			"paging" : false,
			info : true,
			"columns" : [ 
				{"data" : "indice"}, 
				{"data" : "numart"}, 
				{"data" : "nomart"},
				{"data" : "unidad"},								
				{"data" : "acciones"}
				],
				
		});
	$(document).on("click",".clselectArticulo",function(){
		var idselect =  $(this).data("idselect");
		var idnumart = $(this).data("numart");
		var nombrex =  $(this).data("nombrex");
		var unidad  =  $(this).data("unidad");
		var contador =  $("#selectContador").val();
		console.log( idselect+" "+idnumart + ""+nombrex +" "+unidad)
		
		$("#clarticulo"+contador).attr('value',nombrex);
		$("#clcodigosku"+contador).attr('value',idnumart);
		$("#clcodigosku"+contador).data('idselect',idselect);
		
		$('#modalaps').modal('hide');
		
	});
});

</script>
</body>
</html>