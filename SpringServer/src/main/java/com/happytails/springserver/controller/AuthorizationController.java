package com.happytails.springserver.controller;

import com.happytails.springserver.dto.CustomerDTO;
import com.happytails.springserver.service.CustomerService;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1.0")
public class AuthorizationController {
    private final CustomerService customerService;

    @PostMapping("/authorization")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDTO authorization(HttpServletRequest request) throws IOException, GeoIp2Exception {
        String auth = request.getHeader("Authorization").substring(6);
        var arr = Base64.getDecoder().decode(auth);
        return customerService.getUserByUsername(new String(arr, StandardCharsets.UTF_8).split(":")[0]);
    }
}
