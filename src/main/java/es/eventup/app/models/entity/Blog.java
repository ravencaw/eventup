package es.eventup.app.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;

import com.sun.istack.NotNull;

@Entity
@Table(name = "blog")
public class Blog implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty
	@Column(name="fotos")
	private String foto;

	@NotEmpty
	@Column(name="videos")
	private String video;

	@Column(name="id_evento")
	Long idEvento;

	public Blog() {
		super();
	}

	public Blog(Long id, @NotEmpty String foto, @NotEmpty String video, @NotEmpty Long idEvento) {
		super();
		this.id = id;
		this.foto = foto;
		this.video = video;
		this.idEvento = idEvento;

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
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
