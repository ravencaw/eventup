package es.eventup.app.models.service;

import java.util.List;
import java.util.Optional;


import es.eventup.app.models.entity.Entrada;
import es.eventup.app.models.projections.EntradaProjection;
import es.eventup.app.models.projections.EntradaProjection_Lista;

public interface EntradaService {
	
		public List<Entrada> findAll();

		public List<EntradaProjection_Lista> findByVenta(Long id);

		public void save(Entrada entity);
		
		public Optional<Entrada> findOne(Long id);
		
		public void delete(Long id);
		
		public Optional<EntradaProjection> findByUserAndEvento(String dni, Long id);
		
		public List<Entrada> findByUsuario(Long id);

		public List<Entrada> findByTransporte(Long id);
	}


