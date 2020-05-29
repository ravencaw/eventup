package es.eventup.app.models.projections;

public interface TransporteProjection {
	Long getId();
	
	String getTipo();
	
	String getEmpresa();
	
	Integer getCapacidad();
	
	Float getPrecio();
}
