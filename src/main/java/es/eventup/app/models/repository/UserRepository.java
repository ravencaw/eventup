package es.eventup.app.models.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import es.eventup.app.models.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>  {
    public Optional<User> findByUsername(String username);
}
