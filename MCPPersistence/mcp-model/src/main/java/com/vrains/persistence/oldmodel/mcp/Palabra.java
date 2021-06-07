package com.vrains.persistence.oldmodel.mcp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "MCP_palabras")
public class Palabra {

	private Long		idPalabra;
	private Servicio	servicio;
	private Estado		estado;
	private String		palabra;
	private Long		idPalabraPadre;

	public Palabra() {
	}

	public Palabra(Servicio servicio, Estado estado, String palabra) {
		this.servicio = servicio;
		this.estado = estado;
		this.palabra = palabra;
	}

	public Palabra(Servicio servicio, Estado estado, String palabra, Long idPalabraPadre) {
		this.servicio = servicio;
		this.estado = estado;
		this.palabra = palabra;
		this.idPalabraPadre = idPalabraPadre;
	}

	@Id
	@Column(name = "id_palabra")
	public Long getIdPalabra() {
		return this.idPalabra;
	}

	public void setIdPalabra(Long idPalabra) {
		this.idPalabra = idPalabra;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_servicio")
	public Servicio getServicio() {
		return this.servicio;
	}

	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_estado")
	public Estado getEstado() {
		return this.estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public String getPalabra() {
		return this.palabra;
	}

	public void setPalabra(String palabra) {
		this.palabra = palabra;
	}

	@Column(name = "id_palabra_padre")
	public Long getIdPalabraPadre() {
		return this.idPalabraPadre;
	}

	public void setIdPalabraPadre(Long idPalabraPadre) {
		this.idPalabraPadre = idPalabraPadre;
	}

}