package es.eventup.app.models.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import es.eventup.app.models.entity.Authority;

@Repository
public interface AuthorityRepository extends CrudRepository<Authority, Long>  {

}
