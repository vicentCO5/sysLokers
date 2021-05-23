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
				<button class="btn btn-sm btn-primary" id="importTraspaso">
					<i class="ace-icon glyphicon glyphicon-upload bigger-110"></i>
					Importar Traspaso OT
				</button>
				<button class="btn btn-sm btn-success" id="searchTraspaso">
					<i class="ace-icon glyphicon glyphicon-search bigger-110"></i>
					Consultar
				</button>
				<button class="btn btn-sm btn-danger" id="deletesTraspaso"
					onclick="deleteallTraspaso()">
					<i class="ace-icon glyphicon glyphicon-trash bigger-110"></i>
					Eliminar
				</button>				
			</div>
		</div>
		<div class="hr hr-8 hr-dotted"></div>

		<div class="row">
			<div id="listTraspasos" >			

			</div>
		</div>
	</div>
	<!-- /.col -->
</div>
<!-- /.row -->
</html>
<script>
	$(function(){
		$("#importTraspaso").click(function() {
			$.ajax({
				url : '${pageContext.request.contextPath}/getimportartraspaso',
				dataType : 'html',
				success : function(data) {
					$("#modal-body").html(data);
					$("#modal-header").html("<h4>Importar Ordenes de Traspaso</h4>");
					$('#modalaps').modal('show');
				},
				error : function(data) {
					alert("Ocurrio un error");
				}

			});
		});
		// ----------------------
		$("#searchTraspaso").click(function() {			
			
			 $.ajax({             	
             	url : '${pageContext.request.contextPath}/listtraspasos',
             	dataType : 'html',             	
		       }).done(function(data, textStatus, jqXHR  ) {
		           if ( console && console.log ) {
		              $("#listTraspasos").html(data);		                
		           }
		       }).fail(function( jqXHR, textStatus, errorThrown ) {
	               if ( console && console.log ) {
	                  // function de diferentes errores index
	                  errorsAjax(jqXHR, textStatus);		                    
	               }
		        }); // end .ajax			
		});
		
	})
	/*eliminar lista de traspasos */
		function deleteallTraspaso(){
			if(confirm("¿Cofirme acción ?")){
				
				 $.ajax({             	
	             	url : '${pageContext.request.contextPath}/deletetraspasos', 
	             	type: "post",
	             	dataType : 'JSON',
	    			data:{},
			       }).done(function(data, textStatus, jqXHR  ) {
			           if ( console && console.log ) {
			             
			              if(data.exito=="true"){
								alert(data.mensaje);
								traspasotable.ajax.reload( null, false );	
							}else{					
				 	 			for( i=0;i<data.error.length;i++){
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
		}
</script>