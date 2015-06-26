package com.rigil.fda.config;

import com.rigil.fda.support.AuthenticationEnterpriseDocumentSupport;
import com.rigil.fda.support.FDADataEnterpriseDocumentSupport;
import com.rigil.fda.support.UserEnterpriseDocumentSupport;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;

@Configurable
public class SupportContextConfig {

    @Bean
    public UserEnterpriseDocumentSupport userEnterpriseDocumentSupport(){
        return new UserEnterpriseDocumentSupport();
    }

    @Bean
    public AuthenticationEnterpriseDocumentSupport authenticationEnterpriseDocumentSupport(){
        return new AuthenticationEnterpriseDocumentSupport();
    }

    @Bean
    public FDADataEnterpriseDocumentSupport fdaDataEnterpriseDocumentSupport(){
        return new FDADataEnterpriseDocumentSupport();
    }


}
