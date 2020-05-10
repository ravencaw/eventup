package es.eventup.app.models.service;

import es.eventup.app.models.entity.Valoracion;

import java.util.List;
import java.util.Optional;



public interface ValoracionService {
	
	public List<Valoracion> findAll();

	public void save(Valoracion entity);
	
	public Optional<Valoracion> findOne(Long id);
	
	public void delete(Long id);
}
