package es.eventup.app.models.service;

import es.eventup.app.models.entity.Transporte;

import java.util.List;
import java.util.Optional;


public interface TransporteService {
	
	public List<Transporte> findAll();

	public void save(Transporte entity);
	
	public Optional<Transporte> findOne(Long id);
	
	public void delete(Long id);
}
