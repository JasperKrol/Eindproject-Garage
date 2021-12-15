package com.eqriesracingteam.garage.controller;

import com.eqriesracingteam.garage.dto.UserDto;
import com.eqriesracingteam.garage.dto.UserInputDto;

import com.eqriesracingteam.garage.dto.UserPostRequestDto;
import com.eqriesracingteam.garage.exceptions.BadRequestException;
import com.eqriesracingteam.garage.model.User;
import com.eqriesracingteam.garage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/garage/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Requests

    // Get
    // -- All
    @GetMapping(value = "")
    public ResponseEntity<Object> getUsers() {
        return ResponseEntity.ok().body(userService.getUsers());
    }

    // -- one by username, id is username
    @GetMapping(value = "/{username}")
    public ResponseEntity<Object> getUser(@PathVariable("username") String username) {
        return ResponseEntity.ok().body(userService.getUser(username));
    }

    @GetMapping(value = "/{username}/authorities")
    public ResponseEntity<Object> getUserAuthorities(@PathVariable("username") String username) {
        return ResponseEntity.ok().body(userService.getAuthorities(username));
    }

    // Post
    // TODO: 15-12-2021 working request
    @PostMapping(value = "")
    public ResponseEntity<Object> createUser(@RequestBody UserPostRequestDto user) {
        String newUsername = userService.createUser(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{username}").buildAndExpand(newUsername).toUri();

        return ResponseEntity.created(location).build();
    }

    // TODO: 15-12-2021 testing
//    @PostMapping(value = "")
//    public UserDto createUser(@RequestBody UserInputDto dto) {
//        var user = userService.createUser(dto.toUser());
//        return UserDto.fromUser(user);
//    }

    // Put
    @PutMapping(value = "/{username}")
    public ResponseEntity<Object> updateUser(@PathVariable("username") String username, @RequestBody User user) {
        userService.updateUser(username, user);
    return ResponseEntity.noContent().build();
    }
    // Delete
    @DeleteMapping(value = "/{username}")
    public ResponseEntity<Object> deleteUser(@PathVariable("username") String username) {
        userService.deleteUser(username);
        return ResponseEntity.noContent().build();
    }

    // Assign
    @PostMapping(value = "/{username}/authorities")
    public ResponseEntity<Object> addUserAuthority(@PathVariable("username") String username, @RequestBody Map<String, Object> fields) {
        try {
            String authorityName = (String) fields.get("authority");
            userService.addAuthority(username, authorityName);
            return ResponseEntity.noContent().build();
        }
        catch (Exception ex) {
            throw new BadRequestException();
        }
    }

    @DeleteMapping(value = "/{username}/authorities/{authority}")
    public ResponseEntity<Object> deleteUserAuthority(@PathVariable("username") String username, @PathVariable("authority") String authority) {
        userService.removeAuthority(username, authority);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(value = "/{username}/password")
    public ResponseEntity<Object> setPassword(@PathVariable("username") String username, @RequestBody String password) {
        userService.setPassword(username, password);
        return ResponseEntity.noContent().build();
    }


}
