package it.sitodskij.myblueprint.util.enums;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


/**
 * @author sitodskij
 * 
 * classe di utilita' utilizzata per indicare gli indici posizionali delle colonne
 * con dei valori di default, o ridisposti secondo i valori di una mappa <string,string> di 
 * nomecampo:indice (nomecampo deve corrispondere ai nomi delle COLUMN_COMPONENT delle classi figlie di questsa)
 * Per ridisporre gli indici secondo la mappa chiamare il metodo build (se invocato con null o mappa vuota vengono usati i valori di default)
 * Per utilizzare effettivamente la SmartEnum bisogna estenderla
 * 
 * Le classi che estendono questa classe sono delle smart enum, nel senso che e' possibile usarle come semplici enum (valori associati indici fissati 
 * a compile time) o e' possibile manipolarle a runtime, secondo una mappa ricevuta dal client, e poi continuare ad usarle (dinamicamente costruite) come se fossero 
 * delle enum
 */


public class SmartEnum {
	
	public  class COLUMN_COMPONENT
	{
		public String COLUMN_TITLE;
		public Integer COLUMN_IDX;
		
		public COLUMN_COMPONENT() {}
		public COLUMN_COMPONENT( Integer idx,String title) { this.COLUMN_TITLE = title; this.COLUMN_IDX = idx;}
		public void setColumnIdx(Integer idx) {this.COLUMN_IDX = idx;}
	}
	
	protected SmartEnum(){} //per evitare instanziazione diretta della classe, che non ha senso in quanto non ci sono COLUMN_COMPONENT dichiarate
	
	
	public static String[] getStringheNomiColonneOrdinateEFiltrate(SmartEnum smartEnumObj) {
		//prendo i nomi delle colonne ritornandoli come array di stringhe
		//li ritorno ordinati per gli indici, non prendendo quelli con indici negativi (in quanto non desiderati )
		
		List<String > nomiColonneOrdinatiEFiltrati = Arrays.asList(smartEnumObj.getClass().getFields()).stream()
			.filter
				(	field ->   
				  		COLUMN_COMPONENT.class.getName().equals(field.getType().getName() )     
				)
			.filter
				(	field -> {
						try{
							COLUMN_COMPONENT fieldInstance = ((COLUMN_COMPONENT)field.get(smartEnumObj));
							return fieldInstance.COLUMN_IDX >= 0;
						}catch(Exception ex) {return false;}
						
					}
				)
			.sorted
				(  (field1,field2) -> {
						try {
							COLUMN_COMPONENT field1Inst = ((COLUMN_COMPONENT)field1.get(smartEnumObj));
							COLUMN_COMPONENT field2Inst = ((COLUMN_COMPONENT)field2.get(smartEnumObj));
							return field1Inst.COLUMN_IDX.compareTo(field2Inst.COLUMN_IDX  );  
						}catch(Exception ex) { return 0; }
					}    
				)
			
			.map
				(	field -> 
					{
						try {
							COLUMN_COMPONENT fieldInstance = ((COLUMN_COMPONENT)field.get(smartEnumObj));
							return fieldInstance.COLUMN_TITLE;
						} catch(Exception ex) {return null;}
					}
				) 
			.collect(Collectors.toList());
		
		return nomiColonneOrdinatiEFiltrati.toArray(new String[nomiColonneOrdinatiEFiltrati.size()]);
		
	}
	
	public static SmartEnum build(Map<String,String> desideredColumnsAndPositions, Class<? extends SmartEnum> smartEnumClass) throws Exception 
	{
		
		SmartEnum newDesidered = smartEnumClass.getDeclaredConstructor().newInstance();
		
		if(null != desideredColumnsAndPositions && desideredColumnsAndPositions.size() > 0)
		{
			/*per sicurezza rendo tutte maiuscole le keys che mi interessano */
			for(String key : desideredColumnsAndPositions.keySet())
			{
				desideredColumnsAndPositions.put(key.toUpperCase(), desideredColumnsAndPositions.get(key));
			}
			
			for(Field field : newDesidered.getClass().getFields()) //prendo tutti i campi di tipo COLUMN_COMPONENT di questa stessa classe
			{
				if(COLUMN_COMPONENT.class.getName().equals(field.getType().getName()) )
				{
					String columnKeyRefName = field.getName().toUpperCase(); 
					Integer indiceDesiderato = null;
					
					try 
					{
						//sulla mappa arrivata prendo l'indice desiderato, se non c'e' -1
						String indiceDesideratoAsString = desideredColumnsAndPositions.getOrDefault(columnKeyRefName, "-1");
						indiceDesiderato= Integer.parseInt(indiceDesideratoAsString);
					} 
					catch(Exception ex) { }
					
				    ((COLUMN_COMPONENT)field.get(newDesidered)).setColumnIdx(indiceDesiderato);
				}
			}
			
			
			
			/*a questo punto ho settato gli indici desiderati, faccio ordinamento e li forzo sequenziali per poter lavorare con le routine di utils di apache poi
			 */
			final List<Field> fieldsL = Arrays.asList(newDesidered.getClass().getFields()).stream()
				.filter
				(	field ->   
				  		COLUMN_COMPONENT.class.getName().equals(field.getType().getName() )     
				)
				.filter
				(	field -> {
						try{
							COLUMN_COMPONENT fieldInstance = ((COLUMN_COMPONENT)field.get(newDesidered));
							return fieldInstance.COLUMN_IDX >= 0;
						}catch(Exception ex) {return false;}
						
					}
				)
				.sorted
				(  (field1,field2) -> {
						try {
							COLUMN_COMPONENT field1Inst = ((COLUMN_COMPONENT)field1.get(newDesidered));
							COLUMN_COMPONENT field2Inst = ((COLUMN_COMPONENT)field2.get(newDesidered));
							return field1Inst.COLUMN_IDX.compareTo(field2Inst.COLUMN_IDX  );  
						}catch(Exception ex) { return 0; }
					}    
				)
				.collect(Collectors.toList());
			
				
			
			IntStream.range(0, fieldsL.size())
				.forEach(i -> { 
					Field fieldT = fieldsL.get(i);
					try
					{((COLUMN_COMPONENT)fieldT.get(newDesidered)).setColumnIdx(i);} catch(Exception ex) {}
					
				});
			
				
		}
		
		
		return newDesidered;
	} 
}
