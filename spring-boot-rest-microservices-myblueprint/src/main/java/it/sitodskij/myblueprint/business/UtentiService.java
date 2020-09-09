package it.sitodskij.myblueprint.business;

import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;

import it.sitodskij.myblueprint.to.UtenteTO;

public interface UtentiService extends UserDetailsService{
	UtenteTO getUtente(Long id) throws Exception ;
	UtenteTO getUtenteByUsername(String username) throws Exception ;
	List<UtenteTO> getAllUtenti() throws Exception;
	Boolean attivaUtente(UtenteTO utente) throws Exception;
	Boolean saveUtente(UtenteTO utente) throws Exception;
	Boolean updateUtente(UtenteTO utente, UsernamePasswordAuthenticationToken userDetails) throws Exception;
	List<UtenteTO> clearCacheUtenti() throws Exception;
}
