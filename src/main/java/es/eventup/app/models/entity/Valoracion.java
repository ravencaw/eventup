package es.eventup.app.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "valoraciones")
public class Valoracion implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String comentario;
	
	private Integer like;
	
	@Column(name="id_blog")
	private Long idBlog;
	
	
	@Column(name="id_usuario")
	private Long idUsuario;
	
	public Valoracion() {
		
	}
	
	public Valoracion(Long id, String comentario, Integer like, Long idBlog, Long idUsuario ) {
		this.id=id;
		this.comentario=comentario;
		this.like=like;
		this.idBlog=idBlog;
		this.idUsuario=idUsuario;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id=id;
	}
	
	public String getComentario() {
		return comentario;
	}
	
	public void setComentario(String comentario) {
		this.comentario=comentario;
	}
	
	public Integer getLike() {
		return like;
	}
	
	public void setLike(Integer like) {
		this.like=like;
	}
	
	public Long getIdBlog() {
		return idBlog;
	}
	
	public void setIdBlog(Long idBlog) {
		this.idBlog=idBlog;
	}
	
	public Long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario=idUsuario;
	}
}
