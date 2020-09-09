package it.sitodskij.myblueprint.data.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the TB_UTENTI database table.
 * 
 */
@Entity
@Table(name="TB_UTENTI")
@NamedQuery(name="TbUtenti.findAll", query="SELECT t FROM TbUtenti t")
public class TbUtenti implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_UTENTE")
	private Long idUtente;

	private String cognome;

	private String disabilitato;

	private String email;

	private String nome;

	private String password;

	private String username;

	//bi-directional many-to-one association to TbRuoli
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ID_Ruolo")
	private TbRuoli tbRuoli;

	public TbUtenti() {
	}

	public Long getIdUtente() {
		return this.idUtente;
	}

	public void setIdUtente(Long idUtente) {
		this.idUtente = idUtente;
	}

	public String getCognome() {
		return this.cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getDisabilitato() {
		return this.disabilitato;
	}

	public void setDisabilitato(String disabilitato) {
		this.disabilitato = disabilitato;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public TbRuoli getTbRuoli() {
		return this.tbRuoli;
	}

	public void setTbRuoli(TbRuoli tbRuoli) {
		this.tbRuoli = tbRuoli;
	}

}