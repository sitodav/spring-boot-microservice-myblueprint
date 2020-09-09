package it.sitodskij.myblueprint.data.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the TB_ASSISTITI_FONDO database table.
 * 
 */
@Entity
@Table(name = "TB_ASSISTITI_FONDO")
@NamedQuery(name = "TbAssistitiFondo.findAll", query = "SELECT t FROM TbAssistitiFondo t")
public class TbAssistitiFondo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ID_Assistito;

	private String note;

	@Column(name = "`Num_Aderenti_16-24`")
	private Long num_Aderenti_16_24;

	@Column(name = "`Num_Aderenti_25-40`")
	private Long num_Aderenti_25_40;

	@Column(name = "`Num_Aderenti_41-99`")
	private Long num_Aderenti_41_99;

	private Long num_Aderenti_CCNL;

	private Long num_Aderenti_CCNL_DET;

	private Long num_Aderenti_CCNL_INDT;

	private Long num_Aderenti_Contr_Individuale;

	private Long num_Aderenti_Contr_Individuale_Det;

	private Long num_Aderenti_Contr_Individuale_Ind;

	private Long num_Aderenti_Contratto_Lavoro;

	private Long num_Aderenti_Termine_Copertura_Anno;

	private Long num_Aderenti_Termine_Copertura_Oltre_Anno;

	private Long num_Aderenti_Termine_Copertura_Tutti;

	private Long num_Aderenti_Volontari;

	private Long num_Beneficiari_Familiari_Facenti_Parte;
	

	@Column(name="`Num_Beneficiari_Familiari_0-15`")
	private Long num_Beneficiari_Familiari_0_15;

	@Column(name="`Num_Beneficiari_Familiari_16-24`")
	private Long num_Beneficiari_Familiari_16_24;
	
	@Column(name="`Num_Beneficiari_Familiari_25-40`")
	private Long num_Beneficiari_Familiari_25_40;
	
	@Column(name="`Num_Beneficiari_Familiari_41-99`")
	private Long num_Beneficiari_Familiari_41_99;

	private Long totale_Aderenti_Fondo;

	private Long totale_Assistiti;

	// bi-directional many-to-one association to TbFondi
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_Fondo")
	private TbFondi tbFondi;

	public TbAssistitiFondo() {
	}

	public Long getID_Assistito() {
		return ID_Assistito;
	}

	public void setID_Assistito(Long iD_Assistito) {
		ID_Assistito = iD_Assistito;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Long getNum_Aderenti_16_24() {
		return num_Aderenti_16_24;
	}

	public void setNum_Aderenti_16_24(Long num_Aderenti_16_24) {
		this.num_Aderenti_16_24 = num_Aderenti_16_24;
	}

	public Long getNum_Aderenti_25_40() {
		return num_Aderenti_25_40;
	}

	public void setNum_Aderenti_25_40(Long num_Aderenti_25_40) {
		this.num_Aderenti_25_40 = num_Aderenti_25_40;
	}

	public Long getNum_Aderenti_41_99() {
		return num_Aderenti_41_99;
	}

	public void setNum_Aderenti_41_99(Long num_Aderenti_41_99) {
		this.num_Aderenti_41_99 = num_Aderenti_41_99;
	}

	public Long getNum_Aderenti_CCNL() {
		return num_Aderenti_CCNL;
	}

	public void setNum_Aderenti_CCNL(Long num_Aderenti_CCNL) {
		this.num_Aderenti_CCNL = num_Aderenti_CCNL;
	}

	public Long getNum_Aderenti_CCNL_DET() {
		return num_Aderenti_CCNL_DET;
	}

	public void setNum_Aderenti_CCNL_DET(Long num_Aderenti_CCNL_DET) {
		this.num_Aderenti_CCNL_DET = num_Aderenti_CCNL_DET;
	}

	public Long getNum_Aderenti_CCNL_INDT() {
		return num_Aderenti_CCNL_INDT;
	}

	public void setNum_Aderenti_CCNL_INDT(Long num_Aderenti_CCNL_INDT) {
		this.num_Aderenti_CCNL_INDT = num_Aderenti_CCNL_INDT;
	}

	public Long getNum_Aderenti_Contr_Individuale() {
		return num_Aderenti_Contr_Individuale;
	}

	public void setNum_Aderenti_Contr_Individuale(Long num_Aderenti_Contr_Individuale) {
		this.num_Aderenti_Contr_Individuale = num_Aderenti_Contr_Individuale;
	}

	public Long getNum_Aderenti_Contr_Individuale_Det() {
		return num_Aderenti_Contr_Individuale_Det;
	}

	public void setNum_Aderenti_Contr_Individuale_Det(Long num_Aderenti_Contr_Individuale_Det) {
		this.num_Aderenti_Contr_Individuale_Det = num_Aderenti_Contr_Individuale_Det;
	}

	public Long getNum_Aderenti_Contr_Individuale_Ind() {
		return num_Aderenti_Contr_Individuale_Ind;
	}

	public void setNum_Aderenti_Contr_Individuale_Ind(Long num_Aderenti_Contr_Individuale_Ind) {
		this.num_Aderenti_Contr_Individuale_Ind = num_Aderenti_Contr_Individuale_Ind;
	}

	public Long getNum_Aderenti_Contratto_Lavoro() {
		return num_Aderenti_Contratto_Lavoro;
	}

	public void setNum_Aderenti_Contratto_Lavoro(Long num_Aderenti_Contratto_Lavoro) {
		this.num_Aderenti_Contratto_Lavoro = num_Aderenti_Contratto_Lavoro;
	}

	public Long getNum_Aderenti_Termine_Copertura_Anno() {
		return num_Aderenti_Termine_Copertura_Anno;
	}

	public void setNum_Aderenti_Termine_Copertura_Anno(Long num_Aderenti_Termine_Copertura_Anno) {
		this.num_Aderenti_Termine_Copertura_Anno = num_Aderenti_Termine_Copertura_Anno;
	}

	public Long getNum_Aderenti_Termine_Copertura_Oltre_Anno() {
		return num_Aderenti_Termine_Copertura_Oltre_Anno;
	}

	public void setNum_Aderenti_Termine_Copertura_Oltre_Anno(Long num_Aderenti_Termine_Copertura_Oltre_Anno) {
		this.num_Aderenti_Termine_Copertura_Oltre_Anno = num_Aderenti_Termine_Copertura_Oltre_Anno;
	}

	public Long getNum_Aderenti_Termine_Copertura_Tutti() {
		return num_Aderenti_Termine_Copertura_Tutti;
	}

	public void setNum_Aderenti_Termine_Copertura_Tutti(Long num_Aderenti_Termine_Copertura_Tutti) {
		this.num_Aderenti_Termine_Copertura_Tutti = num_Aderenti_Termine_Copertura_Tutti;
	}

	public Long getNum_Aderenti_Volontari() {
		return num_Aderenti_Volontari;
	}

	public void setNum_Aderenti_Volontari(Long num_Aderenti_Volontari) {
		this.num_Aderenti_Volontari = num_Aderenti_Volontari;
	}

	public Long getNum_Beneficiari_Familiari_Facenti_Parte() {
		return num_Beneficiari_Familiari_Facenti_Parte;
	}

	public void setNum_Beneficiari_Familiari_Facenti_Parte(Long num_Beneficiari_Familiari_Facenti_Parte) {
		this.num_Beneficiari_Familiari_Facenti_Parte = num_Beneficiari_Familiari_Facenti_Parte;
	}

	public Long getNum_Beneficiari_Familiari_0_15() {
		return num_Beneficiari_Familiari_0_15;
	}

	public void setNum_Beneficiari_Familiari_0_15(Long num_Beneficiari_Familiari_0_15) {
		this.num_Beneficiari_Familiari_0_15 = num_Beneficiari_Familiari_0_15;
	}

	public Long getNum_Beneficiari_Familiari_16_24() {
		return num_Beneficiari_Familiari_16_24;
	}

	public void setNum_Beneficiari_Familiari_16_24(Long num_Beneficiari_Familiari_16_24) {
		this.num_Beneficiari_Familiari_16_24 = num_Beneficiari_Familiari_16_24;
	}

	public Long getNum_Beneficiari_Familiari_25_40() {
		return num_Beneficiari_Familiari_25_40;
	}

	public void setNum_Beneficiari_Familiari_25_40(Long num_Beneficiari_Familiari_25_40) {
		this.num_Beneficiari_Familiari_25_40 = num_Beneficiari_Familiari_25_40;
	}

	public Long getNum_Beneficiari_Familiari_41_99() {
		return num_Beneficiari_Familiari_41_99;
	}

	public void setNum_Beneficiari_Familiari_41_99(Long num_Beneficiari_Familiari_41_99) {
		this.num_Beneficiari_Familiari_41_99 = num_Beneficiari_Familiari_41_99;
	}

	public Long getTotale_Aderenti_Fondo() {
		return totale_Aderenti_Fondo;
	}

	public void setTotale_Aderenti_Fondo(Long totale_Aderenti_Fondo) {
		this.totale_Aderenti_Fondo = totale_Aderenti_Fondo;
	}

	public Long getTotale_Assistiti() {
		return totale_Assistiti;
	}

	public void setTotale_Assistiti(Long totale_Assistiti) {
		this.totale_Assistiti = totale_Assistiti;
	}

	public TbFondi getTbFondi() {
		return tbFondi;
	}

	public void setTbFondi(TbFondi tbFondi) {
		this.tbFondi = tbFondi;
	}

	
	
	 

}