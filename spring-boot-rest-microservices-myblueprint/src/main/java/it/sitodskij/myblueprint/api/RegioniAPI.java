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

import it.sitodskij.myblueprint.business.RegioniService;
import it.sitodskij.myblueprint.to.CountableWrapper;
import it.sitodskij.myblueprint.to.RegioneTO;

@RequestMapping("/regioni")
@CrossOrigin(allowCredentials="true")
@RestController
public class RegioniAPI {

	Logger logger = LoggerFactory.getLogger(RegioniAPI.class);
			
	@Autowired
	RegioniService regioniService;
	 
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public ResponseEntity<CountableWrapper<RegioneTO>> getListaRegioni(
			@RequestParam(name="deepSearch", defaultValue="false") Boolean deepSearch) throws Exception
	{
		logger.info("Contattato endpoint lista regioni");
		try
		{

			return new ResponseEntity<>(regioniService.getAllRegioni(deepSearch), HttpStatus.OK);
		}
		catch(Exception ex)
		{
			logger.error("Errore ottenimento lista regioni",ex);
			throw ex;
		}
		
	}
	
	@RequestMapping(value="/{idRegione}", method=RequestMethod.GET)
	public ResponseEntity<RegioneTO> getRegioneById(
			@PathVariable("idRegione") Long idRegione,
			@RequestParam(name="deepSearch", defaultValue="false") Boolean deepSearch
			) throws Exception
	{
		
		try
		{

			logger.info("Contattato endpoint regione by id");
			return new ResponseEntity<>(regioniService.findById(idRegione,deepSearch), HttpStatus.OK);
		}
		catch(Exception ex)
		{
			logger.error("Errore ottenimento lista fondi sanitari",ex);
			throw ex;
		}
	}
	
	 
	
}
