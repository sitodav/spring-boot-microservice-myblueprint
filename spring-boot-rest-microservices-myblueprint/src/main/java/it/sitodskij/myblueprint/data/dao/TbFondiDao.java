package it.sitodskij.myblueprint.data.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.sitodskij.myblueprint.data.model.TbFondi;

public interface TbFondiDao extends JpaRepository<TbFondi, Long> {

	@Query("select distinct fondi.anno_Adesione from TbFondi fondi")
	public List<Long> getAllAnni( );
	
	@Query("select fondi from TbFondi fondi where (:anno is null or fondi.anno_Adesione = :anno) ")
	public Page<TbFondi> searchFondi(@Param("anno") Long anno, Pageable page);
	
	@Query("select fondi from TbFondi fondi where lower(fondi.denominazione_Fondo) like "
			+ "lower('%:denominazione%')")
	public List<TbFondi> searchByDenominazione(@Param("denominazione") String denominazione);
	
	@Query("select fondi from TbFondi fondi where lower(fondi.codice_Fiscale_Fondo) = lower(:cf)")
    public List<TbFondi> findByCF(@Param("cf") String cf);
	
	@Query("select distinct fondi from TbFondi fondi left join fetch fondi.tbPrestazioniFondos prestazioni")
	public List<TbFondi> testQuery();
	
}
