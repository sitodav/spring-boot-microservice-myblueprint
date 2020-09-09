package it.sitodskij.myblueprint.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;


/**
 * 
 * @author sitodskij
 * Classe usata per il bootstrap dell'applicazione.
 * Viene tenuta in un package separato dagli altri per evitare di fare autoscan direttamente di tutti i package sottostanti.
 * Vengono manualmente indicati i package sotto scan per le configurazioni,api,business e decorators
 */
@SpringBootApplication
@ComponentScan(
		basePackages= {
				"it.sitodskij.myblueprint.config",
				"it.sitodskij.myblueprint.api",
				"it.sitodskij.myblueprint.business",
				"it.sitodskij.myblueprint.decorators"
		}
)
public class MyBlueprintBootstrap extends SpringBootServletInitializer
{

	 
	
	public static void main(String[] args) {
		SpringApplication.run(MyBlueprintBootstrap.class, args);
	}
	
	

}
