package com.example.hgeo.address.dto.response.kakao;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;

@Getter
@JsonNaming(SnakeCaseStrategy.class)
public class KakaoAddress {

    private String addressName;

    @JsonProperty("region_1depth_name")
    private String region1depthName;

    @JsonProperty("region_2depth_name")
    private String region2depthName;

    @JsonProperty("region_3depth_name")
    private String region3depthName;

    @JsonProperty("region_3depth_h_name")
    private String region3depthHName;

    private String hCode;

    private String bCode;

    private String mountainYn;

    private String mainAddressNo;

    private String subAddressNo;

    private String zipCode;

    private String x;

    private String y;

}
