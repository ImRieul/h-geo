package com.example.hgeo.address.dto.response.kakao;

import com.example.hgeo.address.vo.Address;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

@Getter
@ToString
@JsonNaming(SnakeCaseStrategy.class)
public class KakaoAddressResponse {

    @NotNull
    @JsonProperty("documents")
    private List<KakaoDocument> kakaoDocuments;

    @NotNull
    private KakaoMeta meta;

    public Address toAddress() {
        if (kakaoDocuments.isEmpty()) {
            return new Address();
        }

        KakaoDocument kakaoDocument = kakaoDocuments.get(0);
        KakaoAddress kakaoAddress = kakaoDocument.getKakaoAddress();
        KakaoRoadAddress kakaoRoadAddress = kakaoDocument.getKakaoRoadAddress();

        return Address.builder()
                .cityName(kakaoAddress.getRegion1depthName())
                .guName(kakaoAddress.getRegion2depthName())
                .legalDongName(kakaoAddress.getRegion3depthName())
                .adminDongName(kakaoAddress.getRegion3depthHName())
                .mainLotNumber(kakaoAddress.getMainAddressNo().isEmpty() ? 0 : Integer.parseInt(kakaoAddress.getMainAddressNo()))
                .subLotNumber(kakaoAddress.getSubAddressNo().isEmpty() ? 0 : Integer.parseInt(kakaoAddress.getSubAddressNo()))
                .adminCode(kakaoAddress.getHCode())
                .legalCode(kakaoAddress.getBCode())
                .lotXpoint(new BigDecimal(kakaoAddress.getX()))
                .lotYpoint(new BigDecimal(kakaoAddress.getY()))
                .roadName(kakaoRoadAddress.getRoadName())
                .mainBuildingNumber(kakaoRoadAddress.getMainBuildingNo().isEmpty() ? 0 : Integer.parseInt(kakaoRoadAddress.getMainBuildingNo()))
                .subBuildingNumber(kakaoRoadAddress.getSubBuildingNo().isEmpty() ? 0 : Integer.parseInt(kakaoRoadAddress.getSubBuildingNo()))
                .buildingName(kakaoRoadAddress.getBuildingName())
                .zipCode(kakaoRoadAddress.getZoneNo())
                .roadXpoint(new BigDecimal(kakaoRoadAddress.getX()))
                .roadYpoint(new BigDecimal(kakaoRoadAddress.getY()))
                .build();

    }

}
