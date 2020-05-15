package es.eventup.app.models.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.eventup.app.models.entity.Mensaje;
import es.eventup.app.models.repository.MensajeRepository;



@Service
public class MensajeServiceImpl implements MensajeService {

	@Autowired
	MensajeRepository repository;

	@Override
	@Transactional(readOnly = true)
	public List<Mensaje> findAll() {
		return (List<Mensaje>) repository.findAll();
	}

	@Override
	@Transactional
	public void save(Mensaje entity) {
		repository.save(entity);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Mensaje> findOne(Long id) {
		return repository.findById(id);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
}