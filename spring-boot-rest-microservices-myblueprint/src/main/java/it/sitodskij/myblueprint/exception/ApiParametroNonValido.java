package it.sitodskij.myblueprint.exception;

public class ApiParametroNonValido extends Exception{

	public ApiParametroNonValido() 
	{
	}
	
	public ApiParametroNonValido(String msg)
	{
		super(msg);
	}
}
