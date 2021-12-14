package com.eqriesracingteam.garage.controller;

import com.eqriesracingteam.garage.dto.UserDto;
import com.eqriesracingteam.garage.dto.UserInputDto;

import com.eqriesracingteam.garage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Requests

    // Get
    // -- All
    @GetMapping(value = "/api/garage/users")
    public ResponseEntity<Object> getUsers(){
        return ResponseEntity.ok().body(userService.getUsers());
    }
    // -- one by username, id is username
    @GetMapping(value = "/api/garage/users/{username}")
    public ResponseEntity<Object> getUser(@PathVariable("username") String username){
        return ResponseEntity.ok().body(userService.getUser(username));
    }
    // Post
//    @PostMapping(value = "/api/garage/users")
//    public UserDto createUser(@RequestBody UserInputDto dto){
//        var user = userService.createUser(dto.toUser());
//        return UserDto.fromUser(user);
//    }
    // Put
    // Delete
    // Patch




    //Old Post
//    @PostMapping(value = "/api/garage/users")
    //    public ResponseEntity<Object> createUser(@RequestBody User user){
    //        String newUsername = userService.createUser(user);
    //
    //        URI location = ServletUriComponentsBuilder
    //                .fromCurrentRequest()
    //                .path("/{username}")
    //                .buildAndExpand(newUsername)
    //                .toUri();
    //
    //        return ResponseEntity.created(location).build();
    //    }
}
