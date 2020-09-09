package it.sitodskij.myblueprint.to;

import java.io.Serializable;

public class FunzionalitaTO implements Serializable {

	private static final long serialVersionUID = -5228003677925029128L;
	
	private Long id;
	private String tipo;
	private String descrizione;
	private String nomeFunzionalita;
	private String predefinita;
 
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public String getNomeFunzionalita() {
		return nomeFunzionalita;
	}
	public void setNomeFunzionalita(String nomeFunzionalita) {
		this.nomeFunzionalita = nomeFunzionalita;
	}
	public String getPredefinita() {
		return predefinita;
	}
	public void setPredefinita(String predefinita) {
		this.predefinita = predefinita;
	}
	 
	
	

}
