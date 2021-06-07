package com.vrains.persistence.oldmodel.mcp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "MCP_clientes")
public class Cliente {

	private Long					idCliente;
	private Estado					estado;
	private String					nombre;
	private String					identificador;
	private boolean					usarDefault;

	public Cliente() {
	}

	public Cliente(Estado estado, String nombre, String identificador, boolean usarDefault) {
		this.estado = estado;
		this.nombre = nombre;
		this.identificador = identificador;
		this.usarDefault = usarDefault;
	}

	@Id
	@Column(name = "id_cliente")
	public Long getIdCliente() {
		return this.idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
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

	public String getIdentificador() {
		return this.identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	@Column(name = "usar_default")
	public boolean isUsarDefault() {
		return this.usarDefault;
	}

	public void setUsarDefault(boolean usarDefault) {
		this.usarDefault = usarDefault;
	}

}
