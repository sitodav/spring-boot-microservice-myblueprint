package it.sitodskij.myblueprint.data.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.sitodskij.myblueprint.data.model.TbLivelloAssistenziale;

public interface TbLivelloAssistenzialeDao extends JpaRepository<TbLivelloAssistenziale, Long> {

	 
	
	@Query("select lvlAssistenz from TbLivelloAssistenziale lvlAssistenz "
			+ "WHERE (:idMacroambito is null or lvlAssistenz.tbMacroambito.ID_Macroambito = :idMacroambito) "
			+ "AND  (:anno is null or exists (   "  
			+ " 	select prestaz from TbPrestazioniFondo prestaz join prestaz.tbTipoPrestazione tipiPrestaz "
			+ "     join tipiPrestaz.tbLivelloAssistenziale lvlAssis"
			+ "		where lvlAssis.ID_Livello_Assistenziale = lvlAssistenz.ID_Livello_Assistenziale "
			+ " 	and prestaz.anno= :anno    )"
			+ "		) ")
	public Page<TbLivelloAssistenziale> searchLvlAssistenziale(@Param("anno") Long anno, @Param("idMacroambito") Long idMacroambito, Pageable page);
}
