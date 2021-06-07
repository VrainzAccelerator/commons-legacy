package com.vrains.persistence.oldmodel.mcp;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "MCP_suscripciones")
public class Suscripcion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, name = "id_suscripcion", columnDefinition = "BIGINT(20)")
	Integer	id_suscripcion;

	@Column(nullable = false, name = "ani")
	String	ani;

	@Column(name = "canal_alta")
	String	canal_alta;

	@Column(name = "canal_baja")
	String	canal_baja;

	@Column(nullable = false, name = "cantidad_publicaciones", columnDefinition = "INT(10)")
	Integer	cantidad_publicaciones;

	@Column(nullable = false, name = "cobros_no_exitosos", columnDefinition = "INT(10)")
	Integer	cobros_no_exitosos;

	@Column(name = "esme_id", columnDefinition = "INT(11)")
	Integer	esme_id;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_estado")
	Estado	estado;

	@Column(name = "fecha_alta", columnDefinition = "DATETIME")
	Date	fecha_alta;

	@Column(name = "fecha_baja", columnDefinition = "DATETIME")
	Date	fecha_baja;

	@Column(name = "fecha_creacion", columnDefinition = "DATETIME")
	Date	fecha_creacion;

	@Column(nullable = false, name = "fecha_ultima_publicacion", columnDefinition = "DATETIME")
	Date	fecha_ultima_publicacion;

	@Column(nullable = false, name = "fecha_ultimo_cobro", columnDefinition = "DATETIME")
	Date	fecha_ultimo_cobro;

	@Column(nullable = false, name = "id_cliente_operador", columnDefinition = "BIGINT(20)")
	Integer	id_cliente_operador;

	@Column(name = "trans_id")
	String	trans_id;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_palabra")
	Palabra	palabra;

	public Integer getId_suscripcion() {
		return id_suscripcion;
	}

	public void setId_suscripcion(Integer id_suscripcion) {
		this.id_suscripcion = id_suscripcion;
	}

	public String getAni() {
		return ani;
	}

	public void setAni(String ani) {
		this.ani = ani;
	}

	public String getCanal_alta() {
		return canal_alta;
	}

	public void setCanal_alta(String canal_alta) {
		this.canal_alta = canal_alta;
	}

	public String getCanal_baja() {
		return canal_baja;
	}

	public void setCanal_baja(String canal_baja) {
		this.canal_baja = canal_baja;
	}

	public Integer getCantidad_publicaciones() {
		return cantidad_publicaciones;
	}

	public void setCantidad_publicaciones(Integer cantidad_publicaciones) {
		this.cantidad_publicaciones = cantidad_publicaciones;
	}

	public Integer getCobros_no_exitosos() {
		return cobros_no_exitosos;
	}

	public void setCobros_no_exitosos(Integer cobros_no_exitosos) {
		this.cobros_no_exitosos = cobros_no_exitosos;
	}

	public Integer getEsme_id() {
		return esme_id;
	}

	public void setEsme_id(Integer esme_id) {
		this.esme_id = esme_id;
	}

	public Date getFecha_alta() {
		return fecha_alta;
	}

	public void setFecha_alta(Date fecha_alta) {
		this.fecha_alta = fecha_alta;
	}

	public Date getFecha_baja() {
		return fecha_baja;
	}

	public void setFecha_baja(Date fecha_baja) {
		this.fecha_baja = fecha_baja;
	}

	public Date getFecha_creacion() {
		return fecha_creacion;
	}

	public void setFecha_creacion(Date fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}

	public Date getFecha_ultima_publicacion() {
		return fecha_ultima_publicacion;
	}

	public void setFecha_ultima_publicacion(Date fecha_ultima_publicacion) {
		this.fecha_ultima_publicacion = fecha_ultima_publicacion;
	}

	public Date getFecha_ultimo_cobro() {
		return fecha_ultimo_cobro;
	}

	public void setFecha_ultimo_cobro(Date fecha_ultimo_cobro) {
		this.fecha_ultimo_cobro = fecha_ultimo_cobro;
	}

	public Integer getId_cliente_operador() {
		return id_cliente_operador;
	}

	public void setId_cliente_operador(Integer id_cliente_operador) {
		this.id_cliente_operador = id_cliente_operador;
	}

	public String getTrans_id() {
		return trans_id;
	}

	public void setTrans_id(String trans_id) {
		this.trans_id = trans_id;
	}

}
