package it.sitodskij.myblueprint.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

import com.github.dozermapper.core.Mapper;

import it.sitodskij.myblueprint.decorators.CustomDecorator;
import it.sitodskij.myblueprint.to.CountableWrapper;
import it.sitodskij.myblueprint.to.filtriricerca.FiltroRicercaPaginata;


/**
 * 
 * @author sitodskij
 *
 * Helper per la paginazione di spring data jpa, permette di creare i page da dare in pasto ai dao per le ricerche paginate/con indici/ordinate per campi 
 * (id o custom)
 */
public class PaginationHelper {
	
	public static final long DEFAULT_PAGINATION = 500;
	
	public static Pageable buildPageOrderById(FiltroRicercaPaginata filtroRicerca, Class<?> entityClass)
	{
		Long pIdx = Optional.ofNullable(filtroRicerca.getP()).orElse(0L);
		Long pSize = Optional.ofNullable(filtroRicerca.getPsize()).orElse(DEFAULT_PAGINATION);
		
		Pageable page = 
				  PageRequest.of(pIdx.intValue(), pSize.intValue(), Sort.by(getIdAnnotatedFieldOfClass(entityClass)).ascending());
		return page;
	}
	 
	
	
	public static Pageable buildPageCustomOrderField(FiltroRicercaPaginata filtroRicerca)
	{
		Long pIdx = Optional.ofNullable(filtroRicerca.getP()).orElse(0L);
		Long pSize = Optional.ofNullable(filtroRicerca.getPsize()).orElse(DEFAULT_PAGINATION);
		
		Direction sortDirection = "ASC".equalsIgnoreCase(filtroRicerca.getSortDir())
				? 		Sort.Direction.ASC 
				:		Sort.Direction.DESC  ;
		 
		
		List<Order> orders = Optional.ofNullable(filtroRicerca.getSortBy()).orElse(new ArrayList<>()).stream()
			.map( orderField -> new Order(sortDirection,orderField))
			.collect(Collectors.toList());
		
		Pageable page = 
				  PageRequest.of(pIdx.intValue(), pSize.intValue(), Sort.by(orders));
		
		return page;
	}
	
	
	
	private static String getIdAnnotatedFieldOfClass(Class<?> entityClass)
	{
		Field[] fields = entityClass.getDeclaredFields();
		for(Field f : fields)
		{
			if(f.isAnnotationPresent(javax.persistence.Id.class))
			{
				return f.getName();
			}
		}
		throw new RuntimeException("Field annotato con @Id non trovato su "+entityClass);
	}
	
	
	/**
	 * 
	 * 
	 * @return
	 * 
	 * Oggetto TO di wrapper che indica il numero di elementi e la pagina 
	 */
	/*Il fatto che il metodo e' statico e prende in input il mapper non dovrebbe creare problemi
	 * di race condition, in quanto il mapper gia' e' un singleton, e diverse chiamate possono
	 * utilizzare la stessa istanza quando non si passa per l'helper
	 * In questo caso passando per il metodo statico e' unica anche l'istanza dell'helper, ma il mapper e' comunque
	 * quello con scope esterno
	 */
	public static <V,T> CountableWrapper<T> buildCountableWrapper(
			Page<V> dbDataPage, Class<T> targetClass, String mappingId, Mapper mapper, CustomDecorator customDecorator)
	{ 
		List<V> entityData = dbDataPage.getContent();
		List<T> toData = Optional.ofNullable(entityData).orElse(new ArrayList<V>())
			.stream()
			.map(dbEnt -> {
					T outTO = mapper.map(dbEnt,targetClass,mappingId); 
					customDecorator.decore(outTO,dbEnt);
					return outTO;
				})
			.collect(Collectors.toList());
		
		 
		CountableWrapper<T> countableWrapp = new CountableWrapper<T>(toData, dbDataPage.getTotalElements(), (long)dbDataPage.getTotalPages());
		return countableWrapp;
	}
	 
	 
}
