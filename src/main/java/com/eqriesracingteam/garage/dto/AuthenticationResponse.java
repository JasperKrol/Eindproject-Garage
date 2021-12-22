package com.eqriesracingteam.garage.dto;

public class AuthenticationResponse {
    // Response for authentication -> jwt token
    private final String jwt;

    public AuthenticationResponse(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }

}
