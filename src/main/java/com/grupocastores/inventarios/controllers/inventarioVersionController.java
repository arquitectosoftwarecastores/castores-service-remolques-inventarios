package com.grupocastores.inventarios.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/version")
@Api(value = "VersionController", produces = "application/json")
public class inventarioVersionController {

	@Value("${spring.profiles.active:Unknown}")
	private String activeProfile;
	
	@Value("${spring.application.name}")
	private String nombre;
	
	@Value("${globales.version}")
	private String version;

	@ApiOperation(value = "Obtiene la version")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Version obtenida", response = String.class) })
	@GetMapping("/get")
	@ResponseBody
	public ResponseEntity<?> getAll() {
		return new ResponseEntity<>(version, HttpStatus.OK);

	}
	
}