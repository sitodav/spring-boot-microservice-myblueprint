package it.sitodskij.myblueprint.decorators;

import org.springframework.stereotype.Component;

/**
 * 
 * @author sitodskij
 *
 * Decoratore da usare quando non si vogliono decorazioni
 */
@Component
public class DoNothingDecorator implements CustomDecorator{

	@Override
	public <T,V> void decore(T objTO, V objEntity) {
		/*FAI ESATTAMENTE NULLA */
	}

}
