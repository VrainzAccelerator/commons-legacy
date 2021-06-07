package com.vrains.persistence.oldmodel.mcp;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "MCP_servicios")
public class Servicio {

	private Long									idServicio;
	private Estado									estado;
	private String									nombre;
	private String									alias;
	private TipoServicio							tipoServicio;
	private Set<Palabra>							palabras									= new HashSet<Palabra>(0);
	private Set<ServicioNumerocortoClienteOperador>	serviciosNumeroscortosClientesOperadores	= new HashSet<ServicioNumerocortoClienteOperador>(0);

	public Servicio() {
	}

	public Servicio(Estado estado, String nombre) {
		this.estado = estado;
		this.nombre = nombre;
	}

	public Servicio(Estado estado, String nombre, String alias, Set<Palabra> palabras,
			Set<ServicioNumerocortoClienteOperador> serviciosNumeroscortosClientesOperadores) {
		this.estado = estado;
		this.nombre = nombre;
		this.alias = alias;
		this.palabras = palabras;
		this.serviciosNumeroscortosClientesOperadores = serviciosNumeroscortosClientesOperadores;
	}

	@Id
	@Column(name = "id_servicio")
	public Long getIdServicio() {
		return this.idServicio;
	}

	public void setIdServicio(Long idServicio) {
		this.idServicio = idServicio;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_estado")
	public Estado getEstado() {
		return this.estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getAlias() {
		return this.alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "servicio")
	public Set<Palabra> getPalabras() {
		return this.palabras;
	}

	public void setPalabras(Set<Palabra> palabras) {
		this.palabras = palabras;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "servicio")
	public Set<ServicioNumerocortoClienteOperador> getServiciosNumeroscortosClientesOperadores() {
		return this.serviciosNumeroscortosClientesOperadores;
	}

	public void setServiciosNumeroscortosClientesOperadores(Set<ServicioNumerocortoClienteOperador> serviciosNumeroscortosClientesOperadores) {
		this.serviciosNumeroscortosClientesOperadores = serviciosNumeroscortosClientesOperadores;
	}

	public TipoServicio getTipoServicio() {
		return tipoServicio;
	}

	public void setTipoServicio(TipoServicio tipoServicio) {
		this.tipoServicio = tipoServicio;
	}

}
