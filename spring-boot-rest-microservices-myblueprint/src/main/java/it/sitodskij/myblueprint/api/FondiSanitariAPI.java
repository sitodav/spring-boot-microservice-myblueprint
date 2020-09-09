package it.sitodskij.myblueprint.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import it.sitodskij.myblueprint.business.FondiSanitariService;
import it.sitodskij.myblueprint.to.CountableWrapper;
import it.sitodskij.myblueprint.to.FondoSanitarioTO;
import it.sitodskij.myblueprint.to.filtriricerca.RicercaConAnno;

@RequestMapping("/fondisanitari")
@CrossOrigin(allowCredentials="true")
@RestController
public class FondiSanitariAPI {

	Logger logger = LoggerFactory.getLogger(FondiSanitariAPI.class);
			
	@Autowired
	FondiSanitariService fondiService;
	
	
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public ResponseEntity<CountableWrapper<FondoSanitarioTO>> searchFondiSanitari(
			RicercaConAnno filtroRicerca
			) throws Exception
	{
		
		try
		{

			logger.info("Contattato endpoint lista fondisanitari");
			return new ResponseEntity<>(fondiService.searchFondi(filtroRicerca), HttpStatus.OK);
		}
		catch(Exception ex)
		{
			logger.error("Errore ottenimento lista fondi sanitari",ex);
			throw ex;
		}
	}
	
	@RequestMapping(value="/{idfondo}", method=RequestMethod.GET)
	public ResponseEntity<FondoSanitarioTO> searchById(
			@PathVariable("idfondo") Long idFondo
			) throws Exception
	{
		
		try
		{

			logger.info("Contattato endpoint fondo by id "+idFondo);
			return new ResponseEntity<>(fondiService.searchById(idFondo), HttpStatus.OK);
		}
		catch(Exception ex)
		{
			logger.error("Errore ottenimento  fondo"+idFondo,ex);
			throw ex;
		}
	}
	
	
	@RequestMapping(value="/{idfondo}", method=RequestMethod.DELETE)
	public ResponseEntity<FondoSanitarioTO> deleteFondo(
			@PathVariable("idfondo") Long idFondo
			) throws Exception
	{
		
		try
		{

			logger.info("Contattato endpoint delete fondo by id "+idFondo);
			fondiService.deleteById(idFondo);
			return new ResponseEntity<>(null, HttpStatus.OK);
		}
		catch(Exception ex)
		{
			logger.error("Errore ottenimento delete fondo"+idFondo,ex);
			throw ex;
		}
	}
	
	@RequestMapping(value="", method=RequestMethod.POST)
	public ResponseEntity<FondoSanitarioTO> insertFondo(@RequestBody FondoSanitarioTO fondoTO) throws Exception
	{
		try
		{
			logger.info("Contattato endpoint insert fondo");
			return new ResponseEntity<>(fondiService.insertFondo(fondoTO), HttpStatus.OK);
		}
		catch(Exception ex)
		{
			logger.error("Errore insert fondo",ex);
			throw ex;
		}
	}
	
	
	@RequestMapping(value="", method=RequestMethod.PUT)
	public ResponseEntity<FondoSanitarioTO> modificaFondo(@RequestBody FondoSanitarioTO fondoTO) throws Exception
	{
		try
		{
			logger.info("Contattato endpoint modifica fondo");
			return new ResponseEntity<>(fondiService.modificaFondo(fondoTO), HttpStatus.OK);
		}
		catch(Exception ex)
		{
			logger.error("Errore modifica fondo",ex);
			throw ex;
		}
	}
	
}
