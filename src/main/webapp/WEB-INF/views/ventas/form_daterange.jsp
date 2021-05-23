<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<div class="row">
		
		<div class="col-xs-4">
			<div class="form-group has-info">
				<label for="almacen_imp"><b> Almacen o PV:</b></label> <select
					id='almacen_imp' class='form-control'>
				</select>
			</div>
		</div>
		<div class="col-xs-2">
			<label for="finicio_imp"><b>Inicio:</b></label>			
			<div class="form-group has-info">
					<span class="block input-icon input-icon-right"> <input
						type="text"  id="finicio_imp" class="width-100 date-picker" /> <i
						class="ace-icon fa fa-calendar bigger-110"></i>
					</span>
				</div>
		</div>
		<div class="col-xs-2">
			<label for="ffin_imp"><b>Fin:</b></label>
			<div class="form-group has-info">
					<span class="block input-icon input-icon-right"> <input
						type="text" id="ffin_imp" class="width-100 date-picker" /> <i
						class="ace-icon fa fa-calendar bigger-110"></i>
					</span>
				</div>
		</div>
		<div class="col-xs-2">
			<br>
			<button type="button" id="showDatatableventas" class="btn btn-primary">
				<i class="ace-icon fa fa-table"></i> <b>Aceptar</b>
			</button>
		</div>
	</div>
	<div class="row" id="listventas" style="display:none">
		
	</div>
	<script type="text/javascript">
		var tableventas;
		$(function() {
			$('.date-picker').datepicker({
				autoclose : true,
				todayHighlight : true,
				format: 'yyyy-mm-dd',
			
			});
			$("#showDatatableventas").click(function() {
				loader_open();
				$.ajax({
					url : '${pageContext.request.contextPath}/listventas',
					dataType : 'html',
					success : function(data) {
						$("#listventas").html(data);
						$('#listventas').show(); 
						loader_close();
					},
					error : function(data) {
						alert("Ocurrio un error");
					}

				});

			});
		});
	</script>
</body>
</html>
