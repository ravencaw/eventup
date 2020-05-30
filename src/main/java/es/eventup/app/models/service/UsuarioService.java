package es.eventup.app.models.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;

import es.eventup.app.models.entity.Evento;
import es.eventup.app.models.entity.User;
import es.eventup.app.models.projections.UserProjection;

public interface UsuarioService{
   List<User> findAll();
	
	void save(User entity);
	
	Optional<User> findOne(Long id);
	
	void delete(Long id);
	
	List<UserProjection> porProvincias(String provincia);

	Optional<User> findByUsername(String username); 
	
}
