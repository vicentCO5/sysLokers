<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<div class="row">
	<div class="col-xs-12">
		<div class="page-header"><h1>Transitos de Orden de Compra</h1>
		</div>
		<!-- /.page-header -->
		<div class="row">
			<div class="alert alert-info">
				<button class="btn btn-sm btn-primary" id="importTransito">
					<i class="ace-icon glyphicon glyphicon-upload bigger-110"></i>
					Importar Transito OC
				</button>
				<button class="btn btn-sm btn-success" id="searchTransito">
					<i class="ace-icon glyphicon glyphicon-search bigger-110"></i>
					Consultar
				</button>
				<button class="btn btn-sm btn-danger" id="deleteTransito"
					onclick="deleteallTransito()">
					<i class="ace-icon glyphicon glyphicon-trash bigger-110"></i>
					Eliminar
				</button>				
			</div>
		</div>
		<div class="hr hr-8 hr-dotted"></div>

		<div class="row">
			<div id="listaTransito">			

			</div>
		</div>
	</div>
	<!-- /.col -->
</div>
<!-- /.row -->
</html>
<script>
	$(function(){
		$("#importTransito").click(function() {
			$.ajax({
				url : '${pageContext.request.contextPath}/getimportartransito',
				dataType : 'html',
				success : function(data) {
					$("#modal-body").html(data);
					$("#modal-header").html("<h4>Importar Transito</h4>");
					$('#modalaps').modal('show');
				},
				error : function(data) {
					alert("Ocurrio un error");
				}

			});
		});
		/*
		Transito lista */
		$("#searchTransito").on("click",function(){
			 $.ajax({             	
		          	url : '${pageContext.request.contextPath}/listtransito',
		          	dataType : 'html',             	
			       }).done(function(data, textStatus, jqXHR  ) {
			           if ( console && console.log ) {
			              $("#listaTransito").html(data);		                
			           }
			       }).fail(function( jqXHR, textStatus, errorThrown ) {
		               if ( console && console.log ) {
		                  // function de diferentes errores index
		                  errorsAjax(jqXHR, textStatus);		                    
		               }
			        }); // end .ajax
		})
	});
	function deleteallTransito(){
		 $.ajax({             	
	          	url : '${pageContext.request.contextPath}/deletetransito', 
	          	type: "post",
             	dataType : 'JSON',
    			data:{},
		       }).done(function(data, textStatus, jqXHR  ) {
		           if ( console && console.log ) {
		        	   console.log(data);
		        	   console.log(data.exito);
		        	   if(data.exito=="true"){
							alert(data.mensaje);
							transitotable.ajax.reload( null, false );	
						}else{					
							for( i=0;i<data.error.length; i++){
			 	 				alert(data.error[i]);
			 	 	 		}
						}		                
		           }
		       }).fail(function( jqXHR, textStatus, errorThrown ) {
	               if ( console && console.log ) {
	                  // function de diferentes errores index
	                  errorsAjax(jqXHR, textStatus);		                    
	               }
		        }); // end .ajax
	}

</script>