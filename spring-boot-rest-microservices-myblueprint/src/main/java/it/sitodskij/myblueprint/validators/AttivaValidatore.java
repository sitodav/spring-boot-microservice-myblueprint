package it.sitodskij.myblueprint.validators;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @author sitodskij
 *
 * I metodi, a livello business, annotati con questa @annotation 
 * (in cui si specifica la classe figlia di CustomValidator da usare come validatore) fanno si che venga chiamato il validate
 * del validatore effettivo
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Inherited
public @interface AttivaValidatore {
	
	public Class<? extends CustomValidator> validatorClass();
}
