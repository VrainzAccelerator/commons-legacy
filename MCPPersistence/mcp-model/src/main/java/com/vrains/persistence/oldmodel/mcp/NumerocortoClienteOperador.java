package com.vrains.persistence.oldmodel.mcp;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "MCP_numeroscortos_clientes_operadores")
public class NumerocortoClienteOperador {

	private Long									idNumerocortoClienteOperador;
	private ClienteOperador							clienteOperador;
	private NumeroCorto								numeroCorto;
	private Set<ServicioNumerocortoClienteOperador>	serviciosNumeroscortosClientesOperadores	= new HashSet<ServicioNumerocortoClienteOperador>(0);

	public NumerocortoClienteOperador() {
	}

	public NumerocortoClienteOperador(ClienteOperador clienteOperador, NumeroCorto numeroCorto) {
		this.clienteOperador = clienteOperador;
		this.numeroCorto = numeroCorto;
	}

	public NumerocortoClienteOperador(ClienteOperador clienteOperador, NumeroCorto numeroCorto,
			Set<ServicioNumerocortoClienteOperador> serviciosNumeroscortosClientesOperadores) {
		this.clienteOperador = clienteOperador;
		this.numeroCorto = numeroCorto;
		this.serviciosNumeroscortosClientesOperadores = serviciosNumeroscortosClientesOperadores;
	}

	@Id
	@Column(name = "id_numerocorto_cliente_operador")
	public Long getIdNumerocortoClienteOperador() {
		return this.idNumerocortoClienteOperador;
	}

	public void setIdNumerocortoClienteOperador(Long idNumerocortoClienteOperador) {
		this.idNumerocortoClienteOperador = idNumerocortoClienteOperador;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_cliente_operador")
	public ClienteOperador getClienteOperador() {
		return this.clienteOperador;
	}

	public void setClienteOperador(ClienteOperador clienteOperador) {
		this.clienteOperador = clienteOperador;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_numero_corto")
	public NumeroCorto getNumeroCorto() {
		return this.numeroCorto;
	}

	public void setNumeroCorto(NumeroCorto numeroCorto) {
		this.numeroCorto = numeroCorto;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "numerocortoClienteOperador")
	public Set<ServicioNumerocortoClienteOperador> getServiciosNumeroscortosClientesOperadores() {
		return this.serviciosNumeroscortosClientesOperadores;
	}

	public void setServiciosNumeroscortosClientesOperadores(Set<ServicioNumerocortoClienteOperador> serviciosNumeroscortosClientesOperadores) {
		this.serviciosNumeroscortosClientesOperadores = serviciosNumeroscortosClientesOperadores;
	}

}