package it.sitodskij.myblueprint.data.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the TB_LIVELLO_ASSISTENZIALE database table.
 * 
 */
@Entity
@Table(name="TB_LIVELLO_ASSISTENZIALE")
@NamedQuery(name="TbLivelloAssistenziale.findAll", query="SELECT t FROM TbLivelloAssistenziale t")
public class TbLivelloAssistenziale implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long ID_Livello_Assistenziale;

	private String nome_LivelloAssistenziale;

	//bi-directional many-to-one association to TbMacroambito
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ID_Macroambito")
	private TbMacroambito tbMacroambito;

	//bi-directional many-to-one association to TbPrestazioniFondo
	@OneToMany(mappedBy="tbLivelloAssistenziale")
	private List<TbTipoPrestazione> tbTipoPrestaziones;

	public TbLivelloAssistenziale() {
	}

	public Long getID_Livello_Assistenziale() {
		return ID_Livello_Assistenziale;
	}

	public void setID_Livello_Assistenziale(Long iD_Livello_Assistenziale) {
		ID_Livello_Assistenziale = iD_Livello_Assistenziale;
	}

	public String getNome_LivelloAssistenziale() {
		return nome_LivelloAssistenziale;
	}

	public void setNome_LivelloAssistenziale(String nome_LivelloAssistenziale) {
		this.nome_LivelloAssistenziale = nome_LivelloAssistenziale;
	}

	public TbMacroambito getTbMacroambito() {
		return tbMacroambito;
	}

	public void setTbMacroambito(TbMacroambito tbMacroambito) {
		this.tbMacroambito = tbMacroambito;
	}

	public List<TbTipoPrestazione> getTbTipoPrestaziones() {
		return tbTipoPrestaziones;
	}

	public void setTbTipoPrestaziones(List<TbTipoPrestazione> tbTipoPrestaziones) {
		this.tbTipoPrestaziones = tbTipoPrestaziones;
	}

	 

}