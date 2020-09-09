package it.sitodskij.myblueprint.business;

import it.sitodskij.myblueprint.to.CountableWrapper;
import it.sitodskij.myblueprint.to.NaturaPrestazioniTO;
import it.sitodskij.myblueprint.to.filtriricerca.FiltroRicercaPaginata;

 
public interface NaturaPrestazioniService {
	
	public CountableWrapper<NaturaPrestazioniTO> searchNaturaPrestazioni(FiltroRicercaPaginata ricercaPaginata) throws Exception;
	public NaturaPrestazioniTO searchById(Long id) throws Exception;
	 
}
