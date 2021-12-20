package com.eqriesracingteam.garage.controller;

import com.eqriesracingteam.garage.dto.AuthenticationRequestDto;
import com.eqriesracingteam.garage.dto.AuthenticationResponseDto;
import com.eqriesracingteam.garage.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping(value = "/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequestDto authenticationRequestDto) {

        AuthenticationResponseDto authenticationResponseDto = authenticationService.authenticateUser(authenticationRequestDto);

        return ResponseEntity.ok(authenticationResponseDto);
    }

}
