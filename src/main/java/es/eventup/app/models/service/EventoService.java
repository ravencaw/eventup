package es.eventup.app.models.service;

import java.util.List;
import java.util.Optional;

import es.eventup.app.models.entity.Evento;
import es.eventup.app.models.projections.EventoProjection;


public interface EventoService{
	public List<Evento> findAll();
	
	public void save(Evento entity);
	
	public Optional<Evento> findOne(Long id);
	
	public void delete(Long id);
	
	List<EventoProjection> findAllProjections();
}
