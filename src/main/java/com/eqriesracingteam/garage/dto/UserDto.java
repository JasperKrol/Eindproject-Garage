package com.eqriesracingteam.garage.dto;

import com.eqriesracingteam.garage.model.Authority;
import com.eqriesracingteam.garage.model.User;

import java.util.Set;

public class UserDto {
    public String username;
    public String password;
    public boolean enabled;
    public String email;
    public Set<Authority> authorities;

    public static UserDto fromUser(User user) {
        var dto = new UserDto();

        dto.username = user.getUsername();
        dto.password = user.getPassword();
        dto.enabled = user.isEnabled();
        dto.email = user.getEmail();
        dto.authorities = user.getAuthorities();

        return dto;
    }
}
