package com.example.hgeo.address.dto.request;


import lombok.Getter;

import java.net.URI;

@Getter
public abstract class AddressRequest {

    private final String address;

    protected AddressRequest(String address) {
        this.address = address;
    }

    public abstract URI getUri();
}
