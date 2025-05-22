package com.grupocastores.inventarios.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.grupocastores.inventarios.dto.ResponseDTO;
import com.grupocastores.inventarios.dto.inventariosDTO;
import com.grupocastores.inventarios.service.IInventarioService;
import com.grupocastores.inventarios.service.IRemolquesService;
import com.grupocastores.inventarios.service.domain.Estatus;
import com.grupocastores.inventarios.service.domain.EstatusInventario;
import com.grupocastores.inventarios.service.domain.EstatusRemolque;
import com.grupocastores.inventarios.service.domain.Ingreso;
import com.grupocastores.inventarios.service.domain.Inventarios;
import com.grupocastores.inventarios.service.domain.Remolques;
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
@RequestMapping(value = "/inventarios")
@Api(value = "InventarioController", produces = "application/json")
public class InventarioController {
	
	/**
	 * DECLARACION DE VARIABLES GLOBALES
	 */
	@Autowired
	IInventarioService inventarioService;
	
	@Autowired
	IRemolquesService remolquesService;

	private Inventarios inventarios;
	
	private static Logger logger = LoggerFactory.getLogger(InventarioController.class);
	
	static final String HEADERBACK = "/inventarios";
	
	/**
	 * Obtener todos los inventarios
	 * @return
	 */
	@ApiOperation(value = "Recupera lista de inventarios")
	@ApiResponses(value = { //
	@ApiResponse(code = 200, message = "Lista de inventarios", response = inventariosDTO.class),
	@ApiResponse(code = 404, message = "No encontrados"),
	@ApiResponse(code = 500, message = "Error Inesperado", response = Exception.class) })
	@GetMapping("/all")
	public ResponseEntity<?> lstInventarios() {
	    try {
	        List<Inventarios> lista = inventarioService.findAll();
	        return ResponseEntity.ok(lista);
	    } catch (Exception e) {
	        logger.error("Error al obtener la lista de inventarios", e);
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                             .body(new ResponseDTO("Error al obtener la lista de inventarios: " + e.getMessage()));
	    }
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
	@ApiOperation(value = "Recupera lista de inventarios por idRemolque")
	@ApiResponses(value = { //
	@ApiResponse(code = 200, message = "Lista de inventarios por idRemolque", response = inventariosDTO.class),
	@ApiResponse(code = 404, message = "No encontrados"),
	@ApiResponse(code = 500, message = "Error Inesperado", response = Exception.class) })
	@GetMapping("/{idRemolque}")
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
	@ApiOperation(value = "Recupera lista de inventarios por noEconomico")
	@ApiResponses(value = { //
	@ApiResponse(code = 200, message = "Lista de inventarios por noEconomico", response = inventariosDTO.class),
	@ApiResponse(code = 404, message = "No encontrados"),
	@ApiResponse(code = 500, message = "Error Inesperado", response = Exception.class) })
	@GetMapping("/remolque/{numRemolque}")
	public ResponseEntity<?> ver(@PathVariable String numRemolque) {
	    try {
	        Inventarios inventarios = inventarioService.findByNumRemolque(numRemolque);
	        if (inventarios == null) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                                 .body(new ResponseDTO("No se encontró un inventario con número de remolque: " + numRemolque));
	        }
	        return ResponseEntity.ok(inventarios);
	    } catch (Exception excepcion) {
	        logger.error("Error al obtener inventario con número de remolque: " + numRemolque, excepcion);
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                             .body(new ResponseDTO("Error al obtener inventario: " + excepcion.getMessage()));
	    }
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
	@ApiOperation(value = "Recupera lista de inventarios por clave")
	@ApiResponses(value = { //
	@ApiResponse(code = 200, message = "Lista de inventarios por clave", response = inventariosDTO.class),
	@ApiResponse(code = 404, message = "No encontrados"),
	@ApiResponse(code = 500, message = "Error Inesperado", response = Exception.class) })
	@GetMapping("/clave/{clave}")
	public ResponseEntity<?> show(@PathVariable String clave) {
		Map<String, Object> response = new HashMap<>();
		List<Inventarios> lstInventarios = new ArrayList<>();

		try {
			if (clave.equals("1100") || clave.equals("1107")) {
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
	@ApiOperation(value = "Agregar un nuevo inventario a la lista")
	@ApiResponses(value = { //
	@ApiResponse(code = 200, message = "Agregar Lista de inventarios", response = inventariosDTO.class),
	@ApiResponse(code = 404, message = "No encontrados"),
	@ApiResponse(code = 500, message = "Error Inesperado", response = Exception.class) })
	@PostMapping("/agregar")
	public ResponseEntity<?> createOrUpdate(@Validated @RequestBody Inventarios inventarios, BindingResult result) {
		Inventarios newRemolque = null;
		Map<String, Object> response = new HashMap<>();
		try {
			Inventarios existingRemolque;
			if (inventarios.getIdRemolque() == null) {
				existingRemolque = inventarioService.findByRemolque(inventarios.getFolio());
			} else {
				existingRemolque = inventarioService.findByNumRemolque(inventarios.getNumRemolque());
			}
			if (existingRemolque != null) {
				EstatusRemolque estatus = new EstatusRemolque();
				estatus.setIdEstatusRemolque(2L);
				existingRemolque.setEstatusRemolque(estatus);
				inventarioService.update(existingRemolque);
			}
			inventarios.setIdRemolque(null);
			
			newRemolque = inventarioService.save(inventarios);

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		} catch (Exception excepcion) {
			logger.error(HEADERBACK, excepcion);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(excepcion);
		}
	}

	/**
	 * Obtener lista de ingreso
	 * @return
	 */
	@ApiOperation(value = "Recupera lista de ingreso por inventarios")
	@ApiResponses(value = { //
	@ApiResponse(code = 200, message = "Lista de ingreso por inventarios", response = inventariosDTO.class),
	@ApiResponse(code = 404, message = "No encontradas"),
	@ApiResponse(code = 500, message = "Error Inesperado", response = Exception.class) })
	@GetMapping("/ingreso")
	public ResponseEntity<?> lstIngreso() {
	    try {
	        List<Ingreso> ingresos = inventarioService.findAllIngreso();
	        return ResponseEntity.ok(ingresos);
	    } catch (Exception e) {
	        logger.error("Error al obtener la lista de ingresos", e);
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                             .body(new ResponseDTO("Error al obtener la lista de ingresos: " + e.getMessage()));
	    }
	}

	/**
	 * Obtener lista de estatus
	 * @return
	 */
	@ApiOperation(value = "Recupera lista de estatus por inventarios")
	@ApiResponses(value = { //
	@ApiResponse(code = 200, message = "Lista de estatus por inventarios", response = inventariosDTO.class),
	@ApiResponse(code = 404, message = "No encontradas"),
	@ApiResponse(code = 500, message = "Error Inesperado", response = Exception.class) })
	@GetMapping("/estatus")
	public ResponseEntity<?> lstEstatus() {
	    try {
	        List<Estatus> estatusList = inventarioService.findAllEstatus();
	        return ResponseEntity.ok(estatusList);
	    } catch (Exception e) {
	        logger.error("Error al obtener la lista de estatus", e);
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                             .body(new ResponseDTO("Error al obtener la lista de estatus: " + e.getMessage()));
	    }
	}
	/**
	 * Obtener lista de tarjeta
	 * @return
	 */
	@ApiOperation(value = "Recupera lista de tarjeta por inventarios")
	@ApiResponses(value = { //
	@ApiResponse(code = 200, message = "Lista de tarjeta por inventarios", response = inventariosDTO.class),
	@ApiResponse(code = 404, message = "No encontradas"),
	@ApiResponse(code = 500, message = "Error Inesperado", response = Exception.class) })
	@GetMapping("/tarjeta")
	public ResponseEntity<?> lstTarjeta() {
	    try {
	        List<Tarjeta> tarjetas = inventarioService.findAllTarjeta();
	        return ResponseEntity.ok(tarjetas);
	    } catch (Exception e) {
	        logger.error("Error al obtener la lista de tarjetas", e);
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                             .body(new ResponseDTO("Error al obtener la lista de tarjetas: " + e.getMessage()));
	    }
	}

	/**
	 * Obtener lista de estatus remolque
	 * @return
	 */
	@ApiOperation(value = "Recupera lista de estatus Remolques por inventarios")
	@ApiResponses(value = { //
	@ApiResponse(code = 200, message = "Lista de estatus Remolques por inventarios", response = inventariosDTO.class),
	@ApiResponse(code = 404, message = "No encontradas"),
	@ApiResponse(code = 500, message = "Error Inesperado", response = Exception.class) })
	@GetMapping("/estatusRem")
	public ResponseEntity<?> lstEstatusRem() {
	    try {
	        List<EstatusRemolque> estatusRemolques = inventarioService.findAllEstatusRemolque();
	        return ResponseEntity.ok(estatusRemolques);
	    } catch (Exception e) {
	        logger.error("Error al obtener la lista de estatus de remolques", e);
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                             .body(new ResponseDTO("Error al obtener la lista de estatus de remolques: " + e.getMessage()));
	    }
	}
	
	/**
	 * Obtener lista de estatus inventario
	 * @return
	 */
	@ApiOperation(value = "Recupera lista de estatus Inventarios por inventarios")
	@ApiResponses(value = { //
	@ApiResponse(code = 200, message = "Lista de estatus Inventarios por inventarios", response = inventariosDTO.class),
	@ApiResponse(code = 404, message = "No encontradas"),
	@ApiResponse(code = 500, message = "Error Inesperado", response = Exception.class) })
	@GetMapping("/estatusInv")
	public ResponseEntity<?> lstEstatusInv() {
	    try {
	        List<EstatusInventario> estatusInventario = inventarioService.findAllEstatusInventario();
	        return ResponseEntity.ok(estatusInventario);
	    } catch (Exception e) {
	        logger.error("Error al obtener la lista de estatus de inventario", e);
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                             .body(new ResponseDTO("Error al obtener la lista de estatus de inventario: " + e.getMessage()));
	    }
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
	@ApiOperation(value = "Se edita algun inventario")
	@ApiResponses(value = { //
	@ApiResponse(code = 200, message = "Se edita algun inventario por su ID", response = inventariosDTO.class),
	@ApiResponse(code = 404, message = "No encontradas"),
	@ApiResponse(code = 500, message = "Error Inesperado", response = Exception.class) })
	@PutMapping("/{idRemolque}")
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
			if (remolqueActual != null) {
				EstatusRemolque estatus = new EstatusRemolque();
				estatus.setIdEstatusRemolque(2L);
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
	
	@GetMapping(value = "/remolques/{noEconomico}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public ResponseEntity<?> findByOficina(@PathVariable String noEconomico) {
	    try {
	        List<Remolques> remolques = remolquesService.findByRemolques("PRODUCCION13", noEconomico);
	        return ResponseEntity.ok(remolques);
	    } catch (Exception e) {
	        logger.error("Error al buscar remolques con código 'PRODUCCION13' y número económico: " + noEconomico, e);
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                             .body(new ResponseDTO("Error al buscar remolques: " + e.getMessage()));
	    }
	}

}