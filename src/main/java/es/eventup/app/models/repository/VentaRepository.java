package es.eventup.app.models.repository;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import es.eventup.app.models.entity.Venta;

@Repository
public interface VentaRepository<T, PK extends Serializable> extends CrudRepository<Venta, Long>{

}
