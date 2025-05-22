package com.grupocastores.inventarios.service.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Remolques")
@IdClass(Remolques.RemolquesId.class)
public class Remolques implements Serializable{

	private static final long serialVersionUID = 9222149548475112671L;

public static class RemolquesId implements Serializable {
		
		private static final long serialVersionUID = 5134989896763792476L;

			public RemolquesId() {
				super();
			}
			
			String idRemolque;
	}

@Id
@Column(name = "idremolque", unique = true, nullable = false)
private String idRemolque; 

@Column(name = "noserie", unique = true, nullable = false)
private String noSerie;

private String placas;

private String modelo;

@Column(name = "noeconomico", unique = true, nullable = false)
private String noEconomico;
}
