package es.eventup.app.models.repository;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import es.eventup.app.models.entity.Asistencia;

@Repository
public interface AsistenciaRepository<T, PK extends Serializable> extends CrudRepository<Asistencia, Long>{

}
