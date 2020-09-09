package it.sitodskij.myblueprint.api;

import java.util.NoSuchElementException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import it.sitodskij.myblueprint.exception.ApiParametroNonValido;
import it.sitodskij.myblueprint.exception.BusinessException;

@ControllerAdvice
@Component
public class ExceptionHandlerComponent extends ResponseEntityExceptionHandler  {

 
	@ExceptionHandler(ApiParametroNonValido.class)
	protected ResponseEntity<String> handleEccezioneApi(Exception ex) {
		// TODO Auto-generated method stub
		String exMessage = !StringUtils.isEmpty(ex.getMessage()) ? ex.getMessage() : "Parametro non valido";
		return new ResponseEntity(exMessage,new HttpHeaders(),HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(BusinessException.class )
	public ResponseEntity<String> eccezioniBusiness(Exception ex){ 
		String exMessage = !StringUtils.isEmpty(ex.getMessage()) ? ex.getMessage() : "Eccezione di Business";
		return new ResponseEntity(exMessage,new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(UsernameNotFoundException.class )
	public ResponseEntity<String> usernameNotFound(Exception ex){ 
		String exMessage = !StringUtils.isEmpty(ex.getMessage()) ? ex.getMessage() : "Utente non trovato";
		return new ResponseEntity(exMessage,new HttpHeaders(), HttpStatus.UNAUTHORIZED);
	}
	
	
	@ExceptionHandler({EmptyResultDataAccessException.class, NoSuchElementException.class})
	public ResponseEntity<String>  entityNotFound(Exception ex){ 
		String exMessage = "Una o piu' entita' non trovate";
		return new ResponseEntity(exMessage,new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(Exception.class )
	public ResponseEntity<String>  eccezioniGeneriche(Exception ex){ 
		String exMessage = "Errore Inatteso";
		return new ResponseEntity(exMessage,new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	 
	
	 
}
