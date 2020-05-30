package es.eventup.app.models.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import es.eventup.app.models.entity.Actividad;
import es.eventup.app.models.projections.ActividadProjection;

@Repository
public interface ActividadRepository<T, PK extends Serializable> extends CrudRepository<Actividad, Long>{
	public List<ActividadProjection> findByEvento_Id(Long id);
}
