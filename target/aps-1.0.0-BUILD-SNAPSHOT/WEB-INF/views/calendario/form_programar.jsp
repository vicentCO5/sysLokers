<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<% String fechaEvent = (String)request.getAttribute("fechaEvent");%>
<% String tipocalendario = (String)request.getAttribute("tipocalendario");%>
<html>
<head>
</head>
<body>
<div class="row">
	<div class="col-sm-6" id="notSelectAlmacen">
		<div class="alert alert-info">
			<button class="btn btn-sm btn-primary" id="cal_agregarAlmacen">
				<i class="ace-icon glyphicon glyphicon-indent-left bigger-110"></i>
				Agregar
			</button>
					
		</div>
		<table class="table table-bordered" id="dt_notSelectAlmacen">
			<thead>
				<th>#</th>
				<th>CLAVE</th>
				<th>ALMACEN</th>
				<th>ACCION</th>
			</thead>
			<tbody>
			</tbody>
		</table>
	</div>
	<div class="col-sm-6" id="yesSelectAlmacen">
		<div class="alert alert-info">
			<button class="btn btn-sm btn-primary" id="cal_eliminarAlmacen">
				<i class="ace-icon glyphicon glyphicon-indent-right bigger-110"></i>
				Eliminar
			</button>
					
		</div>
		<table class="table table-bordered" id="dt_yesSelectAlmacen">
			<thead>
				<th>#</th>
				<th>CLAVE</th>
				<th>ALMACEN</th>
				<th>ACCION</th>
			</thead>
			<tbody>
			</tbody>
		</table></div>
</div>
</body>
<script type="text/javascript">
var dtable_notSelectAlmacen;
var dtable_yesSelectAlmacen;


$(function(){

	//lista de almacenes no seleccionados
    //notSelectAlmacen("${fechaEvent}","${tipocalendario}");
	var fechaEvent     = "${fechaEvent}";
    var tipocalendario = "${tipocalendario}";
    console.log(fechaEvent +" - "+ tipocalendario);
    
	dtable_notSelectAlmacen = $('#dt_notSelectAlmacen').DataTable({	
        'ajax':{
        	"data" :{"fechaEvent":fechaEvent,"tipocalendario":tipocalendario },
			"type" : "POST",
			"url" :"${pageContext.request.contextPath}/listNotselectAlmacen",						
			},
			 paging: true,
			 searching : true,
			"ordering" : false,
			 scrollY:  "300px",	
			 scrollX:  "300px",
			 "pageLength" : '50',
			 "scrollCollapse" : true,	 				     
			 info:true, 
			 "order": [[ 1, "asc" ]],
			 "columns" : [ 
					{"data" : "indice"}, 
					{"data" : "clave"},
					{"data" : "nombre"},
					{"data" : "almacen_id"}, 					
													
				]
			
          });	
	
	// lista de almacenes si seleccionados
    // 	yesSelectAlmacen("${fechaEvent}","${tipocalendario}");
	dtable_yesSelectAlmacen = $('#dt_yesSelectAlmacen').DataTable({	
        'ajax':{
        	"data" :{"fechaEvent":fechaEvent,"tipocalendario":tipocalendario },
			"type" : "POST",
			"url" :"${pageContext.request.contextPath}/listYesselectAlmacen",						
			},
			 paging: true,
			 searching : true,
			"ordering" : false,
			 scrollY:  "300px",	
			 scrollX:  "300px",
			 "pageLength" : '50',
			 "scrollCollapse" : true,	 				     
			 info:true, 
			 "order": [[ 1, "asc" ]],
			 "columns" : [ 
					{"data" : "indice"}, 
					{"data" : "clave"},
					{"data" : "nombre"},
					{"data" : "almacen_id"}, 					
													
				]
			
          });
	
});

</script>
</html>