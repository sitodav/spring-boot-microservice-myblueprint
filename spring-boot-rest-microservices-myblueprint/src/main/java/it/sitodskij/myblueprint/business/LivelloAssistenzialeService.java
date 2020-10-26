package it.sitodskij.myblueprint.business;

import it.sitodskij.myblueprint.to.CountableWrapper;
import it.sitodskij.myblueprint.to.LivelloAssistenzialeTO;
import it.sitodskij.myblueprint.to.filtriricerca.RicercaConAnno;

public interface LivelloAssistenzialeService {
	public CountableWrapper<LivelloAssistenzialeTO> searchLivelloAssistenziale(RicercaConAnno filtroRicerca, Long idMacroambito) throws Exception;
	
	public LivelloAssistenzialeTO searchById(Long id) throws Exception;
	public LivelloAssistenzialeTO save(LivelloAssistenzialeTO lvlAssistenz) throws Exception;

}
