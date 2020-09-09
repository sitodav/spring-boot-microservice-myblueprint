package it.sitodskij.myblueprint.business.impl;

import java.util.List;
import java.util.stream.Collectors;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.dozermapper.core.Mapper;

import it.sitodskij.myblueprint.business.RegioniService;
import it.sitodskij.myblueprint.data.dao.TbRegioniDao;
import it.sitodskij.myblueprint.to.CountableWrapper;
import it.sitodskij.myblueprint.to.RegioneTO;

@Service
@Transactional(rollbackFor=Exception.class)
public class RegioniServiceImpl implements RegioniService {

	private static final Logger logger = LoggerFactory.getLogger(RegioniServiceImpl.class);
	
	@Autowired
	Mapper mapper;
	
	@Autowired
	TbRegioniDao regioniDao;
	
	@Override
	public CountableWrapper<RegioneTO> getAllRegioni(Boolean deep) throws Exception 
	{
		
		try
		{
			String dozerMappingKey = "regioni" + (deep ? "DEEP" : "SHALLOW");
			logger.info("Ottenimento lista regioni ");
			List<RegioneTO> allRegioni = 
					regioniDao.findAll().stream()
						.map(tbRegioni -> mapper.map(tbRegioni, RegioneTO.class,dozerMappingKey))
						.collect(Collectors.toList());
			
			return new CountableWrapper<>(allRegioni,allRegioni.size(), 1);
		}
		catch(Exception ex)
		{
			logger.error("Errore ottenimento lista regioni ",ex);
			throw ex;
		}
	}

	@Override
	public RegioneTO findById(Long id, Boolean deep) throws Exception 
	{
		
		try
		{
			logger.info("Ottenimento regione by id "+id);
			String dozerMappingKey = "regioni" + (deep ? "DEEP" : "SHALLOW");
			return mapper.map(regioniDao.findById(id).get(), RegioneTO.class,dozerMappingKey);
			 
		}
		catch(Exception ex)
		{
			logger.error("Errore ottenimento regioni by id",ex);
			throw ex;
		}
	}

}
