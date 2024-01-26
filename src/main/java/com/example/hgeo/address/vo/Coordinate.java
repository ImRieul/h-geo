package com.example.hgeo.address.vo;

import lombok.Getter;

import java.math.BigDecimal;


@Getter
public class Coordinate {

    private BigDecimal x;

    private BigDecimal y;

    private CoordinateSystemEnum coordinateSystem;

    @Getter
    public enum CoordinateSystemEnum {

        WGS84("WGS84"),
        EPSG5179("EPSG5179");

        private final String type;

        CoordinateSystemEnum(String type) {
            this.type = type;
        }

    }
}
