package com.grupocastores.inventarios.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import com.grupocastores.inventarios.service.IRemolquesService;
import com.grupocastores.inventarios.service.domain.Remolques;

@Service
public class RemolquesServiceImpl implements IRemolquesService{
	
	@PersistenceContext
	private EntityManager entityManager;
	static final String queryNegociaciones =
			"SELECT * FROM OPENQUERY (%s, 'SELECT r.idremolque, r.noserie, r.placas, r.modelo, r.noeconomico "
			+ "FROM camiones.remolques r "
			+ "WHERE r.status=1 AND r.noeconomico= %s "
			+ "ORDER BY r.idremolque') "; 
	
	@Override
	public List<Remolques> findByRemolques(String linkedServer, String noEconomico) {
		try 
		{
			Query query = entityManager.createNativeQuery(String.format(
					queryNegociaciones,
					linkedServer,
					noEconomico
				), Remolques.class);	
			
				return (List<Remolques>) query.getResultList();
				
		} 
		catch (Exception e) {
			return null;
		}
	}
}
