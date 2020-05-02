package es.eventup.app.models.entity;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.hibernate.annotations.Entity;
import org.hibernate.annotations.Table;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;

import com.sun.istack.NotNull;

@Entity
//@Table(name="actividades") FALTA HACER LA BBDD
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
	
	public Actividad() {
		
	}
	
	public Actividad(Long id, String nombre, String descripcion, String ponentes, int capacidad, String lugar) {
		super();
		this.id=id;
		this.nombre=nombre;
		this.descripcion=descripcion;
		this.ponentes=ponentes;
		this.capacidad=capacidad;
		this.lugar=lugar;
	
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

}
