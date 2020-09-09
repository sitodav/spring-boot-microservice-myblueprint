package it.sitodskij.myblueprint.to;

import java.io.Serializable;

public class NaturaPrestazioniTO implements Serializable {

	private static final long serialVersionUID = 2911817106661383434L;
	
	private Long id;
	private String nomeNaturaPrestazione;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeNaturaPrestazione() {
		return nomeNaturaPrestazione;
	}

	public void setNomeNaturaPrestazione(String nomeNaturaPrestazione) {
		this.nomeNaturaPrestazione = nomeNaturaPrestazione;
	}

}
