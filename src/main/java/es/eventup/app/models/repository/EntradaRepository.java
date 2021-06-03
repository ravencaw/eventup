package es.eventup.app.models.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import es.eventup.app.models.entity.Entrada;
import es.eventup.app.models.projections.EntradaProjection;
import es.eventup.app.models.projections.EntradaProjection_Lista;

@Repository
public interface EntradaRepository<T, PK extends Serializable> extends CrudRepository<Entrada, Long>{

	public Optional<EntradaProjection> findByUsuario_DniAndVenta_Evento_Id(String dni, Long id);
	
	public List<Entrada> findByUsuario_Id(Long id);
	
	public List<EntradaProjection_Lista> findByVenta_Id(Long id);
	
	public List<Entrada> findByTransporte_Id(Long id);
	
}
