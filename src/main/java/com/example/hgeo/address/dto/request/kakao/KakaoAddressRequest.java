package com.example.hgeo.address.dto.request.kakao;

import com.example.hgeo.address.dto.request.AddressRequest;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static com.example.hgeo.http.ApiUriEnum.KAKAO;

public class KakaoAddressRequest extends AddressRequest {

    public KakaoAddressRequest(String address) {
        super(address);
    }

    /**
     * 이 메서드는 webClient 와 조합해서 사용할 때,
     * kakao 서버가 제대로 된 응답을 하지 못해 사용할 수 없어
     * AddressRequest 확장으로 구현만 해놓았습니다.
     * @return null
     */
    @Override
    public URI getUri() {
        return UriComponentsBuilder.fromUriString(KAKAO.getUri())
                .queryParam("query", super.getAddress())
                .build()
                .toUri();
//        return null;
    }
}
