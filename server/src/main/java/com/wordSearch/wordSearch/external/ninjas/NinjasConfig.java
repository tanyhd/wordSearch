package com.wordSearch.wordSearch.external.ninjas;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NinjasConfig {

    @Value("${ninjas.host}")
    String ninjasHost;

    @Bean
    public NinjasApi ninjasApi() {
        return Feign.builder()
                .encoder(new JacksonEncoder(new ObjectMapper()))
                .decoder(new JacksonDecoder(new ObjectMapper()))
                .target(NinjasApi.class, ninjasHost);
    }

}
