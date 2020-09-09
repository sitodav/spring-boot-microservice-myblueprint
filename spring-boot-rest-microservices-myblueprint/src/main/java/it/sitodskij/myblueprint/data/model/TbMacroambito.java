package it.sitodskij.myblueprint.data.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the TB_MACROAMBITO database table.
 * 
 */
@Entity
@Table(name="TB_MACROAMBITO")
@NamedQuery(name="TbMacroambito.findAll", query="SELECT t FROM TbMacroambito t")
public class TbMacroambito implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long ID_Macroambito;

	private String nome_Macroambito;

	//bi-directional many-to-one association to TbLivelloAssistenziale
	@OneToMany(mappedBy="tbMacroambito")
	private List<TbLivelloAssistenziale> tbLivelloAssistenziales;

	public TbMacroambito() {
	}

	public Long getID_Macroambito() {
		return this.ID_Macroambito;
	}

	public void setID_Macroambito(Long ID_Macroambito) {
		this.ID_Macroambito = ID_Macroambito;
	}

	public String getNome_Macroambito() {
		return this.nome_Macroambito;
	}

	public void setNome_Macroambito(String nome_Macroambito) {
		this.nome_Macroambito = nome_Macroambito;
	}

	public List<TbLivelloAssistenziale> getTbLivelloAssistenziales() {
		return this.tbLivelloAssistenziales;
	}

	public void setTbLivelloAssistenziales(List<TbLivelloAssistenziale> tbLivelloAssistenziales) {
		this.tbLivelloAssistenziales = tbLivelloAssistenziales;
	}

	public TbLivelloAssistenziale addTbLivelloAssistenziale(TbLivelloAssistenziale tbLivelloAssistenziale) {
		getTbLivelloAssistenziales().add(tbLivelloAssistenziale);
		tbLivelloAssistenziale.setTbMacroambito(this);

		return tbLivelloAssistenziale;
	}

	public TbLivelloAssistenziale removeTbLivelloAssistenziale(TbLivelloAssistenziale tbLivelloAssistenziale) {
		getTbLivelloAssistenziales().remove(tbLivelloAssistenziale);
		tbLivelloAssistenziale.setTbMacroambito(null);

		return tbLivelloAssistenziale;
	}

}