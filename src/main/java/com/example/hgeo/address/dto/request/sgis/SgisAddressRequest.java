package com.example.hgeo.address.dto.request.sgis;

import com.example.hgeo.address.dto.request.AddressRequest;
import com.example.hgeo.token.vo.Token;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static com.example.hgeo.http.ApiUriEnum.SGIS_WGS84;

public class SgisAddressRequest extends AddressRequest {

    private final Token token;

    public SgisAddressRequest(Token token, String address) {
        super(address);
        this.token = token;
    }

    @Override
    public URI getUri() {
        return UriComponentsBuilder.fromUriString(SGIS_WGS84.getUri())
                .queryParam("accessToken", token.getKey())
                .queryParam("address", super.getAddress())
                .build()
                .toUri();
    }

}
