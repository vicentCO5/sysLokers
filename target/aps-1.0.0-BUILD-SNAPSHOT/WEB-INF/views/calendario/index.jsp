<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="ISO-8859-1">
</head>
<body>
   <div class="row">
   		<div class="col-sm-2"></div>
   		<div class="col-sm-10"><div id="calendar"></div></div>  
    </div>
 	
</body>
<script type="text/javascript">
$(function() {
	/* initialize the calendar
	-----------------------------------------------------------------*/

	var date = new Date();
	var d = date.getDate();
	var m = date.getMonth();
	var y = date.getFullYear();


	var calendar = $('#calendar').fullCalendar({
		
		selectable: true,
	      eventLimit: true, // for all non-agenda views
	      views: {
	        agenda: {
	          eventLimit: 6 // adjust to 6 only for agendaWeek/agendaDay
	        }
	      },
	      header: {
	    	language: 'es',
	        left: 'prev,next today',
	        center: 'title',
	        right: 'month,agendaWeek,agendaDay'
	      },
	      buttonText: {
	        today: 'Hoy',
	        month: 'Mes',
	        week: 'Semana',
	        day: 'Dia'
	      },
	      dayClick: function(date) {
	         // ok('clicked ' + date.format());
	          var fechaEvent = date.format();
	          var tipocalendario = 0;
 	          agregarEvento_programado(fechaEvent, tipocalendario );				
	      },
	      select: function(startDate, endDate) {
	         // alert('selected ' + startDate.format() + ' to ' + endDate.format());
	    },
	      eventSources: [
	   // your event source
	    {
      // 	      url: 'services/resurtido/calendario/eventos.php',
	      url : '${pageContext.request.contextPath}/listaeventos',
	      type: 'POST',
	      data: {
	        custom_param1: 'something',
	        custom_param2: 'somethingelse',
	        tipocalendario : '0'
	      },
	      error: function() {
	        alert('there was an error while fetching events!');
	      },
	      // color: 'yellow',   // a non-ajax option
	      textColor: 'black' // a non-ajax option
	    } ]
	    
	   /* events: function(start, end, timezone, callback) {
	        $.ajax({
	            // url: 'schedule.php/load',
	            url : '${pageContext.request.contextPath}/listaeventos',
	            type: 'POST',
	            dataType: 'json',
	            data: {
	                start: start.format(),
	                end: end.format()
	            },
	            success: function(doc) {
	                var events = [];
	                if(!!doc.result){
	                    $.map( doc.result, function( r ) {
	                        events.push({
	                            id: r.id,
	                            title: r.title,
	                            start: r.start,
// 	                            end: r.date_end
	                        });
	                    });
	                }
	                callback(events);
	            }
	        });
	    } */
	      
	});

	});
	function agregarEvento_programado(fechaEvent, tipocalendario){
		console.log(fechaEvent + tipocalendario );		
		$("#modal-header").html("<h4>Programar Almacenes "+fechaEvent+" </h4>");
		$('#modalaps').modal('show');
		$(".modal-dialog").addClass('modal-lg');
		$("#modal-body").html(fechaEvent);
		
		$.ajax({             	
          	url : '${pageContext.request.contextPath}/formprogramar',
          	dataType : 'html',
          	data:{fechaEvent:fechaEvent, tipocalendario:tipocalendario},
	       }).done(function(data, textStatus, jqXHR  ) {
	           if ( console && console.log ) {	              
	              $("#modal-body").html(data);
	           }
	       }).fail(function( jqXHR, textStatus, errorThrown ) {
               if ( console && console.log ) {
                  // function de diferentes errores index
                  errorsAjax(jqXHR, textStatus);		                    
               }
	        }); // end .ajax
		
	}
</script>
</html>