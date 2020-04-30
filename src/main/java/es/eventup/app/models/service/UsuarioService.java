package es.eventup.app.models.service;

import java.util.List;
import java.util.Optional;

import es.eventup.app.models.entity.Usuario;

public interface UsuarioService{
	public List<Usuario> findAll();
	
	public void save(Usuario entity);
	
	public Optional<Usuario> findOne(Long id);
	
	public void delete(Long id);
}
