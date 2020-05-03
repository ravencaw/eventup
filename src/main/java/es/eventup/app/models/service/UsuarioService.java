package es.eventup.app.models.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;

import es.eventup.app.models.entity.User;

public interface UsuarioService{
	public List<User> findAll();
	
	public void save(User entity);
	
	public Optional<User> findOne(Long id);
	
	public void delete(Long id);
	
}
