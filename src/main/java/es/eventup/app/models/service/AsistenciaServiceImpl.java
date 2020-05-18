package es.eventup.app.models.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.eventup.app.models.entity.Asistencia;
import es.eventup.app.models.repository.AsistenciaRepository;



@Service
public class AsistenciaServiceImpl implements AsistenciaService {

	@Autowired
	AsistenciaRepository<Asistencia, Long> repository;

	@Override
	@Transactional(readOnly = true)
	public List<Asistencia> findAll() {
		return (List<Asistencia>) repository.findAll();
	}

	@Override
	@Transactional
	public void save(Asistencia entity) {
		repository.save(entity);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Asistencia> findOne(Long id) {
		return repository.findById(id);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
}