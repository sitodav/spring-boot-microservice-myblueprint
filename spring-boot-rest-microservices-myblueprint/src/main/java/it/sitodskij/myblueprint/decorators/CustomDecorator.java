package it.sitodskij.myblueprint.decorators;

/**
 * 
 * @author sitodskij
 *
 * Interfaccia per decorare, all'interno dell'util di paginazione, i TO per campi compositi non mappati dal dozer
 */
public interface CustomDecorator {

	public <T,V> void  decore(T objTO, V objEntity);
}
