package it.sitodskij.myblueprint.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.naming.Name;
import javax.naming.directory.Attribute;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.DirContext;
import javax.naming.directory.ModificationItem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.support.LdapNameBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.sitodskij.myblueprint.data.dao.TbUtentiDao;
import it.sitodskij.myblueprint.data.model.TbUtenti;
import it.sitodskij.myblueprint.to.UtenteTO;

/**
 * 
 * @author sitodskij
 *
 *  Helper per lavorare con ldap
 */
@Component
public class LDAPHelper {

	
	private static final Logger logger = LoggerFactory.getLogger(LDAPHelper.class);
	
	@Autowired
	LdapTemplate ldapTemplate;
	
	@Value("${openam.ldap.ou}")
	String organizationUnit;
	
	@Autowired
	TbUtentiDao utentiDao;
	
	
	private DirContextAdapter buildContextNodoLdap(UtenteTO utente)
	{
		Name dn = LdapNameBuilder.newInstance().add("ou", organizationUnit).add("cn", utente.getUsername()).build();
		DirContextAdapter context = new DirContextAdapter(dn);

		context.setAttributeValues("objectclass",
				new String[] { "pushDeviceProfilesContainer", "kbaInfoContainer", "iplanet-am-managed-person",
						"inetuser", "sunFMSAML2NameIdentifier", "inetorgperson", "devicePrintProfilesContainer",
						"sunIdentityServerLibertyPPService", "iplanet-am-user-service", "sunFederationManagerDataStore",
						"forgerock-am-dashboard-service", "oathDeviceProfilesContainer", "sunAMAuthAccountLockout",
						"organizationalperson", "top", "person", "iPlanetPreferences",
						"iplanet-am-auth-configuration-service" });

		context.setAttributeValue("uid", utente.getUsername());
		context.setAttributeValue("cn", utente.getUsername());
		context.setAttributeValue("sn", utente.getUsername());
		context.setAttributeValue("inetUserStatus", "Active");
		context.setAttributeValue("givenName", utente.getUsername());
		context.setAttributeValue("userPassword", utente.getPassword());

		return context;
	}
	
	public void createUser(UtenteTO utente) throws Exception
	{
		try
		{
			deleteUser(utente);
		}
		catch(Exception ex)
		{
			
		}
		ldapTemplate.bind(buildContextNodoLdap(utente));
	}
	
	public void deleteUser(UtenteTO utente) throws Exception
	{
		ldapTemplate.unbind("cn="+utente.getUsername()+",ou="+organizationUnit);
	}
	
	public void updateUserPW(UtenteTO utente) throws Exception
	{ 
        Attribute gnAttribute = new BasicAttribute("userPassword", utente.getPassword());
        
        ModificationItem[] mods = new ModificationItem[] { 
        		new ModificationItem(DirContext.REPLACE_ATTRIBUTE, gnAttribute)
        };
        
      ldapTemplate.modifyAttributes(
              "cn="+utente.getUsername()+",ou="+organizationUnit,
              mods);
	}
	
	
	
	

	/*scorro sugli utenti del db dell'applicativo
	 * e per ognuno di questi resetto la pw come nome.cognome01
	 * e allineo l'ldap 
	 */
	@Transactional(rollbackFor=Exception.class)
	public void allineaDbUtentiPdrConLdap() throws Exception
	{
		try
		{
			
			List<TbUtenti> utentiToAdd = Optional.ofNullable(utentiDao.findAll())
						.orElse(new ArrayList<>())
						.stream()
						.filter(utenteDB -> !utenteDB.getUsername().equalsIgnoreCase("sistema")
								&& !utenteDB.getUsername().equalsIgnoreCase("admin"))
						 
						.collect(Collectors.toList());
			
			BCryptPasswordEncoder encoderLocaleDB = new BCryptPasswordEncoder();
			
			for(TbUtenti utenteDB : utentiToAdd)
			{
				try
				{
					/*Presi dal db la prima volta, la pw e' data da nome.cognome01*/
					String pw = utenteDB.getNome()+"."+utenteDB.getCognome()+"01";
					utenteDB.setPassword(encoderLocaleDB.encode(pw.toLowerCase()));
					utenteDB = utentiDao.save(utenteDB);
					UtenteTO uTO = new UtenteTO();
					uTO.setUsername(utenteDB.getUsername());
					uTO.setPassword(utenteDB.getPassword()); /*la pw ad ldap viene mandata in chiaro, automaticamente il sistema la encripta*/
					this.createUser(uTO);
				}
				catch(Exception ex)
				{
					logger.info("Impossibile allineare ldap con utente da db :"+utenteDB.getUsername(),ex);
					logger.info("Continuo lo stesso...");
				}
			}
						
		}
		catch(Exception ex)
		{
			logger.error("Errore allineamento tramite file di ldap e db",ex);
		}
	}
	
	
	
	
}
