package it.sitodskij.myblueprint.to.filtriricerca;

public class RicercaPrestazioniByDescs extends RicercaConAnno  {

	private static final long serialVersionUID = 4766033754533982078L;

	private Long idMacroambito;
	private Long idLivelloAssistenziale;
	private Long idNaturaPrestazione;
	private String percorsoAssistenziale;
	private String nomeFondo;
	private String nomeTipoPrestazione;
	
	
	
	public String getNomeFondo() {
		return nomeFondo;
	}
	public void setNomeFondo(String nomeFondo) {
		this.nomeFondo = nomeFondo;
	}
	public String getNomeTipoPrestazione() {
		return nomeTipoPrestazione;
	}
	public void setNomeTipoPrestazione(String nomeTipoPrestazione) {
		this.nomeTipoPrestazione = nomeTipoPrestazione;
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
	public String getPercorsoAssistenziale() {
		return percorsoAssistenziale;
	}
	public void setPercorsoAssistenziale(String percorsoAssistenziale) {
		this.percorsoAssistenziale = percorsoAssistenziale;
	}
	@Override
	public String toString() {
		return "RicercaPrestazioniByDescs [idMacroambito=" + idMacroambito + ", idLivelloAssistenziale="
				+ idLivelloAssistenziale + ", idNaturaPrestazione=" + idNaturaPrestazione + ", percorsoAssistenziale="
				+ percorsoAssistenziale + ", nomeFondo=" + nomeFondo + ", nomeTipoPrestazione=" + nomeTipoPrestazione
				+ "]";
	}
	 
	
	
	
	
	
	 
	
	 
	 
	
	 
	
	
	 
	
	
	
}
