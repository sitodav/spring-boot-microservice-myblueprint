package it.sitodskij.myblueprint.data.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.sitodskij.myblueprint.data.Attivo;
import it.sitodskij.myblueprint.data.model.TbUtenti;

public interface TbUtentiDao extends JpaRepository<TbUtenti,Long>{
	
	@Attivo
	@Query("select utenti from TbUtenti utenti where username = :username")
	public TbUtenti findByUserIfActive(@Param("username") String user);
	
	
	public TbUtenti findByUsername(String user);
	
	@Attivo
	public TbUtenti findByEmail(String email);
	
	public List<TbUtenti> findAllByOrderByCognomeAsc();
}
