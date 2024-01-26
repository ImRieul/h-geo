package com.example.hgeo.token.service.impl;

import kr.co.roda.token.vo.Token;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;

import java.util.Objects;

@SpringBootTest
class SgisTokenServiceTest {

    @Autowired
    private SgisTokenService sgisTokenService;

    @Autowired
    private CacheManager cacheManager;

    @Test
    @DisplayName("token 얻기 성공")
    void testGetSuccess() {
        // given, when
        Token token = sgisTokenService.get();

        // then
        System.out.println("token = " + token.getKey());
        Assertions.assertNotNull(token.getKey());
    }

    @Test
    @DisplayName("sgis token 은 캐싱됩니다.")
    void testGetCaching() {
        // given, when
        Token token1 = sgisTokenService.get();
        Token token2 = sgisTokenService.get();

        // then
        System.out.println("token1 = " + token1.getKey());
        System.out.println("token2 = " + token2.getKey());

        Assertions.assertEquals(token1, token2);
    }

    @Test
    @DisplayName("캐시가 만료되면 다시 요청합니다.")
    void testGetReissueToken() {
        // given, when
        Token token1 = sgisTokenService.get();
        Objects.requireNonNull(cacheManager.getCache("sgisToken")).clear();
        Token token2 = sgisTokenService.get();

        // then
        System.out.println("token1 = " + token1.getKey());
        System.out.println("token2.getKey() = " + token2.getKey());
        Assertions.assertNotEquals(token1, token2);
    }

}