package es.eventup.app.models.repository;

import java.io.Serializable;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import es.eventup.app.models.entity.Valoracion;

@Repository
public interface ValoracionRepository<T, PK extends Serializable> extends PagingAndSortingRepository<Valoracion, Long> {

}
