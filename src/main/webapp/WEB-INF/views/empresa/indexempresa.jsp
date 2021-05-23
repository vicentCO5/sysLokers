<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="row">
	<div class="alert alert-info" id="errordelete" style="display:none">
		<button class="close" data-dismiss="alert">
			<i class="ace-icon fa fa-times"></i>
		</button>
		</div>
	<div class="col-sm-12">
		<div class="clearfix">
			<div class="pull-right tableTools-container"></div>
		</div>
		
		<div class="table-header">
			Lista de Empresas &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a class="white"
				href="#" id="newEmpresa" title="Agregar Nueva Empresa"> <i
				class="ace-icon fa fa-search-plus bigger-150"></i>
			</a>
		</div>
		<div>
			<table class="table  table-bordered table-hover" id="empresa-table">
				<thead>
					<tr>
						<th class="center">Id</th>
						<th class="center">Clave</th>
						<th class="center">Nombre</th>
						<th class="center">Ruc</th>
						<th class="center">Acciones</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="empresa" items="${list}">
						<tr>
							<td class="center">${empresa.id}</td>
							<td class="center">${empresa.clave}</td>
							<td class="center">${empresa.nombre}</td>
							<td class="center">${empresa.ruc}</td>
							<td class="center"><button
									onclick="editempresa(${empresa.id})"
									class="btn btn-info btn-xs" title="Editar">
									<i class="ace-icon fa fa-pencil bigger-130"></i>
								</button>
								<button onclick="deleteempresa(${empresa.id})"
									class="btn btn-danger btn-xs" title="Eliminar">
									<i class="ace-icon fa fa-trash-o bigger-130"></i>
								</button></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>

</div>
<script>
$(function(){
	var myTable = 
		$('#empresa-table').DataTable( {
			bAutoWidth: false,
			"bPaginate": false,			
	    } );
	
	$( "#newEmpresa" ).click(function() {
		$.ajax({
			url:'${pageContext.request.contextPath}/createempresa',
			dataType : 'html',
			success : function(data) {
				$("#modal-body").html(data);
				$("#modal-header").html("<h4>Nueva Empresa</h4>");
				$('#modalaps').modal('show'); 
				},
			});
		});
	
});
function editempresa(id){	
	console.log(id);
	$.ajax({
		url:'${pageContext.request.contextPath}/editempresa',
		type: "post",
		dataType : 'html',
		data:{id:id},
		success : function(data) {
			$("#modal-body").html(data);
			$("#modal-header").html("<h4>Editar Empresa</h4>");
			$('#modalaps').modal('show'); 
			},
		});
	}
function deleteempresa(id){	
	console.log(id);
	$.ajax({
		url:'${pageContext.request.contextPath}/deleteempresa',
		type: "post",
		dataType : 'html',
		data:{id:id},
		}).done(function(data){		
			alert(data);
 			   		jQuery.ajax({
	    			type:'POST',
	    			url:'${pageContext.request.contextPath}/getindexempresa',
	    			beforeSend: function() {	    				
	    			},success:function(data,textStatus){
	    				jQuery('#divprincipal').html(data);
	    				
	    				},	    				
	    			});							
	    });
	}
</script>


