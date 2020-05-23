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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="user", uniqueConstraints = @UniqueConstraint(columnNames = {"username", "email"}))
public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	@NotEmpty
	private String username;

	@Column
	@NotEmpty
	private String password;

	@Column
	private boolean enabled;
	
	@NotEmpty
	@Email
	private String email;
	
	@NotEmpty
	private String nombre;
	
	@NotEmpty
	private String apellidos;
	
	@NotNull
	private String provincia;
	@NotNull
	private String localidad;
	
	private String pais;
	
	private String sexo;
	
	@NotNull
	@Column(name="fecha_nac")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Date fechaNac;
	
	@NotNull
	private String dni;
	
//	@OneToMany(mappedBy = "eventos",  cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
//	Evento evento;
	
//	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@OneToMany(mappedBy = "usuario",  cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	Set<Entrada> entrada;
	
	@OneToMany(mappedBy = "usuario",  cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	Set<Evento> evento;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "authorities_users", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "authority_id"))
	private Set<Authority> authority;

	
	public User() {
		super();
	}

	

	public User(@NotEmpty String username, @NotEmpty String password, boolean enabled, @NotEmpty @Email String email,
			@NotEmpty String nombre, @NotEmpty String apellidos, String provincia, String localidad, String pais,
			String sexo, @NotNull Date fechaNac, @NotNull String dni, Set<Entrada> entrada, Set<Evento> evento,
			Set<Authority> authority) {
		super();
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.email = email;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.provincia = provincia;
		this.localidad = localidad;
		this.pais = pais;
		this.sexo = sexo;
		this.fechaNac = fechaNac;
		this.dni = dni;
		this.entrada = entrada;
		this.evento = evento;
		this.authority = authority;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Date getFechaNac() {
		return fechaNac;
	}

	public void setFechaNac(Date fechaNac) {
		this.fechaNac = fechaNac;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public Set<Authority> getAuthority() {
		return authority;
	}

	public void setAuthority(Set<Authority> authority) {
		this.authority = authority;
	}
	
	

	public Set<Entrada> getEntrada() {
		return entrada;
	}

	public void setEntrada(Set<Entrada> entrada) {
		this.entrada = entrada;
	}
	
	public Set<Evento> getEvento() {
		return evento;
	}



	public void setEvento(Set<Evento> evento) {
		this.evento = evento;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + "]";
	}

}
