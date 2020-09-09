package it.sitodskij.myblueprint.config.auth.springsecurity;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import it.sitodskij.myblueprint.config.auth.SpringSecurityGeneralConf;

 
/**
 * 
 * @author sitodskij
 * Quindi se il profilo di esecuzione fa si che venga caricato un .properties con il valore di ambiente.autenticazione = springsecurity
 * viene estesa la SpringSecurityGeneralConf configurando su tutti gli endpoint la basic auth di spring, con sessione
 * (quindi quando viene autenticato una volta il client, riceve un token valido per la sessione di spring security) quindi a patto di far girare
 * il cookie usando useCredentials: true (clientside) le successive chiamate non necessiteranno di reinviare user e pw
 * Viene anche configurato url di logout che invalida la sessione e invia codice 200
 */
@ConditionalOnProperty(name="ambiente.autenticazione",havingValue="springsecurity") //dipendente dal valore della property
@Configuration
@EnableWebSecurity //per abilitare spring security
public class SpringSecurityForBasicAuth extends SpringSecurityGeneralConf{
	

	private static final Logger logger = LoggerFactory.getLogger(SpringSecurityForBasicAuth.class);
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		 
			http
			.cors()
			.and().csrf().disable()
			 .authorizeRequests() 
			 .anyRequest().authenticated()
			 .and().httpBasic()
			 .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
			 
			 .and().logout().logoutUrl("/utenti/logout")
			 .permitAll()
			 .logoutSuccessHandler((httpServletRequest, httpServletResponse, authentication) -> {
				    httpServletResponse.setStatus(HttpServletResponse.SC_OK); //invio 200 come codice alla logout
			 }).addLogoutHandler(logoutHandler());
		
	}
	
	
	@Bean
	public CommandLineRunner debugStartSecurityCon()
	{
		return args -> {
			logger.info("Abilitata Basic Auth di Spring Security");
		};
	}
	
	
	
	@Bean
	public LogoutHandler logoutHandler()
	{
		return  (request, response,
				 authentication) -> {
					 logger.info("Utente sloggato ");
				 };
	}
	
	 
	 
 
}
