package es.eventup.app.models.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.eventup.app.models.entity.Actividad;
import es.eventup.app.models.projections.ActividadProjection;
import es.eventup.app.models.repository.ActividadRepository;



@Service
public class ActividadServiceImpl implements ActividadService {

	@Autowired
	ActividadRepository repository;

	@Override
	@Transactional(readOnly = true)
	public List<Actividad> findAll() {
		return (List<Actividad>) repository.findAll();
	}

	@Override
	@Transactional
	public void save(Actividad entity) {
		repository.save(entity);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Actividad> findOne(Long id) {
		return repository.findById(id);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		repository.deleteById(id);
	}

	@Override
	public List<ActividadProjection> FindByEvento(Long id) {
		return repository.findByEvento_Id(id);
	}
	
}