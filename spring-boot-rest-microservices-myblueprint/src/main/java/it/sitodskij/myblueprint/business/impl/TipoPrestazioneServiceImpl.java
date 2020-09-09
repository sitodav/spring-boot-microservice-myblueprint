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

import it.sitodskij.myblueprint.business.LivelloAssistenzialeService;
import it.sitodskij.myblueprint.business.TipoPrestazioneService;
import it.sitodskij.myblueprint.data.dao.TbLivelloAssistenzialeDao;
import it.sitodskij.myblueprint.data.dao.TbTipoPrestazioneDao;
import it.sitodskij.myblueprint.data.model.TbLivelloAssistenziale;
import it.sitodskij.myblueprint.data.model.TbMacroambito;
import it.sitodskij.myblueprint.data.model.TbTipoPrestazione;
import it.sitodskij.myblueprint.decorators.DoNothingDecorator;
import it.sitodskij.myblueprint.to.CountableWrapper;
import it.sitodskij.myblueprint.to.LivelloAssistenzialeTO;
import it.sitodskij.myblueprint.to.TipoPrestazioneTO;
import it.sitodskij.myblueprint.to.filtriricerca.RicercaConAnno;
import it.sitodskij.myblueprint.util.PaginationHelper;

 

@Service
@Transactional(rollbackFor = Exception.class)
public class TipoPrestazioneServiceImpl implements TipoPrestazioneService {

	private static final Logger logger = LoggerFactory.getLogger(TipoPrestazioneServiceImpl.class);
  

	@Autowired
	Mapper mapper;
	
	@Autowired
	TbTipoPrestazioneDao tipoPrestazioneDao;
	
	@Autowired
	DoNothingDecorator doNothingDecor;
	
	@Autowired
	TbLivelloAssistenzialeDao lvlAssistDao;


	@Override
	public CountableWrapper<TipoPrestazioneTO> searchTipoPrestazione(RicercaConAnno filtroRicerca, Long idLvlAssistenziale) throws Exception {
		try
		{
			logger.info("Ricerca tipi prestazioni con filtro : "+filtroRicerca+" , lvl assistenziale : "+idLvlAssistenziale);
			String dozerMappingKey = "tipoprestazione" + ( filtroRicerca.getDeepSearch() ? "DEEP" : "SHALLOW" );
			
			Page<TbTipoPrestazione> tipiPrestazDB = tipoPrestazioneDao.searchTipoPrestazione(filtroRicerca.getAnno(),idLvlAssistenziale, PaginationHelper.buildPageOrderById(filtroRicerca, TbTipoPrestazione.class));
			CountableWrapper<TipoPrestazioneTO> countableWrap = PaginationHelper.buildCountableWrapper(tipiPrestazDB, TipoPrestazioneTO.class, dozerMappingKey, mapper, doNothingDecor);
			
			return countableWrap;
		}
		catch(Exception ex)
		{
			logger.error("Errore ricerca tipi prestazioni",ex);
			throw ex;
		}
	}


	@Override
	public TipoPrestazioneTO searchById(Long id) throws Exception {
		try
		{
			logger.info("Ricerca livello tipo prestazione by id: "+id);
			
			 return mapper.map(tipoPrestazioneDao.findById(id).get(), TipoPrestazioneTO.class,"tipoprestazioneDEEP");
		}
		catch(Exception ex)
		{
			logger.error("Errore ottenimento  tipo prestazione  by id "+id,ex);
			throw ex;
		}
	}


	@Override
	public TipoPrestazioneTO save(TipoPrestazioneTO tipoprestTO) throws Exception {
		try
		{
			logger.info("save tipo prestaz ");
			TbTipoPrestazione tipoPrestazDB = mapper.map(tipoprestTO,TbTipoPrestazione.class,"tipoprestazioneSHALLOW");
			TbLivelloAssistenziale lvlAssistDB = lvlAssistDao.findById(tipoprestTO.getIdLivelloAssistenziale()).get();
			tipoPrestazDB.setTbLivelloAssistenziale(lvlAssistDB);
			
			if(tipoprestTO.getId() != null)
				tipoPrestazDB.setID_Tipo_Prestazione(tipoprestTO.getId());
			
			tipoPrestazDB = tipoPrestazioneDao.save(tipoPrestazDB);
			return mapper.map(tipoPrestazDB,TipoPrestazioneTO.class,"tipoprestazioneSHALLOW");
		}
		catch(Exception ex)
		{
			logger.error("Errore save tipo prestaz",ex);
			throw ex;
		}

	}

 
	 
}
