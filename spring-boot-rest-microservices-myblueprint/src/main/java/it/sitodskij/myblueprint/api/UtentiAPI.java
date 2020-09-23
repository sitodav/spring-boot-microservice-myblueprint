package it.sitodskij.myblueprint.api;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.dozermapper.core.Mapper;

import it.sitodskij.myblueprint.business.UtentiService;
import it.sitodskij.myblueprint.exception.ApiParametroNonValido;
import it.sitodskij.myblueprint.to.UtenteTO;

 

@RequestMapping("/utenti")
@CrossOrigin(allowCredentials="true")
@RestController
public class UtentiAPI {

	Logger logger = LoggerFactory.getLogger(UtentiAPI.class);
	
	@Autowired 
	UtentiService utentiService;
	 
	
	
	@RequestMapping(value="/getUtenteUsingOpenAMHeader", method=RequestMethod.GET)
	public ResponseEntity<UtenteTO> getUtenteUsingOpenAMHeader(HttpServletRequest req) throws Exception
	{
		try
		{ 
			
			return new ResponseEntity<UtenteTO>(utentiService.getUtenteByUsername(req.getHeader("http_userid")),HttpStatus.OK);
		}
		catch(Exception ex)
		{
			logger.error("Errore ottenimento header",ex);
			throw ex;
		}
	}
	
	
	
	@RequestMapping(value="/openAMHeader", method=RequestMethod.GET)
	public ResponseEntity<String> getHeaderOpenAM(HttpServletRequest req) throws Exception
	{
		try
		{
			return new ResponseEntity<String>(req.getHeader("http_userid"),HttpStatus.OK);
		}
		catch(Exception ex)
		{
			logger.error("Errore ottenimento header",ex);
			throw ex;
		}
	}
	
	@RequestMapping(value="/forzaReloadCache", method=RequestMethod.GET)
	public ResponseEntity<List<UtenteTO>> forzaReloadCache() throws Exception
	{
		try
		{
			List<UtenteTO> utenti = utentiService.clearCacheUtenti();
			utenti.stream().forEach(utenteTO -> utenteTO.setPassword(null));
			
			return new ResponseEntity<List<UtenteTO>>(utenti,HttpStatus.OK);
		}
		catch(Exception ex)
		{
			logger.error("Errore ottenimento lista di tutti gli utenti",ex);
			throw ex;
		}
	}
	
	@RequestMapping(value="", method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<UtenteTO>> findAll() throws Exception
	{
		try
		{
			List<UtenteTO> utenti = utentiService.getAllUtenti();
			utenti.stream().forEach(utenteTO -> utenteTO.setPassword(null));
			
			return new ResponseEntity<List<UtenteTO>>(utenti,HttpStatus.OK);
		}
		catch(Exception ex)
		{
			logger.error("Errore ottenimento lista di tutti gli utenti",ex);
			throw ex;
		}
	}
	
	
	
	
	
	
	/* 
	 * in caso positivo ritorna utente decorato con l'anagrafica
	 * altrimenti ritorna 401 */
	
	@RequestMapping(value="/autenticaUtente", method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity autenticaUtente(@RequestBody UtenteTO userToFromClient) throws Exception
	{
		boolean validAccess = false;
		UtenteTO utenteTO = null;
		UserDetails userDetails = null;
		
		try
		{
			if(StringUtils.isEmpty(userToFromClient.getUsername()))
					throw new ApiParametroNonValido("user richiesto");
			
			/*per il check della pw devo usare la loadUserByUsername 
			 * poiche' userTO , quando salito dal metodo getUtenteByUsername() del servizio utentiService
			 * visto che la pw e' annotata per non essere serializzata nel TO (per non inviarla dietro al client)
			 * se stiamo usando il meccanismo del caching (ed e' questo il caso su getUtenteByUsername)
			 * la pw non viene scritta nella cache. Quindi tutti gli hit della cache ritornerebbero pw vuota
			 */
			userDetails = utentiService.loadUserByUsername(userToFromClient.getUsername()); //questo lo uso per validazione pw
			utenteTO = utentiService.getUtenteByUsername(userToFromClient.getUsername()); //questo per l'anagrafica da rimandare al client
			
			
			if(null != utenteTO)
			{
				boolean abilitato =  StringUtils.equalsIgnoreCase(utenteTO.getDisabilitato(), "0");
				if(abilitato && BCrypt.checkpw(userToFromClient.getPassword(), userDetails.getPassword()))
				{
					validAccess = true;
					/*decoro utente con info anagrafica */
//					utenteDecorato = mapper.map(utenteDB,UtenteTO.class,"utente");
					utenteTO.setPassword(null); /*non rimando ovviamente pw codificata */
					
					/*tutto ok, esiste e matcha (ma qui entra sempre se esiste per il meccanismo
					 * di spring security 
					 */
					return new ResponseEntity<UtenteTO>(utenteTO,HttpStatus.OK);
				}
				else if( !abilitato )
				{
					/*se utente disabilitato ritorniamo status 403 */
					return new ResponseEntity<String>( HttpStatus.FORBIDDEN);
				}
				else
				{
					/*altrimenti 401 ma tanto qui non arriva per il meccanismo di spring security */
					return new ResponseEntity<String>( HttpStatus.UNAUTHORIZED);
				}
				
			}
			else
			{
				/*altrimenti 401 ma tanto qui non arriva per il meccanismo di spring security */
				return new ResponseEntity<String>( HttpStatus.UNAUTHORIZED);
				 
			}
			
		}
		catch(UsernameNotFoundException ex)
		{
			logger.error("Username non trovato");
			throw ex;
		}
		catch(Exception ex)
		{
			logger.error("Errore ottenimento utente con user nel body ",ex);
			throw ex;
		}
		
		 
		 
	}
	
	@RequestMapping(value="/attiva", method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> attivaUtente(@RequestBody UtenteTO userTO) throws Exception
	{
		try
		{
			return new ResponseEntity<Boolean>(utentiService.attivaUtente(userTO),HttpStatus.OK);			
		}
		catch(Exception ex)
		{
			logger.error("Errore attivazione utente ",ex);
			throw ex;
		}
	}
	
	@RequestMapping(value="", method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> salvaUtente(@RequestBody UtenteTO userTO) throws Exception
	{
		try
		{
			return new ResponseEntity<Boolean>(utentiService.saveUtente(userTO),HttpStatus.OK);			
		}
		catch(Exception ex)
		{
			throw new ApiParametroNonValido(ex.getMessage());
		}
	}
	
	@RequestMapping(value="", method=RequestMethod.PUT,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> aggiornaUtente(
			
			@RequestBody UtenteTO userTO,
			HttpServletRequest request) throws Exception
	{
		try
		{
			UsernamePasswordAuthenticationToken userDetail = (UsernamePasswordAuthenticationToken)request.getUserPrincipal();
			return new ResponseEntity<Boolean>(utentiService.updateUtente(userTO,userDetail),HttpStatus.OK);			
		}
		catch(Exception ex)
		{
			throw new ApiParametroNonValido(ex.getMessage());
		}
	}
	

	
	@RequestMapping(value="/{idUtente}", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UtenteTO> findById(@PathVariable("idUtente") Long idUtente) throws Exception 
	{
		
		if(idUtente == null || idUtente<= 0)
		{
			throw new ApiParametroNonValido("parametro idUfficio mancante");
		}
		
		try
		{
			
			logger.info("Ottengo utente by id "+idUtente);
			return new ResponseEntity<UtenteTO>(utentiService.getUtente(idUtente),HttpStatus.OK);			
		}
		catch(Exception ex)
		{
			throw new ApiParametroNonValido(ex.getMessage());
		}
		
	}

}
