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
@Table(name ="actividades")
public class Actividad implements Serializable{
	
	private static final long serialVersionUID =1L;
	
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
	private int capacidad;
	
	@NotEmpty
	private String lugar;
	
	@Column(name="id_evento")
	private Long idEvento;
	
	public Actividad() {
		super();
	}
	
	public Actividad(Long id, @NotEmpty String nombre, @NotEmpty String descripcion, @NotEmpty String ponentes, @NotNull int capacidad, @NotEmpty String lugar, Long idEvento) {
		super();
		this.id=id;
		this.nombre=nombre;
		this.descripcion=descripcion;
		this.ponentes=ponentes;
		this.capacidad=capacidad;
		this.lugar=lugar;
		this.idEvento = idEvento;
	
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id=id;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre=nombre;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion() {
		this.descripcion=descripcion;
	}
	
	public String getPonentes() {
		return ponentes;
	}
	
	public void setPonentes() {
		this.ponentes=ponentes;
	}
	
	public int getCapacidad() {
		return capacidad;
	}
	
	public void setCapacidad() {
		this.capacidad=capacidad;
	}
	
	public String getLugar() {
		return lugar;
	}
	
	public void setLugar() {
		this.lugar=lugar;
	}
	
	public Long getIdEvento() {
		return idEvento;
	}
	public void setIdEvento(Long idEvento) {
		this.idEvento=idEvento;
	}

}
