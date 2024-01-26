package com.example.hgeo.address.service.impl;

import kr.co.roda.address.dto.request.AddressRequest;
import kr.co.roda.address.dto.request.kakao.KakaoAddressRequest;
import kr.co.roda.address.vo.Address;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;


@SpringBootTest
class KakaoAddressServiceTest {

    @Autowired
    private KakaoAddressService kakaoAddressService;

    @Test
    @DisplayName("하나의 주소 검색 성공")
    void testGetSuccess() {
        // given
        AddressRequest addressRequest = new KakaoAddressRequest("서울특별시 강남구 봉은사로 120");
        String expectedCityName = "서울";

        // when
        Address address = kakaoAddressService.findByAddressName(addressRequest).block();

        // then
        assertEquals(expectedCityName, Objects.requireNonNull(address).getCityName());
    }

    @Test
    @DisplayName("도시 이름에는 행정구역 종류가 포함되지 않습니다.")
    void testGetFailNotIncludeCityNameInAdministrativeDistrictType() {
        // given
        AddressRequest addressRequest = new KakaoAddressRequest("서울특별시 강남구 봉은사로 120");
        String expectedCityName = "서울특별시";

        // when
        Address address = kakaoAddressService.findByAddressName(addressRequest).block();

        // then
        assertNotEquals(expectedCityName, Objects.requireNonNull(address).getCityName());
    }

    @Test
    @DisplayName("여러 주소 검색 성공")
    void testGetAllSuccess() {
        // given
        List<AddressRequest> addressRequests = Arrays.asList(
                new KakaoAddressRequest("서울특별시 강남구 봉은사로 120"),
                new KakaoAddressRequest("대전 서구 배재로 128")
        );
        List<String> expectedCityNames = Arrays.asList("서울", "대전");

        // when
        Mono<List<Address>> addresses = kakaoAddressService.findAllByAddressName(addressRequests);

        // then
        StepVerifier.create(addresses)
                .expectNextMatches(addressResponses -> {
                    for (int i = 0; i < addressResponses.size(); i++) {
                        System.out.println("addressResponses.get(i).getCityName() = " + addressResponses.get(i).getCityName());
                        if (!expectedCityNames.get(i).equals(addressResponses.get(i).getCityName())) {
                            return false;
                        }
                    }
                    return true;
                })
                .verifyComplete();
    }

}