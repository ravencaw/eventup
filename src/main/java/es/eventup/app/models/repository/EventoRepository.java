package es.eventup.app.models.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import es.eventup.app.models.entity.Evento;
import es.eventup.app.models.projections.EventoProjection;



@Repository
public interface EventoRepository<T, PK extends Serializable>  extends JpaRepository<Evento, Long>{
	List<EventoProjection> findAllProjectedBy();
}
