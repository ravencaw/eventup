/**
 * 
 */
$(document).ready(function(){
		
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");
		
		$(document).ajaxSend(function (e, xhr, options) {
			xhr.setRequestHeader(header, token);
		});

	    $.ajax({
	        method: "get",
	        url: "/rest/evento/calendar",
	        datatype : "application/json; charset=utf-8",
		    contentType: "application/json; charset=utf-8",
	        success: function (result) {

	            var eventos = new Array();
	            
	            result.forEach(element => {
	                var evento = new Object();

	                evento.id=element.id;
	                evento.name=element.direccion+" "+element.ciudad;
	                evento.startdate=element.fecha;
	                evento.starttime=element.hora;
	                evento.color="#FFB128";
	                evento.url="#";

	                eventos.push(evento);
	            });

	            var eventos_calendario = new Object();

	            eventos_calendario.monthly=eventos;

	            var eventos_json = JSON.stringify(eventos_calendario);
	            eventos_json = JSON.parse(eventos_json);

	            console.log(eventos_json);
	            
	            $('#mycalendar').monthly({
	                mode: 'event',
	                stylePast: true,
	                dataType: 'json',
	                events: eventos_json,
	                weekStart: 'Mon'
	            });
	        }
	    });
	});