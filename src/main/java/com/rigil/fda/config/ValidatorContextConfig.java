package com.rigil.fda.config;

import com.rigil.fda.validator.CommonValidator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValidatorContextConfig {

    @Bean
    public CommonValidator commonValidator(){
        return new CommonValidator();
    }
}
