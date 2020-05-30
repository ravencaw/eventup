package es.eventup.app.models.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import es.eventup.app.models.entity.Asistencia;
import es.eventup.app.models.projections.AsistenciaProjection_Lista;

@Repository
public interface AsistenciaRepository<T, PK extends Serializable> extends CrudRepository<Asistencia, Long>{
	public List<AsistenciaProjection_Lista> findByEntrada_Venta_Evento_Id(Long id_evento);
}
