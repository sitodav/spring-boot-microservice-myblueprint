package it.sitodskij.myblueprint.data.dao;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.sitodskij.myblueprint.data.model.TbNaturaPrestazione;

public interface TbNaturaPrestazioneDao extends JpaRepository<TbNaturaPrestazione, Long> {

	@Query("select naturaprestaz from TbNaturaPrestazione naturaprestaz")
	public Page<TbNaturaPrestazione> findNaturaPrestazioni(Pageable page);
	
	@Query("select naturaprestaz from TbNaturaPrestazione naturaprestaz "
			+ "where lower(naturaprestaz.nome_Natura_Prestazione) = lower(:nome)")
	public Optional<TbNaturaPrestazione> findByPreciseNameIgnoreCase(@Param("nome") String nome);
	 
}
