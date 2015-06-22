package com.rigil.fda.config;

import com.rigil.fda.builder.UserBuilder;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BuilderContextConfig {
	
	@Bean
	public UserBuilder userBuilder(){
		return new UserBuilder();
	}
	
}
