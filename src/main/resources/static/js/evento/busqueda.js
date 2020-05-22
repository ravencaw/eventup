/**
 * 
 */

$(document).ready(function(){
		
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");
		
		$(document).ajaxSend(function (e, xhr, options) {
			xhr.setRequestHeader(header, token);
		});
		
		
		$("#busqueda").on("click", function(){
			var nombre = $("#patron").val();
			var ciudad = $("#ciudad").val();
			 busquedaAjax(nombre, ciudad);
		});
		
		$("#busqueda").trigger("click");
});

function busquedaAjax(nombre, ciudad){
	var name = (nombre!="")?nombre:null;
	var city = (ciudad!="")?ciudad:null;
	
	$("#busqueda_div").empty();
	
	$.ajax({
        method: "get",
        url: "/rest/evento/busqueda/"+name+"/"+city,
        datatype : "application/json; charset=utf-8",
	    contentType: "application/json; charset=utf-8",
        success: function (result) {
        	console.log(result);
        	result.forEach(evento => {
        		console.log(evento);
                $("#busqueda_div").append(
                	"<div class='container'>"+
                        "<a href='/evento/show/"+evento.id+"'><div class='row'>"+
                            "<div class='col-md-12'>"+
                                "<div class='card'>"+
                                     "<div class='card-body'>"+
                                        "<h4 class='card-title'>"+evento.nombre+"</h4>"+
            				            "<h6 class='text-muted card-subtitle mb-2'>Fecha: <b>"+evento.fecha+"</b> Hora: <b>"+evento.hora+"</b></h6>"+
            				            "<p class='card-text'>Descripción: "+evento.descripcion+"</p><br>"+
            				            "<h4>Precio: "+evento.precio+" €</h4>"+
            				            "<a class='btn btn-light btn-xs col-md-12' href='/venta/nuevo/"+evento.id+"'>Comprar</a></td>"+
                                "</div>"+
                            "</div>"+
                        "</div>"+
                    "</div></div><br>"
                );
            });
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
			$("#busqueda_div").html("<div class='container'>"+
					"<div class='row'>"+
	                "<div class='col-md-12'>"+
	                "<div class='alert alert-danger' role='alert'>No se han encontrado resultados</div>"+
	                "</div>"+
	                "</div>"+
	    	        "</div>");
		  }
    });
}