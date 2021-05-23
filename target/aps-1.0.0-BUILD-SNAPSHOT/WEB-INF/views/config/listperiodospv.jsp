<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<div class="row">
	<div class="col-xs-4">
		<div class="form-group has-info">
			<label for="almacen_pv"><b> Almacen o PV:</b></label> <select
				id='almacen_pv' class='form-control'
				onchange="showlistperiodosvts(this.value);"></select>
		</div>
	</div>
</div>
<div class="hr hr-8 hr-dotted"></div>
<div class="row">
	<div class="form-group has-info">
		<div class="col-sm-6">
			<div class="table-header">Periodos Extracción
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
			<div>
				<table class="table  table-bordered table-hover"
					id="periodosconfigpv">
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
		<div class="table-header">Periodos a considerar PMS (Reabasto - Resurtido)
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
		<div>
			<table class="table  table-bordered table-hover"
				id="periodosselectedre_re" >
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
</html>
<script>
	var tablaperiodospv;
	var periodosselectedre_re;
	$(function() {
		$.ajax({
			method : 'POST',
			url : '${pageContext.request.contextPath}/searchalmacenes',
			data : {},
			dataType : 'JSON',
			success : function(data) {
				var options = '<option value="0"> [ Seleccione]  </option>';
				$.each(data, function(key, dat) {
					options += '<option value="'+dat.clave+'">' + dat.nombre
							+ '</option>'
				});
				$("#almacen_pv").html(options);
			},
			error : function(jqXHR, exception) {
				var msg = errorsAjax(jqXHR, exception);
				alert("MSG: " + msg)
			}
		});
		
		$('#periodosconfigpv').on( 'dblclick', 'tr', function () {
			loader_open();
	        $(this).toggleClass('selected');
	        var idd = this.id;
	        var res = idd.split("_");
	        var id = res[1];	        
	        var almacen = $(this).find("td:eq(1)").text();
	        var periodo = $(this).find("td:eq(2)").text();
			console.log(id);
			$.ajax({
				url:'${pageContext.request.contextPath}/addperiodo',
				type: "post",
				dataType : 'html',
				data:{id:id,almacen:id,periodo:periodo},
				}).done(function(data){		
					alert(data);
					periodosselectedre_re.ajax.reload( null, false );
					loader_close();
			    });
	    });
		$('#periodosselectedre_re').on( 'dblclick', 'tr', function () {
	        $(this).toggleClass('selected');
	        var idd = this.id;
	        var res = idd.split("_");
	        var id = res[1];
	        var indice = $(this).find("td:eq(0)").text();
	        var almacen = $(this).find("td:eq(1)").text();
	        var periodo = $(this).find("td:eq(2)").text();
			console.log(periodo+":"+id+":"+almacen);
			if( indice < 4 ){
				alert("Los 3 primeros periodos no se pueden eliminar");	
			}else{
				loader_open();
				$.ajax({
					url:'${pageContext.request.contextPath}/deleteperiodo',
					type: "post",
					dataType : 'html',
					data:{id:id,almacen:id,periodo:periodo},
					}).done(function(data){		
						alert(data);
						periodosselectedre_re.ajax.reload( null, false );
						loader_close();
				    });
			}
	    });

	});

	function showlistperiodosvts(option) {
		tablaperiodospv = $('#periodosconfigpv').DataTable({	
			'ajax':{
				"type" : "POST",
				"url":"${pageContext.request.contextPath}/configperiodos",
				"data":function(d){
						d.almacen=$("#almacen_pv").val();
						
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
	 		       		{"data":"descripcion"},
	 		       		{"data":"periodo"},
	 		       	],	
		});
		periodosselectedre_re = $('#periodosselectedre_re').DataTable({	
			'ajax':{
				"type" : "POST",
				"url":"${pageContext.request.contextPath}/periodosselected",
				"data":function(d){
						d.almacen = $("#almacen_pv").val();
						
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
	}
</script>