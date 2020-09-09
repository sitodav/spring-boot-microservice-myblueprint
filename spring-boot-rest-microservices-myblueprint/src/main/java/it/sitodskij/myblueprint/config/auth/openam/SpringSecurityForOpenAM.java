package it.sitodskij.myblueprint.config.auth.openam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import it.sitodskij.myblueprint.business.UtentiService;
import it.sitodskij.myblueprint.config.auth.SpringSecurityGeneralConf;
import lombok.extern.log4j.Log4j2;

/*Bean di configurazione per abilitare la Basic Auth di Spring Security
 */

@ConditionalOnProperty(name = "ambiente.autenticazione", havingValue = "openam")
@Configuration
@EnableWebSecurity
@Log4j2
public class SpringSecurityForOpenAM extends SpringSecurityGeneralConf {

	@Autowired
	UtentiService utenteService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.cors().and().csrf().disable().authorizeRequests().anyRequest().permitAll();

	}

	@Bean
	public CommandLineRunner debugStartSecurityCon() {
		return args -> {
			log.info("Abilitata Spring Security per OpenAM (tutti url ammessi)");
		};
	}

}
