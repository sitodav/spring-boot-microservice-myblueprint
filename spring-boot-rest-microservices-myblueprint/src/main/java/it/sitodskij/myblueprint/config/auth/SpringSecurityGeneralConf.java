package it.sitodskij.myblueprint.config.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import it.sitodskij.myblueprint.business.UtentiService;

/**
 * 
 * @author sitodskij
 *
 * Quest'applicazione ha due tipi di configurazione della security/autenticazione.
 * Una per ambiente con openam, una per ambiente classico (locale) con spring security basic auth (username e pw)
 * A seconda del profilo con cui viene eseguita la build maven (mvn clean install -P=profilo) viene fatta injection
 * nell'application.properties da maven del profilo spring di esecuzione, e il pacchetto generato a seconda del profilo spring di esecuzione
 * attiva e quindi legge l'application-profilo.properties relativo a tale profilo
 * Indipendentemente dal profilo di esecuzione di spring, viene sempre caricata questa classe per configurare spring security
 */
public class SpringSecurityGeneralConf extends WebSecurityConfigurerAdapter {

	private static final Logger logger = LoggerFactory.getLogger(SpringSecurityGeneralConf.class);

	@Autowired
	UtentiService utenteService;

	/**
	 * 
	 * @return
	 * Password encoder per l'encoding di spring security basic auth
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {

		return new BCryptPasswordEncoder();
	}
	
	/**
	 * 
	 * @return
	 * DaoAutenticationProvider usato da spring per salire, in fase di autenticazione con la basic auth in maniera trasparente
	 * i dati di validazione relativi all'user di cui è arrivata l'username, usando il service custom che sale l'utente (utenteService)
	 */
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(utenteService);
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}

	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}
	
	/**
	 * Per lasciare scoperte le risorse statiche di swagger 
	 */
	@Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/v2/api-docs",
                                   "/configuration/ui",
                                   "/swagger-resources/**",
                                   "/configuration/security",
                                   "/swagger-ui.html",
                                   "/webjars/**");
    }
	
	 
}
