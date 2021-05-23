<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="utf-8">
</head>
<body>
 <!-- ============================================================== -->
   <!-- Start Page Content -->
   <!-- ============================================================== -->
   <div class="row">
       <div class="col-md-12">
           <div class="card card-body printableArea">
               <h3><b>Corte</b> <span class="pull-right">#</span></h3>
               <hr>
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
                   <div class="col-md-12">
                       <div class="table-responsive m-t-5" style="clear: both;">
<!--                         class="text-center"  class="text-right" -->
                           <table class="table table-hover" id="dt_listaCorte" >
                               <thead>
                                   <tr>
                                       <th class="text-center">#</th>
                                       <th class=text-center>Cve Equipaje</th>
                                       <th class="text-center">Equipaje</th>
                                       <th class="text-center">Tamaño</th>
                                       <th class="text-center">Collor</th>
                                       <th class="text-center">Cantidad</th>
                                       <th class="text-center">Precio</th>
                                       <th class="text-center">Tiempo</th>
                                       <th class="text-center">Total</th>
                                   </tr>
                               </thead>
                               <tbody>
                                   
                               </tbody>
                           </table>
                       </div>
                   </div>
                    <div class="col-md-12">
                    	<div class="pull-right m-t-5 text-right">
                           <hr style="margin-top: 1rem; margin-bottom: 1rem; ">
                           <h3><b>+ Total Cobrado :</b>$  ${totalcobro} </h3>
                           <p> + Fondo de Caja : $  ${totalCaja} </p>
                           <p> - Retiros : $  ${totalRetiro} </p>
                           <hr>
                           <h3><b>Total :</b> $  ${total}</h3>
                       </div>
                    </div>                    
              </div>
           </div>
             <div class="col-md-12">
                  <div class="clearfix"></div>
                       <hr>
                       <div class="text-right">
                           <button class="btn btn-danger" type="submit"> Cerrar Corte </button>
                           <button id="print" class="btn btn-default btn-outline" type="button"> <span><i class="fa fa-print"></i> Print</span> </button>
                       </div>
              </div>
       </div>
   </div>
   <!-- ============================================================== -->
   <!-- End PAge Content -->
   <!-- ============================================================== -->
</body>
<script>
    $(document).ready(function() {
    	// rellenar tabla por json
    	    	
		 var tablearticulos = $('#dt_listaCorte').DataTable({
			"ajax" : "${pageContext.request.contextPath}/listaCobrado",
					"destroy" : true,
					"searching" : false,
					"ordering" : false,					
					"scrollCollapse" : true,
					"paging" : false,
					info : true,
					"columns" : [ 
						{"data" : "indice"}, 
						{"data" : "numart"}, 
						{"data" : "nombreart"},
						{"data" : "tamanio"},				
						{"data" : "color"},
						{"data" : "cantidad"},
						{"data" : "precio"},
						{"data" : "tiempototal"},
						{"data" : "preciofinal"},
						],
						"columnDefs":[
							{className : "text-right", "targets":[5,6,7,8] }
						]
				});
         $("#dt_listaCorte tr td").addClass('text-right');
        $("#print").click(function() {
            var mode = 'iframe'; //popup
            var close = mode == "popup";
            var options = {
                mode: mode,
                popClose: close
            };
            $("div.printableArea").printArea(options);
        });
    });
    </script>
</html>