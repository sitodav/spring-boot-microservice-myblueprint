package it.sitodskij.myblueprint.to;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class LivelloAssistenzialeTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3542206118705467973L;
	
	  
	private Long id;
	private String nomeLivelloAssistenziale;
	private MacroambitoAssistenzialeTO macroambito;
	private Long idMacroambito;
	
	private List<TipoPrestazioneTO> tipiPrestazioni=new ArrayList<>();
	
	public LivelloAssistenzialeTO() {}
	public LivelloAssistenzialeTO(Long id, String nomeLivelloAssistenziale)
	{
		this.id = id;
		this.nomeLivelloAssistenziale = nomeLivelloAssistenziale;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNomeLivelloAssistenziale() {
		return nomeLivelloAssistenziale;
	}
	public void setNomeLivelloAssistenziale(String nomeLivelloAssistenziale) {
		this.nomeLivelloAssistenziale = nomeLivelloAssistenziale;
	}
	public List<TipoPrestazioneTO> getTipiPrestazioni() {
		return tipiPrestazioni;
	}
	public void setTipiPrestazioni(List<TipoPrestazioneTO> tipiPrestazioni) {
		this.tipiPrestazioni = tipiPrestazioni;
	}
	public MacroambitoAssistenzialeTO getMacroambito() {
		return macroambito;
	}
	public void setMacroambito(MacroambitoAssistenzialeTO macroambito) {
		this.macroambito = macroambito;
	}
	public Long getIdMacroambito() {
		return idMacroambito;
	}
	public void setIdMacroambito(Long idMacroambito) {
		this.idMacroambito = idMacroambito;
	}
	
	 
	 
	 
	

}
