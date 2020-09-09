package it.sitodskij.myblueprint.data.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the TB_RUOLI database table.
 * 
 */
@Entity
@Table(name="TB_RUOLI")
@NamedQuery(name="TbRuoli.findAll", query="SELECT t FROM TbRuoli t")
public class TbRuoli implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long ID_Ruolo;

	private String nome_Ruolo;

	//bi-directional many-to-one association to TbUtenti
	@OneToMany(mappedBy="tbRuoli")
	private List<TbUtenti> tbUtentis;

	//bi-directional many-to-many association to TbFunzionalita
	@ManyToMany(mappedBy="tbRuolis")
	private List<TbFunzionalita> tbFunzionalitas;

	public TbRuoli() {
	}

	public Long getID_Ruolo() {
		return this.ID_Ruolo;
	}

	public void setID_Ruolo(Long ID_Ruolo) {
		this.ID_Ruolo = ID_Ruolo;
	}

	public String getNome_Ruolo() {
		return this.nome_Ruolo;
	}

	public void setNome_Ruolo(String nome_Ruolo) {
		this.nome_Ruolo = nome_Ruolo;
	}

	public List<TbUtenti> getTbUtentis() {
		return this.tbUtentis;
	}

	public void setTbUtentis(List<TbUtenti> tbUtentis) {
		this.tbUtentis = tbUtentis;
	}

	public TbUtenti addTbUtenti(TbUtenti tbUtenti) {
		getTbUtentis().add(tbUtenti);
		tbUtenti.setTbRuoli(this);

		return tbUtenti;
	}

	public TbUtenti removeTbUtenti(TbUtenti tbUtenti) {
		getTbUtentis().remove(tbUtenti);
		tbUtenti.setTbRuoli(null);

		return tbUtenti;
	}

	public List<TbFunzionalita> getTbFunzionalitas() {
		return this.tbFunzionalitas;
	}

	public void setTbFunzionalitas(List<TbFunzionalita> tbFunzionalitas) {
		this.tbFunzionalitas = tbFunzionalitas;
	}

}