package com.vrains.persistence.oldmodel.mcp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "MCP_servicios_numeroscortos_clientes_operadores")
public class ServicioNumerocortoClienteOperador {

	private Long						idNumerocortoclienteoperadorServicio;
	private NumerocortoClienteOperador	numerocortoClienteOperador;
	private Servicio					servicio;
	private String						ovc1;
	private String						ovc2;
	private String						ovc3;
	private String						ovc4;
	private String						ovc5;
	private Integer						oint1;
	private Integer						oint2;
	private Integer						oint3;

	public ServicioNumerocortoClienteOperador() {
	}

	public ServicioNumerocortoClienteOperador(NumerocortoClienteOperador numerocortoClienteOperador, Servicio servicio) {
		this.numerocortoClienteOperador = numerocortoClienteOperador;
		this.servicio = servicio;
	}

	public ServicioNumerocortoClienteOperador(NumerocortoClienteOperador numerocortoClienteOperador, Servicio servicio, String ovc1, String ovc2,
			String ovc3, String ovc4, String ovc5, Integer oint1, Integer oint2, Integer oint3) {
		this.numerocortoClienteOperador = numerocortoClienteOperador;
		this.servicio = servicio;
		this.ovc1 = ovc1;
		this.ovc2 = ovc2;
		this.ovc3 = ovc3;
		this.ovc4 = ovc4;
		this.ovc5 = ovc5;
		this.oint1 = oint1;
		this.oint2 = oint2;
		this.oint3 = oint3;
	}

	@Id
	@Column(name = "id_numerocortoclienteoperador_servicio", columnDefinition = "BIGINT(20)")
	public Long getIdNumerocortoclienteoperadorServicio() {
		return this.idNumerocortoclienteoperadorServicio;
	}

	public void setIdNumerocortoclienteoperadorServicio(Long idNumerocortoclienteoperadorServicio) {
		this.idNumerocortoclienteoperadorServicio = idNumerocortoclienteoperadorServicio;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_numerocorto_clienteoperador")
	public NumerocortoClienteOperador getNumerocortoClienteOperador() {
		return this.numerocortoClienteOperador;
	}

	public void setNumerocortoClienteOperador(NumerocortoClienteOperador numeroscortosClientesOperadores) {
		this.numerocortoClienteOperador = numeroscortosClientesOperadores;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_servicio")
	public Servicio getServicio() {
		return this.servicio;
	}

	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}

	@Column(name = "ovc_1")
	public String getOvc1() {
		return this.ovc1;
	}

	public void setOvc1(String ovc1) {
		this.ovc1 = ovc1;
	}

	@Column(name = "ovc_2")
	public String getOvc2() {
		return this.ovc2;
	}

	public void setOvc2(String ovc2) {
		this.ovc2 = ovc2;
	}

	@Column(name = "ovc_3")
	public String getOvc3() {
		return this.ovc3;
	}

	public void setOvc3(String ovc3) {
		this.ovc3 = ovc3;
	}

	@Column(name = "ovc_4")
	public String getOvc4() {
		return this.ovc4;
	}

	public void setOvc4(String ovc4) {
		this.ovc4 = ovc4;
	}

	@Column(name = "ovc_5")
	public String getOvc5() {
		return this.ovc5;
	}

	public void setOvc5(String ovc5) {
		this.ovc5 = ovc5;
	}

	@Column(name = "oint_1")
	public Integer getOint1() {
		return this.oint1;
	}

	public void setOint1(Integer oint1) {
		this.oint1 = oint1;
	}

	@Column(name = "oint_2")
	public Integer getOint2() {
		return this.oint2;
	}

	public void setOint2(Integer oint2) {
		this.oint2 = oint2;
	}

	@Column(name = "oint_3")
	public Integer getOint3() {
		return this.oint3;
	}

	public void setOint3(Integer oint3) {
		this.oint3 = oint3;
	}

}
