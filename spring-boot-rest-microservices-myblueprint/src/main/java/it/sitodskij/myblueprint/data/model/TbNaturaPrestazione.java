package it.sitodskij.myblueprint.data.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the TB_NATURA_PRESTAZIONE database table.
 * 
 */
@Entity
@Table(name="TB_NATURA_PRESTAZIONE")
@NamedQuery(name="TbNaturaPrestazione.findAll", query="SELECT t FROM TbNaturaPrestazione t")
public class TbNaturaPrestazione implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long ID_Natura_Prestazione;

	private String nome_Natura_Prestazione;

	//bi-directional many-to-one association to TbPrestazioniFondo
	@OneToMany(mappedBy="tbNaturaPrestazione")
	private List<TbPrestazioniFondo> tbPrestazioniFondos;

	public TbNaturaPrestazione() {
	}

	public Long getID_Natura_Prestazione() {
		return this.ID_Natura_Prestazione;
	}

	public void setID_Natura_Prestazione(Long ID_Natura_Prestazione) {
		this.ID_Natura_Prestazione = ID_Natura_Prestazione;
	}

	public String getNome_Natura_Prestazione() {
		return this.nome_Natura_Prestazione;
	}

	public void setNome_Natura_Prestazione(String nome_Natura_Prestazione) {
		this.nome_Natura_Prestazione = nome_Natura_Prestazione;
	}

	public List<TbPrestazioniFondo> getTbPrestazioniFondos() {
		return this.tbPrestazioniFondos;
	}

	public void setTbPrestazioniFondos(List<TbPrestazioniFondo> tbPrestazioniFondos) {
		this.tbPrestazioniFondos = tbPrestazioniFondos;
	}

	public TbPrestazioniFondo addTbPrestazioniFondo(TbPrestazioniFondo tbPrestazioniFondo) {
		getTbPrestazioniFondos().add(tbPrestazioniFondo);
		tbPrestazioniFondo.setTbNaturaPrestazione(this);

		return tbPrestazioniFondo;
	}

	public TbPrestazioniFondo removeTbPrestazioniFondo(TbPrestazioniFondo tbPrestazioniFondo) {
		getTbPrestazioniFondos().remove(tbPrestazioniFondo);
		tbPrestazioniFondo.setTbNaturaPrestazione(null);

		return tbPrestazioniFondo;
	}
	
	

}