package it.sitodskij.myblueprint.data.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.sitodskij.myblueprint.data.model.TbFunzionalita;

public interface TbFunzionalitaDao extends JpaRepository<TbFunzionalita,Long> {

	@Query("SELECT distinct funzionalita FROM TbFunzionalita funzionalita "
			+ "JOIN funzionalita.tbRuolis ruoli "
			+ "WHERE (:idRuolo is null OR ruoli.ID_Ruolo = :idRuolo) "
			+ "AND (:tipo is null OR LOWER(funzionalita.tipo_Funzionalita) = LOWER(:tipo)) "
			+ "ORDER BY funzionalita.ID_Funzionalita")
	public List<TbFunzionalita> findFunzionalitaByIdRuoloETipo(@Param("idRuolo") Long idRuolo, @Param("tipo") String tipo, Pageable page);
	
	 
	
	
	@Query("select funzionalita from TbFunzionalita funzionalita "
			+ "where LOWER(funzionalita.descrizione_Funzionalita) = LOWER(:descrizione)")
	public TbFunzionalita getByNome(@Param("descrizione") String descrizione);
	
 
	
}
