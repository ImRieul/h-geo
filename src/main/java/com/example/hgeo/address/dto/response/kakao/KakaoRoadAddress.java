package com.example.hgeo.address.dto.response.kakao;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;

@Getter
@JsonNaming(SnakeCaseStrategy.class)
public class KakaoRoadAddress {

        private String addressName;

        @JsonProperty("region_1depth_name")
        private String region1depthName;

        @JsonProperty("region_2depth_name")
        private String region2depthName;

        @JsonProperty("region_3depth_name")
        private String region3depthName;

        private String roadName;

        private String undergroundYn;

        private String mainBuildingNo;

        private String subBuildingNo;

        private String buildingName;

        private String zoneNo;

        private String x;

        private String y;
}
