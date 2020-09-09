package it.sitodskij.myblueprint.to;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FondoSanitarioTO implements Serializable {

	private static final long serialVersionUID = -5793980464780020081L;

	private Long id;

	private Long annoAdesione;

	private String autogestito;

	private Long capSedeLegale;

	private String codiceFiscaleFondo;

	private String compilatore;

	private String comuneSedeLegale;

	private String denominazioneFondo;

	private String email;

	private String indirizzoSedeLegale;

	private Long telefono;
	
	private String tipoFondo;
	
	private List<PrestazioneTO> prestazioni = new ArrayList<>();
	
	private RegioneTO regione;
	private Long idRegione;
	
	private Long prestazAnnueGarantite;
	
	private Long numeroIscritti;
	
	private Long totPrestazioni;
	

	public FondoSanitarioTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAnnoAdesione() {
		return annoAdesione;
	}

	public void setAnnoAdesione(Long annoAdesione) {
		this.annoAdesione = annoAdesione;
	}

	public String getAutogestito() {
		return autogestito;
	}

	public void setAutogestito(String autogestito) {
		this.autogestito = autogestito;
	}

	public Long getCapSedeLegale() {
		return capSedeLegale;
	}

	public void setCapSedeLegale(Long capSedeLegale) {
		this.capSedeLegale = capSedeLegale;
	}

	public String getCodiceFiscaleFondo() {
		return codiceFiscaleFondo;
	}

	public void setCodiceFiscaleFondo(String codiceFiscaleFondo) {
		this.codiceFiscaleFondo = codiceFiscaleFondo;
	}

	public String getCompilatore() {
		return compilatore;
	}

	public void setCompilatore(String compilatore) {
		this.compilatore = compilatore;
	}

	public String getComuneSedeLegale() {
		return comuneSedeLegale;
	}

	public void setComuneSedeLegale(String comuneSedeLegale) {
		this.comuneSedeLegale = comuneSedeLegale;
	}

	public String getDenominazioneFondo() {
		return denominazioneFondo;
	}

	public void setDenominazioneFondo(String denominazioneFondo) {
		this.denominazioneFondo = denominazioneFondo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIndirizzoSedeLegale() {
		return indirizzoSedeLegale;
	}

	public void setIndirizzoSedeLegale(String indirizzoSedeLegale) {
		this.indirizzoSedeLegale = indirizzoSedeLegale;
	}

	public Long getTelefono() {
		return telefono;
	}

	public void setTelefono(Long telefono) {
		this.telefono = telefono;
	}

	public List<PrestazioneTO> getPrestazioni() {
		return prestazioni;
	}

	public void setPrestazioni(List<PrestazioneTO> prestazioni) {
		this.prestazioni = prestazioni;
	}

	public RegioneTO getRegione() {
		return regione;
	}

	public void setRegione(RegioneTO regione) {
		this.regione = regione;
	}

	public Long getPrestazAnnueGarantite() {
		return prestazAnnueGarantite;
	}

	public void setPrestazAnnueGarantite(Long prestazAnnueGarantite) {
		this.prestazAnnueGarantite = prestazAnnueGarantite;
	}

	public Long getNumeroIscritti() {
		return numeroIscritti;
	}

	public void setNumeroIscritti(Long numeroIscritti) {
		this.numeroIscritti = numeroIscritti;
	}

	public Long getTotPrestazioni() {
		return totPrestazioni;
	}

	public void setTotPrestazioni(Long totPrestazioni) {
		this.totPrestazioni = totPrestazioni;
	}

	public Long getIdRegione() {
		return idRegione;
	}

	public void setIdRegione(Long idRegione) {
		this.idRegione = idRegione;
	}

	public String getTipoFondo() {
		return tipoFondo;
	}

	public void setTipoFondo(String tipoFondo) {
		this.tipoFondo = tipoFondo;
	}

	
	
}
