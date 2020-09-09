package it.sitodskij.myblueprint.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import com.github.dozermapper.spring.DozerBeanMapperFactoryBean;

/**
 * 
 * @author sitodskij
 * 
 * Classe di configurazione per mapper di dozer, 
 * che si prende tutti i file di tipo xml inclusi nella cartella dozer nel classpath per i mapping to-entities
 */
@Configuration
public class DozerConfig {
  
	@Bean
	public DozerBeanMapperFactoryBean registerDozer(@Value("classpath*:dozer/*.xml") Resource[] resources) throws IOException
	{
		DozerBeanMapperFactoryBean beanFactory = new DozerBeanMapperFactoryBean();
		beanFactory.setMappingFiles(resources);
		return beanFactory;
	}
	 
}
