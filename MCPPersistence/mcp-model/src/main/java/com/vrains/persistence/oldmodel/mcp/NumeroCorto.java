package com.vrains.persistence.oldmodel.mcp;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "MCP_numeros_cortos")
public class NumeroCorto {

	private Long							idNumeroCorto;
	private Estado							estado;
	private String							mt;
	private Set<NumerocortoClienteOperador>	numeroscortosClientesOperadores	= new HashSet<NumerocortoClienteOperador>(0);

	public NumeroCorto() {
	}

	public NumeroCorto(Estado estado, String mt) {
		this.estado = estado;
		this.mt = mt;
	}

	public NumeroCorto(Estado estado, String mt, Set<NumerocortoClienteOperador> numeroscortosClientesOperadores) {
		this.estado = estado;
		this.mt = mt;
		this.numeroscortosClientesOperadores = numeroscortosClientesOperadores;
	}

	@Id
	@Column(name = "id_numero_corto")
	public Long getIdNumeroCorto() {
		return this.idNumeroCorto;
	}

	public void setIdNumeroCorto(Long idNumeroCorto) {
		this.idNumeroCorto = idNumeroCorto;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_estado")
	public Estado getEstado() {
		return this.estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public String getMt() {
		return this.mt;
	}

	public void setMt(String mt) {
		this.mt = mt;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "numeroCorto")
	public Set<NumerocortoClienteOperador> getNumeroscortosClientesOperadores() {
		return this.numeroscortosClientesOperadores;
	}

	public void setNumeroscortosClientesOperadores(Set<NumerocortoClienteOperador> numeroscortosClientesOperadores) {
		this.numeroscortosClientesOperadores = numeroscortosClientesOperadores;
	}
}
