package com.rigil.fda.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages={"com.rigil.fda.controller"})
@Import(value={
		BeanContextConfig.class,
		ServiceContextConfig.class, 
		SupportContextConfig.class, 
		BuilderContextConfig.class,
		ValidatorContextConfig.class})
public class WebMvcContextConfig {
	
	@Bean
	public MessageSource messageSource(){
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:message/messages");
		messageSource.setUseCodeAsDefaultMessage(true);
		return messageSource;
	}
}
