package it.sitodskij.myblueprint.to;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class RuoloTO implements Serializable {


	private static final long serialVersionUID = -5528171781636804592L;
	
	private Long id;
	private String nomeRuolo;
	private List<FunzionalitaTO> funzionalita=new ArrayList<>();; 

	public RuoloTO() {
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNomeRuolo() {
		return nomeRuolo;
	}


	public void setNomeRuolo(String nomeRuolo) {
		this.nomeRuolo = nomeRuolo;
	}


	public List<FunzionalitaTO> getFunzionalita() {
		return funzionalita;
	}


	public void setFunzionalita(List<FunzionalitaTO> funzionalita) {
		this.funzionalita = funzionalita;
	}
 
	
  


}
