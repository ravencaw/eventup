package es.eventup.app.models.service;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;

import es.eventup.app.models.entity.Actividad;

public interface ActividadService {
	
		public List<Actividad> findAll();

		public void save(Actividad entity);
		
		public Optional<Actividad> findOne(Long id);
		
		public void delete(Long id);
	}


