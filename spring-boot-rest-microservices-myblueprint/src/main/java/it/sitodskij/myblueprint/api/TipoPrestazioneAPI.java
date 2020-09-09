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

import it.sitodskij.myblueprint.business.TipoPrestazioneService;
import it.sitodskij.myblueprint.to.CountableWrapper;
import it.sitodskij.myblueprint.to.TipoPrestazioneTO;
import it.sitodskij.myblueprint.to.filtriricerca.RicercaConAnno;

@RequestMapping("/tipiprestazioni")
@CrossOrigin(allowCredentials="true")
@RestController
public class TipoPrestazioneAPI {

	Logger logger = LoggerFactory.getLogger(TipoPrestazioneAPI.class);
			
	@Autowired
	TipoPrestazioneService tipoPrestazioneService;
	 
	 
	@RequestMapping(value="/", method=RequestMethod.GET)
	public ResponseEntity<CountableWrapper<TipoPrestazioneTO>> searchTipiPrestazioni(
			RicercaConAnno filtroRicerca,
			@RequestParam(name="idLvlAssistenziale", required = false) Long idLvlAssistenziale 
			) throws Exception
	{

		try
		{
			logger.info("Contattato endpoint search tipi prestazioni");
			return new ResponseEntity<>(tipoPrestazioneService.searchTipoPrestazione(filtroRicerca,idLvlAssistenziale), HttpStatus.OK);
		}
		catch(Exception ex)
		{
			logger.error("Errore ottenimento tipi prestazioni",ex);
			throw ex;
		}
	}
	
	@RequestMapping(value="/{idTipoPrestazione}", method=RequestMethod.GET)
	public ResponseEntity<TipoPrestazioneTO> searchById(
			@PathVariable("idTipoPrestazione") Long idTipoPrestazione
			) throws Exception
	{
		
		try
		{

			logger.info("Contattato endpoint tipo prestazione by id "+idTipoPrestazione);
			return new ResponseEntity<>(tipoPrestazioneService.searchById(idTipoPrestazione), HttpStatus.OK);
		}
		catch(Exception ex)
		{
			logger.error("Errore ottenimento  tipo prestazione"+idTipoPrestazione,ex);
			throw ex;
		}
	}
	
}
