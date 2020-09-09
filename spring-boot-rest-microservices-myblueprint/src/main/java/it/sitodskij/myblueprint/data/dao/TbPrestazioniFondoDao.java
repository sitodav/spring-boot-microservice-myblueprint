package it.sitodskij.myblueprint.data.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.sitodskij.myblueprint.data.model.TbPrestazioniFondo;

public interface TbPrestazioniFondoDao extends JpaRepository<TbPrestazioniFondo, Long> {

	@Query("select prestazioni from TbPrestazioniFondo prestazioni join prestazioni.tbFondi fondi "
			+ "join prestazioni.tbTipoPrestazione tipoPrestaz "
			+ "join tipoPrestaz.tbLivelloAssistenziale lvlAssistenz "
			+ "join lvlAssistenz.tbMacroambito macroambiti where "
			+ "(:anno is null or prestazioni.anno = :anno) AND"
			+ "(:idFondo is null or fondi.ID_Fondo = :idFondo) AND "
			+ "(:idMacroambito is null or macroambiti.ID_Macroambito = :idMacroambito) AND"
			+ "(:idLvlAssistenziale is null or lvlAssistenz.ID_Livello_Assistenziale = :idLvlAssistenziale) AND "
			+ "(:idTipoPrestazione is null or tipoPrestaz.ID_Tipo_Prestazione = :idTipoPrestazione) AND "
			+ "(:idNaturaPrestazione is null or prestazioni.tbNaturaPrestazione.ID_Natura_Prestazione = :idNaturaPrestazione) AND "
			+ "(:percorsoAssistenziale is null or prestazioni.percorso_Assistenziale = :percorsoAssistenziale)")
	public Page<TbPrestazioniFondo> searchPrestazioniFondoByIds(
			@Param("anno") Long anno, 
			@Param("idFondo") Long idFondo,
			@Param("idMacroambito") Long idMacroambito, 
			@Param("idLvlAssistenziale") Long idLvlAssistenziale,
			@Param("idTipoPrestazione") Long idTipoPrestazione,
			@Param("idNaturaPrestazione") Long idNaturaPrestazione,
			@Param("percorsoAssistenziale") String percorsoAssistenziale, 
			Pageable page);
	
	
	@Query("select prestazioni from TbPrestazioniFondo prestazioni join prestazioni.tbFondi fondi "
			+ "join prestazioni.tbTipoPrestazione tipoPrestaz "
			+ "join tipoPrestaz.tbLivelloAssistenziale lvlAssistenz "
			+ "join lvlAssistenz.tbMacroambito macroambiti where "
			+ "(:anno is null or prestazioni.anno = :anno) AND"
			+ "(:nomeFondo is null or lower(fondi.denominazione_Fondo) LIKE lower( concat('%',:nomeFondo,'%') ) ) AND "
			+ "(:idMacroambito is null or macroambiti.ID_Macroambito = :idMacroambito) AND"
			+ "(:idLvlAssistenziale is null or lvlAssistenz.ID_Livello_Assistenziale = :idLvlAssistenziale) AND "
			+ "(:nomeTipoPrestazione is null or lower(tipoPrestaz.nome_Tipo_Prestazione) LIKE lower( concat('%',:nomeTipoPrestazione,'%') )) AND "
			+ "(:idNaturaPrestazione is null or prestazioni.tbNaturaPrestazione.ID_Natura_Prestazione = :idNaturaPrestazione) AND "
			+ "(:percorsoAssistenziale is null or prestazioni.percorso_Assistenziale = :percorsoAssistenziale)")
	public Page<TbPrestazioniFondo> searchPrestazioniFondoByDescs(
			@Param("anno") Long anno, 
			@Param("nomeFondo") String nomeFondo,
			@Param("idMacroambito") Long idMacroambito, 
			@Param("idLvlAssistenziale") Long idLvlAssistenziale,
			@Param("nomeTipoPrestazione") String nomeTipoOperazione,
			@Param("idNaturaPrestazione") Long idNaturaPrestazione,
			@Param("percorsoAssistenziale") String percorsoAssistenziale,
			Pageable page);

}
