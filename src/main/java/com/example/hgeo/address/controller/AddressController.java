package com.example.hgeo.address.controller;

import com.example.hgeo.address.dto.request.kakao.KakaoAddressRequest;
import com.example.hgeo.address.dto.request.sgis.SgisAddressRequest;
import com.example.hgeo.address.service.AddressService;
import com.example.hgeo.address.vo.Address;
import com.example.hgeo.token.service.TokenService;
import com.example.hgeo.token.vo.Token;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class AddressController {

    private final AddressService sgisAddressService;

    private final AddressService kakaoAddressService;

    private final TokenService sgisTokenService;

    @GetMapping("/address")
    public List<Address> getAddress(@RequestParam List<String> address) {
        Token token = sgisTokenService.get();

        // TODO response 필드에 검색어 추가

        return sgisAddressService.findAllByAddressName(address.stream().map(
                addressString -> new SgisAddressRequest(token, addressString)).collect(Collectors.toList()))
                .block();
    }

    @GetMapping("/address/kakao")
    public List<Address> getAddressKakao(@RequestParam List<String> address) {
        return kakaoAddressService.findAllByAddressName(address.stream().map(
                        KakaoAddressRequest::new).collect(Collectors.toList()))
                .block();
    }
}
