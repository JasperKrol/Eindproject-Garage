package com.eqriesracingteam.garage.dto;

import com.eqriesracingteam.garage.model.Authority;
import com.eqriesracingteam.garage.model.User;

import java.util.Set;

public class UserInputDto {
    public String username;
    public String password;
    public boolean enabled;
    private String telephoneNumber;
    public String email;
    public Set<Authority> authority;

    public User toUser() {
        var user = new User();

        user.setUsername(username);
        user.setPassword(password);
        user.setEnabled(enabled);
        user.setEmail(email);
        user.setTelephoneNumber(telephoneNumber);
        user.setAuthorities(authority);
        return user;
    }
}
