<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<html>
<head>
</head>
<body>
<div class="row">
<div class="tabbable">
			<ul class="nav nav-tabs" id="myTab">
				<li class="">
					<a data-toggle="tab" href="#tab_prioridad" aria-expanded="false" id="prioridadalmacen">
						<i class="green ace-icon fa fa-calendar bigger-120"></i>
						Prioridad Almacen
					</a>
				</li>
				<li class=" active" >
					<a data-toggle="tab" href="#messages" aria-expanded="false">
					<i class="green ace-icon fa fa-fire bigger-120"></i>
						Cálculos Resurtido
						
					</a>
				</li>
				<li class="">
					<a data-toggle="tab" href="#messages" aria-expanded="false">
					 <i class="green ace-icon fa fa-tachometer bigger-120"></i>
						Aplicar Sugerido						
					</a>
				</li>
				
				<li class="dropdown">
					<a data-toggle="dropdown" class="dropdown-toggle" href="#" aria-expanded="false">
						Configuración &nbsp;
						<i class="ace-icon fa fa-cogs bigger-110 width-auto"></i>
					</a>
					<ul class="dropdown-menu dropdown-info">
						<li class="active">
							<a data-toggle="tab" href="#dropdown1" aria-expanded="true">Parametros Almacen</a>
						</li>
						<li>
							<a data-toggle="tab" href="#dropdown2">@ido</a>
						</li>
					</ul>
				</li>
			</ul>

			<div class="tab-content">
				<div id="tab_prioridad" class="tab-pane fade">
					<p>Raw denim you probably haven't heard of them jean shorts Austin.</p>
				</div>
				<div id="messages" class="tab-pane fade">
					<p>Food truck fixie locavore, accusamus mcsweeney's marfa nulla single-origin coffee squid.</p>
				</div>
				<div id="dropdown1" class="tab-pane fade active in">
					<p>Etsy mixtape wayfarers, ethical wes anderson tofu before they sold out mcsweeney's organic lomo retro fanny pack lo-fi farm-to-table readymade.</p>
				</div>
				<div id="dropdown2" class="tab-pane fade">
					<p>Trust fund seitan letterpress, keytar raw denim keffiyeh etsy art party before they sold out master cleanse gluten-free squid scenester freegan cosby sweater. Fanny pack portland seitan DIY, art party locavore wolf cliche high life echo park Austin.</p>
				</div>
			</div>
		</div>
</div>
<script type="text/javascript">
	$("#prioridadalmacen").on("click",function(){
		
		 $("#tab_prioridad").html("");
		 $.ajax({             	
	          	url : '${pageContext.request.contextPath}/indexcalendario',
	          	dataType : 'html',             	
		       }).done(function(data, textStatus, jqXHR  ) {
		           if ( console && console.log ) {
		              $("#tab_prioridad").html(data);		                
		           }
		       }).fail(function( jqXHR, textStatus, errorThrown ) {
	               if ( console && console.log ) {
	                  // function de diferentes errores index
	                  errorsAjax(jqXHR, textStatus);		                    
	               }
		        }); // end .ajax
		        		     
	})
	
</script>
</body>

</html>