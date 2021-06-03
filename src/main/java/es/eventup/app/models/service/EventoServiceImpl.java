package es.eventup.app.models.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.eventup.app.models.entity.Evento;
import es.eventup.app.models.projections.EventoProjection;
import es.eventup.app.models.repository.EventoRepository;



@Service
public class EventoServiceImpl implements EventoService {

	@Autowired
	EventoRepository<Evento,Long> repository;

	@Override
	@Transactional(readOnly = true)
	public List<Evento> findAll() {
		return (List<Evento>) repository.findAll();
	}

	@Override
	@Transactional
	public void save(Evento entity) {
		repository.save(entity);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Evento> findOne(Long id) {
		return repository.findById(id);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		repository.deleteById(id);
	}

	@Override
	public List<EventoProjection> findAllProjections() {
		// TODO Auto-generated method stub
		return repository.findAllProjectedBy();
	}

	@Override
	public List<EventoProjection> findByNombreAndCiudad(String nombre, String ciudad) {
		// TODO Auto-generated method stub
		return repository.findByNombreContainingAndCiudadContainingOrderByFechaDesc(nombre, ciudad);
	}

	@Override
	public List<EventoProjection> findByNombre(String nombre) {
		// TODO Auto-generated method stub
		return repository.findByNombreContainingOrderByFechaDesc(nombre);
	}

	@Override
	public List<EventoProjection> findByCiudad(String ciudad) {
		// TODO Auto-generated method stub
		return repository.findByCiudadContainingOrderByFechaDesc(ciudad);
	}

	@Override
	public List<EventoProjection> findByUser(Long id) {
		// TODO Auto-generated method stub
		return repository.findByUsuario_Id(id);
	}

	@Override
	public List<EventoProjection> findAllLimited() {
		return repository.findAllProjectedByOrderByFechaDesc().subList(0, 3);
	}
}
