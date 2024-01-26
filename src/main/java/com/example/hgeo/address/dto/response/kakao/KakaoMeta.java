package com.example.hgeo.address.dto.response.kakao;

import com.fasterxml.jackson.annotation.JsonProperty;

public class KakaoMeta {

    @JsonProperty("is_end")
    boolean isEnd;

    @JsonProperty("pageable_count")
    int pageableCount;

    @JsonProperty("total_count")
    int totalCount;

}
