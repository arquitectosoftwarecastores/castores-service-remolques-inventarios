package com.grupocastores.inventarios.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.grupocastores.inventarios.dto.inventariosDTO;
import com.grupocastores.inventarios.service.IInventarioService;
import com.grupocastores.inventarios.service.domain.Estatus;
import com.grupocastores.inventarios.service.domain.EstatusInventario;
import com.grupocastores.inventarios.service.domain.EstatusRemolque;
import com.grupocastores.inventarios.service.domain.Ingreso;
import com.grupocastores.inventarios.service.domain.Inventarios;
import com.grupocastores.inventarios.service.domain.Tarjeta;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/*
* Título                    : InventarioController
* Descripción               : Controllador de la entidad de Inventario
* Compañía                  : Transportes Castores de Baja California S.A. de C.V. – Área de Desarrollo
* Fecha de creación         : 2023-05-17
* Autor                     : Tavera Godinez Zyanya Guadalupe
* Versión                   : 1.0.0
* ID Requerimiento/Ticket   : NULL
*/

@RestController
@Api(value = "InventarioController", produces = "application/json")
public class InventarioController {
	
	/**
	 * DECLARACION DE VARIABLES GLOBALES
	 */
	@Autowired
	IInventarioService inventarioService;

	private Inventarios inventarios;
	
	@ApiOperation(value = "Recupera inventarios", tags = { "Controlador inventarios" })
	@ApiResponses(value = { 
	@ApiResponse(code = 200, message = "inventarios obtenidos", response = inventariosDTO.class), 
	@ApiResponse(code = 404, message = "No encontrados") })
	/**
	 * Obtener todos los inventarios
	 * @return
	 */
	@GetMapping("/inventarios")
	public List<Inventarios> lstInventarios() {
		return inventarioService.findAll();
	}

	/**
	 * Título                    : show
	 * Descripción               : Filtro de inventario por su idRemolque
	 * Compañía                  : Transportes Castores de Baja California S.A. de C.V. – Área de Desarrollo
	 * Fecha de creación         : 2023-05-17
	 * Autor                     : Tavera Godinez Zyanya Guadalupe
	 * Versión                   : 1.0.0
	 * ID Requerimiento/Ticket   : NULL
	 * 
	 * @param idRemolque
	 * @return
	 */
	@GetMapping("/inventarios/{idRemolque}")
	public ResponseEntity<?> show(@PathVariable Long idRemolque) {
		Map<String, Object> response = new HashMap<>();
		try {
			inventarios = inventarioService.findByIdRemolque(idRemolque);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (inventarios == null) {
			response.put("mensaje",
					"El remolque con el ID: ".concat(idRemolque.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Inventarios>(inventarios, HttpStatus.OK);
	}

	/**
	 * Título                    : ver
	 * Descripción               : Filtro de inventario por su numRemolque
	 * Compañía                  : Transportes Castores de Baja California S.A. de C.V. – Área de Desarrollo
	 * Fecha de creación         : 2023-05-17
	 * Autor                     : Tavera Godinez Zyanya Guadalupe
	 * Versión                   : 1.0.0
	 * ID Requerimiento/Ticket   : NULL
	 * 
	 * @param numRemolque
	 * @return
	 */
	@GetMapping("/inventarios/remolque/{numRemolque}")
	public ResponseEntity<?> ver(@PathVariable String numRemolque) {
		Map<String, Object> response = new HashMap<>();
		try {
			inventarios = inventarioService.findByNumRemolque(numRemolque);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (inventarios == null) {
			response.put("mensaje",
					"El remolque: ".concat(numRemolque.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Inventarios>(inventarios, HttpStatus.OK);
	}

	/**
	 * Título                    : show
	 * Descripción               : Filtro de inventario por clave de oficina
	 * Compañía                  : Transportes Castores de Baja California S.A. de C.V. – Área de Desarrollo
	 * Fecha de creación         : 2023-05-17
	 * Autor                     : Tavera Godinez Zyanya Guadalupe
	 * Versión                   : 1.0.0
	 * ID Requerimiento/Ticket   : NULL
	 * 
	 * @param clave
	 * @return
	 */
	@GetMapping("/inventarios/clave/{clave}")
	public ResponseEntity<?> show(@PathVariable String clave) {
		Map<String, Object> response = new HashMap<>();
		List<Inventarios> lstInventarios = new ArrayList<>();

		try {
			if (clave.equals("1100")) {
				lstInventarios = inventarioService.findAll();
			} else {
				lstInventarios = inventarioService.findByClaveRemolque(clave);
			}
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(lstInventarios, HttpStatus.OK);
	}

	/**
	 * Título                    : createOrUpdate
	 * Descripción               : Creación y edición de inventario por su idRemolque y folio
	 * 							   cuando existen ambos parametros se crea un nuevo registro con los cambios
	 * 							   solo se cambia su estatus, de ser datos no existentes igual se creará uno nuevo.
	 * Compañía                  : Transportes Castores de Baja California S.A. de C.V. – Área de Desarrollo
	 * Fecha de creación         : 2023-05-17
	 * Autor                     : Tavera Godinez Zyanya Guadalupe
	 * Versión                   : 1.0.0
	 * ID Requerimiento/Ticket   : NULL
	 * 
	 * @param inventarios
	 * @param result
	 * @return
	 */
	@PostMapping("/inventarios")
	public ResponseEntity<?> createOrUpdate(@Validated @RequestBody Inventarios inventarios, BindingResult result) {
		Inventarios newRemolque = null;
		Map<String, Object> response = new HashMap<>();
		if (result.hasErrors()) {
			List<String> lstErrors = result.getFieldErrors().stream().map(err -> {
				return "El campo '" + err.getField() + "' " + err.getDefaultMessage();
			}).collect(Collectors.toList());
			response.put("errors", lstErrors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		try {
			Inventarios existingRemolque;
			if (inventarios.getIdRemolque() == null) {
				existingRemolque = inventarioService.findByRemolque(inventarios.getFolio());
			} else {
				existingRemolque = inventarioService.findByNumRemolque(inventarios.getNumRemolque());
			}
			if (existingRemolque != null) {
				// Actualiza el estatus del registro existente
				EstatusRemolque estatus = new EstatusRemolque();
				estatus.setIdEstatusRemolque(2L); // Asigna el id correspondiente al estatus
				existingRemolque.setEstatusRemolque(estatus);
				inventarioService.update(existingRemolque);
			}
			inventarios.setIdRemolque(null);
			
			newRemolque = inventarioService.save(inventarios);
			response.put("mensaje", "El remolque ha sido agregado con éxito");
			response.put("inventarios", newRemolque);

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert o update en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Obtener lista de ingreso
	 * @return
	 */
	@GetMapping("/inventarios/ingreso")
	public List<Ingreso> lstIngreso() {
		return inventarioService.findAllIngreso();
	}

	/**
	 * Obtener lista de estatus
	 * @return
	 */
	@GetMapping("/inventarios/estatus")
	public List<Estatus> lstEstatus() {
		return inventarioService.findAllEstatus();
	}

	/**
	 * Obtener lista de tarjeta
	 * @return
	 */
	@GetMapping("/inventarios/tarjeta")
	public List<Tarjeta> lstTarjeta() {
		return inventarioService.findAllTarjeta();
	}

	/**
	 * Obtener lista de estatus remolque
	 * @return
	 */
	@GetMapping("/inventarios/estatusRem")
	public List<EstatusRemolque> lstEstatusRem() {
		return inventarioService.findAllEstatusRemolque();
	}
	
	/**
	 * Obtener lista de estatus inventario
	 * @return
	 */
	@GetMapping("/inventarios/estatusInv")
	public List<EstatusInventario> lstEstatusInv() {
		return inventarioService.findAllEstatusInventario();
	}

	/**
	 * Título                    : update
	 * Descripción               : Edición de inventario por su idRemolque
	 * Compañía                  : Transportes Castores de Baja California S.A. de C.V. – Área de Desarrollo
	 * Fecha de creación         : 2023-05-17
	 * Autor                     : Tavera Godinez Zyanya Guadalupe
	 * Versión                   : 1.0.0
	 * ID Requerimiento/Ticket   : NULL
	 * 
	 * @param inventarios
	 * @param result
	 * @param idRemolque
	 * @return
	 */
	@PutMapping("/inventarios/{idRemolque}")
	public ResponseEntity<?> update(@RequestBody Inventarios inventarios, BindingResult result,
			@PathVariable Long idRemolque) {
		Inventarios remolqueActual = inventarioService.findByIdRemolque(idRemolque);
		Map<String, Object> response = new HashMap<>();
		if (result.hasErrors()) {
			List<String> lstErrors = result.getFieldErrors().stream().map(err -> {
				return "El campo '" + err.getField() + "' " + err.getDefaultMessage();
			}).collect(Collectors.toList());
			response.put("errors", lstErrors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		if (remolqueActual == null) {
			response.put("mensaje", "Error: no se pudo editar, el inventario con el ID: "
					.concat(idRemolque.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
			// Verificar si el registro ya existe en la base de datos
			if (remolqueActual != null) {
				EstatusRemolque estatus = new EstatusRemolque();
				estatus.setIdEstatusRemolque(2L); // Asigna el id correspondiente al estatus
				remolqueActual.setEstatusRemolque(estatus);
				inventarioService.update(remolqueActual);
			}
			inventarios.setIdRemolque(null);
			Inventarios newInventario = inventarioService.save(inventarios);
			response.put("mensaje", "Inventario agregado con éxito");
			response.put("rentas", newInventario);

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar el inventario en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}