package org.jalcantararivera.mitosales.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestConfig {
    @Bean
    public RestTemplate restTemplate(){
        //Alternativas RESTROFIT,FEIGN,HTTPCLIENT
        return new RestTemplate();


    }
}
