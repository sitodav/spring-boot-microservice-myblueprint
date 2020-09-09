package it.sitodskij.myblueprint.config.auth.openam;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import it.sitodskij.myblueprint.business.UtentiService;
import it.sitodskij.myblueprint.to.UtenteTO;
import lombok.extern.log4j.Log4j2;


/**
 * 
 * @author sitodskij
 * Se l'ambiente e' openam allora viene creato un filtro per prendere l'header del webagent di openam e usarlo per un doppio check
 * di esistenza dell'utente connesso (l'header che viaggia e' http_userid e il valore e' il nome utente proprio)
 */
@ConditionalOnProperty(name = "ambiente.autenticazione", havingValue = "openam")
@Order(1)  
@Component
@Log4j2
public class OpenAMIntegrationFilter implements Filter {

	@Autowired
	UtentiService utentiService;

	public OpenAMIntegrationFilter() {
		log.info("OpenAMIntegrationFilter init");
	}

	/*
	 * Questo filtro si occupa di integrarsi col meccanismo di protezione dell'user
	 * agent di OpenAM Tutte le richieste http che superano l'agent, sono decorate
	 * con un http_userid che fa match con un utente sul DB In tal caso come
	 * controllo aggiuntivo basta fare assicurarsi che l'utente non sia scaduto
	 */
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;

		String openAmHttpUserId = request.getHeader("http_userid");
		if (StringUtils.isBlank(openAmHttpUserId)) {

			log.info("Header di OpenAM (http_userid) mancante");
			response.sendError(HttpStatus.UNAUTHORIZED.value(), "header http_userid mancante");
		} else {
			/* uso l'http_userid per estrarre username */
			log.info("http_userid :" + openAmHttpUserId);

			UtenteTO utente = null;
			try {
				utente = utentiService.getUtenteByUsername(openAmHttpUserId);
				log.info("utente trovato con http_userid :" + openAmHttpUserId);

				if (utente == null) {
					log.info("Header di OpenAM (http_userid) non corrispondente ad un username sul db");
					response.sendError(HttpStatus.UNAUTHORIZED.value(), "header http_userid non valido");
				} else if (utente.getDisabilitato() != null && utente.getDisabilitato().equalsIgnoreCase("s")) {
					log.info("Header di OpenAM (http_userid) non corrispondente ad un username sul db");
					response.sendError(HttpStatus.FORBIDDEN.value(), "utente scaduto");

				} else // success !
				{
					chain.doFilter(req, res);

				}

			} catch (Exception ex) {
				log.error("Errore ottenimento username by http_userid header", ex);
				response.sendError(HttpStatus.UNAUTHORIZED.value(), "header http_userid non valido");
			}

		}

	}

	@Override
	public void init(FilterConfig filterConfig) {
		log.info(
				"Inizializzato filtro per OPEN AM (controllo ad ogni chiamata dell'header di open am http_userid valido");
	}

}
