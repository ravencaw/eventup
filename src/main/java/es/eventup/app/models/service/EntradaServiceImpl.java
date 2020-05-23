package es.eventup.app.models.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.eventup.app.models.entity.Entrada;
import es.eventup.app.models.projections.EntradaProjection;
import es.eventup.app.models.repository.EntradaRepository;



@Service
public class EntradaServiceImpl implements EntradaService {

	@Autowired
	EntradaRepository<Entrada, Long> repository;

	@Override
	@Transactional(readOnly = true)
	public List<Entrada> findAll() {
		return (List<Entrada>) repository.findAll();
	}

	@Override
	@Transactional
	public void save(Entrada entity) {
		repository.save(entity);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Entrada> findOne(Long id) {
		return repository.findById(id);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		repository.deleteById(id);
	}

	@Override
	public Optional<EntradaProjection> findByUserAndEvento(String dni, Long id) {
		// TODO Auto-generated method stub
		return repository.findByUsuario_DniAndVenta_Evento_Id(dni, id);
	}

	@Override
	public List<Entrada> findByUsuario(Long id) {
		// TODO Auto-generated method stub
		return repository.findByUsuario_Id(id);
	}
	
}