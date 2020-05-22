package es.eventup.app.models.projections;

import java.util.Date;

public interface EventoProjection {
	Long getId();
	
	String getNombre();
	
	Date getFecha();
	
	Date getHora();
	
	String getDireccion();
	
	String getCiudad();
	
	String getDescripcion();
	
	Double getPrecio();
	
	UserProjection getUsuario();
}
