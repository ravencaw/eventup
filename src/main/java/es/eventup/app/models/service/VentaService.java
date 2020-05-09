package es.eventup.app.models.service;

import java.util.List;
import java.util.Optional;


import es.eventup.app.models.entity.Venta;

public interface VentaService {
	
		public List<Venta> findAll();

		public void save(Venta entity);
		
		public Optional<Venta> findOne(Long id);
		
		public void delete(Long id);
	}


