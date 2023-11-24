package com.facol.livraria.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.logging.Logger;

@Configuration
public class LoggingConfiguration {

    @Bean
    public Logger logger(){
        return Logger.getLogger("com.facol.bookstore.patterns.singleton");
    }
}
