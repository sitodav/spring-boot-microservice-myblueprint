package it.sitodskij.myblueprint.business.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.dozermapper.core.Mapper;

import it.sitodskij.myblueprint.business.LivelloAssistenzialeService;
import it.sitodskij.myblueprint.data.dao.TbLivelloAssistenzialeDao;
import it.sitodskij.myblueprint.data.dao.TbMacroambitoDao;
import it.sitodskij.myblueprint.data.model.TbLivelloAssistenziale;
import it.sitodskij.myblueprint.data.model.TbMacroambito;
import it.sitodskij.myblueprint.decorators.DoNothingDecorator;
import it.sitodskij.myblueprint.to.CountableWrapper;
import it.sitodskij.myblueprint.to.LivelloAssistenzialeTO;
import it.sitodskij.myblueprint.to.filtriricerca.RicercaConAnno;
import it.sitodskij.myblueprint.util.PaginationHelper;
import it.sitodskij.myblueprint.util.mocker.Mocked; 

@Service
@Transactional(rollbackFor = Exception.class)
public class LivelloAssistenzialeServiceImpl implements LivelloAssistenzialeService {

	private static final Logger logger = LoggerFactory.getLogger(LivelloAssistenzialeServiceImpl.class);
  

	@Autowired
	Mapper mapper;
	
	@Autowired
	TbLivelloAssistenzialeDao livelloAssistenzialeDao;
	
	@Autowired
	DoNothingDecorator doNothingDecor;
	
	@Autowired
	TbMacroambitoDao macroambitoDao;
	
	


	@Override
	public CountableWrapper<LivelloAssistenzialeTO> searchLivelloAssistenziale(RicercaConAnno filtroRicerca, Long idMacroambito) throws Exception {
		try
		{
			logger.info("Ricerca livelli assistenziali con filtro : "+filtroRicerca);
			String dozerMappingKey = "livelloassistenziale" + ( filtroRicerca.getDeepSearch() ? "DEEP" : "SHALLOW" );
			
			Page<TbLivelloAssistenziale> lvlAssistenzialiDB = livelloAssistenzialeDao.searchLvlAssistenziale(filtroRicerca.getAnno(),idMacroambito, PaginationHelper.buildPageOrderById(filtroRicerca, TbLivelloAssistenziale.class));
			CountableWrapper<LivelloAssistenzialeTO> countableWrap = PaginationHelper.buildCountableWrapper(lvlAssistenzialiDB, LivelloAssistenzialeTO.class, dozerMappingKey, mapper, doNothingDecor);
			
			return countableWrap;
		}
		catch(Exception ex)
		{
			logger.error("Errore ricerca macroambiti",ex);
			throw ex;
		}
	}


	@Mocked(mockerClass = LivelloAssistenzialeServiceMock.class, mockingValue = "12L")
	@Override
	public LivelloAssistenzialeTO searchById(Long id) throws Exception {
		try
		{
			logger.info("Ricerca livello assistenziale by id: "+id);
			
			 return mapper.map(livelloAssistenzialeDao.findById(id).get(), LivelloAssistenzialeTO.class,"livelloassistenzialeDEEP");
		}
		catch(Exception ex)
		{
			logger.error("Errore ottenimento  livello assistenziale by id "+id,ex);
			throw ex;
		}
	}
	
	
	@Override
	public LivelloAssistenzialeTO save(LivelloAssistenzialeTO lvlAssist) throws Exception {
		try
		{
			logger.info("save lvl assistenziale ");
			TbLivelloAssistenziale lvlAssistDB = mapper.map(lvlAssist,TbLivelloAssistenziale.class,"livelloassistenzialeSHALLOW");
			TbMacroambito macroambitoDB = macroambitoDao.findById(lvlAssist.getIdMacroambito()).get();
			lvlAssistDB.setTbMacroambito(macroambitoDB);
			
			if(lvlAssist.getId() != null)
				lvlAssistDB.setID_Livello_Assistenziale(lvlAssist.getId()); 
			
			
			lvlAssistDB = livelloAssistenzialeDao.save(lvlAssistDB);
			return mapper.map(lvlAssistDB,LivelloAssistenzialeTO.class,"livelloassistenzialeSHALLOW");
		}
		catch(Exception ex)
		{
			logger.error("Errore save lvl assistenziale",ex);
			throw ex;
		}

	}
	 
}
