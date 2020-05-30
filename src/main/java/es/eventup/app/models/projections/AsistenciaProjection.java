package es.eventup.app.models.projections;

import java.util.Date;

public interface AsistenciaProjection {
	Long getId();
	
	Date getFecha();
	
	Date getHora();
	
}
