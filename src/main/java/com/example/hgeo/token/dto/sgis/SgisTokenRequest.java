package com.example.hgeo.token.dto.sgis;


import com.example.hgeo.token.dto.TokenRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import static com.example.hgeo.http.ApiUriEnum.SGIS_KEY;


@Component
public class SgisTokenRequest extends TokenRequest {

    @Value("${sgis.key}")
    private String consumerKey;

    @Value("${sgis.secret}")
    private String consumerSecret;

    public SgisTokenRequest() {
        super(null);
    }

    @EventListener(ContextRefreshedEvent.class)
    public void init() {
        this.setUri(UriComponentsBuilder.fromUriString(SGIS_KEY.getUri())
                .queryParam("consumer_key", consumerKey)
                .queryParam("consumer_secret", consumerSecret)
                .build()
                .toUri());
    }
}
