package it.sitodskij.myblueprint.business.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.dozermapper.core.Mapper;

import it.sitodskij.myblueprint.business.FondiSanitariService;
import it.sitodskij.myblueprint.data.dao.TbFondiDao;
import it.sitodskij.myblueprint.data.dao.TbRegioniDao;
import it.sitodskij.myblueprint.data.model.TbFondi;
import it.sitodskij.myblueprint.data.model.TbPrestazioniFondo;
import it.sitodskij.myblueprint.data.model.TbRegioni;
import it.sitodskij.myblueprint.decorators.CustomDecorator;
import it.sitodskij.myblueprint.to.CountableWrapper;
import it.sitodskij.myblueprint.to.FondoSanitarioTO;
import it.sitodskij.myblueprint.to.filtriricerca.RicercaConAnno;
import it.sitodskij.myblueprint.util.ApachePOIHelper;
import it.sitodskij.myblueprint.util.PaginationHelper;
import it.sitodskij.myblueprint.util.enums.ExcelReportRicercaFondi_SmartEnum;
import it.sitodskij.myblueprint.util.enums.ExcelReportRicercaPrestazioni_SmartEnum;
import it.sitodskij.myblueprint.util.enums.SmartEnum;
import it.sitodskij.myblueprint.validators.AttivaValidatore;
import it.sitodskij.myblueprint.validators.FondoSanitarioInsertValidator;
import it.sitodskij.myblueprint.validators.FondoSanitarioUpdateValidator;

 

@Service
@Transactional(rollbackFor = Exception.class)
public class FondiSanitariServiceImpl implements FondiSanitariService, CustomDecorator {

	private static final Logger logger = LoggerFactory.getLogger(FondiSanitariServiceImpl.class);
  

	@Autowired
	Mapper mapper;
	
	@Autowired
	TbFondiDao fondiDao;
	
	@Autowired
	TbRegioniDao regioniDao;

	@Override
	public CountableWrapper<Long> getAllAnni() throws Exception 
	{
		
		try
		{ 
			logger.info("Ottenimento lista anni dai fondi");
			List<Long> allAnni = fondiDao.getAllAnni();
			return new CountableWrapper<>(allAnni,allAnni.size(), 1);
		}
		catch(Exception ex)
		{
			logger.error("Errore ottenimento lista anni fondi",ex);
			throw ex;
		}
	}

	
	

	@Override
	public CountableWrapper<FondoSanitarioTO> searchFondi(RicercaConAnno filtroRicerca) throws Exception 
	{
		
		try
		{
			logger.info("Ricerca fondi sanitari con filtro : "+filtroRicerca);
			String dozerMappingKey = "fondosanitario" + ( filtroRicerca.getDeepSearch() ? "DEEP" : "SHALLOW" );
			boolean customSortRichiesto = null != filtroRicerca.getSortBy()  && filtroRicerca.getSortBy() .size() > 0;
//			 Page<TbFondi> fondi = fondiDao.searchFondi(filtroRicerca.getAnno(), PaginationHelper.buildPageOrderById(filtroRicerca, TbFondi.class)) ;
			Page<TbFondi> fondi = fondiDao.searchFondi(filtroRicerca.getAnno(), 
					customSortRichiesto 
						? PaginationHelper.buildPageCustomOrderField(filtroRicerca)
						: PaginationHelper.buildPageOrderById(filtroRicerca, TbFondi.class)) ;
			
			CountableWrapper<FondoSanitarioTO> countableWrapper = PaginationHelper.buildCountableWrapper(fondi, FondoSanitarioTO.class, dozerMappingKey, mapper, this::decore);
			return countableWrapper;
			 
		}
		catch(Exception ex)
		{
			logger.error("Errore ottenimento lista fondi",ex);
			throw ex;
		}
	}


	@Override
	public FondoSanitarioTO searchById(Long id) throws Exception {
		try
		{
			logger.info("Ricerca fondo by id: "+id);
			 TbFondi fondoDB = fondiDao.findById(id).get();
			 FondoSanitarioTO fondoTO = mapper.map(fondoDB, FondoSanitarioTO.class,"fondosanitarioDEEP");
			 this.decore(fondoTO, fondoDB);
			 return fondoTO;
		}
		catch(Exception ex)
		{
			logger.error("Errore ottenimento fondo by id "+id,ex);
			throw ex;
		}
	}


	
	
	@Override
	public void deleteById(Long id) throws Exception {
		try
		{
			logger.info("delete fondo by id: "+id);
			fondiDao.deleteById(id);
			logger.info("delete effettuata con successo");
		}
		catch(Exception ex)
		{
			logger.error("Errore delete fondo by id "+id,ex);
			throw ex;
		}
		
	}
	
	
	
	
	@Override
	public <T, V> void decore(T objTO, V objEntity) {
		 
		/*TODO da dove prendere tot iscritti fondo e numero prest annue garantite ?*/
		FondoSanitarioTO fondoTO = (FondoSanitarioTO)objTO;
		TbFondi fondoDB = (TbFondi)objEntity;
		Long totPrestazioni = fondoDB.getTbPrestazioniFondos() != null ? Long.valueOf(fondoDB.getTbPrestazioniFondos().size()) : 0L;
		fondoTO.setTotPrestazioni(totPrestazioni);
		
	}



	@AttivaValidatore(validatorClass=FondoSanitarioInsertValidator.class)
	@Override
	public FondoSanitarioTO insertFondo(FondoSanitarioTO fondoTO) throws Exception {
		 try
		 {
			 TbRegioni regione =
					  regioniDao.findById(fondoTO.getIdRegione()).get();
			 TbFondi fondoDB = mapper.map(fondoTO, TbFondi.class,"fondosanitarioSHALLOW");
			 fondoDB.setTbRegioni(regione);
			 fondoDB = fondiDao.save(fondoDB);
			 
			 return mapper.map(fondoDB,FondoSanitarioTO.class,"fondosanitarioDEEP");
		 }
		 catch(Exception ex)
		 {
			 logger.error("Errore inserimento fondo sanitario",ex);
			 throw ex;
		 }
	}



	@AttivaValidatore(validatorClass=FondoSanitarioUpdateValidator.class)
	@Override
	public FondoSanitarioTO modificaFondo(FondoSanitarioTO fondoTO) throws Exception {
		try
		 {
			 
			 TbFondi fondoDB =fondiDao.findById(fondoTO.getId()).get();
			 fondoDB.setTelefono(fondoTO.getTelefono());
			 fondoDB.setEmail(fondoTO.getEmail());
			 fondoDB.setTipo_Fondo(fondoTO.getTipoFondo());
			 fondoDB.setAutogestito(fondoTO.getAutogestito());
			 fondoDB=fondiDao.save(fondoDB);
			 return mapper.map(fondoDB,FondoSanitarioTO.class,"fondosanitarioDEEP");
		 }
		 catch(Exception ex)
		 {
			 logger.error("Errore inserimento fondo sanitario",ex);
			 throw ex;
		 }
	}




	@Override
	public Workbook generaReportRicercaFondi(RicercaConAnno filtroRicerca, StringBuffer fileTitle) throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		filtroRicerca.setDeepSearch(true);
		filtroRicerca.setPsize(1000L);
		filtroRicerca.setP(0L);
 

		fileTitle.append(
				"Report_Fondi_"+sdf.format(new Date()));

		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet(fileTitle.toString());
		ApachePOIHelper utils = new ApachePOIHelper(wb);

		utils.creaEntryTestuale(1, 0, 1, "Ricerca Fondi", sheet);
		/*TODO cambiare stile visualizzazione filtro ricerca */
		utils.creaEntryTestuale(3, "Lista dei risultati", sheet);
		
		ExcelReportRicercaFondi_SmartEnum columnSettings = (ExcelReportRicercaFondi_SmartEnum)SmartEnum.build(filtroRicerca.getColumnFilter(),ExcelReportRicercaFondi_SmartEnum.class);
		
		String[] intestazioniTab = SmartEnum.getStringheNomiColonneOrdinateEFiltrate(columnSettings);

 
		utils.creaIntestazioneTabella(sheet, 5, intestazioniTab);

		int startingRiga = 6;
 
		/*
		 * ottengo separatamente TUTTE le istruttorie e le mappo ai protocolli di
		 * appartenenza
		 * e per le sole colonne specificate nell'ordine voluto
		 */
		List<FondoSanitarioTO> fondi = Optional.ofNullable(this.searchFondi(filtroRicerca).getWrappedResult())
			.orElse(new ArrayList<>());
	  
		for (int idx = 0; idx < fondi.size(); idx++) {
			FondoSanitarioTO fondoTO = fondi.get(idx);
			
			String prestazAnnueGarant = fondoTO.getPrestazAnnueGarantite() != null ? ""+fondoTO.getPrestazAnnueGarantite() : "";
			String numIscritti = fondoTO.getNumeroIscritti() != null ? ""+fondoTO.getNumeroIscritti() : "";
			String totPrestazErogate = fondoTO.getTotPrestazioni() != null ? ""+fondoTO.getTotPrestazioni() : "";
			
			String[] dataS = new String[intestazioniTab.length];
			
			if(columnSettings.NOME.COLUMN_IDX >= 0)
				dataS[columnSettings.NOME.COLUMN_IDX] = fondoTO.getDenominazioneFondo();
			if(columnSettings.REGIONE.COLUMN_IDX >= 0)
				dataS[columnSettings.REGIONE.COLUMN_IDX] = fondoTO.getRegione().getNomeRegione();
			if(columnSettings.CF.COLUMN_IDX >= 0)
				dataS[columnSettings.CF.COLUMN_IDX] = fondoTO.getCodiceFiscaleFondo();
			if(columnSettings.ANNO.COLUMN_IDX >= 0)
				dataS[columnSettings.ANNO.COLUMN_IDX] = fondoTO.getAnnoAdesione()+"";
			if(columnSettings.PREST_ANNUE_GARANT.COLUMN_IDX >= 0)
				dataS[columnSettings.PREST_ANNUE_GARANT.COLUMN_IDX] = prestazAnnueGarant;
			if(columnSettings.NUM_ISCRITTI.COLUMN_IDX >= 0)
				dataS[columnSettings.NUM_ISCRITTI.COLUMN_IDX] = numIscritti;
			if(columnSettings.TOT_PRESTAZ_EROGATE.COLUMN_IDX >= 0)
				dataS[columnSettings.TOT_PRESTAZ_EROGATE.COLUMN_IDX] = totPrestazErogate;
			
			utils.creaRigaTabella(sheet, startingRiga + idx, 0,
					Arrays.asList(
							dataS 
					));
		}

		utils.marcaStampaDocumento(startingRiga + fondi.size() + 1, 0, 1, sheet);

		for (int iCol = 0; iCol < intestazioniTab.length - 1; iCol++) {
			sheet.autoSizeColumn(iCol);
		}


		for (int iCol = 3; iCol < 6; iCol++) {
			utils.allineaColonnaSinistra(sheet, startingRiga, fondi.size(), iCol);
		}

		return wb;
	}




	
	

	 
}
