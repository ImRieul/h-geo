package com.example.hgeo.address.service;

import com.example.hgeo.address.dto.request.AddressRequest;
import com.example.hgeo.address.vo.Address;
import reactor.core.publisher.Mono;

import java.util.List;

public interface AddressService {

    Mono<Address> findByAddressName(AddressRequest addressRequest);
    Mono<List<Address>> findAllByAddressName(List<AddressRequest> addressRequests);
    Mono<Address> findByPoint(AddressRequest addressRequest);
    Mono<List<Address>> findAllByPoint(List<AddressRequest> addressRequests);

}
