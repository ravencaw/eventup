package es.eventup.app.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="transportes")
public class Transporte implements Serializable{

	
	
	private static final long serialVersionUID =1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String empresa;
	
	
	@NotNull
	private int capacidad;
	
	
	@Column(name="lat_inicio")
	private String latInicio;
	@Column(name="lng_inicio")
	private String lngInicio;
	
	
	@Column(name="lat_fin")
	private String latFin;
	@Column(name="lng_fin")
	private String lngFin;
	
	
	@NotNull
	private float precio;
	
	@NotNull
	private String tipo;
	
	@NotNull
	@Column(name="hora_salida")
	@Temporal(TemporalType.TIME)
	@DateTimeFormat(pattern="HH:mm")
	private Date horaSalida;
	
	@NotNull
	@Column(name="hora_llegada")
	@Temporal(TemporalType.TIME)
	@DateTimeFormat(pattern="HH:mm")
	private Date horaLlegada;
	
//	@OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "id_entrada", referencedColumnName = "id")
//	Entrada entrada;
	
	@ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Evento evento;
	
	
	public Transporte() {
		super();
	}
	

	public Transporte(@NotNull String empresa, @NotNull int capacidad, String latInicio, String lngInicio,
			String latFinal, String lngFinal, @NotNull float precio, @NotNull String tipo, @NotNull Date horaSalida,
			@NotNull Date horaLlegada, Evento evento) {
		super();
		this.empresa = empresa;
		this.capacidad = capacidad;
		this.latInicio = latInicio;
		this.lngInicio = lngInicio;
		this.latFin = latFinal;
		this.lngFin = lngFinal;
		this.precio = precio;
		this.tipo = tipo;
		this.horaSalida = horaSalida;
		this.horaLlegada = horaLlegada;
		this.evento = evento;
	}


	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id=id;
	}
	
	public String getEmpresa() {
		return empresa;
	}
	
	public void setEmpresa(String empresa) {
		this.empresa=empresa;
	}
	
	public int getCapacidad() {
		return capacidad;
	}
	
	public void setCapacidad(int capacidad) {
		this.capacidad=capacidad;
	}
	
	
	public String getLatInicio() {
		return latInicio;
	}


	public void setLatInicio(String latInicio) {
		this.latInicio = latInicio;
	}


	public String getLngInicio() {
		return lngInicio;
	}


	public void setLngInicio(String lngInicio) {
		this.lngInicio = lngInicio;
	}


	public String getLatFin() {
		return latFin;
	}


	public void setLatFin(String latFinal) {
		this.latFin = latFinal;
	}


	public String getLngFin() {
		return lngFin;
	}


	public void setLngFin(String lngFinal) {
		this.lngFin = lngFinal;
	}


	public Evento getEvento() {
		return evento;
	}


	public void setEvento(Evento evento) {
		this.evento = evento;
	}


	public float getPrecio() {
		return precio;
	}
	
	public void setPrecio(float precio) {
		this.precio=precio;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public void setTipo(String tipo) {
		this.tipo=tipo;
	}
	
	public Date getHoraSalida() {
		return horaSalida;
	}
	
	public void setHoraSalida(Date horaSalida) {
		this.horaSalida=horaSalida;
	}
	
	public Date getHoraLlegada() {
		return horaLlegada;
	}
	
	public void setHoraLlegada(Date horaLlegada) {
		this.horaLlegada=horaLlegada;
	}
}
