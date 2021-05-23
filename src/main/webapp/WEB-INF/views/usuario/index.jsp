<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
<div class="col-lg-12" >
	<div class="card">
	 	<div class="card-body" >
	 	    <h4 class="card-title">Usuarios</h4>
			<h6 class="card-subtitle">Lista de usuarios ingresados</h6>
			<div class="alert alert-danger hidden" id="errordelete"></div>
			<div class="table-header">
				<button onclick="createusr()" class="btn waves-effect waves-light btn-rounded btn-success"> <span class="glyphicon glyphicon-plus blue"></span>  Nuevo  </button>
			</div>
			<div class="table-responsive m-t-10">
				<table class="display nowrap table table-hover table-striped table-bordered"id="dt-usuario" style=" width: 100%;">
				<thead>
					<tr>
						<th class="center">Username</th>
						<th class="center">Nombre</th>
						<th class="center">Perfil</th>
						<th class="center">Tipo</th>
						<th class="center">Roles</th>
						<th class="center">Acciones</th>
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
	var dtusuario;
	$(function() {
		jQuery("#dt-usuario tbody").on("click", "span", function(){
 
			
			$("#modal-body").html("");
			$("#modal-header").html("<h4>Editar Usuario</h4>");
			$('#modalaps').modal('show');
			
			var thatId = jQuery(this).closest("tr").attr("id");
			var id = thatId.substr(thatId.indexOf("_")+1);
			console.log(id);
			loader_open();
			$.ajax({
				url:'${pageContext.request.contextPath}/editUser',
				data:{id:id},
				dataType : 'html',
				success : function(data) {
					
					 $("#modal-body").html(data);		
					
					loader_close();
					}
				});	
		});
		dtusuario = jQuery("#dt-usuario").DataTable({
			"ajax":"${pageContext.request.contextPath}/listJSON",
			"destroy": true,
			"paging":false,						
			"scrollX":"380px",
			"scrollCollapse": false,					
			"info":true,
			"columns":[
				{"data":"username"},
				{"data":"nombre"},
				{"data":"perfil"},
				{"data":"tipo"},
				{"data":"roles"},
				{"data":"accion"}
			]
		});
	});
	function createusr(){
		loader_open();
		$.ajax({
			url:'${pageContext.request.contextPath}/createuser',
			dataType : 'html',
			success : function(data) {
				$("#modal-body").html(data);
				$("#modal-header").html("<h4>Nuevo Usuario</h4>");
				$('#modalaps').modal('show'); 
				loader_close();
				},
			});	
	}
	function eliminarusuario(id){
		if(!confirm("Desea eliminar este usuario"))
		return 	
   
		 swal({   
	         title: "Confirme !",   
	         text: "Confirme para eliminar",   
	         type: "warning",   
	         showCancelButton: true,   
	         confirmButtonColor: "#DD6B55",   
	         confirmButtonText: "Aceptar",   
	         closeOnConfirm: false 
	     }, function(){   
	    	 swal({   
	             title: "",   
	             text: "Procesado ..!", 
	             type: "success",  
	             timer: 1200,   
	             showConfirmButton: true 
	         });
	    	   $.ajax({
					method:'POST',			
					url:'${pageContext.request.contextPath}/deleteUser',
					data:{id:id},
					dataType : 'JSON',
					success : function(data) {
						if(data.exito=="true"){
							dtusuario.ajax.reload( null, false );
						}else{
							var html = "";
							for( i=0;i<data.error.length;i++){
			 					html += '<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span> '+data.error[i]+'<br/>';
			 				};
			 				var diverrors = jQuery("#errordelete")
							diverrors.removeClass("hidden")
							diverrors.html(html)
							setTimeout(function() {
						        $("#errordelete").fadeOut(500);
						    },2000);
						}									
					 }
				    });
	     });

	}
	</script>
</body>
</html>