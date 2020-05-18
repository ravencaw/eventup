package es.eventup.app.models.service;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;

import es.eventup.app.models.entity.Mensaje;

public interface MensajeService {
	
		public List<Mensaje> findAll();

		public void save(Mensaje entity);
		
		public Optional<Mensaje> findOne(Long id);
		
		public void delete(Long id);
	}


