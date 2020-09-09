package it.sitodskij.myblueprint.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

/**
 * 
 * @author sitodskij
 *
 * Classe di configurazione del data layer
 * Vengono scansionate le entities
 * E abilitata la generazione dei dao di spring data jpa
 */
@Component
@EntityScan(basePackages="it.sitodskij.myblueprint.data.model")
@EnableJpaRepositories(basePackages="it.sitodskij.myblueprint.data.dao") 
public class DataLayerConfig {

 
}
