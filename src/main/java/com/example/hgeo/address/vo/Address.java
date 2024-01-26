package com.example.hgeo.address.vo;

import lombok.*;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class Address {

    private String cityCode;
    private String cityName;
    private String guName;
    private String legalCode;
    private String legalDongName;
    private String adminCode;
    private String adminDongName;
    private Integer mainLotNumber;
    private Integer subLotNumber;

    // TODO 좌표 타입 변경 (Enum)
    private BigDecimal lotXpoint;
    private BigDecimal lotYpoint;

    private String roadName;
    private Integer mainBuildingNumber;
    private Integer subBuildingNumber;
    private String buildingName;
    private String zipCode;
    private BigDecimal roadXpoint;
    private BigDecimal roadYpoint;

}
