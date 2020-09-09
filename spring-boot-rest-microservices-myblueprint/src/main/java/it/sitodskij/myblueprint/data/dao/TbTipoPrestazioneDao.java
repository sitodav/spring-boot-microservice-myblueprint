package it.sitodskij.myblueprint.data.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.sitodskij.myblueprint.data.model.TbMacroambito;
import it.sitodskij.myblueprint.data.model.TbTipoPrestazione;

public interface TbTipoPrestazioneDao extends JpaRepository<TbTipoPrestazione, Long> {

	 
	
	@Query("select tipoPrestaz from TbTipoPrestazione tipoPrestaz "
			+ "where (:idLvlAssistenziale is null or tipoPrestaz.tbLivelloAssistenziale.ID_Livello_Assistenziale = :idLvlAssistenziale) "
			+ "AND (:anno is null or exists("
			+ "		select prestaz from TbPrestazioniFondo prestaz where "
			+ "     prestaz.anno = :anno AND prestaz.tbTipoPrestazione.ID_Tipo_Prestazione = tipoPrestaz.ID_Tipo_Prestazione)"
			+ ")")
	public Page<TbTipoPrestazione> searchTipoPrestazione(
			@Param("anno") Long anno, 
			@Param("idLvlAssistenziale") Long idLvlAssistenz, 
			Pageable page);
}
