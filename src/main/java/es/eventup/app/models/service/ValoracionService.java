package es.eventup.app.models.service;

import es.eventup.app.models.entity.Valoracion;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;




public interface ValoracionService {
	
	public List<Valoracion> findAll();

	public Page<Valoracion> findAll(Pageable pageable);
	
	public void save(Valoracion entity);
	
	public Optional<Valoracion> findOne(Long id);
	
	public void delete(Long id);
}
