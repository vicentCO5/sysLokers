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
			Lista de Almacenes &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		</div>
		<div>
			<table class="table  table-bordered table-hover" id="almacen-table">
				<thead>
					<tr>
						<th class="center">Id</th>
						<th class="center">Clave</th>
						<th class="center">Nombre</th>
						<th class="center">Empresa</th>
						<th class="center">Pais</th>
					</tr>
				</thead>
				<tbody>
					
				</tbody>
			</table>
		</div>
	</div>

</div>
<script>
$(function(){
	

	
});


</script>


