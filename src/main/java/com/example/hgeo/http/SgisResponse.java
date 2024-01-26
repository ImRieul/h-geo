package com.example.hgeo.http;


import lombok.Getter;

@Getter
public class SgisResponse<T> {

    private String id;

    private T result;

    private String errMsg;

    private Integer errCd;

    private String trId;

}
