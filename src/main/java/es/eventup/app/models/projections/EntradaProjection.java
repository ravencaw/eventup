package es.eventup.app.models.projections;


public interface EntradaProjection {
	Long getId();
	
	VentaProjection getVenta();
	
	UserProjection getUsuario();
	
	String getNumAsiento();
	
	String getTipo();
	
	AsistenciaProjection getAsistencia();
}
