package es.eventup.app.models.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import es.eventup.app.models.entity.User;
import es.eventup.app.models.projections.EventoProjection;

@Repository
public interface UserRepository extends CrudRepository<User, Long>  {
    public Optional<User> findByUsername(String username);
    
    <S> List<S> findAllByProvincia(String provincia, Class<S> classType);
    
}
