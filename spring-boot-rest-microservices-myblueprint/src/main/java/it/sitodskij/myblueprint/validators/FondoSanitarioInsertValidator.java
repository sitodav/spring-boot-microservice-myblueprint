package it.sitodskij.myblueprint.validators;

import org.apache.commons.lang3.StringUtils;

import it.sitodskij.myblueprint.exception.ApiParametroNonValido;
import it.sitodskij.myblueprint.to.FondoSanitarioTO;

public class FondoSanitarioInsertValidator implements CustomValidator{

	@Override
	public void validate(Object obj) throws ApiParametroNonValido {
		 
		FondoSanitarioTO fondoTO = (FondoSanitarioTO) obj;
		 
		 if(null == fondoTO.getIdRegione())
		 {
			 throw new ApiParametroNonValido("id regione assente");
		 }
		 
		 if(StringUtils.isBlank(fondoTO.getDenominazioneFondo()))
		 {
			 throw new ApiParametroNonValido("denominazione assente");
		 }
		 if(StringUtils.isBlank(fondoTO.getCodiceFiscaleFondo()))
		 {
			 throw new ApiParametroNonValido("CF fondo assente");
		 }
		 
		 if(null == fondoTO.getAnnoAdesione())
		 {
			 throw new ApiParametroNonValido("anno fondo assente");
		 }
		 
		 if(StringUtils.isBlank(fondoTO.getCompilatore()))
		 {
			 throw new ApiParametroNonValido("compilatore fondo assente");
		 }
		 
		 if(StringUtils.isBlank(fondoTO.getIndirizzoSedeLegale()))
		 {
			 throw new ApiParametroNonValido("indirizzo sede legale fondo assente");
		 }
		 
		 if(StringUtils.isBlank(fondoTO.getComuneSedeLegale()))
		 {
			 throw new ApiParametroNonValido("comune sede legale fondo assente");
		 }
		 
		 if(null == fondoTO.getCapSedeLegale())
		 {
			 throw new ApiParametroNonValido("cap sede legale fondo assente");
		 }
		 
		 if(!StringUtils.isBlank(fondoTO.getAutogestito()) 
				 && !"1".equals(fondoTO.getAutogestito()) && !"0".equals(fondoTO.getAutogestito()))
			 throw new ApiParametroNonValido("specificare valore 0/1 per il campo 'fondo autogestito'");
		 
		 if(StringUtils.isBlank(fondoTO.getTipoFondo()) && 
				 !"A".equalsIgnoreCase(fondoTO.getTipoFondo()) && !"B".equalsIgnoreCase(fondoTO.getTipoFondo()))
			 throw new ApiParametroNonValido("specificare 'A' o 'B' per il campo tipo fondo");
		 
		
	}

}
