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

import it.sitodskij.myblueprint.business.FondiSanitariService;
import it.sitodskij.myblueprint.business.NaturaPrestazioniService;
import it.sitodskij.myblueprint.to.CountableWrapper;
import it.sitodskij.myblueprint.to.NaturaPrestazioniTO;
import it.sitodskij.myblueprint.to.filtriricerca.FiltroRicercaPaginata;

@RequestMapping("/tipologiche")
@CrossOrigin(allowCredentials="true")
@RestController
public class TipologicheApi {

	Logger logger = LoggerFactory.getLogger(TipologicheApi.class);
			
	@Autowired
	NaturaPrestazioniService naturaPrestazioni;
 
	@Autowired
	FondiSanitariService fondiService;
	
	@RequestMapping(value="/annifondi", method=RequestMethod.GET)
	public ResponseEntity<CountableWrapper<Long>> getListaAnni() throws Exception
	{
		logger.info("Contattato endpoint lista anni");
		try
		{

			return new ResponseEntity<>(fondiService.getAllAnni(), HttpStatus.OK);
		}
		catch(Exception ex)
		{
			logger.error("Errore ottenimento lista anni",ex);
			throw ex;
		}
		
	}
	
	
	
	@RequestMapping(value="/naturaprestazioni", method=RequestMethod.GET)
	public ResponseEntity<CountableWrapper<NaturaPrestazioniTO>> searchPrestazioni(
				FiltroRicercaPaginata filtroRicerca
			) throws Exception
	{
		
		try
		{
			logger.info("Contattato endpoint search natura prestazioni");
			return new ResponseEntity<>(naturaPrestazioni.searchNaturaPrestazioni(filtroRicerca), HttpStatus.OK);
		}
		catch(Exception ex)
		{
			logger.error("Errore ottenimento natura prestazioni",ex);
			throw ex;
		}
	}
	
	
	@RequestMapping(value="/naturaprestazioni/{idTipologica}", method=RequestMethod.GET)
	public ResponseEntity<NaturaPrestazioniTO> searchById(
			@PathVariable("idTipologica") Long idTipologica
			) throws Exception
	{
		try
		{
			logger.info("Contattato endpoint tipologiche natura prestazioni, by id "+idTipologica);
			return new ResponseEntity<>(naturaPrestazioni.searchById(idTipologica), HttpStatus.OK);
		}
		catch(Exception ex)
		{
			logger.error("Contattato endpoint tipologiche natura prestazioni, by id "+idTipologica,ex);
			throw ex;
		}
	}
	
	
	
	
}
