package com.eqriesracingteam.garage.dto;

public class AuthenticationResponseDto {
    // Response for authentication -> jwt token
    private final String jwt;

    // constructor
    public AuthenticationResponseDto(String jwt) {
        this.jwt = jwt;
    }

    // getter
    public String getJwt() {
        return jwt;
    }
}
