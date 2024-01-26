package com.example.hgeo.token.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.net.URI;

import static lombok.AccessLevel.PROTECTED;

@Getter
@Setter(PROTECTED)
@AllArgsConstructor
public class TokenRequest {

    private URI uri;

}
