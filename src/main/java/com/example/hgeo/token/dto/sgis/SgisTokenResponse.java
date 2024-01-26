package com.example.hgeo.token.dto.sgis;

import com.example.hgeo.token.vo.SgisToken;
import lombok.Getter;

import jakarta.validation.constraints.NotNull;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Getter
public class SgisTokenResponse {

    @NotNull
    private String accessToken;

    @NotNull
    private String accessTimeout;

    public SgisToken toSgisToken() {
        LocalDateTime timeout = LocalDateTime.ofInstant(Instant.ofEpochSecond(Long.parseLong(accessTimeout)), ZoneId.systemDefault());
        return new SgisToken(accessToken, timeout);
    }

}
