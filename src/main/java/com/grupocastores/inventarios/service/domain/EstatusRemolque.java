package com.grupocastores.inventarios.service.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

/*
* Título                    : Estatus Remolques
* Descripción               : Entity estatus remolques 
* Compañía                  : Transportes Castores de Baja California S.A. de C.V. – Área de Desarrollo
* Fecha de creación         : 2023-05-17
* Autor                     : Tavera Godinez Zyanya Guadalupe
* Versión                   : 1.0.0
* ID Requerimiento/Ticket   : NULL
*/
@Entity
@Table(name = "remolques_estatus_remolque")
@Data
@NoArgsConstructor
public class EstatusRemolque implements Serializable {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idEstatusRemolque;
	private String nombre;

	private static final long serialVersionUID = 1L;

}
