package es.eventup.app.models.service;

import es.eventup.app.models.entity.Transporte;
import es.eventup.app.models.projections.TransporteProjection;

import java.util.List;
import java.util.Optional;


public interface TransporteService {
	
	public List<Transporte> findAll();

	public void save(Transporte entity);
	
	public Optional<Transporte> findOne(Long id);

	public Optional<TransporteProjection> find(Long id);
	
	public void delete(Long id);
	
	public List<TransporteProjection> findByEvento(Long id);
}
