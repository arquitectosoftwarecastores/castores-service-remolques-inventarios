package com.grupocastores.inventarios.service;

import java.util.List;

import com.grupocastores.inventarios.service.domain.Estatus;
import com.grupocastores.inventarios.service.domain.EstatusInventario;
import com.grupocastores.inventarios.service.domain.EstatusRemolque;
import com.grupocastores.inventarios.service.domain.Ingreso;
import com.grupocastores.inventarios.service.domain.Inventarios;
import com.grupocastores.inventarios.service.domain.Tarjeta;

/*
* Título                    : IInventarioService
* Descripción               : Interfaz para las funciones de Inventarios, ingreso, estatus, tarjeta, estatus remolque y estatus inventario.
* Compañía                  : Transportes Castores de Baja California S.A. de C.V. – Área de Desarrollo
* Fecha de creación         : 2023-05-17
* Autor                     : Tavera Godinez Zyanya Guadalupe
* Versión                   : 1.0.0
* ID Requerimiento/Ticket   : NULL
*/

public interface IInventarioService {
	
	/**
	 * BUSCAR TODOS LOS INVENTARIOS
	 * 
	 * @return
	 */
	public List<Inventarios> findAll();
	
	/**
	 * BUSCAR POR FOLIO
	 * 
	 * @param folio
	 * @return
	 */
	public Inventarios findByRemolque(String folio);
	
	/**
	 * BUSCAR POR ID
	 * 
	 * @param idRemolque
	 * @return
	 */
	public Inventarios findByIdRemolque(Long idRemolque);
	
	/**
	 * BUSCAR POR REMOLQUE
	 * 
	 * @param numRemolque
	 * @return
	 */
	public Inventarios findByNumRemolque(String numRemolque);
	
	/**
	 * BUSCAR POR CLAVE DE OFICINA
	 * 
	 * @param clave
	 * @return
	 */
	public List<Inventarios> findByClaveRemolque(String clave);

	/**
	 * GUARDAR INVENTARIO
	 * 
	 * @param remolques
	 * @return
	 */
	public Inventarios save(Inventarios remolques);
	
	/**
	 * LISTA DE INGRESO
	 * 
	 * @return
	 */
	public List<Ingreso> findAllIngreso();
	
	/**
	 * LISTA DE ESTATUS
	 * 
	 * @return
	 */
	public List<Estatus> findAllEstatus();
	
	/**
	 * LISTA DE TARJETA
	 * 
	 * @param idRemolque
	 * @return
	 */
	public List<Tarjeta> findAllTarjeta();
	
	/**
	 * LISTA DE ESTATUS REMOLQUE
	 * 
	 * @return
	 */
	public List<EstatusRemolque> findAllEstatusRemolque();
	
	/**
	 * LISTA DE ESTATUS INVENTARIO
	 * 
	 * @return
	 */
	public List<EstatusInventario> findAllEstatusInventario();

	/**
	 * EDITAR INVENTARIO
	 * 
	 * @param inventarios
	 * @return
	 */
	Inventarios update(Inventarios inventarios);
}
