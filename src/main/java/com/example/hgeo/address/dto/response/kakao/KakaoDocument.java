package com.example.hgeo.address.dto.response.kakao;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.PropertyNamingStrategy.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;


@Getter
@JsonNaming(SnakeCaseStrategy.class)
public class KakaoDocument {

    private String addressName;

    private String addressType;

    @JsonProperty("address")
    private KakaoAddress kakaoAddress;

    @JsonProperty("road_address")
    private KakaoRoadAddress kakaoRoadAddress;

    private String x;

    private String y;
}
