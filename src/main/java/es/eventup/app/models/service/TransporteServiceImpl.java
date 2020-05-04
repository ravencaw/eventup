package es.eventup.app.models.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import es.eventup.app.models.entity.Transporte;
import es.eventup.app.models.repository.TransporteRepository;

@Service
public class TransporteServiceImpl implements TransporteService {

	@Autowired
	TransporteRepository repository;

	@Override
	@Transactional(readOnly = true)
	public List<Transporte> findAll() {
		return (List<Transporte>) repository.findAll();
	}

	@Override
	@Transactional
	public void save(Transporte entity) {
		repository.save(entity);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Transporte> findOne(Long id) {
		return repository.findById(id);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
}
