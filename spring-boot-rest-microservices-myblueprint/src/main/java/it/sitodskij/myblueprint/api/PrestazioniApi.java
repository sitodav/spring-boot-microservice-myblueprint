package it.sitodskij.myblueprint.api;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import it.sitodskij.myblueprint.business.PrestazioniService;
import it.sitodskij.myblueprint.exception.ApiParametroNonValido;
import it.sitodskij.myblueprint.to.CountableWrapper;
import it.sitodskij.myblueprint.to.PrestazioneTO;
import it.sitodskij.myblueprint.to.filtriricerca.RicercaPrestazioniByDescs;
import it.sitodskij.myblueprint.to.filtriricerca.RicercaPrestazioniByIds;


@RequestMapping("/prestazioni")
@CrossOrigin(allowCredentials="true")
@RestController
public class PrestazioniApi {

	Logger logger = LoggerFactory.getLogger(PrestazioniApi.class);
			
	@Autowired
	PrestazioniService prestazioniService;
	
	/*ricerca usando id specifici delle componenti innestate */
	@RequestMapping(value="/", method=RequestMethod.OPTIONS )
	public ResponseEntity<CountableWrapper<PrestazioneTO>> searchPrestazioniByIds(
			RicercaPrestazioniByIds filtroRicerca
			) throws Exception
	{
		try
		{
			logger.info("Contattato endpoint search prestazioni livelli assistenziali");
			return new ResponseEntity<>(prestazioniService.searchPrestazioni(filtroRicerca), HttpStatus.OK);
		}
		catch(Exception ex)
		{
			logger.error("Errore ottenimento lista prestazioni",ex);
			throw ex;
		}
	}
	
	/*ricerca come da specifica con alcune componenti specificate tramite descrizioni 
	 * in like
	 */
	@RequestMapping(value="/", method=RequestMethod.GET )
	public ResponseEntity<CountableWrapper<PrestazioneTO>> searchPrestazioni(
			RicercaPrestazioniByDescs filtroRicerca
			) throws Exception
	{
		try
		{
			logger.info("Contattato endpoint search prestazioni livelli assistenziali");
			return new ResponseEntity<>(prestazioniService.searchPrestazioni(filtroRicerca), HttpStatus.OK);
		}
		catch(Exception ex)
		{
			logger.error("Errore ottenimento lista prestazioni",ex);
			throw ex;
		}
	}
	 
	
	@RequestMapping(value="/{idPrestazione}", method=RequestMethod.GET)
	public ResponseEntity<PrestazioneTO> searchById(
			@PathVariable("idPrestazione") Long idPrestazione
			) throws Exception
	{
		try
		{
			logger.info("Contattato endpoint prestazione by id "+idPrestazione);
			return new ResponseEntity<>(prestazioniService.searchById(idPrestazione), HttpStatus.OK);
		}
		catch(Exception ex)
		{
			logger.error("Errore ottenimento prestazione"+idPrestazione,ex);
			throw ex;
		}
	}
	
	
	@RequestMapping(value="/{idPrestazione}", method=RequestMethod.DELETE)
	public ResponseEntity<PrestazioneTO> deleteById(
			@PathVariable("idPrestazione") Long idPrestazione
			) throws Exception
	{
		try
		{
			logger.info("Contattato delete prestazione by id "+idPrestazione);
			prestazioniService.deletePrestazioneById(idPrestazione);
			return new ResponseEntity<>(null, HttpStatus.OK);
		}
		catch(Exception ex)
		{
			logger.error("Errore delete prestazione "+idPrestazione,ex);
			throw ex;
		}
	}
	
	/*creazione singola prestazione come post*/
	@RequestMapping(value="", method=RequestMethod.POST)
	public ResponseEntity<PrestazioneTO> createPrestazione( 
			@RequestBody PrestazioneTO newPrestazione 
			) throws Exception
	{
		try
		{
			logger.info("Contattato create prestazione "+newPrestazione);
			return new ResponseEntity<>(prestazioniService.savePrestazione(newPrestazione), HttpStatus.OK);
		}
		catch(Exception ex)
		{
			logger.error("Errore create prestazione "+newPrestazione,ex);
			throw ex;
		}
	}
	
	
	/*update singola prestazione come put*/
	@RequestMapping(value="", method=RequestMethod.PUT)
	public ResponseEntity<PrestazioneTO> updateSnapshot( 
			@RequestBody PrestazioneTO updatePrestazione
			) throws Exception
	{
		try
		{
			logger.info("Contattato update prestazione "+updatePrestazione);
			
			if(null == updatePrestazione.getId())
				throw new ApiParametroNonValido("Id prestazione richiesto nel TO");
			
			return new ResponseEntity<>(prestazioniService.savePrestazione(updatePrestazione), HttpStatus.OK);
		}
		catch(Exception ex)
		{
			logger.error("Errore update prestazione "+updatePrestazione,ex);
			throw ex;
		}
	}
	
	
 
	@RequestMapping(value = "inserimentoexcelsingolo", method = { RequestMethod.POST }, consumes = {
			"multipart/form-data" })
	public ResponseEntity<List<PrestazioneTO>> inserimentoMultiplo( @RequestParam("file") MultipartFile file) throws Exception {
		try {
			return new ResponseEntity<List<PrestazioneTO>>(
					prestazioniService.caricamentoPrestazioniExcel( file),
					HttpStatus.OK);

		} catch (Exception ex) {
			logger.error("Errore inserimento prestazione singolo excel ", ex);
			throw ex;
		}
	}
	
	
	
	
	
	
	
}
