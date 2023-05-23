package com.grupocastores.inventarios.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.grupocastores.inventarios.service.IInventarioService;
import com.grupocastores.inventarios.service.domain.Estatus;
import com.grupocastores.inventarios.service.domain.EstatusInventario;
import com.grupocastores.inventarios.service.domain.EstatusRemolque;
import com.grupocastores.inventarios.service.domain.Ingreso;
import com.grupocastores.inventarios.service.domain.Inventarios;
import com.grupocastores.inventarios.service.domain.Tarjeta;
import com.grupocastores.inventarios.service.repository.InventarioRepository;

/*
* Título                    : InventarioServiceImpl
* Descripción               : Interfaz para las funciones de Inventarios, estatus, tarjeta, ingreso, estatus remolque y estatus inventario
* Compañía                  : Transportes Castores de Baja California S.A. de C.V. – Área de Desarrollo
* Fecha de creación         : 2023-05-17
* Autor                     : Tavera Godinez Zyanya Guadalupe
* Versión                   : 1.0.0
* ID Requerimiento/Ticket   : NULL
*/
@Service
public class InventarioServiceImpl implements IInventarioService {

	@Autowired
	private InventarioRepository inventarioRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Inventarios> findAll() {
		return (List<Inventarios>) inventarioRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Inventarios findByIdRemolque(Long idRemolque) {
		return inventarioRepository.findById(idRemolque).orElse(null);
	}

	@Override
	@Transactional
	public Inventarios save(Inventarios remolques) {
		return inventarioRepository.save(remolques);
	}
	
	@Override
    public Inventarios update(Inventarios inventarios) {
        return inventarioRepository.save(inventarios);
    }

	@Override
	@Transactional(readOnly = true)
	public List<Ingreso> findAllIngreso() {
		return inventarioRepository.findAllIngreso();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Estatus> findAllEstatus() {
		return inventarioRepository.findAllEstatus();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Tarjeta> findAllTarjeta() {
		return inventarioRepository.findAllTarjeta();
	}

	@Override
	public List<Inventarios> findByClaveRemolque(String clave) {
		return inventarioRepository.findByClaveRemolque(clave);
	}

	@Override
	public Inventarios findByNumRemolque(String numRemolque) {
		return inventarioRepository.findByNumRemolque(numRemolque);
	}

	@Override
	public List<EstatusRemolque> findAllEstatusRemolque() {
		return inventarioRepository.findAllEstatus1();
	}
	
	@Override
	public List<EstatusInventario> findAllEstatusInventario() {
		return inventarioRepository.findAllEstatus2();
	}

	@Override
	public Inventarios findByRemolque(String folio) {
		return inventarioRepository.findByRemolque(folio);
	}
}
