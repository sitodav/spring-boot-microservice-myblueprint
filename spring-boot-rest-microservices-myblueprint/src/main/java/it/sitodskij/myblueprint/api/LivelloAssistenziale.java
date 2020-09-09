package it.sitodskij.myblueprint.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.dozermapper.core.Mapper;

import it.sitodskij.myblueprint.business.LivelloAssistenzialeService;
import it.sitodskij.myblueprint.to.CountableWrapper;
import it.sitodskij.myblueprint.to.LivelloAssistenzialeTO;
import it.sitodskij.myblueprint.to.filtriricerca.RicercaConAnno;

@RequestMapping("/livelliassistenziali")
@CrossOrigin(allowCredentials="true")
@RestController
public class LivelloAssistenziale {

	Logger logger = LoggerFactory.getLogger(LivelloAssistenziale.class);
			
	@Autowired
	LivelloAssistenzialeService lvlAssistenzialiService;
	 
	 
	@RequestMapping(value="/", method=RequestMethod.GET)
	public ResponseEntity<CountableWrapper<LivelloAssistenzialeTO>> searchLivelliAssistenziali(
			RicercaConAnno filtroRicerca,
			@RequestParam(name="idMacroambito", required = false) Long idMacroambito 
			) throws Exception
	{

		try
		{

			logger.info("Contattato endpoint search livelli assistenziali");
			return new ResponseEntity<>(lvlAssistenzialiService.searchLivelloAssistenziale(filtroRicerca,idMacroambito), HttpStatus.OK);
		}
		catch(Exception ex)
		{
			logger.error("Errore ottenimento livelli assistenziali",ex);
			throw ex;
		}
	}
	
	@RequestMapping(value="/{idLivelloAssistenziale}", method=RequestMethod.GET)
	public ResponseEntity<LivelloAssistenzialeTO> searchById(
			@PathVariable("idLivelloAssistenziale") Long idLivelloAssistenziale
			) throws Exception
	{
		
		try
		{

			logger.info("Contattato endpoint livello assistenziale by id "+idLivelloAssistenziale);
			return new ResponseEntity<>(lvlAssistenzialiService.searchById(idLivelloAssistenziale), HttpStatus.OK);
		}
		catch(Exception ex)
		{
			logger.error("Errore ottenimento  livello assistenziale"+idLivelloAssistenziale,ex);
			throw ex;
		}
	}
	
}
