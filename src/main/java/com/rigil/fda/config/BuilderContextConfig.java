package com.rigil.fda.config;

import com.rigil.fda.builder.FDADataBuilder;
import com.rigil.fda.builder.UserBuilder;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BuilderContextConfig {
	
	@Bean
	public UserBuilder userBuilder(){
		return new UserBuilder();
	}
	
	@Bean
	public FDADataBuilder fdaDataBuilder(){
		return new FDADataBuilder();
	}
	
}
