package it.sitodskij.myblueprint.to.filtriricerca;

public class RicercaPrestazioniByIds extends RicercaConAnno {

	private static final long serialVersionUID = 4766033754533982078L;

	private Long idFondo;
	private Long idMacroambito;
	private Long idLivelloAssistenziale;
	private Long idTipoPrestazione;
	private Long idNaturaPrestazione;
	private String percorsoAssistenziale;
	
	public Long getIdFondo() {
		return idFondo;
	}
	public void setIdFondo(Long idFondo) {
		this.idFondo = idFondo;
	}
	public Long getIdMacroambito() {
		return idMacroambito;
	}
	public void setIdMacroambito(Long idMacroambito) {
		this.idMacroambito = idMacroambito;
	}
	public Long getIdLivelloAssistenziale() {
		return idLivelloAssistenziale;
	}
	public void setIdLivelloAssistenziale(Long idLivelloAssistenziale) {
		this.idLivelloAssistenziale = idLivelloAssistenziale;
	}
	public Long getIdNaturaPrestazione() {
		return idNaturaPrestazione;
	}
	public void setIdNaturaPrestazione(Long idNaturaPrestazione) {
		this.idNaturaPrestazione = idNaturaPrestazione;
	}
	
	
	
	public Long getIdTipoPrestazione() {
		return idTipoPrestazione;
	}
	public void setIdTipoPrestazione(Long idTipoPrestazione) {
		this.idTipoPrestazione = idTipoPrestazione;
	}
	 
	 
	

	public String getPercorsoAssistenziale() {
		return percorsoAssistenziale;
	}
	public void setPercorsoAssistenziale(String percorsoAssistenziale) {
		this.percorsoAssistenziale = percorsoAssistenziale;
	}
	
	@Override
	public String toString() {
		return "RicercaPrestazioniByIds [idFondo=" + idFondo + ", idMacroambito=" + idMacroambito
				+ ", idLivelloAssistenziale=" + idLivelloAssistenziale + ", idTipoPrestazione=" + idTipoPrestazione
				+ ", idNaturaPrestazione=" + idNaturaPrestazione + ", inseritoInPianoSanitario="
				+ ", percorsoAssistenziale=" + percorsoAssistenziale + "]";
	}
	
	
	 
	
	
	 
	
	
	
}
