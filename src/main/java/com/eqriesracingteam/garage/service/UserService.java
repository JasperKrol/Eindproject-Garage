package com.eqriesracingteam.garage.service;

import com.eqriesracingteam.garage.exceptions.BadRequestException;
import com.eqriesracingteam.garage.model.User;
import com.eqriesracingteam.garage.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //Handling the requests
    public Iterable<User> getUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUser(String username) {
        return userRepository.findById(username);
    }

//    public User createUser(User user) {
//        String username = user.getUsername();
//        List<User> users = userRepository.findAllByUsernameContainingAndIgnoreCase(username);
//        if (users.size() > 0) {
//            throw new BadRequestException("User already exists");
//        }
//        User newUser = userRepository.save(user);
//        return newUser;
//    }
}
