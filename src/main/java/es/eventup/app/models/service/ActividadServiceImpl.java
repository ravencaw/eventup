package es.eventup.app.models.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.sun.mail.iap.Response;

import es.eventup.app.models.entity.Actividad;
import es.eventup.app.models.repository.ActividadRepository;

@Service
public class ActividadServiceImpl  implements ActividadService {

	
	@Autowired
	ActividadRepository<Actividad, Long>  repository;

	@Override
	//@Transactional(readOnly=true)
	public List<Actividad> findAll() {
		
		return (List<Actividad>) repository.findAll();
	}

	@Override
	@Transactional
	public void save(Actividad entity) {
		repository.save(entity);
		
	}

	@Override
	//@Transactional(readOnly=true)
	public Optional<Actividad> findOne(Long id) {
		
		return repository.findById(id);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		repository.deleteById(id);
		
	}
	

	
	

}