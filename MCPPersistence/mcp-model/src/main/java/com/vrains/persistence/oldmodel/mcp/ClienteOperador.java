package com.vrains.persistence.oldmodel.mcp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "MCP_clientes_operadores")
public class ClienteOperador {

	private Long		idClienteOperador;
	private Operador	operador;
	private Cliente		cliente;
	private String		host;
	private String		url;
	private Integer		puerto;
	private Integer		esme;
	private Integer		esme2;
	private Integer		puedePublicar;
	private String		configuracion;

	public ClienteOperador() {
	}

	public ClienteOperador(Operador operador, Cliente cliente) {
		this.operador = operador;
		this.cliente = cliente;
	}

	public ClienteOperador(Operador operador, Cliente cliente, String host, String url, Integer puerto, Integer esme, Integer esme2,
			Integer puedePublicar, String configuracion) {
		this.operador = operador;
		this.cliente = cliente;
		this.host = host;
		this.url = url;
		this.puerto = puerto;
		this.esme = esme;
		this.esme2 = esme2;
		this.puedePublicar = puedePublicar;
		this.configuracion = configuracion;
	}

	@Id
	@Column(name = "id_cliente_operador")
	public Long getIdClienteOperador() {
		return this.idClienteOperador;
	}

	public void setIdClienteOperador(Long idClienteOperador) {
		this.idClienteOperador = idClienteOperador;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_operador")
	public Operador getOperador() {
		return this.operador;
	}

	public void setOperador(Operador operador) {
		this.operador = operador;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_cliente")
	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getHost() {
		return this.host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(columnDefinition = "smallint")
	public Integer getPuerto() {
		return this.puerto;
	}

	public void setPuerto(Integer puerto) {
		this.puerto = puerto;
	}

	public Integer getEsme() {
		return this.esme;
	}

	public void setEsme(Integer esme) {
		this.esme = esme;
	}

	public Integer getEsme2() {
		return this.esme2;
	}

	public void setEsme2(Integer esme2) {
		this.esme2 = esme2;
	}

	@Column(name = "puede_publicar")
	public Integer getPuedePublicar() {
		return this.puedePublicar;
	}

	public void setPuedePublicar(Integer puedePublicar) {
		this.puedePublicar = puedePublicar;
	}

	public String getConfiguracion() {
		return this.configuracion;
	}

	public void setConfiguracion(String configuracion) {
		this.configuracion = configuracion;
	}
}
