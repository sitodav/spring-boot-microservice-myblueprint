package it.sitodskij.myblueprint.business;

import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.multipart.MultipartFile;

import it.sitodskij.myblueprint.to.CountableWrapper;
import it.sitodskij.myblueprint.to.PrestazioneTO;
import it.sitodskij.myblueprint.to.filtriricerca.RicercaPrestazioniByDescs;
import it.sitodskij.myblueprint.to.filtriricerca.RicercaPrestazioniByIds;

public interface PrestazioniService {
	public CountableWrapper<PrestazioneTO> searchPrestazioni(RicercaPrestazioniByIds filtroRicerca) throws Exception;
	public CountableWrapper<PrestazioneTO> searchPrestazioni(RicercaPrestazioniByDescs filtroRicerca) throws Exception;
	public PrestazioneTO searchById(Long id) throws Exception;
	public void deletePrestazioneById(Long id) throws Exception;
	public PrestazioneTO savePrestazione(PrestazioneTO prestazione) throws Exception;
	public List<PrestazioneTO> savePrestazione(List<PrestazioneTO> lista) throws Exception;
	public List<PrestazioneTO> caricamentoPrestazioniExcel(MultipartFile file) throws Exception;
	public Workbook generaReportRicercaPrestazioni(RicercaPrestazioniByDescs filtroRicerca, StringBuffer fileTitle) throws Exception;
	
}
