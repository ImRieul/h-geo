package com.example.hgeo.address.service.impl;

import com.example.hgeo.address.vo.Address;
import com.example.hgeo.address.dto.request.AddressRequest;
import com.example.hgeo.address.dto.response.kakao.KakaoAddressResponse;
import com.example.hgeo.address.service.AddressService;
import com.example.hgeo.http.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;

import static com.example.hgeo.http.ApiUriEnum.KAKAO;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;


@Service("kakaoAddressService")
public class KakaoAddressService implements AddressService {

    private final WebClient webClient;

    private final JsonParser jsonParser;

    @Autowired
    public KakaoAddressService(WebClient.Builder webClient, JsonParser jsonParser) {
        this.webClient = webClient
                .baseUrl(KAKAO.getUri())
                .defaultHeader(AUTHORIZATION, "KakaoAK a0c311321bde6c2065182411ac028caa")
                .filter(ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
                    System.out.println("Request: " + clientRequest.method() + " " + clientRequest.url());
                    clientRequest.headers().forEach((name, values) -> values.forEach(value -> System.out.println(name + ":" + value)));
                    return Mono.just(clientRequest);
                }))
                .filter(ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
                    System.out.println("Response: " + clientResponse.statusCode());
                    return Mono.just(clientResponse);
                }))
                .build();
        this.jsonParser = jsonParser;
    }

    @Override
    public Mono<Address> findByAddressName(AddressRequest addressRequest) {
        return this.findAllByAddressName(Collections.singletonList(addressRequest))
                .map(addresses -> addresses.get(0));
    }

    @Override
    public Mono<List<Address>> findAllByAddressName(List<AddressRequest> addressRequests) {
        return Flux.fromIterable(addressRequests)
                .flatMap(addressRequest -> webClient.get()
                        .uri(uriBuilder -> uriBuilder
                                .queryParam("query", addressRequest.getAddress())
                                .build())
                        .retrieve()
                        .toEntity(String.class)
                        .flux())
                .map(response -> jsonParser.parse(response.getBody(), KakaoAddressResponse.class).toAddress())
                .collectList();
    }

    @Override
    public Mono<Address> findByPoint(AddressRequest addressRequest) {
        return null;
    }

    @Override
    public Mono<List<Address>> findAllByPoint(List<AddressRequest> addressRequests) {
        return null;
    }
}
