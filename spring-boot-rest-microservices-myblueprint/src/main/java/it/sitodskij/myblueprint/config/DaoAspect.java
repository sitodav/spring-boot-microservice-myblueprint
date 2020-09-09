package it.sitodskij.myblueprint.config;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import it.sitodskij.myblueprint.data.Attivo;
import it.sitodskij.myblueprint.data.model.TbUtenti;

/**
 * 
 * @author sitodskij
 *
 * Aspect che parte attorno all'esecuzione di tutti i metodi del dao degli utenti
 * e se presente l'annotation @Attivo filtra i risultati di utenti scaduti
 */

@Aspect
@Component
public class DaoAspect {

	@Around("execution( it.sitodskij.myblueprint.data.model.TbUtenti it.sitodskij.myblueprint.data.dao.TbUtentiDao.*(..) ) || "
			+ "execution( java.util.List<it.sitodskij.myblueprint.data.model.TbUtenti> it.sitodskij.myblueprint.data.dao.TbUtentiDao.*(..) ) ")
	public Object filtraUtentiDisabilitati(ProceedingJoinPoint proceedingJoinPoint) throws Throwable
	{
		Object returnedObjs = proceedingJoinPoint.proceed();
		
		MethodSignature methodSign = (MethodSignature) proceedingJoinPoint.getSignature();
		Method method = methodSign.getMethod();
		if(method.isAnnotationPresent(Attivo.class))
		{
			if(returnedObjs instanceof List<?>)
			{
				 return Optional.ofNullable((List<TbUtenti>)returnedObjs).orElse(new ArrayList<>())
						 	.stream()
						 	.filter(utenteDB -> checkValid(utenteDB))
						 	.collect(Collectors.toList());
			}
			else
			{
				return Optional.ofNullable((TbUtenti)returnedObjs).filter(utenteDB -> checkValid(utenteDB))
						.orElse(null);
			}
			
		}
		else
		{
			return returnedObjs;
		}
 
		
	}
	
	

	private boolean checkValid(TbUtenti utenteDB)
	{
		return utenteDB.getDisabilitato() != null && utenteDB.getDisabilitato().toLowerCase().equals("n");
	}
		
}
