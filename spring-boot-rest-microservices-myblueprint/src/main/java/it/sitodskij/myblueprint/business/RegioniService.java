package it.sitodskij.myblueprint.business;

import it.sitodskij.myblueprint.to.CountableWrapper;
import it.sitodskij.myblueprint.to.RegioneTO;

 
public interface RegioniService {
	
	public CountableWrapper<RegioneTO> getAllRegioni(Boolean deepSearch) throws Exception;
	public RegioneTO findById(Long id, Boolean deepSearch) throws Exception;
	
}
