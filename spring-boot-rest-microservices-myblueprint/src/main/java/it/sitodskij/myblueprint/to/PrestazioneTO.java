package it.sitodskij.myblueprint.to;

import java.io.Serializable;

public class PrestazioneTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4654925965218535578L;

	private Long id;
	private int anno;
	private String descrizionePrestazione;
	private String note;
	private int numPrestRimbIntero;
	private int numPrestRimbTicket;
	private int prestazioniAnnue;
	private double quantitaTotaliErogate;
	private double valPrestRimbIntero;
	private double valPrestRimbTicket;
	private double valoreComplessivo;
	private String percorsoAssistenziale;
	private String descrizionePercorso;
	
	private NaturaPrestazioniTO naturaPrestazione;
	private Long idNaturaPrestazione;
	
	private FondoSanitarioTO fondo;
	private Long idFondo;
	
	private TipoPrestazioneTO tipoPrestazione;
	private Long idTipoPrestazione;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getAnno() {
		return anno;
	}
	public void setAnno(int anno) {
		this.anno = anno;
	}
	public String getDescrizionePrestazione() {
		return descrizionePrestazione;
	}
	public void setDescrizionePrestazione(String descrizionePrestazione) {
		this.descrizionePrestazione = descrizionePrestazione;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public int getNumPrestRimbIntero() {
		return numPrestRimbIntero;
	}
	public void setNumPrestRimbIntero(int numPrestRimbIntero) {
		this.numPrestRimbIntero = numPrestRimbIntero;
	}
	public int getNumPrestRimbTicket() {
		return numPrestRimbTicket;
	}
	public void setNumPrestRimbTicket(int numPrestRimbTicket) {
		this.numPrestRimbTicket = numPrestRimbTicket;
	}
	public int getPrestazioniAnnue() {
		return prestazioniAnnue;
	}
	public void setPrestazioniAnnue(int prestazioniAnnue) {
		this.prestazioniAnnue = prestazioniAnnue;
	}
	public double getQuantitaTotaliErogate() {
		return quantitaTotaliErogate;
	}
	public void setQuantitaTotaliErogate(double quantitaTotaliErogate) {
		this.quantitaTotaliErogate = quantitaTotaliErogate;
	}
	public double getValPrestRimbIntero() {
		return valPrestRimbIntero;
	}
	public void setValPrestRimbIntero(double valPrestRimbIntero) {
		this.valPrestRimbIntero = valPrestRimbIntero;
	}
	public double getValPrestRimbTicket() {
		return valPrestRimbTicket;
	}
	public void setValPrestRimbTicket(double valPrestRimbTicket) {
		this.valPrestRimbTicket = valPrestRimbTicket;
	}
	public double getValoreComplessivo() {
		return valoreComplessivo;
	}
	public void setValoreComplessivo(double valoreComplessivo) {
		this.valoreComplessivo = valoreComplessivo;
	}
	public NaturaPrestazioniTO getNaturaPrestazione() {
		return naturaPrestazione;
	}
	public void setNaturaPrestazione(NaturaPrestazioniTO naturaPrestazione) {
		this.naturaPrestazione = naturaPrestazione;
	}
	public FondoSanitarioTO getFondo() {
		return fondo;
	}
	public void setFondo(FondoSanitarioTO fondo) {
		this.fondo = fondo;
	}
	public TipoPrestazioneTO getTipoPrestazione() {
		return tipoPrestazione;
	}
	public void setTipoPrestazione(TipoPrestazioneTO tipoPrestazione) {
		this.tipoPrestazione = tipoPrestazione;
	}
	public String getPercorsoAssistenziale() {
		return percorsoAssistenziale;
	}
	public void setPercorsoAssistenziale(String percorsoAssistenziale) {
		this.percorsoAssistenziale = percorsoAssistenziale;
	}
	public String getDescrizionePercorso() {
		return descrizionePercorso;
	}
	public void setDescrizionePercorso(String descrizionePercorso) {
		this.descrizionePercorso = descrizionePercorso;
	}
	public Long getIdNaturaPrestazione() {
		return idNaturaPrestazione;
	}
	public void setIdNaturaPrestazione(Long idNaturaPrestazione) {
		this.idNaturaPrestazione = idNaturaPrestazione;
	}
	public Long getIdFondo() {
		return idFondo;
	}
	public void setIdFondo(Long idFondo) {
		this.idFondo = idFondo;
	}
	public Long getIdTipoPrestazione() {
		return idTipoPrestazione;
	}
	public void setIdTipoPrestazione(Long idTipoPrestazione) {
		this.idTipoPrestazione = idTipoPrestazione;
	}
	
	 
		
	
}
