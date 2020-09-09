package it.sitodskij.myblueprint.business;

import it.sitodskij.myblueprint.to.CountableWrapper;
import it.sitodskij.myblueprint.to.MacroambitoAssistenzialeTO;
import it.sitodskij.myblueprint.to.filtriricerca.RicercaConAnno;

public interface MacroambitoAssistenzialeService {
	public CountableWrapper<MacroambitoAssistenzialeTO> searchMacroambiti(RicercaConAnno filtroRicerca) throws Exception;
	public MacroambitoAssistenzialeTO searchById(Long id) throws Exception;
	public MacroambitoAssistenzialeTO save(MacroambitoAssistenzialeTO macroambito) throws Exception;
}
