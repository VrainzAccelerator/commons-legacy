package com.vrainz.cucumberTest.model;

import java.io.Serializable;

public class Suscripto implements Serializable {

	private static final long serialVersionUID = 2388386216520358456L;

	private Integer id;
	private Palabra palabra;
	private Integer ani;
	private Integer estado;
	private Integer cobros;

	public Suscripto() {
		super();
		palabra = new Palabra();
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Palabra getPalabra() {
		return palabra;
	}

	public void setPalabra(Palabra palabra) {
		this.palabra = palabra;
	}

	public Integer getAni() {
		return ani;
	}

	public void setAni(Integer ani) {
		this.ani = ani;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public Integer getCobros() {
		return cobros;
	}

	public void setCobros(Integer cobros) {
		this.cobros = cobros;
	}

	


}
