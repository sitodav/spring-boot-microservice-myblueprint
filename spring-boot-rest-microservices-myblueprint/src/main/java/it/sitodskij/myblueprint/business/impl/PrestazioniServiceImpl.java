package it.sitodskij.myblueprint.business.impl;

import static it.sitodskij.myblueprint.util.enums.ExcelInputPrestazione_Enum.IDXCOL_ANNO_FONDO;
import static it.sitodskij.myblueprint.util.enums.ExcelInputPrestazione_Enum.IDXCOL_CF_FONDO;
import static it.sitodskij.myblueprint.util.enums.ExcelInputPrestazione_Enum.IDXCOL_LIVELLO_ASSISTENZIALE;
import static it.sitodskij.myblueprint.util.enums.ExcelInputPrestazione_Enum.IDXCOL_NATURA_PRESTAZ;
import static it.sitodskij.myblueprint.util.enums.ExcelInputPrestazione_Enum.IDXCOL_NOMEFONDO;
import static it.sitodskij.myblueprint.util.enums.ExcelInputPrestazione_Enum.IDXCOL_NOME_MACROAMBITO;
import static it.sitodskij.myblueprint.util.enums.ExcelInputPrestazione_Enum.IDXCOL_TIPOLOGIA;
import static it.sitodskij.myblueprint.util.enums.ExcelInputPrestazione_Enum.IDXRIGA_ANNO_FONDO;
import static it.sitodskij.myblueprint.util.enums.ExcelInputPrestazione_Enum.IDXRIGA_CF_FONDO;
import static it.sitodskij.myblueprint.util.enums.ExcelInputPrestazione_Enum.IDXRIGA_NOMEFONDO;
import static it.sitodskij.myblueprint.util.enums.ExcelInputPrestazione_Enum.IDXRIGA_STARTING_ROWDATA;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.github.dozermapper.core.Mapper;

import it.sitodskij.myblueprint.business.LivelloAssistenzialeService;
import it.sitodskij.myblueprint.business.MacroambitoAssistenzialeService;
import it.sitodskij.myblueprint.business.PrestazioniService;
import it.sitodskij.myblueprint.business.TipoPrestazioneService;
import it.sitodskij.myblueprint.data.dao.TbFondiDao;
import it.sitodskij.myblueprint.data.dao.TbNaturaPrestazioneDao;
import it.sitodskij.myblueprint.data.dao.TbPrestazioniFondoDao;
import it.sitodskij.myblueprint.data.dao.TbTipoPrestazioneDao;
import it.sitodskij.myblueprint.data.model.TbFondi;
import it.sitodskij.myblueprint.data.model.TbNaturaPrestazione;
import it.sitodskij.myblueprint.data.model.TbPrestazioniFondo;
import it.sitodskij.myblueprint.data.model.TbTipoPrestazione;
import it.sitodskij.myblueprint.decorators.DoNothingDecorator;
import it.sitodskij.myblueprint.to.CountableWrapper;
import it.sitodskij.myblueprint.to.FondoSanitarioTO;
import it.sitodskij.myblueprint.to.LivelloAssistenzialeTO;
import it.sitodskij.myblueprint.to.MacroambitoAssistenzialeTO;
import it.sitodskij.myblueprint.to.PrestazioneTO;
import it.sitodskij.myblueprint.to.TipoPrestazioneTO;
import it.sitodskij.myblueprint.to.filtriricerca.RicercaPrestazioniByDescs;
import it.sitodskij.myblueprint.to.filtriricerca.RicercaPrestazioniByIds;
import it.sitodskij.myblueprint.util.ApachePOIHelper;
import it.sitodskij.myblueprint.util.PaginationHelper;
import it.sitodskij.myblueprint.util.enums.ExcelInputPrestazione_Enum;
import it.sitodskij.myblueprint.util.enums.ExcelReportRicercaPrestazioni_SmartEnum;
import it.sitodskij.myblueprint.util.enums.SmartEnum;

@Service
@Transactional(rollbackFor = Exception.class)
public class PrestazioniServiceImpl implements PrestazioniService {

	private static final Logger logger = LoggerFactory.getLogger(PrestazioniServiceImpl.class);

	@Autowired
	Mapper mapper;

	@Autowired
	TbPrestazioniFondoDao prestazioniDao;

	@Autowired
	TbTipoPrestazioneDao tipoPrestazDao;

	@Autowired
	TbFondiDao fondiDao;

	@Autowired
	TbNaturaPrestazioneDao naturaPrestazDao;

	@Autowired
	DoNothingDecorator doNothingDecor;
	
	@Autowired
	MacroambitoAssistenzialeService macroambitiService;

	@Autowired
	LivelloAssistenzialeService lvlAssistenzService;
	

	@Autowired
	TipoPrestazioneService tipoPrestazService;
	
	 

	@Override
	public CountableWrapper<PrestazioneTO> searchPrestazioni(RicercaPrestazioniByIds filtroRicerca) throws Exception {

		try {
			logger.info("Ricerca prestazioni con filtro : " + filtroRicerca);

			String dozerMappingId = "prestazioni" + (filtroRicerca.getDeepSearch() ? "DEEP" : "SHALLOW");
			Page<TbPrestazioniFondo> pagePrestazioni = null;
			boolean customSortRichiesto = null != filtroRicerca.getSortBy()  && filtroRicerca.getSortBy() .size() > 0;
			
			pagePrestazioni = prestazioniDao.searchPrestazioniFondoByIds(filtroRicerca.getAnno(),
					filtroRicerca.getIdFondo(), filtroRicerca.getIdMacroambito(),
					filtroRicerca.getIdLivelloAssistenziale(), filtroRicerca.getIdTipoPrestazione(),
					filtroRicerca.getIdNaturaPrestazione(), filtroRicerca.getPercorsoAssistenziale(),
					customSortRichiesto
						? PaginationHelper.buildPageCustomOrderField(filtroRicerca)
						: PaginationHelper.buildPageOrderById(filtroRicerca, TbPrestazioniFondo.class)
					);

			CountableWrapper<PrestazioneTO> countableWrap = PaginationHelper.buildCountableWrapper(pagePrestazioni,
					PrestazioneTO.class, dozerMappingId, mapper, doNothingDecor);
			return countableWrap;
		} catch (Exception ex) {
			logger.error("Errore ricerca prestazioni", ex);
			throw ex;
		}
	}

	@Override
	public CountableWrapper<PrestazioneTO> searchPrestazioni(RicercaPrestazioniByDescs filtroRicerca) throws Exception {

		try {
			logger.info("Ricerca prestazioni con filtro : " + filtroRicerca);

			String dozerMappingId = "prestazioni" + (filtroRicerca.getDeepSearch() ? "DEEP" : "SHALLOW");
			Page<TbPrestazioniFondo> pagePrestazioni = null;
			
			boolean customSortRichiesto = null != filtroRicerca.getSortBy()  && filtroRicerca.getSortBy() .size() > 0;
			
			pagePrestazioni = prestazioniDao.searchPrestazioniFondoByDescs(filtroRicerca.getAnno(),
					filtroRicerca.getNomeFondo(), filtroRicerca.getIdMacroambito(),
					filtroRicerca.getIdLivelloAssistenziale(), filtroRicerca.getNomeTipoPrestazione(),
					filtroRicerca.getIdNaturaPrestazione(), filtroRicerca.getPercorsoAssistenziale(),
					customSortRichiesto 
							? PaginationHelper.buildPageCustomOrderField(filtroRicerca) 
							: PaginationHelper.buildPageOrderById(filtroRicerca, TbPrestazioniFondo.class));

			CountableWrapper<PrestazioneTO> countableWrap = PaginationHelper.buildCountableWrapper(pagePrestazioni,
					PrestazioneTO.class, dozerMappingId, mapper, doNothingDecor);
			return countableWrap;
		} catch (Exception ex) {
			logger.error("Errore ricerca prestazioni", ex);
			throw ex;
		}
	}

	@Override
	public PrestazioneTO searchById(Long id) throws Exception {

		try {
			logger.info("Ricerca  prestazione by id: " + id);
			return mapper.map(prestazioniDao.findById(id).get(), PrestazioneTO.class, "prestazioniDEEP");
		} catch (Exception ex) {
			logger.error("Errore   natura prestazione by id " + id, ex);
			throw ex;
		}

	}

	@Override
	public void deletePrestazioneById(Long id) throws Exception {
		try {
			logger.info("Eliminazione prestazione by id " + id);
			prestazioniDao.deleteById(id);
			logger.info("Eliminazione avvenuta con successo");
		} catch (Exception ex) {
			logger.error("Errore cancellazione prestazione " + id, ex);
			throw ex;
		}
	}

	@Override
	public PrestazioneTO savePrestazione(PrestazioneTO prestazione) throws Exception {
		try {
			logger.info("inserimento  prestazione");
			TbPrestazioniFondo prestazioneDB = mapper.map(prestazione, TbPrestazioniFondo.class, "prestazioniSHALLOW");

			TbNaturaPrestazione naturaPrestaz = naturaPrestazDao.findById(prestazione.getIdNaturaPrestazione()).get();
			TbTipoPrestazione tipoPrestaz = tipoPrestazDao.findById(prestazione.getIdTipoPrestazione()).get();
			TbFondi fondo = fondiDao.findById(prestazione.getIdFondo()).get();

			prestazioneDB.setTbNaturaPrestazione(naturaPrestaz);
			prestazioneDB.setTbTipoPrestazione(tipoPrestaz);
			prestazioneDB.setTbFondi(fondo);

			prestazioneDB = prestazioniDao.save(prestazioneDB);
			logger.info("inserimento avvenuto con successo");
			return mapper.map(prestazioneDB, PrestazioneTO.class, "prestazioniDEEP");

		} catch (Exception ex) {
			logger.error("Errore inserimento prestazione", ex);
			throw ex;
		}

	}

	@Override
	public List<PrestazioneTO> savePrestazione(List<PrestazioneTO> listaPrestazioni) throws Exception {

		try {
			List<PrestazioneTO> savedTOs = new ArrayList<>();
			if (null != listaPrestazioni) {
				for (PrestazioneTO prestTO : listaPrestazioni) {
					savedTOs.add(this.savePrestazione(prestTO));
				}
			}
			return savedTOs;
		} catch (Exception ex) {
			logger.error("Errore inserimento lista prestazioni");
			throw ex;
		}
	}

	@Transactional(rollbackFor = Exception.class)
	public Workbook generaReportRicercaPrestazioni(RicercaPrestazioniByDescs filtroRicerca, StringBuffer fileTitle)
			throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		filtroRicerca.setDeepSearch(true);
		filtroRicerca.setPsize(1000L);
		filtroRicerca.setP(0L);

		fileTitle.append("Report Prestazioni_" + sdf.format(new Date()));

		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet(fileTitle.toString());
		ApachePOIHelper utils = new ApachePOIHelper(wb);

		utils.creaEntryTestuale(1, 0, 1, "Ricerca Prestazioni", sheet);
		/* TODO cambiare stile visualizzazione filtro ricerca */
		utils.creaEntryTestuale(3, "Lista dei risultati", sheet);
		
		ExcelReportRicercaPrestazioni_SmartEnum columnSettings = (ExcelReportRicercaPrestazioni_SmartEnum)SmartEnum.build(filtroRicerca.getColumnFilter(),ExcelReportRicercaPrestazioni_SmartEnum.class);
		
		String[] intestazioniTab = SmartEnum.getStringheNomiColonneOrdinateEFiltrate(columnSettings);

		utils.creaIntestazioneTabella(sheet, 5, intestazioniTab);

		int startingRiga = 6;

		/*
		 * ottengo separatamente TUTTE le istruttorie e le mappo ai protocolli di
		 * appartenenza
		 *  metto solo i campi delle colonne filtrate, e agli indici imposti dall'ordinamento arrivato dal client 
		 */
		List<PrestazioneTO> prestazioni = Optional.ofNullable(this.searchPrestazioni(filtroRicerca).getWrappedResult())
				.orElse(new ArrayList<>());

		for (int idx = 0; idx < prestazioni.size(); idx++) {
			PrestazioneTO prestazioneTO = prestazioni.get(idx);
			FondoSanitarioTO fondo = prestazioneTO.getFondo() != null ? prestazioneTO.getFondo() : null;
			TipoPrestazioneTO tipoPrestaz = prestazioneTO.getTipoPrestazione();
			LivelloAssistenzialeTO lvlAssistenz = tipoPrestaz != null ? tipoPrestaz.getLivelloAssistenziale() : null;
			MacroambitoAssistenzialeTO macroambito = lvlAssistenz != null ? lvlAssistenz.getMacroambito() : null;
			
			
			String[] dataS = new String[intestazioniTab.length];
			if(columnSettings.NOME_FONDO.COLUMN_IDX >= 0)
				dataS[columnSettings.NOME_FONDO.COLUMN_IDX] = fondo != null ? fondo.getDenominazioneFondo() : "";
			if(columnSettings.MACROAREA.COLUMN_IDX>= 0)
				dataS[columnSettings.MACROAREA.COLUMN_IDX] = macroambito != null ? macroambito.getNomeMacroambito() : "";
			if(columnSettings.LIVELLO.COLUMN_IDX >= 0)
				dataS[columnSettings.LIVELLO.COLUMN_IDX] = lvlAssistenz != null ? lvlAssistenz.getNomeLivelloAssistenziale() : "";
			if(columnSettings.TIPOLOGIA_PRESTAZIONE.COLUMN_IDX >= 0)	
				dataS[columnSettings.TIPOLOGIA_PRESTAZIONE.COLUMN_IDX] = tipoPrestaz != null ? tipoPrestaz.getNomeTipoPrestazione() : "";
			if(columnSettings.NATURA_PRESTAZIONE.COLUMN_IDX >= 0)
				dataS[columnSettings.NATURA_PRESTAZIONE.COLUMN_IDX] = prestazioneTO.getNaturaPrestazione().getNomeNaturaPrestazione();
			if(columnSettings.NUMERO_PRESTAZIONI_RIMBTICKET.COLUMN_IDX >= 0)
				dataS[columnSettings.NUMERO_PRESTAZIONI_RIMBTICKET.COLUMN_IDX] = prestazioneTO.getNumPrestRimbTicket() + "";
			if(columnSettings.VALORE_COMPLESSIVO_RIMBTICKET.COLUMN_IDX >= 0)
				dataS[columnSettings.VALORE_COMPLESSIVO_RIMBTICKET.COLUMN_IDX] = prestazioneTO.getValPrestRimbTicket() + "";
			if(columnSettings.NUMERO_PRESTAZIONI_INTEROIMPO.COLUMN_IDX >= 0)
				dataS[columnSettings.NUMERO_PRESTAZIONI_INTEROIMPO.COLUMN_IDX] = prestazioneTO.getNumPrestRimbIntero() + "";
			if(columnSettings.VALORE_COMPLESSIVO_INTEROIMPO.COLUMN_IDX >= 0)
				dataS[columnSettings.VALORE_COMPLESSIVO_INTEROIMPO.COLUMN_IDX] = prestazioneTO.getValPrestRimbIntero() + "";
			if(columnSettings.INSERITA_PIANOSANIT.COLUMN_IDX >= 0)
				dataS[columnSettings.INSERITA_PIANOSANIT.COLUMN_IDX] = prestazioneTO.getPercorsoAssistenziale() + "";
			
			utils.creaRigaTabella(sheet, startingRiga + idx, 0,
					Arrays.asList(dataS));
		}

		utils.marcaStampaDocumento(startingRiga + prestazioni.size() + 1, 0, 1, sheet);

		for (int iCol = 0; iCol < intestazioniTab.length - 1; iCol++) {
			sheet.autoSizeColumn(iCol);
		}

		for (int iCol = 3; iCol < 6; iCol++) {
			utils.allineaColonnaSinistra(sheet, startingRiga, prestazioni.size(), iCol);
		}

		return wb;

	}

	@Override
	public List<PrestazioneTO> caricamentoPrestazioniExcel(MultipartFile file) throws Exception {

		
		List<PrestazioneTO> listPrestazioni = new ArrayList<>();

		try (Workbook workbook = WorkbookFactory.create(file.getInputStream())) {

			Sheet sheet = workbook.getSheetAt(0);

			String nomeFondo = sheet.getRow(IDXRIGA_NOMEFONDO.idx).getCell(IDXCOL_NOMEFONDO.idx).getStringCellValue();
			String cf = sheet.getRow(IDXRIGA_CF_FONDO.idx).getCell(IDXCOL_CF_FONDO.idx).getStringCellValue();
			String annoPrestazione = ""+
					(int)sheet.getRow(IDXRIGA_ANNO_FONDO.idx).getCell(IDXCOL_ANNO_FONDO.idx).getNumericCellValue();

			/*
			 * PER HP fondo e natura prestazioni devono esistere
			 * macroambito, lvl assistenziale e tipologia prestazioni vengono inserite assieme alle prestaz
			 */
			FondoSanitarioTO fondoTO = mapper.map(
					fondiDao.findByCF(cf).get(0), FondoSanitarioTO.class,"fondosanitarioDEEP");
			Map<String,MacroambitoAssistenzialeTO> macrosGiaInseriti = new HashMap<>();
			Map<String,LivelloAssistenzialeTO> lvlAssistGiaInseriti = new HashMap<>();
			Map<String,TipoPrestazioneTO> tipiPrestGiaInseriti = new HashMap<>();
			
			
			for (int i = IDXRIGA_STARTING_ROWDATA.idx; i < sheet.getLastRowNum(); i++) {

				try {
					
					Row riga = sheet.getRow(i);
					PrestazioneTO prestazioneTO = 
							buildPrestazioneFromRow(riga);
					/*fondo deve gia' esistere */
					prestazioneTO.setAnno(Integer.parseInt(annoPrestazione));
					prestazioneTO.setIdFondo(fondoTO.getId());
					
					/*natura prestazione deve gia' esistere, e' una tipologica
					 * se non esiste skippo riga*/
					String nomeNaturaPrestaz = riga.getCell(IDXCOL_NATURA_PRESTAZ.idx).getStringCellValue();
					Optional<TbNaturaPrestazione> naturaPrestazOpt = naturaPrestazDao.findByPreciseNameIgnoreCase(nomeNaturaPrestaz);
					if(!naturaPrestazOpt.isPresent())
					{
						logger.info("Nome natura prestazione non esistente a DB, salto riga");
						continue;
					}	
					prestazioneTO.setIdNaturaPrestazione(naturaPrestazOpt.get().getID_Natura_Prestazione());
					
					/*macroambito, lvl assistenziale e tipo prestazioni
					 * per ciascun livello aggiungo se non esiste gia', 
					 * altrimenti inserisco nodo la prima volta che ne incontro nome nell'excel
					 * e poi lo riutilizzo per i figli successivi quando ricompare
					 */
					String nomeMacroambito = riga.getCell(IDXCOL_NOME_MACROAMBITO.idx).getStringCellValue();
					MacroambitoAssistenzialeTO macroambitoTO = Optional.ofNullable(macrosGiaInseriti.get(nomeMacroambito))
						.orElse(new MacroambitoAssistenzialeTO(null,nomeMacroambito));
					if(macroambitoTO.getId() == null) /*vuol dire che non era stato gia' preso e inserito, quindi lo salvo */
					{
						macroambitoTO = macroambitiService.save(macroambitoTO);
						macrosGiaInseriti.put(nomeMacroambito,macroambitoTO);
					}
					
					
					String nomeLvlAssistenz = riga.getCell(IDXCOL_LIVELLO_ASSISTENZIALE.idx).getStringCellValue();
					LivelloAssistenzialeTO lvlAssistenzTO = Optional.ofNullable(lvlAssistGiaInseriti.get(nomeLvlAssistenz))
							.orElse(new LivelloAssistenzialeTO(null,nomeLvlAssistenz));
					
					if(lvlAssistenzTO.getId() == null)
					{
						lvlAssistenzTO.setIdMacroambito(macroambitoTO.getId());
						lvlAssistenzTO = lvlAssistenzService.save(lvlAssistenzTO);
						lvlAssistGiaInseriti.put(nomeLvlAssistenz,lvlAssistenzTO);
					}
					
					
					String nomeTipologiaPrestaz = riga.getCell(IDXCOL_TIPOLOGIA.idx).getStringCellValue();
					TipoPrestazioneTO tipoPrestazTO = Optional.ofNullable(tipiPrestGiaInseriti.get(nomeTipologiaPrestaz))
							.orElse(new TipoPrestazioneTO(null, nomeTipologiaPrestaz));
					
					if(tipoPrestazTO.getId() == null)
					{
						tipoPrestazTO.setIdLivelloAssistenziale(lvlAssistenzTO.getId());
						tipoPrestazTO = tipoPrestazService.save(tipoPrestazTO);
						tipiPrestGiaInseriti.put(nomeTipologiaPrestaz,tipoPrestazTO);
					}
					prestazioneTO.setIdTipoPrestazione(tipoPrestazTO.getId());
					
					
					listPrestazioni.add(prestazioneTO);
					
				} catch (Exception ex) {
					logger.error("errore salvataggio prestazione",ex);
					throw ex;
				}

			}

			return this.savePrestazione(listPrestazioni);

		} catch (Exception ex) {
			logger.error("Errore parsing excel singolo inserimento prestazionie", ex);
			throw ex;
		}

	}

 
	
	
	private PrestazioneTO buildPrestazioneFromRow( Row riga) {
	 
		PrestazioneTO prestazioneTO = new PrestazioneTO();
//		prestazioneTO.setAnno(2018);//anno usa quello unico specificato nell'excel
		
		/*campo inserita in un percorso assistenziale, e' SI/NO sull'excel, e diventa 1/0 sul db per il campo percorso assistenziale */
		boolean inseritaInPercorsoEsistenziale = (Boolean)ApachePOIHelper.parseCellToType(riga.getCell(ExcelInputPrestazione_Enum.IDXCOL_INSERITA_PERCORSO_ASSISTENZ.idx), Boolean.class);
		prestazioneTO.setPercorsoAssistenziale(inseritaInPercorsoEsistenziale ? "1" : "0");
		
		prestazioneTO.setDescrizionePercorso((String)ApachePOIHelper.parseCellToType(riga.getCell(ExcelInputPrestazione_Enum.IDXCOL_DESCRIZIONE_PERCORSI.idx), String.class));
		
		prestazioneTO.setNumPrestRimbTicket((Integer)ApachePOIHelper.parseCellToType(riga.getCell(ExcelInputPrestazione_Enum.IDXCOL_NUMERO_PRESTAZIONI_TICKET.idx), Integer.class));
		
		prestazioneTO.setValPrestRimbTicket((Double)ApachePOIHelper.parseCellToType(riga.getCell(ExcelInputPrestazione_Enum.IDXCOL_VALORE_COMPLESSIVO_TICKET.idx), Double.class));
		
		prestazioneTO.setNumPrestRimbIntero((Integer)ApachePOIHelper.parseCellToType(riga.getCell(ExcelInputPrestazione_Enum.IDXCOL_NUMERO_PRESTAZIONI_INTERO.idx), Integer.class));
		
		prestazioneTO.setValPrestRimbIntero((Double)ApachePOIHelper.parseCellToType(riga.getCell(ExcelInputPrestazione_Enum.IDXCOL_NUMERO_PRESTAZIONI_INTERO.idx), Double.class));

		prestazioneTO.setQuantitaTotaliErogate((Integer)ApachePOIHelper.parseCellToType(riga.getCell(ExcelInputPrestazione_Enum.IDXCOL_QTA_TOT_EROGATE.idx), Integer.class));
		
		prestazioneTO.setValoreComplessivo((Double)ApachePOIHelper.parseCellToType(riga.getCell(ExcelInputPrestazione_Enum.IDXCOL_VALORE_COMPLESSIVO.idx), Double.class));
		

		prestazioneTO.setPrestazioniAnnue((Integer)ApachePOIHelper.parseCellToType(riga.getCell(ExcelInputPrestazione_Enum.IDXCOL_PRESTAZ_ANNUE_GARANT.idx), Integer.class));
		
		prestazioneTO.setNote("");
		
		return prestazioneTO;
		
		
		
		
	}
	
	

}