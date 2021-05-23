<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<body>
	<div class="row">
		<div class="col-sm-12">
			<div class="widget-box">
				<div class="widget-header widget-header-flat widget-header-small">
					<h4 >Gestión de Usuarios <i class="ace-icon glyphicon glyphicon-user"></i></h4>
				</div>
				<div class="widget-body">
					<div class="widget-main">
						<div class="tabbable">
							<ul class="nav nav-tabs padding-12 tab-color-blue background-blue" role="tablist">
								<li class="active"><a href="#user-index" class="ui-tabs-anchor" role="tab"	data-toggle="tab"><b>Usuarios</b>
									</a>
								</li>
								<li><a href="#perfil-index" role="tab" data-toggle="tab"><b>Perfil</b>
								</a>
								</li>
							</ul>
							<div class="tab-content">
								<div class="tab-pane active" id="user-index">					
								<jsp:include page="${request.contextPath}/listusers"></jsp:include>
								</div>
								<div class="tab-pane" id="perfil-index">
								<jsp:include page="${request.contextPath}/listperfiles"></jsp:include>
								</div>
							</div>
						</div>
					</div>
					<!-- /.widget-main -->
				</div>
				<!-- /.widget-body -->
			</div>
			<!-- /.widget-box -->
		</div>
	</div>



</body>
</html>
<script>
$(function(){
	
});


</script>


