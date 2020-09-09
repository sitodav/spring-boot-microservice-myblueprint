package it.sitodskij.myblueprint.data.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the TB_PRESTAZIONI_FONDO database table.
 * 
 */
@Entity
@Table(name="TB_PRESTAZIONI_FONDO")
@NamedQuery(name="TbPrestazioniFondo.findAll", query="SELECT t FROM TbPrestazioniFondo t")
public class TbPrestazioniFondo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long ID_Prestazione_Fondo;

	private Long anno;

	private String descrizione_Prestazione;

	
	private String note;

	private int num_Prest_Rimb_Intero;

	private int num_Prest_Rimb_Ticket;

	private int prestazioni_Annue;

	private double quantita_Totali_Erogate;

	private double val_Prest_Rimb_Intero;

	private double val_Prest_Rimb_Ticket;

	private double valore_Complessivo;
	
	private String percorso_Assistenziale;
	

	private String descrizione_Percorso;

	//bi-directional many-to-one association to TbFondi
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ID_Fondo")
	private TbFondi tbFondi;

	 
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ID_Tipo_Prestazione")
	private TbTipoPrestazione tbTipoPrestazione;

	 
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ID_Natura_Prestazione")
	private TbNaturaPrestazione tbNaturaPrestazione;

	public TbPrestazioniFondo() {
	}

	public Long getID_Prestazione_Fondo() {
		return ID_Prestazione_Fondo;
	}

	public void setID_Prestazione_Fondo(Long iD_Prestazione_Fondo) {
		ID_Prestazione_Fondo = iD_Prestazione_Fondo;
	}

	public Long getAnno() {
		return anno;
	}

	public void setAnno(Long anno) {
		this.anno = anno;
	}

	public String getDescrizione_Prestazione() {
		return descrizione_Prestazione;
	}

	public void setDescrizione_Prestazione(String descrizione_Prestazione) {
		this.descrizione_Prestazione = descrizione_Prestazione;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public int getNum_Prest_Rimb_Intero() {
		return num_Prest_Rimb_Intero;
	}

	public void setNum_Prest_Rimb_Intero(int num_Prest_Rimb_Intero) {
		this.num_Prest_Rimb_Intero = num_Prest_Rimb_Intero;
	}

	public int getNum_Prest_Rimb_Ticket() {
		return num_Prest_Rimb_Ticket;
	}

	public void setNum_Prest_Rimb_Ticket(int num_Prest_Rimb_Ticket) {
		this.num_Prest_Rimb_Ticket = num_Prest_Rimb_Ticket;
	}

	public int getPrestazioni_Annue() {
		return prestazioni_Annue;
	}

	public void setPrestazioni_Annue(int prestazioni_Annue) {
		this.prestazioni_Annue = prestazioni_Annue;
	}

	public double getQuantita_Totali_Erogate() {
		return quantita_Totali_Erogate;
	}

	public void setQuantita_Totali_Erogate(double quantita_Totali_Erogate) {
		this.quantita_Totali_Erogate = quantita_Totali_Erogate;
	}

	public double getVal_Prest_Rimb_Intero() {
		return val_Prest_Rimb_Intero;
	}

	public void setVal_Prest_Rimb_Intero(double val_Prest_Rimb_Intero) {
		this.val_Prest_Rimb_Intero = val_Prest_Rimb_Intero;
	}

	public double getVal_Prest_Rimb_Ticket() {
		return val_Prest_Rimb_Ticket;
	}

	public void setVal_Prest_Rimb_Ticket(double val_Prest_Rimb_Ticket) {
		this.val_Prest_Rimb_Ticket = val_Prest_Rimb_Ticket;
	}

	public double getValore_Complessivo() {
		return valore_Complessivo;
	}

	public void setValore_Complessivo(double valore_Complessivo) {
		this.valore_Complessivo = valore_Complessivo;
	}

	public TbFondi getTbFondi() {
		return tbFondi;
	}

	public void setTbFondi(TbFondi tbFondi) {
		this.tbFondi = tbFondi;
	}

	public TbTipoPrestazione getTbTipoPrestazione() {
		return tbTipoPrestazione;
	}

	public void setTbTipoPrestazione(TbTipoPrestazione tbTipoPrestazione) {
		this.tbTipoPrestazione = tbTipoPrestazione;
	}

	public TbNaturaPrestazione getTbNaturaPrestazione() {
		return tbNaturaPrestazione;
	}

	public void setTbNaturaPrestazione(TbNaturaPrestazione tbNaturaPrestazione) {
		this.tbNaturaPrestazione = tbNaturaPrestazione;
	}

	public String getPercorso_Assistenziale() {
		return percorso_Assistenziale;
	}

	public void setPercorso_Assistenziale(String percorso_Assistenziale) {
		this.percorso_Assistenziale = percorso_Assistenziale;
	}

	public String getDescrizione_Percorso() {
		return descrizione_Percorso;
	}

	public void setDescrizione_Percorso(String descrizione_Percorso) {
		this.descrizione_Percorso = descrizione_Percorso;
	}
	
	
	

}