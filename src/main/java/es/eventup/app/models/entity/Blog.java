package es.eventup.app.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	Long id;
	
	@NotEmpty
	@Column(name="fotos")
	String fotos;
	
	@NotEmpty
	@Column(name="videos")
	String videos;
	
	@Column(name="id_evento")
	Long idEvento;
	

	public Blog() {

	}
	
	

	public Blog(Long id, @NotEmpty String fotos, @NotEmpty String videos, Long idEvento) {
		super();
		this.id = id;
		this.fotos = fotos;
		this.videos = videos;
		this.idEvento = idEvento;
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


	public Long getIdEvento() {
		return idEvento;
	}



	public void setIdEvento(Long idEvento) {
		this.idEvento = idEvento;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
