package it.sitodskij.myblueprint.util;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;


/**
 * 
 * @author sitodskij
 *
 * Classe contenente un'infinita di metodi helper per lavorare coi fogli excel
 */
public class ApachePOIHelper {

	CellStyle stileCellaTabellaBold;
	CellStyle stileIntestazioneTabella;
	CellStyle stileCellaTabella;
	HSSFFont font;
	CellStyle stileEntryTestuale;
	CellStyle stileCriterioDiRicerca;
	HSSFFont fontGrassetto;
	CellStyle stileSX;

	SimpleDateFormat formatterPiePagina = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

	public ApachePOIHelper(HSSFWorkbook wb) {

		font = (HSSFFont) wb.createFont();
		font.setFontHeightInPoints((short)11);
		font.setFontName("CALIBRI");

		fontGrassetto = wb.createFont();
		fontGrassetto.setFontHeightInPoints((short)11);
		fontGrassetto.setFontName("CALIBRI");
		fontGrassetto.setBold(true);

		stileIntestazioneTabella = wb.createCellStyle();
		stileIntestazioneTabella.setVerticalAlignment(VerticalAlignment.CENTER);
		stileIntestazioneTabella.setAlignment(HorizontalAlignment.CENTER);
		stileIntestazioneTabella.setBorderBottom(BorderStyle.THIN);
		stileIntestazioneTabella.setBorderTop(BorderStyle.THIN);
		stileIntestazioneTabella.setBorderLeft(BorderStyle.THIN);
		stileIntestazioneTabella.setBorderRight(BorderStyle.THIN);
		stileIntestazioneTabella.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		stileIntestazioneTabella.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		stileIntestazioneTabella.setFont(font);
		stileIntestazioneTabella.setWrapText(true);

		stileCellaTabella = wb.createCellStyle();
		stileCellaTabella.setVerticalAlignment(VerticalAlignment.CENTER);
		stileCellaTabella.setAlignment(HorizontalAlignment.CENTER);
		stileCellaTabella.setBorderBottom(BorderStyle.THIN);
		stileCellaTabella.setBorderTop(BorderStyle.THIN);
		stileCellaTabella.setBorderLeft(BorderStyle.THIN);
		stileCellaTabella.setBorderRight(BorderStyle.THIN);
		stileCellaTabella.setFont(font);
		stileCellaTabella.setWrapText(true);
		
		stileSX = wb.createCellStyle();
		stileSX.setVerticalAlignment(VerticalAlignment.CENTER);
		stileSX.setAlignment(HorizontalAlignment.LEFT);
		stileSX.setBorderBottom(BorderStyle.THIN);
		stileSX.setBorderTop(BorderStyle.THIN);
		stileSX.setBorderLeft(BorderStyle.THIN);
		stileSX.setBorderRight(BorderStyle.THIN);
		stileSX.setFont(font);
		stileSX.setWrapText(true);

		stileEntryTestuale = wb.createCellStyle();
		stileEntryTestuale.setVerticalAlignment(VerticalAlignment.CENTER);
		stileEntryTestuale.setFont(font);
		stileEntryTestuale.setWrapText(true);

		stileCellaTabellaBold = wb.createCellStyle();
		stileCellaTabellaBold.setVerticalAlignment(VerticalAlignment.CENTER);
//		stileCellaTabellaBold.setAlignment(HorizontalAlignment.CENTER);
		stileCellaTabellaBold.setBorderBottom(BorderStyle.THIN);
		stileCellaTabellaBold.setBorderTop(BorderStyle.THIN);
		stileCellaTabellaBold.setBorderLeft(BorderStyle.THIN);
		stileCellaTabellaBold.setBorderRight(BorderStyle.THIN);
		stileCellaTabellaBold.setFont(fontGrassetto);

		stileCriterioDiRicerca = wb.createCellStyle();
		stileCriterioDiRicerca.setVerticalAlignment(VerticalAlignment.CENTER);
		stileCriterioDiRicerca.setFont(fontGrassetto);

	}

	/* Entry usata per creare intestazione tabella */
	public Row creaIntestazioneTabella(HSSFSheet sheet, int iRiga, String... intestazioni) {
		Row riga = sheet.createRow(iRiga);
		for (int i = 0; i < Arrays.asList(intestazioni).size(); i++) {
			Cell cella = riga.createCell(i);
			cella.setCellStyle(stileIntestazioneTabella);
			cella.setCellType(CellType.STRING);
			cella.setCellValue(Arrays.asList(intestazioni).get(i));
		}

		return riga;
	}

	/* Metodo per creare singola entry testuale, come fosse una riga di testo */
	public Row creaEntryTestuale(int iRiga, String stringa, HSSFSheet sheet) {
		Row riga = sheet.createRow(iRiga);
		Cell cella = riga.createCell(0);
		cella.setCellStyle(stileEntryTestuale);
		cella.setCellValue(stringa);
		cella.setCellType(CellType.STRING);
		return riga;
	}

	/*
	 * Metodo per creare singola entry testuale, come fosse una riga di testo ,
	 * mergiata su piu' colonne
	 */
	public Row creaEntryTestuale(int iRiga, int iColStart, int iColEnd, String stringa, HSSFSheet sheet) {
		Row riga = sheet.createRow(iRiga);

		for (int i = iColStart; i <= iColEnd; i++) {
			Cell cella = riga.createCell(i);
			cella.setCellStyle(stileEntryTestuale);
			cella.setCellValue(stringa);
			cella.setCellType(CellType.STRING);
		}

		sheet.addMergedRegion(new CellRangeAddress(iRiga, iRiga, iColStart, iColEnd + 1));

		return riga;
	}

	public HSSFRow creaEntryTestuale(int cellaPartenza, int iRiga, String primaStringa, String secondaStringa,
			HSSFSheet sheet) {
		HSSFRow riga = sheet.createRow(iRiga);
		Cell cella = riga.createCell(cellaPartenza);
		cella.setCellStyle(stileEntryTestuale);
		Font boldFont = sheet.getWorkbook().createFont();
		boldFont.setBold(true);
		HSSFRichTextString stringa = new HSSFRichTextString(primaStringa + " " + secondaStringa);
		stringa.applyFont(primaStringa.length(), (primaStringa.length() + 1) + secondaStringa.length(), boldFont);
		cella.setCellValue(stringa);
		cella.setCellType(CellType.STRING);
		return riga;
	}

	public void allineaColonnaSinistra(HSSFSheet sheet, int rigaIniziale, int resultSize, int numColumn) {

		for (int k = rigaIniziale; k < rigaIniziale + resultSize; k++) {
			/*
			 * check perche' potrebbe capitare che se la riga e' vuota non la crea, in
			 * quanto il result size presenta un wrapper con campi vuoti
			 */
			if (sheet.getRow(k) == null || sheet.getRow(k).getCell(numColumn) == null)
				continue;

			sheet.getRow(k).getCell(numColumn).setCellStyle(stileSX);
		}
	}

	public Row criteriDiRicerca(int iRiga, String stringa, HSSFSheet sheet) {
		Row riga = sheet.createRow(iRiga);
		Cell cella = riga.createCell(0);
		cella.setCellStyle(stileCriterioDiRicerca);
		cella.setCellValue(stringa);
		cella.setCellType(CellType.STRING);
		return riga;
	}

	public void creaRigaTabella(HSSFSheet sheet, Integer rowStart, Integer colStart, List<String> data) {

		for (int iData = 0; iData < data.size(); iData++) {
			creaCellaTabella(rowStart, colStart + iData, data.get(iData), sheet);
		}

	}

	public void creaCellaTabella(int iRiga, int iCol, String value, HSSFSheet sheet) {
		Row riga = sheet.getRow(iRiga);
		if (sheet.getRow(iRiga) == null)
			riga = sheet.createRow(iRiga);
		Cell cella = riga.createCell(iCol);
		cella.setCellType(CellType.BLANK);
		cella.setCellValue(value + "");
		cella.setCellStyle(stileCellaTabella);
//		sheet.addIgnoredErrors(new CellRangeAddress(iRiga, iRiga, iCol, iCol), IgnoredErrorType.NUMBER_STORED_AS_TEXT);

		sheet.autoSizeColumn(iCol);

	}

	public void creaCellaTabellaFontBold(int iRiga, int iCol, String value, HSSFSheet sheet) {
		Row riga = sheet.getRow(iRiga);
		if (sheet.getRow(iRiga) == null)
			riga = sheet.createRow(iRiga);
		Cell cella = riga.createCell(iCol);
		cella.setCellType(CellType.STRING);
		cella.setCellValue(value + "");
		cella.setCellStyle(stileCellaTabellaBold);
		sheet.autoSizeColumn(iCol);

	}

	/*
	 * metodo che scorre le righe, per ciascuna colonna, e mergia le celle con lo
	 * stesso valore di stringa, a patto che non si accavalli una stessa regione di
	 * una colonna se alla colonna precedente non esiste regione
	 */
	public void eseguiMergeRegioni(Sheet sheet, Integer startRow, Integer endRow, Integer startCol, Integer endCol) {
		for (int iCol = startCol; iCol <= endCol; iCol++) {
			int iRowStartRegion = startRow;
			String lastRegionCellValue = null;

			for (int iRow = startRow; iRow <= endRow + 1; iRow++) {
				/*
				 * vado fino a endrow +1 in maniera tale da chiudere ultima regione
				 * eventualmente
				 */
				Row riga = null;
				String cellValue = null;
				Cell cella = null;

				if (iRow > endRow) {
					cellValue = "dummy"; /* cosi' da chiudere ultima regione eventualmente */
					lastRegionCellValue = "dummy2";
				} else {
					riga = sheet.getRow(iRow);
					cella = riga.getCell(iCol);
					cellValue = cella.getStringCellValue().trim();
				}

				boolean cambioRegione = false;

				if (null == lastRegionCellValue)
					lastRegionCellValue = cellValue;

				if (!lastRegionCellValue.equals(cellValue)) /* e' cambio regione */
				{

					cambioRegione = true;

				} else {
					/*
					 * non e' cambio regione, ma lo forzo se c'e' un cambio regione nella colonna
					 * precedente
					 */
					/* effettuo controllo quindi */
					if (iCol > startCol && iRow > startRow && riga != null) {
						String valColPrecedenteStessaRiga = riga.getCell(iCol - 1).getStringCellValue();
						if (valColPrecedenteStessaRiga != null)
							valColPrecedenteStessaRiga = valColPrecedenteStessaRiga.trim();

						String valColPrecedenteRigaPrecedente = sheet.getRow(iRow - 1).getCell(iCol - 1)
								.getStringCellValue();
						if (valColPrecedenteRigaPrecedente != null)
							valColPrecedenteRigaPrecedente = valColPrecedenteRigaPrecedente.trim();

						if ((valColPrecedenteStessaRiga == null || StringUtils.isEmpty(valColPrecedenteStessaRiga))
								&& (valColPrecedenteRigaPrecedente == null
										|| StringUtils.isEmpty(valColPrecedenteRigaPrecedente))) {
							cambioRegione = true;
						}

						if (valColPrecedenteStessaRiga != null && 
								!valColPrecedenteStessaRiga.equals(valColPrecedenteRigaPrecedente)) {/* forzo cambio regione */
							cambioRegione = true;
						}
					}
				}

				if (cambioRegione) {
					/* apache poi non funziona se la regione ha size 1, quindi ... */
					if (iRow - 1 - iRowStartRegion >= 1)
						sheet.addMergedRegion(new CellRangeAddress(iRowStartRegion, iRow - 1, iCol, iCol));

					iRowStartRegion = iRow;
					lastRegionCellValue = cellValue;
					cambioRegione = false;
				}
			}
		}

	}

	public HSSFRow marcaStampaDocumento(int iRiga, HSSFSheet sheet) {
		String dataS = this.formatterPiePagina.format(new Date());
		String giornoMeseAnnoS = dataS.split(" ")[0];
		String oreMinutiSecondiS = dataS.split(" ")[1];
		return this.creaEntryTestuale(0, iRiga,
				"Stampa Eseguita il : " + giornoMeseAnnoS + " alle ore: " + oreMinutiSecondiS, "", sheet);

	}

	/* startcol ed endcol per mergiare piu' colonne */
	public Row marcaStampaDocumento(int iRiga, int startCol, int endCol, HSSFSheet sheet) {
		String dataS = this.formatterPiePagina.format(new Date());
		String giornoMeseAnnoS = dataS.split(" ")[0];
		String oreMinutiSecondiS = dataS.split(" ")[1];
		return this.creaEntryTestuale(iRiga, startCol, endCol,
				"Stampa Eseguita il : " + giornoMeseAnnoS + " alle ore: " + oreMinutiSecondiS, sheet);

	}
	
	
	
	public static Object parseCellToType(Cell cell, Class<?> outputType)
	{
		String strValue = null;
		Double doubleValue = null;
		 

		try {
			strValue = cell.getStringCellValue();
			
		} catch (Exception ex) {
			doubleValue = cell.getNumericCellValue();
		}

		if (String.class.getName().equals(outputType.getName())) 
		{
			return (strValue != null? strValue : String.valueOf(doubleValue));
		}
		else if(Integer.class.getName().equals(outputType.getName()))
		{
			return (strValue != null ? Integer.parseInt(strValue) : doubleValue != null ? doubleValue.intValue() : 0);
		}
		else if(Double.class.getName().equals(outputType.getName()))
		{
			return (strValue != null ? Double.parseDouble(strValue) : doubleValue != null ? doubleValue.doubleValue() : 0.0);
		}
		else if(Boolean.class.getName().equals(outputType.getName()))
		{
			if("SI".equalsIgnoreCase(strValue)) return Boolean.valueOf(true);
			if("NO".equalsIgnoreCase(strValue)) return Boolean.valueOf(false);
			if("true".equalsIgnoreCase(strValue)) return Boolean.valueOf(true);
			if("false".equalsIgnoreCase(strValue)) return Boolean.valueOf(false);
			if("vero".equalsIgnoreCase(strValue)) return Boolean.valueOf(true);
			if("false".equalsIgnoreCase(strValue)) return Boolean.valueOf(false);
			if("1".equalsIgnoreCase(strValue)) return Boolean.valueOf(true);
			if("0".equalsIgnoreCase(strValue)) return Boolean.valueOf(false);
			
			throw new RuntimeException("Campo excel non parsabil: r-"+cell.getRowIndex()+", c-"+cell.getColumnIndex()); 
		}
		throw new RuntimeException("Campo excel non parsabil: r-"+cell.getRowIndex()+", c-"+cell.getColumnIndex());
		 
	}

}
