package com.grupocastores.inventarios.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.grupocastores.inventarios.service.domain.Estatus;
import com.grupocastores.inventarios.service.domain.EstatusInventario;
import com.grupocastores.inventarios.service.domain.EstatusRemolque;
import com.grupocastores.inventarios.service.domain.Ingreso;
import com.grupocastores.inventarios.service.domain.Inventarios;
import com.grupocastores.inventarios.service.domain.Tarjeta;

/*
* Título                    : InventarioRepository
* Descripción               : Querys para inventarios, ingreso, estatus, tarjeta, estatus remolque y estatus inventario.
* Compañía                  : Transportes Castores de Baja California S.A. de C.V. – Área de Desarrollo
* Fecha de creación         : 2023-05-17
* Autor                     : Tavera Godinez Zyanya Guadalupe
* Versión                   : 1.0.0
* ID Requerimiento/Ticket   : NULL
*/

@Repository
public interface InventarioRepository extends JpaRepository<Inventarios, Long> {

	public Inventarios findByIdRemolque(int idRemolque);
	
	@Query(value = "SELECT * FROM remolques_inventarios u WHERE u.folio = ?1", nativeQuery = true)
	public Inventarios findByRemolque(String folio);

	@Query(value = "SELECT u FROM remolques_inventarios u WHERE u.idRemolque=?1", nativeQuery = true)
	public Inventarios findByIdRemolque2(int idRemolque);
	
	@Query(value = "SELECT * FROM remolques_inventarios u WHERE u.numremolque = ?1 AND estatus_id = 1", nativeQuery = true)
	public Inventarios findByNumRemolque(String numRemolque);

	@Query(value = "SELECT * FROM remolques_inventarios u WHERE u.clave = ?1", nativeQuery = true)
	public List<Inventarios> findByClaveRemolque(String clave);

	@Query("FROM Ingreso")
	public List<Ingreso> findAllIngreso();

	@Query("FROM Estatus")
	public List<Estatus> findAllEstatus();
	
	@Query("FROM Tarjeta")
	public List<Tarjeta> findAllTarjeta();
	
	@Query("FROM EstatusRemolque")
	public List<EstatusRemolque> findAllEstatusRemolque();
	
	@Query("FROM EstatusInventario")
	public List<EstatusInventario> findAllEstatusInventario();
}