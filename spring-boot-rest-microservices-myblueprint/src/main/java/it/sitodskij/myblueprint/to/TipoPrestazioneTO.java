package it.sitodskij.myblueprint.to;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TipoPrestazioneTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1175830300711415774L;

	private Long id;

	private String nomeTipoPrestazione;
	private LivelloAssistenzialeTO livelloAssistenziale;
	private Long idLivelloAssistenziale;
	
	private List<PrestazioneTO> prestazioni=new ArrayList<>();;
	
	public TipoPrestazioneTO() {}
	public TipoPrestazioneTO(Long id, String nomeTipoPrestazione)
	{
		this.id = id;
		this.nomeTipoPrestazione = nomeTipoPrestazione;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNomeTipoPrestazione() {
		return nomeTipoPrestazione;
	}
	public void setNomeTipoPrestazione(String nomeTipoPrestazione) {
		this.nomeTipoPrestazione = nomeTipoPrestazione;
	}
	public LivelloAssistenzialeTO getLivelloAssistenziale() {
		return livelloAssistenziale;
	}
	public void setLivelloAssistenziale(LivelloAssistenzialeTO livelloAssistenziale) {
		this.livelloAssistenziale = livelloAssistenziale;
	}
	public List<PrestazioneTO> getPrestazioni() {
		return prestazioni;
	}
	public void setPrestazioni(List<PrestazioneTO> prestazioni) {
		this.prestazioni = prestazioni;
	}
	public Long getIdLivelloAssistenziale() {
		return idLivelloAssistenziale;
	}
	public void setIdLivelloAssistenziale(Long idLivelloAssistenziale) {
		this.idLivelloAssistenziale = idLivelloAssistenziale;
	}
	 
	
		
	
}
