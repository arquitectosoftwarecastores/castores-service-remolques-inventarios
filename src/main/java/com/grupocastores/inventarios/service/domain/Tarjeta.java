package com.grupocastores.inventarios.service.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/*
* Título                    : Tarjeta
* Descripción               : Entity de tarjeta
* Compañía                  : Transportes Castores de Baja California S.A. de C.V. – Área de Desarrollo
* Fecha de creación         : 2023-05-17
* Autor                     : Tavera Godinez Zyanya Guadalupe
* Versión                   : 1.0.0
* ID Requerimiento/Ticket   : NULL
*/

@Entity
@Table(name = "remolques_tarjeta")
@Data
public class Tarjeta implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;

	private static final long serialVersionUID = 1L;
}
