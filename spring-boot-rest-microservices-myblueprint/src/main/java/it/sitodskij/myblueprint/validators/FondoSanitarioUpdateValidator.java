package it.sitodskij.myblueprint.validators;

import org.apache.commons.lang3.StringUtils;

import it.sitodskij.myblueprint.exception.ApiParametroNonValido;
import it.sitodskij.myblueprint.to.FondoSanitarioTO;

public class FondoSanitarioUpdateValidator implements CustomValidator{

	@Override
	public void validate(Object obj) throws ApiParametroNonValido {
		 
		 FondoSanitarioTO fondoTO = (FondoSanitarioTO)obj;
		 if(fondoTO.getId() == null)
			 throw new ApiParametroNonValido("id fondo da aggiornare mancante");
		 if(StringUtils.isBlank(fondoTO.getAutogestito()) )
			 throw new ApiParametroNonValido("specificare valore per il campo 'fondo autogestito'");
		 if(!"1".equals(fondoTO.getAutogestito()) && !"0".equals(fondoTO.getAutogestito()))
			 throw new ApiParametroNonValido("specificare valore 0/1 per il campo 'fondo autogestito'");
		 if(StringUtils.isBlank(fondoTO.getTipoFondo()))
			 throw new ApiParametroNonValido("specificare valore per il campo 'tipo fondo'");
		 if(!"A".equalsIgnoreCase(fondoTO.getTipoFondo()) && !"B".equalsIgnoreCase(fondoTO.getTipoFondo()))
			 throw new ApiParametroNonValido("specificare 'A' o 'B' per il campo tipo fondo");
		 
	}

}
