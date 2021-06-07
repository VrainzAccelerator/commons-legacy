package com.vrains.persistence.oldmodel.mcp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MCP_estados")
public class Estado {

	private Byte	idEstado;
	private String	nombre;
	private boolean	activo;

	public Estado() {
	}

	public Estado(String nombre, boolean activo) {
		this.nombre = nombre;
		this.activo = activo;
	}

	@Id
	@Column(name = "id_estado")
	public Byte getIdEstado() {
		return this.idEstado;
	}

	public void setIdEstado(Byte idEstado) {
		this.idEstado = idEstado;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public boolean isActivo() {
		return this.activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}
}
