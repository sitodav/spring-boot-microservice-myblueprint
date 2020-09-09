package it.sitodskij.myblueprint.data.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.sitodskij.myblueprint.data.model.TbMacroambito;

public interface TbMacroambitoDao extends JpaRepository<TbMacroambito, Long> {

	 
	
	@Query("select macrs from TbMacroambito macrs where (:anno is null or exists (  select lvlAssistenz from TbLivelloAssistenziale lvlAssistenz "  
			+ " join lvlAssistenz.tbTipoPrestaziones tipiPrestaz "
			+ " join tipiPrestaz.tbPrestazioniFondos prestaz where lvlAssistenz.tbMacroambito.ID_Macroambito = macrs.ID_Macroambito "
			+ " and prestaz.anno = :anno    )) ")
	public Page<TbMacroambito> searchMacroambito(@Param("anno") Long anno, Pageable page);

	
	 
}
