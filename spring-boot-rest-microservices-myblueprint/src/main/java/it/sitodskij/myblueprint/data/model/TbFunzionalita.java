package it.sitodskij.myblueprint.data.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the TB_FUNZIONALITA database table.
 * 
 */
@Entity
@Table(name="TB_FUNZIONALITA")
@NamedQuery(name="TbFunzionalita.findAll", query="SELECT t FROM TbFunzionalita t")
public class TbFunzionalita implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long ID_Funzionalita;

	@Column(name="Default")
	private byte default_;

	private String descrizione_Funzionalita;

	private String nome_Funzionalita;

	private String tipo_Funzionalita;

	//bi-directional many-to-many association to TbRuoli
	@ManyToMany
	@JoinTable(
		name="`TB_RUOLO-FUNZIONALITA`"
		, joinColumns={
			@JoinColumn(name="ID_Funzionalita")
			}
		, inverseJoinColumns={
			@JoinColumn(name="ID_Ruolo")
			}
		)
	private List<TbRuoli> tbRuolis;

	public TbFunzionalita() {
	}

	public Long getID_Funzionalita() {
		return this.ID_Funzionalita;
	}

	public void setID_Funzionalita(Long ID_Funzionalita) {
		this.ID_Funzionalita = ID_Funzionalita;
	}

	public byte getDefault_() {
		return this.default_;
	}

	public void setDefault_(byte default_) {
		this.default_ = default_;
	}

	public String getDescrizione_Funzionalita() {
		return this.descrizione_Funzionalita;
	}

	public void setDescrizione_Funzionalita(String descrizione_Funzionalita) {
		this.descrizione_Funzionalita = descrizione_Funzionalita;
	}

	public String getNome_Funzionalita() {
		return this.nome_Funzionalita;
	}

	public void setNome_Funzionalita(String nome_Funzionalita) {
		this.nome_Funzionalita = nome_Funzionalita;
	}

	public String getTipo_Funzionalita() {
		return this.tipo_Funzionalita;
	}

	public void setTipo_Funzionalita(String tipo_Funzionalita) {
		this.tipo_Funzionalita = tipo_Funzionalita;
	}

	public List<TbRuoli> getTbRuolis() {
		return this.tbRuolis;
	}

	public void setTbRuolis(List<TbRuoli> tbRuolis) {
		this.tbRuolis = tbRuolis;
	}

}