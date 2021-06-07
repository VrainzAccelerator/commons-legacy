package com.vrainz.cucumberTest.model;

import java.io.Serializable;

public class Servicio implements Serializable {

	private static final long serialVersionUID = 2388386216520358456L;
	
	private Integer id;
	private String servicio;
	private NumeroCorto mt;
	
	public Servicio() {
		super();
		mt = new NumeroCorto();
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getServicio() {
		return servicio;
	}

	public void setServicio(String servicio) {
		this.servicio = servicio;
	}

	public NumeroCorto getMt() {
		return mt;
	}

	public void setMt(NumeroCorto mt) {
		this.mt = mt;
	}


}
