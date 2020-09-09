package it.sitodskij.myblueprint.config;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import it.sitodskij.myblueprint.exception.ApiParametroNonValido;
import it.sitodskij.myblueprint.validators.AttivaValidatore;
import it.sitodskij.myblueprint.validators.CustomValidator;

/**
 * 
 * @author sitodskij
 *
 * Aspect che parte prima dell'esecuzione di tutti i metodi di bean a livello business
 * e nel caso in cui sul metodo sia presente l'annotation @AttivaValidatore
 * lancia il metodo indicato nell'annotation per validare l'input.
 * In caso di errore di validazione (eccezione ApiParametroNonValido) fa fare il bubble up dell'eccezione
 */
@Aspect
@Component
public class BusinessLayerCustomValidatorsAspect {

	private static final Logger logger = LoggerFactory.getLogger(BusinessLayerCustomValidatorsAspect.class); 
	
	@Before("execution (* it.sitodskij.myblueprint.business.*.*(..) )")
	public void checkValidator(JoinPoint joinPoint) throws ApiParametroNonValido, Exception
	{
		Method method = 
				((MethodSignature)joinPoint.getSignature()).getMethod();
		 
		
		if(method.isAnnotationPresent(AttivaValidatore.class)) 
		{
			Class<? extends CustomValidator> validatorClass = 
					method.getAnnotation(AttivaValidatore.class).validatorClass();
			try
			{
				CustomValidator validatore = validatorClass.newInstance(); 
				validatore.validate(joinPoint.getArgs()[0]);
			}
			catch(ApiParametroNonValido eccezioneValidazione)
			{
				throw eccezioneValidazione;
			}
			catch(Exception ex)
			{
				logger.info("errore aspect validazione",ex);
				throw ex;
			}
			
			
		}
	}
	
	
}
