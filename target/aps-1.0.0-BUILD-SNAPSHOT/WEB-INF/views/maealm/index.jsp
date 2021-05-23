<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<div class="row">
	<div class="col-xs-12">
		<div class="page-header">	<h1>INVENTARIO</h1>		
		</div>
		<!-- /.page-header -->
		<div class="row">
			<div class="alert alert-info">
				<button class="btn btn-sm btn-primary" id="importMaealm">
					<i class="ace-icon glyphicon glyphicon-upload bigger-110"></i>
					Importar Stock
				</button>
				<button class="btn btn-sm btn-success" id="searchperiodosMaealm">
					<i class="ace-icon glyphicon glyphicon-search bigger-110"></i>
					Consultar
				</button>
				<button class="btn btn-sm btn-danger" id="deleteMaealm"
					onclick="deleteallstock()">
					<i class="ace-icon glyphicon glyphicon-trash bigger-110"></i>
					Eliminar
				</button>
				<button class="btn btn-sm btn-danger" id="deletesstockxperiodo"
					onclick="deletestockfordate()">
					<i class="ace-icon glyphicon glyphicon-trash bigger-110"></i>
					Eliminar por Periodos
				</button>

			</div>
		</div>
		<div class="hr hr-8 hr-dotted"></div>

		<div class="row">
			<div id="formrangeStock" style="display:none">			

			</div>
		</div>
	</div>
	<!-- /.col -->
</div>
<!-- /.row -->
</html>
<script>
	
	$(function() {
		//////////////////		
		$("#importMaealm").click(function() {
			$.ajax({
				url : '${pageContext.request.contextPath}/importMaealm',
				dataType : 'html',
				success : function(data) {
					$("#modal-body").html(data);
					$("#modal-header").html("<h4>Importar Inventario</h4>");
					$('#modalaps').modal('show');
				},
				error : function(data) {
					alert("Ocurrio un error");
				}

			});
		});
		$("#searchperiodosMaealm").click(function() {
			loader_open();
			$.ajax({
				url : '${pageContext.request.contextPath}/formrangevtas',
				dataType : 'html',
				success : function(data) {
					$("#formrange").show();
					$("#formrange").html(data);
					$.ajax({
	                    method:'POST',
	                    url:'${pageContext.request.contextPath}/searchalmacenes',         
	                    data:{},
	                    dataType:'JSON',
	                    success: function (data){               
	                        var options = '<option value="0"> [ Seleccione]  </option>';
	                        $.each(data, function (key, dat) {	                        	
	                            options += '<option value="'+dat.clave+'">'+dat.nombre+'</option>'                  
	                        });  
	                        options += '<option value="TODOS">TODOS</option>'	                        
	                         $("#almacen_imp").html( options );                      
	                    },
	                    error:function(jqXHR, exception){
	                        var msg =  errorsAjax(jqXHR, exception);
	                        alert("MSG: "+ msg)
	                    }           
	                });
					loader_close();
				},
				error : function(data) {
					alert("Ocurrio un error");
				}

			});

		});

	});
</script>