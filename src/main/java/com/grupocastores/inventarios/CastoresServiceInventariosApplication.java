package com.grupocastores.inventarios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/*
* Título                    : CastoresServiceInventariosApplication
* Descripción               : Configuración principal del servicio de inventarios
* Compañía                  : Transportes Castores de Baja California S.A. de C.V. –Área de Desarrollo
* Fecha de creación         : 2023-05-17
* Autor                     : Tavera Godinez Zyanya Guadalupe
* Versión                   : 1.0.0
* ID Requerimiento/Ticket   : NULL
*/
@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication
public class CastoresServiceInventariosApplication {

	public static void main(String[] args) {
		SpringApplication.run(CastoresServiceInventariosApplication.class, args);
	}

}
