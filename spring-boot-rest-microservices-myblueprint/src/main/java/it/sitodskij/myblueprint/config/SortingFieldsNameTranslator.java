package it.sitodskij.myblueprint.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import it.sitodskij.myblueprint.to.filtriricerca.FiltroRicercaPaginata;

/**
 * @author sitodskij
 * 
 * Aspect utilizzato per effettuare, secondo un'euristica, la traduzione
 * dei nomi dei campi specificati dal client su cui fare ordinamento
 * da nome del TO a nome dell'entity
 * L'aspect si registra sul before esecuzione di tutti i metodi a livello di business che prendono in input oggetti 
 * che estendono il filtro ricerca paginata 
 * NB: se non riesce a tradurre TUTTI i campi richiesti, setta a null la lista dei campi di ordinamento
 * quindi verra' trattata senza ordinamento la richiesta dal business
 */

@Aspect
@Component
public class SortingFieldsNameTranslator {
	
	 
	
	@Before("execution ( * it.sitodskij.myblueprint.business.*.*(..,it.sitodskij.myblueprint.to.filtriricerca.FiltroRicercaPaginata+,..) ) ")
	public void translateField(JoinPoint joinPoint)
	{
		Object[] args = joinPoint.getArgs();
		for(Object arg : args)
		{
			if(arg instanceof FiltroRicercaPaginata)
			{
				FiltroRicercaPaginata filtroRicercaP = (FiltroRicercaPaginata) args[0];
				traduciConEuristica(filtroRicercaP);
			}
		}
		 
	}
	
	
	private void traduciConEuristica(FiltroRicercaPaginata filtroRicercaP )
	{
		
		
		List<String> nomiTradotti = Optional.ofNullable(filtroRicercaP.getSortBy()).orElse(new ArrayList<>())
			.stream()
			.map(  nomeTO ->  
				  traduciNome(nomeTO)
			 )
			.collect(Collectors.toList());
		
		filtroRicercaP.setSortBy(nomiTradotti);
		 
	}
	
	
	/** regola:
	 * prendi nome del to, dove c'e' il camelcase aggiungi _ e la prima lettera successiva diventa maiuscola.
	 * es
	 * nomeFondo diventa nome_Fondo
	 * NB: cap e id diventano sempre maiuscoli
	 */
	private static String traduciNome(String toTranslate)
	{
		StringBuffer tradotto = new StringBuffer();
		for(int i = 0; i< toTranslate.length(); i++)
		{
			char charT = toTranslate.charAt(i);
			if(Character.isUpperCase(charT))
			{
				tradotto.append("_");
			}
			tradotto.append(charT);
		}
		
		String result = tradotto.toString();
		result = result.replace("cap", "CAP");
		result = result.replace("id","ID");
		
		return result;
	}
	
	 
}
