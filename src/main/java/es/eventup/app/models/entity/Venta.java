package es.eventup.app.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="venta")
public class Venta implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Column(name="fecha")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="dd/MM/yyyy")
	Date fecha;
	
	@NotNull
	@Column(name="hora")
	@Temporal(TemporalType.TIME)
	@DateTimeFormat(pattern="HH:mm:ss")
	Date hora;
	
	@NotNull
	@Column(name="cantidad_entradas")
	Integer cantidadEntradas;
	
	@NotNull
	Double total;
	
	@NotNull
	@Column(name="id_evento")
	Long idEvento;

	public Venta(Long id, @NotNull Date fecha, @NotNull Date hora, @NotNull Integer cantidadEntradas,
			@NotNull Double total, @NotNull Long idEvento) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.hora = hora;
		this.cantidadEntradas = cantidadEntradas;
		this.total = total;
		this.idEvento = idEvento;
	}

	public Venta() {

	}
	
	@PrePersist
	public void prePersist() {
		Date fecha = new Date();
		Date hora = new Date();
		setFecha(fecha);
		setHora(hora);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Date getHora() {
		return hora;
	}

	public void setHora(Date hora) {
		this.hora = hora;
	}

	public Long getIdEvento() {
		return idEvento;
	}

	public void setIdEvento(Long idEvento) {
		this.idEvento = idEvento;
	}

	public Integer getCantidadEntradas() {
		return cantidadEntradas;
	}

	public void setCantidadEntradas(Integer cantidadEntradas) {
		this.cantidadEntradas = cantidadEntradas;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	
	
	
	
}
