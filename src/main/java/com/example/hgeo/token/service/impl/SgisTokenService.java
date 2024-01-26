package com.example.hgeo.token.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.example.hgeo.http.JsonParser;
import com.example.hgeo.http.SgisResponse;
import com.example.hgeo.token.vo.Token;
import com.example.hgeo.token.vo.SgisToken;
import com.example.hgeo.token.dto.sgis.SgisTokenRequest;
import com.example.hgeo.token.dto.sgis.SgisTokenResponse;
import com.example.hgeo.token.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service("sgisTokenService")
public class SgisTokenService implements TokenService {

    private final WebClient webClient;

    private final JsonParser jsonParser;

    private final SgisTokenRequest sgisTokenRequest;

    @Autowired
    public SgisTokenService(WebClient.Builder webClient, JsonParser jsonParser, SgisTokenRequest sgisTokenRequest) {
        this.webClient = webClient.build();
        this.jsonParser = jsonParser;
        this.sgisTokenRequest = sgisTokenRequest;
    }

    @Override
    @Cacheable(value = "sgisToken", key = "#root.method.name")
    public Token get() {
        return webClient.get()
                .uri(sgisTokenRequest.getUri())
                .retrieve()
                .bodyToMono(String.class)
                .map(body -> {
                    SgisResponse<SgisTokenResponse> parse = jsonParser.parse(body, new TypeReference<SgisResponse<SgisTokenResponse>>() {});
                    return parse.getResult().toSgisToken();
                })
                .block();
    }
}
