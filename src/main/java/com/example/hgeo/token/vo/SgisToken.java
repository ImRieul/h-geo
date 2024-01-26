package com.example.hgeo.token.vo;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SgisToken extends Token {

    private static final Long LIMIT_TIME = 3L;

    private final LocalDateTime accessTimeout;

    public SgisToken(String accessToken, LocalDateTime accessTimeout) {
        super(accessToken);
        this.accessTimeout = accessTimeout;
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(accessTimeout.plusHours(LIMIT_TIME));
    }

    @Override
    public String getKey() {
        if (!isExpired()) {
            return super.getKey();
        }

        return super.getKey();
    }
}
