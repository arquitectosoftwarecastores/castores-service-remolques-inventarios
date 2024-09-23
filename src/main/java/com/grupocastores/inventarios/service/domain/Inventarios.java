package com.grupocastores.inventarios.service.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;

/*
* Título                    : Inventarios
* Descripción               : Entity de inventarios
* Compañía                  : Transportes Castores de Baja California S.A. de C.V. – Área de Desarrollo
* Fecha de creación         : 2023-05-17
* Autor                     : Tavera Godinez Zyanya Guadalupe
* Versión                   : 1.0.0
* ID Requerimiento/Ticket   : NULL
*/

@Entity
@Table(name = "remolques_inventarios")
@Data
@NoArgsConstructor
public class Inventarios implements Serializable {

	private static final long serialVersionUID = -889490748904568208L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_remolque", unique = true, nullable = false)
	private Long idRemolque;
	
	@Column(nullable = true, length = 20)
	private String clave;

	@Column(name = "numremolque", nullable = true, length = 25)
	private String numRemolque;

	@Column(name = "num_serie", nullable = true, length = 20)
	private String numSerie;

	@Column(nullable = true, length = 20)
	private String placa;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_tarjeta")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Tarjeta tarjeta;

	@Column(nullable = false, length = 20)
	private String modelo;

	@Column(nullable = true, length = 20)
	private String estado;

	@Column(nullable = true, length = 50)
	private String inspector;
	
	@Column(nullable = true, length = 10)
	private String folio;

	@Column(name = "fecha_entrada", nullable = false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date fechaEntrada;

	@Column(name = "fecha_salida", nullable = true)
	private Date fechaSalida;

	@Column(name = "tipo_inventario", nullable = true, length = 20)
	private String tipoInventario;

	@Column(nullable = false, length = 50)
	private String sucursal;
	
	private String estados;
	
	private String esquema;
	
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_ingreso")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Ingreso ingreso;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_estatus")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Estatus estatus;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "estatus_id")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private EstatusRemolque estatusRemolque;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "estatus_inv")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private EstatusInventario estatusInventario;

}
