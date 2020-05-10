package es.eventup.app.models.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.eventup.app.models.entity.Valoracion;
import es.eventup.app.models.repository.ValoracionRepository;


@Service
public class ValoracionServiceImpl implements ValoracionService{

	@Autowired
	ValoracionRepository repository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Valoracion> findAll() {
		return (List<Valoracion>) repository.findAll();
	}

	@Override
	@Transactional
	public void save(Valoracion entity) {
		repository.save(entity);
		
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Valoracion> findOne(Long id) {
		return repository.findById(id);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		repository.deleteById(id);
		
	}


}
