package it.sitodskij.myblueprint.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;

import it.sitodskij.myblueprint.util.LDAPHelper;

/**
 * 
 * @author sitodskij
 *
 * Classe di configurazione per collegarsi all'LDAP per l'allineamento eventuale degli utenti
 */
@Configuration
@Import(LDAPHelper.class)
public class LDAPConfig {
	@Value("${openam.ldap.url}")
	String url;
	
	@Value("${openam.ldap.base}")
	String base;
	
	@Value("${openam.ldap.login.userdn}")
	String userDn;
	
	@Value("${openam.ldap.login.password}")
	String password;
	
	@Bean
	public LdapContextSource  ldapContextSource()
	{
		LdapContextSource source = new LdapContextSource();
		
		 
		source.setUrl(url );
        source.setBase(base);
        source.setUserDn( userDn );
        source.setPassword( password );
         
        return source;
	}
	
	
	@Bean
	public LdapTemplate ldapTemplate()
	{
		return new LdapTemplate(ldapContextSource());
	}
}
