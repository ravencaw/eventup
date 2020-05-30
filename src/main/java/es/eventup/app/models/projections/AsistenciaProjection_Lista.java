package es.eventup.app.models.projections;

import java.util.Date;

public interface AsistenciaProjection_Lista {
	Long getId();
	
	Date getFecha();
	
	Date getHora();
	
	EntradaProjection getEntrada();
}
