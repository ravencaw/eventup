package es.eventup.app.models.repository;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import es.eventup.app.models.entity.Mensaje;

@Repository
public interface MensajeRepository<T, PK extends Serializable> extends CrudRepository<Mensaje, Long>{

}
