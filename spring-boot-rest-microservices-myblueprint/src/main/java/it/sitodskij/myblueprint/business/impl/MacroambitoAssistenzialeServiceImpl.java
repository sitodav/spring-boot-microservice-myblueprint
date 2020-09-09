package it.sitodskij.myblueprint.business.impl;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.dozermapper.core.Mapper;

import it.sitodskij.myblueprint.business.MacroambitoAssistenzialeService;
import it.sitodskij.myblueprint.data.dao.TbMacroambitoDao;
import it.sitodskij.myblueprint.data.model.TbMacroambito;
import it.sitodskij.myblueprint.decorators.DoNothingDecorator;
import it.sitodskij.myblueprint.to.CountableWrapper;
import it.sitodskij.myblueprint.to.MacroambitoAssistenzialeTO;
import it.sitodskij.myblueprint.to.filtriricerca.RicercaConAnno;
import it.sitodskij.myblueprint.util.PaginationHelper;

 

@Service
@Transactional(rollbackFor = Exception.class)
public class MacroambitoAssistenzialeServiceImpl implements MacroambitoAssistenzialeService {

	private static final Logger logger = LoggerFactory.getLogger(MacroambitoAssistenzialeServiceImpl.class);
  

	@Autowired
	Mapper mapper;

	@Autowired
	TbMacroambitoDao macroambitiDao;

	@Autowired
	DoNothingDecorator doNothingDecor;
	
	@Override
	public CountableWrapper<MacroambitoAssistenzialeTO> searchMacroambiti(RicercaConAnno filtroRicerca) throws Exception {
		
		try
		{

			logger.info("Ricerca macroambiti con filtro : "+filtroRicerca);
			String dozerMappingKey = "macroambito" + ( filtroRicerca.getDeepSearch() ? "DEEP" : "SHALLOW" );
			Page<TbMacroambito> macroambitiPage = macroambitiDao.searchMacroambito(filtroRicerca.getAnno(), PaginationHelper.buildPageOrderById(filtroRicerca, TbMacroambito.class));
			CountableWrapper<MacroambitoAssistenzialeTO> countableWrap = PaginationHelper.buildCountableWrapper(macroambitiPage, MacroambitoAssistenzialeTO.class, dozerMappingKey, mapper,doNothingDecor);
			return countableWrap;
			 
		}
		catch(Exception ex)
		{
			logger.error("Errore ricerca macroambiti",ex);
			throw ex;
		}
	}

	@Override
	public MacroambitoAssistenzialeTO searchById(Long id) throws Exception {
		try
		{
			logger.info("Ricerca macroambito by id: "+id);
			
			 return mapper.map(macroambitiDao.findById(id).get(), MacroambitoAssistenzialeTO.class,"macroambitoDEEP");
		}
		catch(Exception ex)
		{
			logger.error("Errore ottenimento  macroambito by id "+id,ex);
			throw ex;
		}
	}

	@Override
	public MacroambitoAssistenzialeTO save(MacroambitoAssistenzialeTO macroambito) throws Exception {
		try
		{
			logger.info("save macroambito ");
			TbMacroambito macroambitoDB = mapper.map(macroambito,TbMacroambito.class,"macroambitoSHALLOW");
			
			if(macroambito.getId() != null)
				macroambitoDB.setID_Macroambito(macroambito.getId());
			
			macroambitoDB = macroambitiDao.save(macroambitoDB);
			return mapper.map(macroambitoDB,MacroambitoAssistenzialeTO.class,"macroambitoSHALLOW");
		}
		catch(Exception ex)
		{
			logger.error("Errore save macroambito",ex);
			throw ex;
		}
	}

	 
}
