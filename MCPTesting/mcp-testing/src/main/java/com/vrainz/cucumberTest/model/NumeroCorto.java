package com.vrainz.cucumberTest.model;

import java.io.Serializable;

public class NumeroCorto implements Serializable {

	private static final long serialVersionUID = 2388386216520358456L;
	
	private Integer id;
	private ClienteOperador clienteOperador;
	private Integer mt;
	
	public NumeroCorto() {
		super();
		clienteOperador = new ClienteOperador();
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ClienteOperador getClienteOperador() {
		return clienteOperador;
	}

	public void setClienteOperador(ClienteOperador clienteOperador) {
		this.clienteOperador = clienteOperador;
	}

	public Integer getMt() {
		return mt;
	}

	public void setMt(Integer mt) {
		this.mt = mt;
	}


}
