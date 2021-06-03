package es.eventup.app.models.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import es.eventup.app.models.entity.Transporte;
import es.eventup.app.models.projections.TransporteProjection;

@Repository
public interface TransporteRepository<T,PK extends Serializable> extends CrudRepository<Transporte, Long> {
	public List<TransporteProjection> findByEvento_Id(Long id);
	
	public Optional<TransporteProjection> Id(Long id);
	
}
