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

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="transporte")
public class Transporte implements Serializable{

	
	
	private static final long serialVersionUID =1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	private String empresa;
	
	
	@NotNull
	private int capacidad;
	
	
	@NotEmpty
	@Column(name="coor_inicio")
	private String coordenadaInicio;
	
	
	@NotEmpty
	@Column(name="coor_final")
	private String coordenadaFinal;
	
	
	@NotNull
	private float precio;
	
	@NotEmpty
	private String tipo;
	
	@NotNull
	@Column(name="hora_salida")
	@Temporal(TemporalType.TIME)
	private Date horaSalida;
	
	@NotNull
	@Column(name="hora_llegada")
	@Temporal(TemporalType.TIME)
	private Date horaLlegada;
	
	
	public Transporte() {
		super();
	}
	
	public Transporte(Long id, @NotEmpty String empresa, @NotNull int capacidad,@NotEmpty String coordenadaInicio, @NotEmpty String coordenadaFinal,
			@NotNull float precio, @NotNull String tipo,@NotNull Date horaSalida, @NotNull Date horaLLegada) {
		
		super();
		this.id=id;
		this.empresa=empresa;
		this.capacidad=capacidad;
		this.coordenadaInicio=coordenadaInicio;
		this.coordenadaFinal=coordenadaFinal;
		this.precio=precio;
		this.tipo=tipo;
		this.horaSalida=horaSalida;
		this.horaLlegada=horaLLegada;
		
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
	
	public String getCoordenadaInicio() {
		return coordenadaInicio;
	}
	
	public void setCoordenadaInicio(String coordenadaInicio) {
		this.coordenadaInicio=coordenadaInicio;
	}
	
	public String getCoordenadaFinal() {
		return coordenadaFinal;
	}
	
	public void setCoordenadaFinal(String coordenadaFinal) {
		this.coordenadaFinal=coordenadaFinal;
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
