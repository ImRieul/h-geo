package com.example.hgeo.address.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.example.hgeo.address.dto.request.AddressRequest;
import com.example.hgeo.address.dto.response.sgis.SgisAddressResponse;
import com.example.hgeo.address.service.AddressService;
import com.example.hgeo.address.vo.Address;
import com.example.hgeo.http.JsonParser;
import com.example.hgeo.http.SgisResponse;
import com.example.hgeo.token.service.impl.SgisTokenService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;

@Service("sgisAddressService")
@AllArgsConstructor
public class SgisAddressService implements AddressService {

    private final WebClient webClient;

    private final JsonParser jsonParser;

    private final SgisTokenService sgisTokenService;

    @Override
    public Mono<Address> findByAddressName(AddressRequest addressRequest) {
        return this.findAllByAddressName(Collections.singletonList(addressRequest))
                .map(addresses -> addresses.get(0));
    }

    @Override
    public Mono<List<Address>> findAllByAddressName(List<AddressRequest> addressRequests) {
        return Flux.fromIterable(addressRequests)
                .flatMap(addressRequest -> webClient.get()
                        .uri(addressRequest.getUri())
                        .retrieve()
                        .toEntity(String.class)
                        .flux())
                .map(response -> {
                    SgisResponse<SgisAddressResponse> parse = jsonParser.parse(response.getBody(), new TypeReference<SgisResponse<SgisAddressResponse>>() {
                    });
                    return parse.getResult().getResultData().get(0).toAddress();
                })
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
