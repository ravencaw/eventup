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

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import com.sun.istack.NotNull;

@Entity
@Table(name = "mensaje")
public class Mensaje implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty
	@Column(name="hora")
	@Temporal(TemporalType.TIME)
	@DateTimeFormat(pattern="HH:mm:ss")
	private Date hora;

	@NotEmpty
	@Column(name="fecha")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Date fecha;

	@NotEmpty
	@Column(name="contenido")
	private String contenido;
	
	@NotEmpty
	@Column(name="tipo")
	private String tipo;

	@NotEmpty
	@Column(name="estado")
	private String estado;
	
	@Column(name="id_evento")
	Long idEvento;
	
	@Column(name="id_usuario")
	Long idUsuario;

	public Mensaje() {
		super();
	}


	public Mensaje(Long id, Date hora, Date fecha, @NotEmpty String contenido, @NotEmpty String tipo,
			@NotEmpty String estado) {
		super();
		this.id = id;
		this.hora = hora;
		this.fecha = fecha;
		this.contenido = contenido;
		this.tipo = tipo;
		this.estado = estado;
	}

	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public Date getHora() {
		return hora;
	}



	public void setHora(Date hora) {
		this.hora = hora;
	}



	public Date getFecha() {
		return fecha;
	}



	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}



	public String getContenido() {
		return contenido;
	}



	public void setContenido(String contenido) {
		this.contenido = contenido;
	}



	public String getTipo() {
		return tipo;
	}



	public void setTipo(String tipo) {
		this.tipo = tipo;
	}



	public String getEstado() {
		return estado;
	}



	public void setEstado(String estado) {
		this.estado = estado;
	}



	public Long getIdEvento() {
		return idEvento;
	}



	public void setIdEvento(Long idEvento) {
		this.idEvento = idEvento;
	}



	public Long getIdUsuario() {
		return idUsuario;
	}



	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
