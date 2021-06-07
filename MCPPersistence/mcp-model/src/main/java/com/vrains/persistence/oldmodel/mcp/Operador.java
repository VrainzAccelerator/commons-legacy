package com.vrains.persistence.oldmodel.mcp;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "MCP_operadores")
public class Operador {

	private Long					idOperador;
	private Estado					estado;
	private String					identificador;
	private String					nombre;
	private Set<ClienteOperador>	clientesOperadores	= new HashSet<ClienteOperador>(0);

	public Operador() {
	}

	public Operador(Estado estado, String identificador, String nombre) {
		this.estado = estado;
		this.identificador = identificador;
		this.nombre = nombre;
	}

	public Operador(Estado estado, String identificador, String nombre, Set<ClienteOperador> clientesOperadores) {
		this.estado = estado;
		this.identificador = identificador;
		this.nombre = nombre;
		this.clientesOperadores = clientesOperadores;
	}

	@Id
	@Column(name = "id_operador")
	public Long getIdOperador() {
		return this.idOperador;
	}

	public void setIdOperador(Long idOperador) {
		this.idOperador = idOperador;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_estado")
	public Estado getEstado() {
		return this.estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public String getIdentificador() {
		return this.identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "operador")
	public Set<ClienteOperador> getClientesOperadores() {
		return this.clientesOperadores;
	}

	public void setClientesOperadores(Set<ClienteOperador> clientesOperadores) {
		this.clientesOperadores = clientesOperadores;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof Operador))
			return false;

		Operador other = (Operador) obj;
		return (this.idOperador == other.idOperador)
				&& ((this.identificador == null && other.identificador == null) || (this.identificador.equals(other.identificador)))
				&& ((this.nombre == null && other.nombre == null) || (this.nombre.equals(other.nombre)));
	}

}
