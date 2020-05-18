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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="eventos")
public class Evento implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	Long id;
	
	@NotEmpty
	@Column(name="nombre")
	String nombre;
	
	@NotEmpty
	@Column(name="descripcion")
	String descripcion;
	
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
	
	@Column(name="latitud")
	String latitud;
	
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
	@DateTimeFormat(pattern="HH:mm")
	Date hora;
	
	@NotNull
	@Column(name="precio")
	Double precio;
	
	@Column(name="cantidad_entradas")
	Integer cantidadEntradas;
	
	@OneToMany(mappedBy = "evento",  cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	Set<Actividad> actividad;
	
	@OneToMany(mappedBy = "evento",  cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	Set<Venta> venta;
	
	@OneToMany(mappedBy = "evento",  cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	Set<Transporte> transporte;

	public Evento() {

	}
	
	public Evento(@NotEmpty String nombre, @NotEmpty String descripcion, String foto,
			@NotNull @NotEmpty String organizador, @NotEmpty String ciudad, @NotEmpty String direccion, String latitud,
			String longitud, @NotNull Date fecha, @NotNull Date hora, @NotNull Double precio, Integer cantidadEntradas,
			Set<Actividad> actividad, Set<Venta> venta, Set<Transporte> transporte) {
		super();
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
		this.actividad = actividad;
		this.venta = venta;
		this.transporte = transporte;
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


	public Date getHora() {
		return hora;
	}



	public void setHora(Date hora) {
		this.hora = hora;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	public Set<Actividad> getActividad() {
		return actividad;
	}



	public void setActividad(Set<Actividad> actividad) {
		this.actividad = actividad;
	}



	public Set<Venta> getVenta() {
		return venta;
	}



	public void setVenta(Set<Venta> venta) {
		this.venta = venta;
	}



	public Set<Transporte> getTransporte() {
		return transporte;
	}



	public void setTransporte(Set<Transporte> transporte) {
		this.transporte = transporte;
	}
	
	
	

}
