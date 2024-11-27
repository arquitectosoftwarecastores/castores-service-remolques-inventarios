package com.grupocastores.inventarios.service;

import java.util.List;

import com.grupocastores.inventarios.service.domain.Remolques;

public interface IRemolquesService {
	
	List<Remolques> findByRemolques(String linkedServer, String noEconomico);

}
