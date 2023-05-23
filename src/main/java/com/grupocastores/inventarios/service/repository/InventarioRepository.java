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
	
	@Query(value = "select * from remolques_inventarios u where u.folio = ?1", nativeQuery = true)
	public Inventarios findByRemolque(String folio);

	@Query(value = "select u from remolques_inventarios u where u.idRemolque=?1", nativeQuery = true)
	public Inventarios findByIdRemolque2(int idRemolque);
	
	@Query(value = "select * from remolques_inventarios u where u.num_remolque = ?1 and estatus_id = 1", nativeQuery = true)
	public Inventarios findByNumRemolque(String numRemolque);

	@Query(value = "select * from remolques_inventarios u where u.clave = ?1", nativeQuery = true)
	public List<Inventarios> findByClaveRemolque(String clave);

	@Query("from Ingreso")
	public List<Ingreso> findAllIngreso();

	@Query("from Estatus")
	public List<Estatus> findAllEstatus();
	
	@Query("from Tarjeta")
	public List<Tarjeta> findAllTarjeta();
	
	@Query("from EstatusRemolque")
	public List<EstatusRemolque> findAllEstatus1();
	
	@Query("from EstatusInventario")
	public List<EstatusInventario> findAllEstatus2();
}