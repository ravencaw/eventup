package es.eventup.app.models.entity;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "valoracion")
public class Valoracion implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String comentario;
	
	private Integer opinion;
	
	@NotNull
	@ManyToOne(optional = false, cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	private Blog blog;
	
	@NotNull
	@ManyToOne(optional = false, cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	private User user;
	
	public Valoracion() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Valoracion(Long id, String comentario, Integer opinion, Blog blog, User user) {
		super();
		this.id = id;
		this.comentario = comentario;
		this.opinion = opinion;
		this.user = user;
		this.blog = blog;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getComentario() {
		return comentario;
	}


	public void setComentario(String comentario) {
		this.comentario = comentario;
	}


	public Integer getOpinion() {
		return opinion;
	}


	public void setOpinion(Integer opinion) {
		this.opinion = opinion;
	}

	

	public Blog getBlog() {
		return blog;
	}


	public void setBlog(Blog blog) {
		this.blog = blog;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
