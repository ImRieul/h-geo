package com.example.hgeo.address.dto.response.sgis;


import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;

import java.util.List;

@Getter
@JsonNaming(PropertyNamingStrategy.LowerCaseStrategy.class)
public class SgisAddressResponse {

    private Integer totalCount;

    private Integer pageNum;

    private Integer returnCount;

    private Boolean matching;

    private List<SgisAddress> resultData;

    public void setMatching(String matching) {
        if ("0".equals(matching)) {
            this.matching = false;
        } else if ("1".equals(matching)) {
            this.matching = true;
        } else {
            this.matching = null;
        }
    }

}
