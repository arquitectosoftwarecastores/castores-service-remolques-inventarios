package com.grupocastores.inventarios.dto;

import java.util.Date;

import javax.persistence.Column;

import io.swagger.annotations.ApiModel;
import lombok.Data;


@ApiModel(value = "Informacion de un inventario", description = "Datos del inventario")
@Data
public class inventariosDTO {
	
	private int id;
	private String description;
	@Column(name = "createddate")
	private Date createdDate;
	private Date lastModifiedTime;
	private Boolean isActive;

	/**
	 * Constructor de inventariosDTO sin argumentos
	 */
	public inventariosDTO () {
		
	}
	/**
	 * Constructor de inventariosDTO con todos los atributos como argumentos
	 * 
	 * @param id the id to set
	 * @param description the description to set
	 * 
	 */
	public inventariosDTO (int id, String description, Date createddate, Date lastModifiedTime, Boolean isActive) {
		this.id = id;
		this.description = description;
		this.isActive = isActive;
		this.createdDate = createddate;
		this.lastModifiedTime = lastModifiedTime;		
	}	
	@Override
	public String toString() {
		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append("inventarios [id=").append(id)
		.append(", description=").append(description)
		.append(", isActive=").append(isActive)	
		.append(", createddate").append(createdDate)
		.append(", lastModifiedTime").append(lastModifiedTime);
		return strBuilder.toString();
	}
}
