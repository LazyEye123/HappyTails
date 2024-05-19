package com.happytails.springserver.controller;

import com.happytails.springserver.dto.CustomerDTO;
import com.happytails.springserver.service.CustomerService;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1.0")
public class AuthorizationController {
    private final CustomerService customerService;

    @PostMapping("/authorization")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDTO authorization(@RequestBody CustomerDTO customerDTO) throws IOException, GeoIp2Exception {
        var response = customerService.getUserLocation(customerDTO.getAddressWalk());
        return CustomerDTO
                .builder()
                .addressWalk(String.format("Ваш город - %s?", response
                        .getCity()
                        .getNames()
                        .get("ru")))
                .build();
    }
}
