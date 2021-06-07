package com.vrainz.cucumberTest.model;

import java.io.Serializable;

public class ClienteOperador implements Serializable {

	private static final long serialVersionUID = 2388386216520358456L;
	
	private Integer id;
	private String nombre;
	
	public ClienteOperador() {
		super();
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
