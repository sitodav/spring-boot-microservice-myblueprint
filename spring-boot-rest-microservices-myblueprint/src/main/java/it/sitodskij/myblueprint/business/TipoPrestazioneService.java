package it.sitodskij.myblueprint.business;

import it.sitodskij.myblueprint.to.CountableWrapper;
import it.sitodskij.myblueprint.to.TipoPrestazioneTO;
import it.sitodskij.myblueprint.to.filtriricerca.RicercaConAnno;

public interface TipoPrestazioneService {
	public CountableWrapper<TipoPrestazioneTO> searchTipoPrestazione(RicercaConAnno filtroRicerca, Long idLivelloAssistenz) throws Exception;
	public TipoPrestazioneTO searchById(Long id) throws Exception;
	public TipoPrestazioneTO save(TipoPrestazioneTO tipoPrestazTO) throws Exception;

}
