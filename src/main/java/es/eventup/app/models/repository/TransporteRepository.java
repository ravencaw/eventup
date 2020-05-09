package es.eventup.app.models.repository;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import es.eventup.app.models.entity.Transporte;

@Repository
public interface TransporteRepository<T,PK extends Serializable> extends CrudRepository<Transporte, Long> {

}
