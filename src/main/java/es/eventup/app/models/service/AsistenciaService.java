package es.eventup.app.models.service;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;

import es.eventup.app.models.entity.Asistencia;
import es.eventup.app.models.projections.AsistenciaProjection;
import es.eventup.app.models.projections.AsistenciaProjection_Lista;

public interface AsistenciaService {
	
		public List<Asistencia> findAll();

		public List<AsistenciaProjection_Lista> findByEvento(Long id_evento);

		public void save(Asistencia entity);
		
		public Optional<Asistencia> findOne(Long id);
		
		public void delete(Long id);
		
	}


