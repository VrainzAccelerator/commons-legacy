package com.vrains.persistence.oldmodel.mcp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "MCP_tipos_servicios")
public class TipoServicio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, name = "id_tipo_servicio", columnDefinition = "BIGINT UNSIGNED")
	private Long	idTipoServicio;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_cliente")
	private Cliente	cliente;

	@Column
	private String	url;

	@Column(columnDefinition = "BIT")
	private char	privado;

	@Column
	private String	descripcion;

}
