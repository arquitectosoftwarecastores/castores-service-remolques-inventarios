package com.grupocastores.inventarios.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.grupocastores.inventarios.service.domain.Remolques;

@Service
public interface IRemolquesService {
	
	List<Remolques> findByRemolques(String linkedServer, String noEconomico);

}
