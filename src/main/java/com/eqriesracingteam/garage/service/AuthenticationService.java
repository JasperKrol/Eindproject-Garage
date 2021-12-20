package com.eqriesracingteam.garage.service;

import com.eqriesracingteam.garage.dto.AuthenticationRequestDto;
import com.eqriesracingteam.garage.dto.AuthenticationResponseDto;
import com.eqriesracingteam.garage.exceptions.UserNotFoundException;
import com.eqriesracingteam.garage.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    public AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    JwtUtil jwtUtl;

    public AuthenticationResponseDto authenticateUser(AuthenticationRequestDto authenticationRequestDto) {

        //Get password and username
        String username = authenticationRequestDto.getUsername();
        String password = authenticationRequestDto.getPassword();

        //Check for authentication
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (BadCredentialsException exception) {
            throw new UserNotFoundException("Incorrect username or password");
        }

        //Get userDetails and create and token based on username and password to be authenticated
        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        final String jwt = jwtUtl.generateToken(userDetails);

        return new AuthenticationResponseDto(jwt);
    }

}


