package it.sitodskij.myblueprint.business.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.dozermapper.core.Mapper;

import it.sitodskij.myblueprint.business.NaturaPrestazioniService;
import it.sitodskij.myblueprint.data.dao.TbNaturaPrestazioneDao;
import it.sitodskij.myblueprint.data.model.TbNaturaPrestazione;
import it.sitodskij.myblueprint.decorators.DoNothingDecorator;
import it.sitodskij.myblueprint.to.CountableWrapper;
import it.sitodskij.myblueprint.to.NaturaPrestazioniTO;
import it.sitodskij.myblueprint.to.filtriricerca.FiltroRicercaPaginata;
import it.sitodskij.myblueprint.util.PaginationHelper;

 

@Service
@Transactional(rollbackFor = Exception.class)
public class NaturaPrestazioniServiceImpl implements NaturaPrestazioniService {

	private static final Logger logger = LoggerFactory.getLogger(NaturaPrestazioniServiceImpl.class);
  

	@Autowired
	Mapper mapper;
	
	@Autowired
	TbNaturaPrestazioneDao naturaPrestazioniDao;
	
	@Autowired
	DoNothingDecorator doNothingDecor;
	

	@Override
	public CountableWrapper<NaturaPrestazioniTO> searchNaturaPrestazioni(FiltroRicercaPaginata filtroRicerca) throws Exception {
		try
		{
			Page<TbNaturaPrestazione> pageNaturaPrest =  naturaPrestazioniDao.findNaturaPrestazioni(PaginationHelper.buildPageOrderById(filtroRicerca, TbNaturaPrestazione.class))   ;
			CountableWrapper<NaturaPrestazioniTO> countableWrap = PaginationHelper.buildCountableWrapper(pageNaturaPrest, NaturaPrestazioniTO.class, "naturaprestazione", mapper, doNothingDecor);
			return countableWrap;
		}
		catch(Exception ex)
		{
			logger.error("Errore ottenimento lista natura prestazioni",ex);
			throw ex;
		}
	}

	@Override
	public NaturaPrestazioniTO searchById(Long id) throws Exception {

		try
		{
			logger.info("Ricerca natura prestazione by id: "+id);
			
			 return mapper.map(naturaPrestazioniDao.findById(id).get(), NaturaPrestazioniTO.class,"naturaprestazione");
		}
		catch(Exception ex)
		{
			logger.error("Errore ottenimento  natura prestazione by id "+id,ex);
			throw ex;
		}
	
	}

	 

	 
}
