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
import org.springframework.web.bind.annotation.RestController;

import com.github.dozermapper.core.Mapper;

import it.sitodskij.myblueprint.business.MacroambitoAssistenzialeService;
import it.sitodskij.myblueprint.to.CountableWrapper;
import it.sitodskij.myblueprint.to.MacroambitoAssistenzialeTO;
import it.sitodskij.myblueprint.to.filtriricerca.RicercaConAnno;

@RequestMapping("/macroambiti")
@CrossOrigin(allowCredentials="true")
@RestController
public class MacroambitoAssistenziale {

	Logger logger = LoggerFactory.getLogger(MacroambitoAssistenziale.class);
			
	@Autowired
	MacroambitoAssistenzialeService macroambitoService;
 
	@RequestMapping(value="/", method=RequestMethod.GET)
	public ResponseEntity<CountableWrapper<MacroambitoAssistenzialeTO>> searchMacroambiti(
				RicercaConAnno filtroRicerca
			) throws Exception
	{

		try
		{

			logger.info("Contattato endpoint search macroambiti assistenziali");
			return new ResponseEntity<>(macroambitoService.searchMacroambiti(filtroRicerca), HttpStatus.OK);
		}
		catch(Exception ex)
		{
			logger.error("Errore ottenimento lista macroambiti assistenziali",ex);
			throw ex;
		}
	}
	
	@RequestMapping(value="/{idMacroambito}", method=RequestMethod.GET)
	public ResponseEntity<MacroambitoAssistenzialeTO> searchById(
			@PathVariable("idMacroambito") Long idMacroambito
			) throws Exception
	{
		
		try
		{

			logger.info("Contattato endpoint macroambito by id "+idMacroambito);
			return new ResponseEntity<>(macroambitoService.searchById(idMacroambito), HttpStatus.OK);
		}
		catch(Exception ex)
		{
			logger.error("Errore ottenimento macroambito"+idMacroambito,ex);
			throw ex;
		}
	}
	
}
