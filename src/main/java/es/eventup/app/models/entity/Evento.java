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

import org.apache.tomcat.jni.Time;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="evento")
public class Evento implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	Integer id;
	
	@NotEmpty
	@Column(name="nombre")
	String nombre;
	
	@NotEmpty
	@Column(name="descripcion")
	String descripcion;
	
	@NotEmpty
	@Column(name="foto")
	String foto;
	
	@NotNull
	@NotEmpty
	@Column(name="organizador")
	String organizador;
	
	@NotEmpty
	@Column(name="ciudad")
	String ciudad;
	
	@NotEmpty
	@Column(name="direccion")
	String direccion;
	
	@NotEmpty
	@Column(name="latitud")
	String latitud;
	
	@NotEmpty
	@Column(name="longitud")
	String longitud;
	
	@NotNull
	@Column(name="fecha")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="dd/MM/yyyy")
	Date fecha;
	
	@NotNull
	@Column(name="hora")
	@Temporal(TemporalType.TIME)
	//REVISAR EL FORMATO SI HA QUE ESPECIFICAR CON @DateTimeFormat 
	Time hora;
	
	@NotEmpty
	@Column(name="precio")
	Double precio;
	
	@NotEmpty
	@Column(name="cantidad_entradas")
	Integer cantidadEntradas;

	public Evento() {

	}
	
	

	public Evento(Integer id, @NotEmpty String nombre, @NotEmpty String descripcion, String foto, String organizador,
			String ciudad,String direccion,String latitud,String longitud,
			@NotNull Date fecha, @NotNull Time hora, Double precio, @NotEmpty Integer cantidadEntradas) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.foto = foto;
		this.organizador = organizador;
		this.ciudad = ciudad;
		this.direccion = direccion;
		this.latitud = latitud;
		this.longitud = longitud;
		this.fecha = fecha;
		this.hora = hora;
		this.precio = precio;
		this.cantidadEntradas = cantidadEntradas;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getOrganizador() {
		return organizador;
	}

	public void setOrganizador(String organizador) {
		this.organizador = organizador;
	}

	public String getCiudad() {
		return ciudad;
	}


	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}



	public String getDireccion() {
		return direccion;
	}



	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}



	public String getLatitud() {
		return latitud;
	}



	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}



	public String getLongitud() {
		return longitud;
	}



	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}


	
	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public Integer getCantidadEntradas() {
		return cantidadEntradas;
	}

	public void setCantidadEntradas(Integer cantidadEntradas) {
		this.cantidadEntradas = cantidadEntradas;
	}


	public Time getHora() {
		return hora;
	}



	public void setHora(Time hora) {
		this.hora = hora;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
