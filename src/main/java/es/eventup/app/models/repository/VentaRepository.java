package es.eventup.app.models.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import es.eventup.app.models.entity.Venta;
import es.eventup.app.models.projections.VentaProjection;

@Repository
public interface VentaRepository<T, PK extends Serializable> extends CrudRepository<Venta, Long>{
	List<VentaProjection> findByEvento_Id(Long id);
}
