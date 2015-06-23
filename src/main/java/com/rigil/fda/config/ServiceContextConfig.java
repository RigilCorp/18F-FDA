package com.rigil.fda.config;

import com.rigil.fda.service.AuthenticationService;
import com.rigil.fda.service.AuthenticationServiceImpl;
import com.rigil.fda.service.UserService;
import com.rigil.fda.service.UserServiceImpl;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;

@Configurable
public class ServiceContextConfig {
	
	@Bean
	public UserService userService(){
		return new UserServiceImpl();
	}	
	
	@Bean
	public AuthenticationService authenticationService(){
		return new AuthenticationServiceImpl();
	}	
}
