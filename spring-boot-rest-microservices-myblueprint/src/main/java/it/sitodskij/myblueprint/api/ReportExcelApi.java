package it.sitodskij.myblueprint.api;

import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import it.sitodskij.myblueprint.business.FondiSanitariService;
import it.sitodskij.myblueprint.business.PrestazioniService;
import it.sitodskij.myblueprint.to.filtriricerca.RicercaConAnno;
import it.sitodskij.myblueprint.to.filtriricerca.RicercaPrestazioniByDescs;
import it.sitodskij.myblueprint.util.FileHelper;


@RestController
@CrossOrigin
@RequestMapping("/reportexcel")
@Configuration
public class ReportExcelApi {

	 
	
	private static final Logger logger = LoggerFactory.getLogger(ReportExcelApi.class);
	
	
	
	@Autowired
	PrestazioniService prestazioniService;
	
	
	@Autowired
	FondiSanitariService fondiService;
	
	
	
	/*endpoint per ottenere excel del risultato di ricerca
	 * ottenuto invocando endpoint di prestazioni sulla get 
	 */
	@RequestMapping(value = "/prestazioni", method = RequestMethod.POST)
	public ResponseEntity<byte[]> reportRicercaPrestazioni( @RequestBody RicercaPrestazioniByDescs filtroRicerca) 
			throws Exception {

		 
		try {
			logger.info("Contattato endpoint generazione report excel ricerca prestazioni by filtro "+filtroRicerca);
			StringBuffer fileTitle = new StringBuffer();
			Workbook reportWB = prestazioniService.generaReportRicercaPrestazioni(filtroRicerca, fileTitle);
			byte[] wbBytes = FileHelper.workbookToByteArray(reportWB);
			return FileHelper.buildResponseForFileDownload(wbBytes, wbBytes.length, fileTitle + ".xls");
		} catch (Exception ex) {
			logger.error("Errore generazione report ricerca prestazioni", ex);
			throw ex;
		}

	}
	
	
	
	@RequestMapping(value = "/fondi", method = RequestMethod.POST)
	public ResponseEntity<byte[]> reportRicercaFondi(@RequestBody  RicercaConAnno filtroRicerca) 
			throws Exception {

		 
		try {
			logger.info("Contattato endpoint generazione report excel ricerca foldi by filtro "+filtroRicerca);
			StringBuffer fileTitle = new StringBuffer();
			Workbook reportWB = fondiService.generaReportRicercaFondi(filtroRicerca, fileTitle);
			byte[] wbBytes = FileHelper.workbookToByteArray(reportWB);
			return FileHelper.buildResponseForFileDownload(wbBytes, wbBytes.length, fileTitle + ".xls");
		} catch (Exception ex) {
			logger.error("Errore generazione report ricerca foldi", ex);
			throw ex;
		}

	}
}
