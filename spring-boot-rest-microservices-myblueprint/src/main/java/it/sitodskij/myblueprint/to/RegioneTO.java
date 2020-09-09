package it.sitodskij.myblueprint.to;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;



public class RegioneTO implements Serializable{
	

	 
	/**
	 * 
	 */
	private static final long serialVersionUID = 4539983997163942634L;
	
	private Long id;
	private String nomeRegione;
	private List<FondoSanitarioTO> fondi=new ArrayList<>();;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNomeRegione() {
		return nomeRegione;
	}
	public void setNomeRegione(String nomeRegione) {
		this.nomeRegione = nomeRegione;
	}
	public List<FondoSanitarioTO> getFondi() {
		return fondi;
	}
	public void setFondi(List<FondoSanitarioTO> fondi) {
		this.fondi = fondi;
	}

	 


	
}
