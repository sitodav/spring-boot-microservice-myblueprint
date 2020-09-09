package it.sitodskij.myblueprint.data.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import it.sitodskij.myblueprint.data.model.TbRegioni;

public interface TbRegioniDao extends JpaRepository<TbRegioni,Long> {
	
}
