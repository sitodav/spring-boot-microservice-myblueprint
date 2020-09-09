package it.sitodskij.myblueprint.data.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the TB_FONDI database table.
 * 
 */
@Entity
@Table(name="TB_FONDI")
@NamedQuery(name="TbFondi.findAll", query="SELECT t FROM TbFondi t")
public class TbFondi implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long ID_Fondo;

	private Long anno_Adesione;

	private String autogestito;

	private Long CAP_Sede_Legale;

	private String codice_Fiscale_Fondo;

	private String compilatore;

	private String comune_Sede_Legale;

	private String denominazione_Fondo;

	private String email;

	private String indirizzo_Sede_Legale;

	private Long telefono;
	
	private String tipo_Fondo;

	//bi-directional many-to-one association to TbAssistitiFondo
	@OneToMany(mappedBy="tbFondi")
	private List<TbAssistitiFondo> tbAssistitiFondos;

	//bi-directional many-to-one association to TbRegioni
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ID_Regione")
	private TbRegioni tbRegioni;

	//bi-directional many-to-one association to TbPrestazioniFondo
	@OneToMany(mappedBy="tbFondi",cascade=CascadeType.REMOVE)
	private List<TbPrestazioniFondo> tbPrestazioniFondos;

	public TbFondi() {
	}

	public Long getID_Fondo() {
		return this.ID_Fondo;
	}

	public void setID_Fondo(Long ID_Fondo) {
		this.ID_Fondo = ID_Fondo;
	}

	public Long getAnno_Adesione() {
		return this.anno_Adesione;
	}

	public void setAnno_Adesione(Long anno_Adesione) {
		this.anno_Adesione = anno_Adesione;
	}

	public String getAutogestito() {
		return this.autogestito;
	}

	public void setAutogestito(String autogestito) {
		this.autogestito = autogestito;
	}

	public Long getCAP_Sede_Legale() {
		return this.CAP_Sede_Legale;
	}

	public void setCAP_Sede_Legale(Long CAP_Sede_Legale) {
		this.CAP_Sede_Legale = CAP_Sede_Legale;
	}

	public String getCodice_Fiscale_Fondo() {
		return this.codice_Fiscale_Fondo;
	}

	public void setCodice_Fiscale_Fondo(String codice_Fiscale_Fondo) {
		this.codice_Fiscale_Fondo = codice_Fiscale_Fondo;
	}

	public String getCompilatore() {
		return this.compilatore;
	}

	public void setCompilatore(String compilatore) {
		this.compilatore = compilatore;
	}

	public String getComune_Sede_Legale() {
		return this.comune_Sede_Legale;
	}

	public void setComune_Sede_Legale(String comune_Sede_Legale) {
		this.comune_Sede_Legale = comune_Sede_Legale;
	}

	public String getDenominazione_Fondo() {
		return this.denominazione_Fondo;
	}

	public void setDenominazione_Fondo(String denominazione_Fondo) {
		this.denominazione_Fondo = denominazione_Fondo;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIndirizzo_Sede_Legale() {
		return this.indirizzo_Sede_Legale;
	}

	public void setIndirizzo_Sede_Legale(String indirizzo_Sede_Legale) {
		this.indirizzo_Sede_Legale = indirizzo_Sede_Legale;
	}

	public Long getTelefono() {
		return this.telefono;
	}

	public void setTelefono(Long telefono) {
		this.telefono = telefono;
	}

	
	public List<TbAssistitiFondo> getTbAssistitiFondos() {
		return this.tbAssistitiFondos;
	}

	public void setTbAssistitiFondos(List<TbAssistitiFondo> tbAssistitiFondos) {
		this.tbAssistitiFondos = tbAssistitiFondos;
	}

	public TbAssistitiFondo addTbAssistitiFondo(TbAssistitiFondo tbAssistitiFondo) {
		getTbAssistitiFondos().add(tbAssistitiFondo);
		tbAssistitiFondo.setTbFondi(this);

		return tbAssistitiFondo;
	}

	public TbAssistitiFondo removeTbAssistitiFondo(TbAssistitiFondo tbAssistitiFondo) {
		getTbAssistitiFondos().remove(tbAssistitiFondo);
		tbAssistitiFondo.setTbFondi(null);

		return tbAssistitiFondo;
	}

	public TbRegioni getTbRegioni() {
		return this.tbRegioni;
	}

	public void setTbRegioni(TbRegioni tbRegioni) {
		this.tbRegioni = tbRegioni;
	}

	public List<TbPrestazioniFondo> getTbPrestazioniFondos() {
		return this.tbPrestazioniFondos;
	}

	public void setTbPrestazioniFondos(List<TbPrestazioniFondo> tbPrestazioniFondos) {
		this.tbPrestazioniFondos = tbPrestazioniFondos;
	}

	public TbPrestazioniFondo addTbPrestazioniFondo(TbPrestazioniFondo tbPrestazioniFondo) {
		getTbPrestazioniFondos().add(tbPrestazioniFondo);
		tbPrestazioniFondo.setTbFondi(this);

		return tbPrestazioniFondo;
	}

	public TbPrestazioniFondo removeTbPrestazioniFondo(TbPrestazioniFondo tbPrestazioniFondo) {
		getTbPrestazioniFondos().remove(tbPrestazioniFondo);
		tbPrestazioniFondo.setTbFondi(null);

		return tbPrestazioniFondo;
	}

	public String getTipo_Fondo() {
		return tipo_Fondo;
	}

	public void setTipo_Fondo(String tipo_Fondo) {
		this.tipo_Fondo = tipo_Fondo;
	}
	
	

}