<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<div class="row">
	<div class="col-xs-12">
		<div class="page-header"><h1>Clasificadores</h1>
		</div>
		<!-- /.page-header -->
		<div class="row">
			<div class="alert alert-info">
				<button class="btn btn-sm btn-primary" id="createclasificador">
					<i class="ace-icon glyphicon glyphicon-plus bigger-110"></i>
					Nuevo
				</button>
			</div>
		</div>
		<div class="hr hr-8 hr-dotted"></div>

		<div class="row">
			<div id="listclasificador" >			
			<div class="col-sm-12">
		<div class="clearfix">
			<div class="pull-right tableTools-container"></div>
		</div>

		<div class="table-header">
			Lista de Clasificadores &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		</div>
		<div>
			<table class="table table-bordered table-hover"
				id="clasificador-table">
				<thead>
					<tr>
						<th class="center">Nivel</th>
						<th class="center">Nombre</th>						
						<th class="center">Forecast</th>
						<th class="center">Forecast PV</th>
						<th class="center">Reabasto</th>
						<th class="center">Resurtido</th>
						<th class="center">Pedido Extraordinario</th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
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
var clasificadortable;
$(function(){
	
	 
	clasificadortable = $('#clasificador-table').DataTable({
 		"ajax":"${pageContext.request.contextPath}/getlistclasificadores",
 		"destroy": true,
 	    paging: true,
 	    searching: true,
 	    "ordering": false,
 	    "scrollY": "380px",
 	    "pageLength": '100',
        "scrollCollapse": true,
        "paging": true,
 	    info:true,
 	    "columns":[	
 	    	        {"data":"nivel"},				
 					{"data":"descripcion"}, 						  			       	
 		       		{"data":"forecast"},
 		       		{"data":"forecastpv"},
 		       		{"data":"reabasto"},
 		       		{"data":"resurtido"},
 		       		{"data":"extraordinario"}
 		       	],
 		  "columnDefs": [
 		          { className: "alinear", "targets": [0, 2,3, 4, 5 , 6] },
 		        
 		   ],
 	   				    
 	    });
	
	
	
	 $(document).on('keypress','.updatelevel',function(keycode){
	        var keycode = (event.keyCode ? event.keyCode : event.which);
	        if(keycode == '13'){
	        var id=	$(this).data('id');
	        var clave = $("#"+id).data('clave');
	        var oldvalue = $("#"+id).data('value');
	        var newvalue = $("#"+id).val();
	        if(newvalue != oldvalue){	        	
		        $.ajax({
					url:'${pageContext.request.contextPath}/updatelevel',
					type: "post",
					dataType : 'html',
					data:{clave:clave,newvalue:newvalue},
					}).done(function(data){		
						alert(data);
						clasificadortable.ajax.reload( null, false );	 							
				    });
		        }
	        }

	      });
	 
		 $(document).on('click','.updateclasificador',function(){
		
	        var id=	$(this).data('id');
	        var clave = $(this).data('clave');
	        var oldvalue = $(this).data('value');
	        var modulo = $(this).data('modulo'); 
	        
		        $.ajax({
					url:'${pageContext.request.contextPath}/updateclasificador',
					type: "post",
					dataType : 'html',
					data:{clave:clave,oldvalue:oldvalue,modulo:modulo},
					}).done(function(data){		
						alert(data);
						clasificadortable.ajax.reload( null, false );	 							
				    });
	      });
		 
		 $( "#createclasificador" ).click(function() {
				loader_open();
				$.ajax({
					url:'${pageContext.request.contextPath}/createclasificador',
					dataType : 'html',
					success : function(data) {
						$("#modal-body").html(data);
						$("#modal-header").html("<h4>Nuevo Clasificador</h4>");
						$('#modalaps').modal('show'); 
						$.ajax({
		                    method:'POST',
		                    url:'${pageContext.request.contextPath}/searchempresas',         
		                    data:{},
		                    dataType:'JSON',
		                    success: function (data){               
		                        var options = '<option value="0"> [ Seleccione]  </option>';
		                        $.each(data, function (key, dat) {	                        	
		                            options += '<option value="'+dat.id+'">'+dat.nombre+'</option>'                  
		                        });  	                        
		                         $("#idempresa").html( options );                      
		                    },
		                    error:function(jqXHR, exception){
		                        var msg =  errorsAjax(jqXHR, exception);
		                        alert("MSG: "+ msg)
		                    }           
		                });
						
						loader_close();
						},
					});
				});

});


</script>


