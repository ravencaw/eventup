package es.eventup.app.models.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import com.sun.istack.NotNull;

@Entity
@Table(name = "actividades")
public class Actividad implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty
	private String nombre;

	@NotEmpty
	private String descripcion;

	@NotEmpty
	private String ponentes;

	@NotNull
	private Integer capacidad;

	@NotEmpty
	private String lugar;
	
	@ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	Evento evento;

	public Actividad() {
		super();
	}

	public Actividad(Long id, @NotEmpty String nombre, @NotEmpty String descripcion, @NotEmpty String ponentes,
			@NotNull Integer capacidad, @NotEmpty String lugar, Evento evento) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.ponentes = ponentes;
		this.capacidad = capacidad;
		this.lugar = lugar;
		this.evento = evento;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getPonentes() {
		return ponentes;
	}

	public void setPonentes(String ponentes) {
		this.ponentes = ponentes;
	}

	public Integer getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(Integer capacidad) {
		this.capacidad = capacidad;
	}

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}
}
