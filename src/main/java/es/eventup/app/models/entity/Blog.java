package es.eventup.app.models.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="blog")
public class Blog implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@NotNull
	@Column(name="fotos")
	private String fotos;
	
	@NotNull
	@Column(name="videos")
	private String videos;
	
	@OneToMany(mappedBy = "blog",  cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	private Set<Valoracion> valoracion;
	
	@NotNull
	@ManyToOne(optional = false, cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	private Evento evento;

	public Blog() {

	}
	
	

	public Blog(Long id, @NotNull String fotos, @NotNull String videos, Evento evento) {
		super();
		this.id = id;
		this.fotos = fotos;
		this.videos = videos;
		this.evento = evento;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public String getFotos() {
		return fotos;
	}



	public void setFotos(String fotos) {
		this.fotos = fotos;
	}



	public String getVideos() {
		return videos;
	}



	public void setVideos(String videos) {
		this.videos = videos;
	}

	public Set<Valoracion> getValoracion() {
		return valoracion;
	}
	
	//DUDOSO
	public void setValoracion(Set<Valoracion> valoracion) {
		this.valoracion = valoracion;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
