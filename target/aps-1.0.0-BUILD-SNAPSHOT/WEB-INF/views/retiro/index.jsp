<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="utf-8">
</head>
<body>
   <div class="col-md-12">
		<div class="card">
			<div class="card-body">	
				<h3>Retiro registrados</h3>
			      <div class="row">
				       	<div class="col-md-12">
				       		 <div class="pull-left">
	                           <address>
	                               <h3> &nbsp;<b class="text-info">Mostrador</b></h3>
	                               <p class="m-l-5"># ${clave},
	                                   <br/> ${nombre},                                   
	                                   <br/> ${direccion}</p>
	                           </address>
	                       </div>
	                       <div class="pull-right text-right">
	                           <address>
	                               <h3>Usuario</h3>
	                               <h4 class="font-bold">${usuario}</h4>
	                               <p class="text-muted m-l-30">Fecha : ${fecha}                                                           </p>
	                               <p class="m-t-5" style='margin :3px;'><b>Inicio :</b> <i class="fa fa-calendar"></i> ${horainicio}</p>
	                               <p><b>Fin :</b> <i class="fa fa-calendar"></i> ${horafin}</p>
	                           </address>
	                       </div>
				       	</div>
                   </div> 		
				  <div class="row" style="margin:0px;">
				    <!-- Column -->
				    <div class="col-lg-3 col-md-6">
				        <div class="card">
				            <div class="card-body" style="padding: 0.25rem;">
				                <div class="d-flex p-10 no-block">
				                 <div class="align-self-center display-6 mx-auto"><i class="text-info icon-Dollar-Sign"></i></div>
				                    <span class="align-slef-center">
				                        <h2 class="m-b-0">${totalCaja}</h2>
				                        <h6 class="text-muted m-b-0">Total Caja</h6>
				                    </span>
				                    
				                </div>
				            </div>
				            <div class="progress">
				                <div class="progress-bar bg-success" role="progressbar" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100" style="width:70%; height:3px;"> <span class="sr-only">50% Complete</span></div>
				            </div>
				        </div>
				    </div>
				    <!-- Column -->
				    <!-- Column -->
				    <div class="col-lg-3 col-md-6">
				        <div class="card">
				            <div class="card-body" style="padding: 0.25rem;">
				                <div class="d-flex p-10 no-block">
				                    <div class="align-self-center display-6 mx-auto"><i class="text-info icon-Dollar-Sign"></i></div>
				                    <span class="align-slef-center">
				                        <h2 class="m-b-0">${totalcobro}</h2>
				                        <h6 class="text-muted m-b-0">Total Cobrado</h6>
				                    </span>
				                    
				                </div>
				            </div>
				            <div class="progress">
				                <div class="progress-bar bg-info" role="progressbar" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100" style="width:70%; height:3px;"> <span class="sr-only">50% Complete</span></div>
				            </div>
				        </div>
				    </div>
				    <!-- Column -->
				    <!-- Column -->
				    <div class="col-lg-3 col-md-6">
				        <div class="card">
				            <div class="card-body" style="padding: 0.25rem;">
				                <div class="d-flex p-10 no-block">
				                <div class="align-self-center display-6 mx-auto"><i class="text-primary icon-Inbox-Forward"></i></div>
				                    <span class="align-slef-center">
				                        <h2 class="m-b-0"> - ${totalRetiro}</h2>
				                        <h6 class="text-muted m-b-0">Retiros</h6>
				                    </span>
				                    
				                </div>
				            </div>
				            <div class="progress">
				                <div class="progress-bar bg-primary" role="progressbar" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100" style="width:70%; height:3px;"> <span class="sr-only">50% Complete</span></div>
				            </div>
				        </div>
				    </div>
				    <!-- Column -->
				    <!-- Column -->
				    <div class="col-lg-3 col-md-6">
				        <div class="card">
				            <div class="card-body" style="padding: 0.25rem;">
				                <div class="d-flex p-10 no-block">
				                    <div class="align-self-center display-6 mx-auto"><i class="text-danger  fa fa-dollar"></i></div>
				                    <span class="align-slef-center">
				                        <h2 class="m-b-0">${total}</h2>
				                        <h6 class="text-muted m-b-0">Total</h6>
				                    </span>
				                    
				                </div>
				            </div>
				            <div class="progress">
				                <div class="progress-bar bg-danger" role="progressbar" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100" style="width:70%; height:3px;"> <span class="sr-only">50% Complete</span></div>
				            </div>
				        </div>
				    </div>
				    <!-- Column -->
				    <!-- Column -->
				</div>
				  <div class="col-md-12">
                       <div class="table-responsive m-t-5" style="clear: both;">
						   <!--  class="text-center"  class="text-right" -->
						   <button type="button" class="btn waves-effect waves-light btn-rounded btn-success" id="newRetiro"><i class="fa  fa-plus-circle"></i> Nuevo</button>
                           <table class="table table-hover" id="dt_listaRetiros" >
                               <thead>
                                   <tr>
                                       <th class="text-center">#</th>
                                       <th class=text-center>Serie</th>
                                       <th class="text-center">Folio</th>
                                       <th class="text-center">Importe</th>
                                       <th class="text-center">Motivo</th>
                                       <th class="text-center">Receptor</th>
                                       <th class="text-center">Fecha</th>
                                       <th class="text-center">Tiempo</th>                                       
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
<script>
    $(document).ready(function() {
    	/* agregar nuevo retiro */
    	$("#newRetiro").click(function(){
    		$('#modalaps').modal('show');
        	$("#modal-header").html("");
    		$("#modal-body-detalle").html("");
    		
    		$.ajax({             	
    	   		 url:'${pageContext.request.contextPath}/formRetiro',
    	   			type: "post",	    	
    	   	       }).done(function(data, textStatus, jqXHR  ){
    	   	           if ( console && console.log ) 
    	   	           {			             			      	   	        	
    	   	        	$("#modal-body").html(data); 
    	   	           }
    	   	       }).fail(function( jqXHR, textStatus, errorThrown ) {
    	                  if ( console && console.log ) {
    	                     // function de diferentes errores index
    	                     errorsAjax(jqXHR, textStatus);		                    
    	                  }
    	   	    }); // end .ajax 
    	});
    	// rellenar tabla por json    	    				
		 var tableretiros = $('#dt_listaRetiros').DataTable({
			"ajax" : "${pageContext.request.contextPath}/listaRetiros",
					"destroy" : true,
					"searching" : false,
					"ordering" : false,					
					"scrollCollapse" : true,
					"paging" : false,
					info : true,
					"columns" : [ 
						{"data" : "indice"}, 
						{"data" : "serie"}, 
						{"data" : "folio"},																
						{"data" : "importe"},
						{"data" : "motivo"},
						{"data" : "receptor"},
						{"data" : "fecha"},
						{"data" : "tiempo"},										
						],
						"columnDefs":[
							{className : "text-right", "targets":[3] }
						]
				});
         $("#dt_listaRetiros tr td").addClass('text-right');      
    });
    </script>
</body>
</html>