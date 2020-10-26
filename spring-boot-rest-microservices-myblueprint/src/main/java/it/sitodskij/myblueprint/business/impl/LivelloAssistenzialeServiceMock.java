package it.sitodskij.myblueprint.business.impl;

import java.util.Date;

import it.sitodskij.myblueprint.to.LivelloAssistenzialeTO;
import it.sitodskij.myblueprint.util.mocker.MockedComponent;

public class LivelloAssistenzialeServiceMock implements MockedComponent{

	
	@Override
	public Object mock(Object mockingValue) {
		
		try
		{
			LivelloAssistenzialeTO dto = new LivelloAssistenzialeTO();
			dto.setId( Long.parseLong(""+mockingValue));
			return dto; 
		}
		catch(Exception ex)
		{
			return new Date();
		}
		
		
		
	}

}
