package es.eventup.app.models.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.eventup.app.models.entity.Venta;
import es.eventup.app.models.projections.VentaProjection;
import es.eventup.app.models.repository.VentaRepository;



@Service
public class VentaServiceImpl implements VentaService {

	@Autowired
	VentaRepository<Venta, Long> repository;

	@Override
	@Transactional(readOnly = true)
	public List<Venta> findAll() {
		return (List<Venta>) repository.findAll();
	}

	@Override
	@Transactional
	public void save(Venta entity) {
		repository.save(entity);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Venta> findOne(Long id) {
		return repository.findById(id);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		repository.deleteById(id);
	}

	@Override
	public List<VentaProjection> findByEvento(Long id) {
		// TODO Auto-generated method stub
		return repository.findByEvento_Id(id);
	}
	
}