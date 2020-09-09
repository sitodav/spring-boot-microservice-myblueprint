package it.sitodskij.myblueprint.data.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the TB_REGIONI database table.
 * 
 */
@Entity
@Table(name="TB_REGIONI")
@NamedQuery(name="TbRegioni.findAll", query="SELECT t FROM TbRegioni t")
public class TbRegioni implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long ID_Regione;

	private String nome_Regione;

	//bi-directional many-to-one association to TbFondi
	@OneToMany(mappedBy="tbRegioni")
	private List<TbFondi> tbFondis;

	public TbRegioni() {
	}

	public Long getID_Regione() {
		return this.ID_Regione;
	}

	public void setID_Regione(Long ID_Regione) {
		this.ID_Regione = ID_Regione;
	}

	public String getNome_Regione() {
		return this.nome_Regione;
	}

	public void setNome_Regione(String nome_Regione) {
		this.nome_Regione = nome_Regione;
	}

	public List<TbFondi> getTbFondis() {
		return this.tbFondis;
	}

	public void setTbFondis(List<TbFondi> tbFondis) {
		this.tbFondis = tbFondis;
	}

	public TbFondi addTbFondi(TbFondi tbFondi) {
		getTbFondis().add(tbFondi);
		tbFondi.setTbRegioni(this);

		return tbFondi;
	}

	public TbFondi removeTbFondi(TbFondi tbFondi) {
		getTbFondis().remove(tbFondi);
		tbFondi.setTbRegioni(null);

		return tbFondi;
	}

}