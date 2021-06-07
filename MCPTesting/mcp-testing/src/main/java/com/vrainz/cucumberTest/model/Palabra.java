package com.vrainz.cucumberTest.model;

import java.io.Serializable;

public class Palabra implements Serializable {

	private static final long serialVersionUID = 2388386216520358456L;
	
	private Integer id;
	private Servicio servicio;
	private String palabra;
	
	public Palabra() {
		super();
		servicio = new Servicio();
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Servicio getServicio() {
		return servicio;
	}

	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}

	public String getPalabra() {
		return palabra;
	}

	public void setPalabra(String palabra) {
		this.palabra = palabra;
	}


}
