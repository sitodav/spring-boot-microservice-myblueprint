package it.sitodskij.myblueprint.validators;

import it.sitodskij.myblueprint.exception.ApiParametroNonValido;

public interface CustomValidator {
	public void validate(Object obj) throws ApiParametroNonValido;
}
