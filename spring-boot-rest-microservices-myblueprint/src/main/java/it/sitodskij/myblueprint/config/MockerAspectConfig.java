package it.sitodskij.myblueprint.config;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import it.sitodskij.myblueprint.util.mocker.Mocked;
import it.sitodskij.myblueprint.util.mocker.MockedComponent;

@Aspect
@Component
public class MockerAspectConfig {
	@Around("execution (* it.sitodskij.myblueprint.business.*.*(..) )"
			+ " && @annotation(it.sitodskij.myblueprint.util.mocker.Mocked)")
 
	public Object mock(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

	

		MethodSignature methodSign = (MethodSignature) proceedingJoinPoint.getSignature();
		Method method = methodSign.getMethod();

		Class<? extends MockedComponent> mockedComponent = method.getAnnotation(Mocked.class).mockerClass();

		Object mockingValue = method.getAnnotation(Mocked.class).mockingValue();
		return mockedComponent.newInstance().mock(mockingValue);

	}

}
