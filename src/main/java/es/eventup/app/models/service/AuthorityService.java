package es.eventup.app.models.service;

import java.util.List;
import java.util.Optional;

import es.eventup.app.models.entity.Authority;

public interface AuthorityService{
	public List<Authority> findAll();
	
	public void save(Authority entity);
	
	public Optional<Authority> findOne(Long id);
	
	public void delete(Long id);
	
}
