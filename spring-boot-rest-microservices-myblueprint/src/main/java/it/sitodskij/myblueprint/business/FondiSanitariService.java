package it.sitodskij.myblueprint.business;

import org.apache.poi.ss.usermodel.Workbook;

import it.sitodskij.myblueprint.to.CountableWrapper;
import it.sitodskij.myblueprint.to.FondoSanitarioTO;
import it.sitodskij.myblueprint.to.filtriricerca.RicercaConAnno;

 
public interface FondiSanitariService {
	
	public CountableWrapper<Long> getAllAnni() throws Exception;
	public CountableWrapper<FondoSanitarioTO> searchFondi(RicercaConAnno filtroRicerca) throws Exception;
	public FondoSanitarioTO searchById(Long id) throws Exception;
	public void deleteById(Long id) throws Exception;
	public FondoSanitarioTO insertFondo(FondoSanitarioTO fondoTo) throws Exception;
	public FondoSanitarioTO modificaFondo(FondoSanitarioTO fondoTO) throws Exception;
	public Workbook generaReportRicercaFondi(RicercaConAnno filtroRicerca, StringBuffer fileTitle) throws Exception;

	
}
