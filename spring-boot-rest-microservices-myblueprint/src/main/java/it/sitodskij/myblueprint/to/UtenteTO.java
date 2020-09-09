package it.sitodskij.myblueprint.to;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;



public class UtenteTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6342948401069444430L;
	private Long id;
	private String cognome;
	private String email;
	private String nome;
	private String disabilitato;
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	private String username;
	private RuoloTO ruolo;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDisabilitato() {
		return disabilitato;
	}
	public void setDisabilitato(String disabilitato) {
		this.disabilitato = disabilitato;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public RuoloTO getRuolo() {
		return ruolo;
	}
	public void setRuolo(RuoloTO ruolo) {
		this.ruolo = ruolo;
	}
	 
	 
	
	
	

 
}
