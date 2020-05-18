package es.eventup.app.models.projections;

import java.util.Date;

import es.eventup.app.models.entity.Evento;

public interface VentaProjection {
	
	Long getId();
	
	Date getFecha();
	
	Date getHora();
	
	EventoProjection getEvento();

}
