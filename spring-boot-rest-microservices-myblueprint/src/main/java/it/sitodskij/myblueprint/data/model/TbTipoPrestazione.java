package it.sitodskij.myblueprint.data.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the TB_NATURA_PRESTAZIONE database table.
 * 
 */
@Entity
@Table(name = "TB_TIPO_PRESTAZIONE")
@NamedQuery(name = "TbTipoPrestazione.findAll", query = "SELECT t FROM TbTipoPrestazione t")
public class TbTipoPrestazione implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ID_Tipo_Prestazione;

	private String nome_Tipo_Prestazione;

	// bi-directional many-to-one association to TbLivelloAssistenziale
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_Livello_Assistenziale")
	private TbLivelloAssistenziale tbLivelloAssistenziale;
	
	@OneToMany(mappedBy="tbTipoPrestazione")
	private List<TbPrestazioniFondo> tbPrestazioniFondos;


	public Long getID_Tipo_Prestazione() {
		return ID_Tipo_Prestazione;
	}

	public void setID_Tipo_Prestazione(Long iD_Tipo_Prestazione) {
		ID_Tipo_Prestazione = iD_Tipo_Prestazione;
	}

	public String getNome_Tipo_Prestazione() {
		return nome_Tipo_Prestazione;
	}

	public void setNome_Tipo_Prestazione(String nome_Tipo_Prestazione) {
		this.nome_Tipo_Prestazione = nome_Tipo_Prestazione;
	}

	public TbLivelloAssistenziale getTbLivelloAssistenziale() {
		return tbLivelloAssistenziale;
	}

	public void setTbLivelloAssistenziale(TbLivelloAssistenziale tbLivelloAssistenziale) {
		this.tbLivelloAssistenziale = tbLivelloAssistenziale;
	}

	public List<TbPrestazioniFondo> getTbPrestazioniFondos() {
		return tbPrestazioniFondos;
	}

	public void setTbPrestazioniFondos(List<TbPrestazioniFondo> tbPrestazioniFondos) {
		this.tbPrestazioniFondos = tbPrestazioniFondos;
	}

	 
	
	
	
}