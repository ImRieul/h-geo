package com.example.hgeo.address.service.impl;

import com.example.hgeo.address.dto.request.AddressRequest;
import com.example.hgeo.address.dto.request.sgis.SgisAddressRequest;
import com.example.hgeo.address.vo.Address;
import com.example.hgeo.token.service.impl.SgisTokenService;
import com.example.hgeo.token.vo.Token;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Objects;

@SpringBootTest
class SgisAddressServiceTest {

    @Autowired
    private SgisAddressService sgisAddressService;

    @Autowired
    private SgisTokenService sgisTokenService;

    @Test
    @DisplayName("하나의 주소 검색 성공")
    void testGetSuccess() {
        // given
        Token token = sgisTokenService.get();
        AddressRequest addressRequest = new SgisAddressRequest(token, "서울특별시 강남구 봉은사로 120");
        String expectedCityName = "서울특별시";

        // when
        Address address = sgisAddressService.findByAddressName(addressRequest).block();

        // then
        Assertions.assertEquals(expectedCityName, Objects.requireNonNull(address).getCityName());
    }

}