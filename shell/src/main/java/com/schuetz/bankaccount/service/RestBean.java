package com.schuetz.bankaccount.service;

import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.slf4j.Slf4jLogger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RestBean {

    @Bean
    public RestClient getRestClient() {
        return Feign.builder()
                .logger(new Slf4jLogger())
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .target(RestClient.class, "http://localhost:8080");
    }
}
