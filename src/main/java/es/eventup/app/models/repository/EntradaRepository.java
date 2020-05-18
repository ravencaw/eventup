package es.eventup.app.models.repository;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import es.eventup.app.models.entity.Entrada;
import es.eventup.app.models.projections.EntradaProjection;

@Repository
public interface EntradaRepository<T, PK extends Serializable> extends CrudRepository<Entrada, Long>{

	Optional<EntradaProjection> findByUsuario_DniAndVenta_Evento_Id(String dni, Long id);
}
