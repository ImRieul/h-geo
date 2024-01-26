package com.example.hgeo.http;

import lombok.Getter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;


@Configuration
public class HttpConfig {

    @Bean
    public JsonParser jsonParser() {
        return new JsonParser();
    }

    @Bean
    public WebClient webClient(WebClient.Builder webClientBuilder) {
        return webClientBuilder.build();
    }

//    @Bean
//    public WebClient kakaoWebClient(WebClient.Builder webClientBuilder) {
//
//    }
}
