package it.sitodskij.myblueprint.to;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MacroambitoAssistenzialeTO implements Serializable{

	 
	private static final long serialVersionUID = 3542206118705467973L;
	 
	private Long id;
	private String nomeMacroambito;
	private List<LivelloAssistenzialeTO> livelliAssistenziali=new ArrayList<>();;
	
	public MacroambitoAssistenzialeTO() {}
	
	public MacroambitoAssistenzialeTO(Long id, String nomeMacroambito) {
		super();
		this.id = id;
		this.nomeMacroambito = nomeMacroambito;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNomeMacroambito() {
		return nomeMacroambito;
	}
	public void setNomeMacroambito(String nomeMacroambito) {
		this.nomeMacroambito = nomeMacroambito;
	}
	public List<LivelloAssistenzialeTO> getLivelliAssistenziali() {
		return livelliAssistenziali;
	}
	public void setLivelliAssistenziali(List<LivelloAssistenzialeTO> livelliAssistenziali) {
		this.livelliAssistenziali = livelliAssistenziali;
	}
	 

	
	
	
	 



}
