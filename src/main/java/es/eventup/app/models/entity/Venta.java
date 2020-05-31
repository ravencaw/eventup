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
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="ventas")
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
	Double total;
	
	@NotNull
	@ManyToOne(optional = false, cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	Evento evento;
	
//	@OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "id_entrada", referencedColumnName = "id")
//	Entrada entrada;

	public Venta() {

	}

	public Venta(@NotNull Date fecha, @NotNull Date hora, @NotNull Double total, @NotNull Evento evento) {
		super();
		this.fecha = fecha;
		this.hora = hora;
		this.total = total;
		this.evento = evento;
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

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

}
