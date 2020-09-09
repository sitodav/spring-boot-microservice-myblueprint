package it.sitodskij.myblueprint.business.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.dozermapper.core.Mapper;

import it.sitodskij.myblueprint.business.UtentiService;
import it.sitodskij.myblueprint.data.dao.TbUtentiDao;
import it.sitodskij.myblueprint.data.model.TbRuoli;
import it.sitodskij.myblueprint.data.model.TbUtenti;
import it.sitodskij.myblueprint.to.UtenteTO;

 

@Service
@Transactional(rollbackFor = Exception.class)
public class UtentiServiceImpl implements UtentiService {

	private static final Logger logger = LoggerFactory.getLogger(UtentiServiceImpl.class);

	@Autowired
	TbUtentiDao utenteDao;
 
  

	@Autowired
	Mapper mapper;

	@Override
	public UtenteTO getUtente(Long id) throws Exception {

		try {
			logger.info("Business: Estraggo utente by id " + id);
			return utenteDao.findById(id).map(utenteDB -> mapper.map(utenteDB, UtenteTO.class, "utente"))
					.orElse(null);
		} catch (Exception ex) {
			logger.error("Errore ottenimento utente by id " + id, ex);
			throw ex;
		}
	}

	@Override
	@Cacheable(value = "cacheUtenteTOUsernameUtente", key = "#username")
	public UtenteTO getUtenteByUsername(String username) throws Exception {

		try {
			logger.info("Business: Estraggo utente by username " + username);
			return Optional.ofNullable(utenteDao.findByUsername(username))
					.map(utenteDB -> mapper.map(utenteDB, UtenteTO.class, "utente")).orElse(null);
		} catch (Exception ex) {
			logger.error("Errore ottenimento utente by username " + username, ex);
			throw ex;
		}
	}

	/*
	 * Implementazione del metodo ereditato dall'interfaccia UserDetailsService per
	 * Spring Security
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UtenteTO utente = null;
		try {

			logger.info("Business: chiamato metodo loadUserByUsername() per spring security e username " + username);

			// utente = getUtenteByUsername(username);
			utente = Optional.ofNullable(utenteDao.findByUsername(username))
					.map(utenteDB -> mapper.map(utenteDB, UtenteTO.class, "utente")).orElse(null);

		} catch (Exception ex) {
			logger.error("Errore loadUserByUsername(), proseguo senza bloccare", ex);
		}

		if (null == utente) {
			throw new UsernameNotFoundException("Utente " + username + " non trovato");
		}

		UserDetails userDetailSpringSecurity = new User(utente.getUsername(), utente.getPassword(),
				Arrays.asList(new SimpleGrantedAuthority("user")));

		return userDetailSpringSecurity;

	}

	
	
	@Override
//	@Caching(evict = {
//			@CacheEvict("cacheUtenteTOUsernameUtente" ),
//			@CacheEvict("cacheUserDetailsUsernameUtente"),
//			@CacheEvict("cacheUrlUtente")
//	})
	public List<UtenteTO> clearCacheUtenti() throws Exception {
		try {
			return utenteDao.findAllByOrderByCognomeAsc().stream()
					.map(utenteDB -> mapper.map(utenteDB, UtenteTO.class, "utente"))
					.collect(Collectors.toList());
		} catch (Exception ex) {
			logger.error("Errore ottenimento di tutti gli utenti", ex);
			throw ex;
		}
	}
	
	
	@Override
	public List<UtenteTO> getAllUtenti() throws Exception {
		try {
			return utenteDao.findAllByOrderByCognomeAsc().stream()
					.map(utenteDB -> mapper.map(utenteDB, UtenteTO.class, "utente"))
					.collect(Collectors.toList());
		} catch (Exception ex) {
			logger.error("Errore ottenimento di tutti gli utenti", ex);
			throw ex;
		}
	}

	@Transactional
//	@CachePut(value = "cacheUtenteTOUsernameUtente", key = "#utente.user")
	@Override
	public Boolean attivaUtente(UtenteTO utente) throws Exception {
		try {
			TbUtenti u = utenteDao.getOne(utente.getId());

			if (u != null) {
				u.setDisabilitato(utente.getDisabilitato());
				utenteDao.save(u);
				return true;
			}
			return false;
		} catch (Exception ex) {
			logger.error("Errore attiva utente ", ex);
			throw ex;
		}
	}

	@Transactional
//	@CachePut(value = "cacheUtenteTOUsernameUtente", key = "#utente.user")
	@Override
	public Boolean saveUtente(UtenteTO utente) throws Exception {
		try {
			TbUtenti ut1 = utenteDao.findByUserIfActive(utente.getUsername());
			if (ut1 != null) {
				throw new Exception("Username già utilizzato.");
			}
			TbUtenti ut2 = utenteDao.findByEmail(utente.getEmail());
			if (ut2 != null) {
				throw new Exception("Email già utilizzata.");
			}

			TbUtenti u = new TbUtenti();
			u.setCognome(utente.getCognome());
			u.setNome(utente.getNome());
			u.setEmail(utente.getEmail());
			u.setUsername(utente.getUsername());
			if (utente.getPassword() != null) {
				u.setPassword(BCrypt.hashpw(utente.getPassword(), BCrypt.gensalt()));
			}
		 
			if (utente.getRuolo() != null && utente.getRuolo().getId() != null) {
				TbRuoli ru = new TbRuoli();
				ru.setID_Ruolo(utente.getRuolo().getId());
				u.setTbRuoli(ru);
			}
			 
			u.setDisabilitato("N");

			utenteDao.save(u);
 
//			TODO
//			logger.info("tento di allineare db ldap");
//			ldapComponentUtil.createUser(utente);
			
			return true;
		} catch (Exception ex) {
			logger.error("Errore salva utente ", ex);
			throw ex;
		}
	}

	@Transactional
//	@CachePut(value = "cacheUtenteTOUsernameUtente", key = "#utente.user")
	@Override
	public Boolean updateUtente(UtenteTO utente, UsernamePasswordAuthenticationToken userDetails) throws Exception {
		try {

			TbUtenti u = utenteDao.getOne(utente.getId());
			

			if (u != null) {
				u.setCognome(utente.getCognome());
				u.setNome(utente.getNome());
				u.setEmail(utente.getEmail());
				u.setUsername(utente.getUsername());
				/*
				 * NB: se l'utente ha ruolo amministratore di sistema o super utente la vecchia
				 * password non deve essere controllata
				 */
				
//				Utente utenteLoggato = utenteDao.findByUser(userDetails.getName());
//				ERuoli ruoloUtente = ERuoli.getByDBValue(utenteLoggato.getRuolo().getNome());
//				
//				if (!StringUtils.isEmpty(utente.getPassword())) {
//					if ( ruoloUtente != ERuoli.AMMINISTRATORE_SISTEMA && ruoloUtente != ERuoli.SUPER_UTENTE ) {
//						if (utente.getOldPassword() == null
//								|| !BCrypt.checkpw(utente.getOldPassword(), u.getPassword())) {
//							throw new Exception("Vecchia password non corrisponde.");
//						}
//					}
				 
					u.setPassword(BCrypt.hashpw(utente.getPassword(), BCrypt.gensalt()));
//					TODO
//					logger.info("tento di allineare db ldap");
//					ldapComponentUtil.updateUserPW(utente);
//				}

				 
				if (utente.getRuolo() != null && utente.getRuolo().getId() != null) {
					TbRuoli ru = new TbRuoli();
					ru.setID_Ruolo(utente.getRuolo().getId());
					u.setTbRuoli(ru);
				}
				 
				utenteDao.save(u);
				return true;
			}

			return false;
		} catch (Exception ex) {
			logger.error("Errore aggiorna utente ", ex);
			throw ex;
		}
	}

}
