package com.example.hgeo.http;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;


@Configuration
public class WebClientConfig {

    @Value("${kakao.key}")
    private String kakaoApiKey;

    private final WebClient.Builder webClientBuilder;

    @Autowired
    public WebClientConfig(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder
                .filter(logRequest())
                .filter(logResponse());
    }

//    @Bean
//    private WebClient.Builder kakaoWebClientBuilder() {
//        return webClientBuilder
//                .baseUrl(ApiUriEnum.KAKAO.getUri())
//                .defaultHeader(AUTHORIZATION, kakaoApiKey);
//    }

//    @Bean
//    private WebClient.Builder sgisWebClientBuilder() {
//
//    }

    @Bean
    public WebClient kakaoAddressWebClient() {
        return webClientBuilder
                .baseUrl(ApiUriEnum.KAKAO.getUri())
                .defaultHeader(AUTHORIZATION, kakaoApiKey)
                .build();
    }


    private ExchangeFilterFunction logRequest() {
        return ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
            // TODO : 로그를 남기는 방식을 변경해야 함
            System.out.println("Request: " + clientRequest.method() + " " + clientRequest.url());
            clientRequest.headers().forEach((name, values) -> values.forEach(value -> System.out.println(name + ":" + value)));
            return Mono.just(clientRequest);
        });
    }

    private ExchangeFilterFunction logResponse() {
        return ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
            // TODO : 로그를 남기는 방식을 변경해야 함
            System.out.println("Response: " + clientResponse.statusCode());
            return Mono.just(clientResponse);
        });
    }
}
