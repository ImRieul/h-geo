package com.example.hgeo.address.dto.response.sgis;

import com.example.hgeo.address.vo.Address;
import com.fasterxml.jackson.databind.PropertyNamingStrategy.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;

@Getter
@JsonNaming(SnakeCaseStrategy.class)
public class SgisAddress {

    private String sidoCd;                  // 시도코드 (법정동)
    private String sidoNm;                  // 시도명
    private String sggCd;                   // 시군구코드 (법정동)
    private String sggNm;                   // 시군구명
    private String admCd;                   // 행정동코드
    private String admNm;                   // 행정동명
    private String legCd;                   // 법정동코드 (읍면동)
    private String legNm;                   // 법정동명 (읍면동)
    private String riCd;                    // 리코드
    private String riNm;                    // 리명 (구 법정동일 경우)
    private String roadCd;                  // 도로명 코드
    private String roadNm;                  // 도로명
    private String roadNmMainNo;                    // 건물 본번
    private String roadNmSubNo;                 // 건물 부번
    private String bdMainNm;                    // 건물본명
    private String bdSubNm;                 // 건물부명
    private String jibunMainNo;                 // 지번 본번
    private String jibunSubNo;                  // 지번 부번
    private String addrType;                    // 주소구분
    private String y;                   // Y좌표
    private String x;                   // X좌표

    @Deprecated
    private String bdMatches;           // 일치 여부..? api 문서에는 없음.

    @Deprecated
    private String originXy;           // 원점 좌표..?

    public Address toAddress() {
        return Address.builder()
                .cityCode(sidoCd)
                .cityName(sidoNm)
                .guName(sggNm)
                .legalCode(legCd)
                .legalDongName(legNm)
                .adminCode(admCd)
                .adminDongName(admNm)
                .mainLotNumber(jibunMainNo.isEmpty() ? null : Integer.parseInt(jibunMainNo))
                .subLotNumber(jibunSubNo.isEmpty() ? null : Integer.parseInt(jibunSubNo))
                .lotXpoint(x.isEmpty() ? null : new java.math.BigDecimal(x))
                .lotYpoint(y.isEmpty() ? null : new java.math.BigDecimal(y))
                .roadName(roadNm)
                .mainBuildingNumber(roadNmMainNo.isEmpty() ? null : Integer.parseInt(roadNmMainNo))
                .subBuildingNumber(roadNmSubNo.isEmpty() ? null : Integer.parseInt(roadNmSubNo))
                .buildingName(bdMainNm)
                .build();
    }

}
