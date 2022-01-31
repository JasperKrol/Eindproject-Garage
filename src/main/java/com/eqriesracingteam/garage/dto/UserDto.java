package com.eqriesracingteam.garage.dto;

import com.eqriesracingteam.garage.model.Authority;
import com.eqriesracingteam.garage.model.User;

import java.util.Set;

public class UserDto {
    private String username;
    private String password;
    private boolean enabled;
    private String telephoneNumber;
    private String email;
    private Set<Authority> authorities;

    public static UserDto fromUser(User user) {
        var dto = new UserDto();

        dto.username = user.getUsername();
        dto.password = user.getPassword();
        dto.enabled = user.isEnabled();
        dto.telephoneNumber = user.getTelephoneNumber();
        dto.email = user.getEmail();
        dto.authorities = user.getAuthorities();

        return dto;
    }
}
