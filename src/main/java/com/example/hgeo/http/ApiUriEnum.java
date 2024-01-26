package com.example.hgeo.http;


import lombok.Getter;

@Getter
public enum ApiUriEnum {
    KAKAO("https://dapi.kakao.com/v2/local/search/address"),
    SGIS_WGS84("https://sgisapi.kostat.go.kr/OpenAPI3/addr/geocodewgs84.json"),
    SGIS_KEY("https://sgisapi.kostat.go.kr/OpenAPI3/auth/authentication.json");

    private final String uri;

    ApiUriEnum(String uri) {
        this.uri = uri;
    }
}
