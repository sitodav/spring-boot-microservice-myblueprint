package it.sitodskij.myblueprint.data;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @author sitodskij
 *
 * Annotation usata per filtrare, tramite un aspect, gli utenti scaduti
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Attivo
{
	  
}