package es.eventup.app.models.projections;

import java.util.Date;

public interface TransporteProjection {
	Long getId();
	
	String getTipo();
	
	String getEmpresa();
	
	Integer getCapacidad();
	
	Date getHoraSalida();
	
	Date getHoraLlegada();
	
	Float getPrecio();
}
