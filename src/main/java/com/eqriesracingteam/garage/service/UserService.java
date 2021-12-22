package com.eqriesracingteam.garage.service;

import com.eqriesracingteam.garage.dto.UserDto;
import com.eqriesracingteam.garage.dto.UserPostRequestDto;
import com.eqriesracingteam.garage.exceptions.BadRequestException;
import com.eqriesracingteam.garage.exceptions.InvalidPasswordException;
import com.eqriesracingteam.garage.exceptions.NotAuthorizedException;
import com.eqriesracingteam.garage.exceptions.UserNotFoundException;
import com.eqriesracingteam.garage.model.Authority;
import com.eqriesracingteam.garage.model.User;
import com.eqriesracingteam.garage.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    private String getCurrentUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ((UserDetails) authentication.getPrincipal()).getUsername();
    }

    //Handling the requests
    public Iterable<User> getUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUser(String username) {
        return userRepository.findById(username);
    }

    // TODO: 15-12-2021 working
    public String createUser(UserPostRequestDto userPostRequest) {
        try {
            String encryptedPassword = passwordEncoder.encode(userPostRequest.getPassword());

            User user = new User();
            user.setUsername(userPostRequest.getUsername());
            user.setPassword(encryptedPassword);
            user.setEmail(userPostRequest.getEmail());
            user.setEnabled(true);
            user.addAuthority("ROLE_USER");
            for (String s : userPostRequest.getAuthorities()) {
                if (!s.startsWith("ROLE_")) {
                    s = "ROLE_" + s;
                }
                s = s.toUpperCase();
                if (!s.equals("ROLE_USER")) {
                    user.addAuthority(s);
                }
            }

            User newUser = userRepository.save(user);
            return newUser.getUsername();
        } catch (Exception ex) {
            throw new BadRequestException("Cannot create user.");
        }
    }

    // TODO: 15-12-2021 testing
    //    public User createUser(User user) {
    //
    //            String username = user.getUsername();
    //            Optional<User> existingUser = userRepository.findById(username);
    //
    //            if (existingUser.isPresent()) {
    //                throw new BadRequestException("Cannot create user.");
    //            } else {
    //                Authority authority = new Authority();
    //                authority.setUsername(user.getUsername());
    //                authority.setAuthority("ROLE_USER");
    //                user.addAuthority(authority);
    //                User newUser = userRepository.save(user);
    //
    //                return newUser;
    //            }
    //    }

    public void updateUser(String username, User user) {
        Optional<User> optionalUser = userRepository.findById(username);
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException(username + " not found");
        } else {
            User existingUser = optionalUser.get();
            existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
            existingUser.setEmail(user.getEmail());
            existingUser.setEnabled(user.isEnabled());
            userRepository.save(user);

        }
    }

    public void deleteUser(String username) {
        if (userRepository.existsById(username)) {
            userRepository.deleteById(username);
        } else {
            throw new UserNotFoundException(username + " not found");
        }
    }

    public Set<Authority> getAuthorities(String username) {
        Optional<User> optionalUser = userRepository.findById(username);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return user.getAuthorities();
        } else {
            throw new UserNotFoundException(username + " not found");
        }
    }

    public void addAuthority(String username, String authorityString) {
        Optional<User> userOptional = userRepository.findById(username);
        if (userOptional.isEmpty()) {
            throw new UserNotFoundException(username);
        } else {
            User user = userOptional.get();
            user.addAuthority(authorityString);
            userRepository.save(user);
        }
    }

    public void removeAuthority(String username, String authorityString) {
        Optional<User> userOptional = userRepository.findById(username);
        if (userOptional.isEmpty()) {
            throw new UserNotFoundException(username);
        } else {
            User user = userOptional.get();
            user.removeAuthority(authorityString);
            userRepository.save(user);
        }
    }

    private boolean isValidPassword(String password) {
        final int MIN_LENGTH = 8;
        final int MIN_DIGITS = 1;
        final int MIN_LOWER = 1;
        final int MIN_UPPER = 1;
        final int MIN_SPECIAL = 1;
        final String SPECIAL_CHARS = "@#$%&*!()+=-_";

        long countDigit = password.chars().filter(ch -> ch >= '0' && ch <= '9').count();
        long countLower = password.chars().filter(ch -> ch >= 'a' && ch <= 'z').count();
        long countUpper = password.chars().filter(ch -> ch >= 'A' && ch <= 'Z').count();
        long countSpecial = password.chars().filter(ch -> SPECIAL_CHARS.indexOf(ch) >= 0).count();

        boolean validPassword = true;
        if (password.length() < MIN_LENGTH) validPassword = false;
        if (countLower < MIN_LOWER) validPassword = false;
        if (countUpper < MIN_UPPER) validPassword = false;
        if (countDigit < MIN_DIGITS) validPassword = false;
        if (countSpecial < MIN_SPECIAL) validPassword = false;

        return validPassword;
    }

    public void setPassword(String username, String password) {
        if (username.equals(getCurrentUserName())) {
            if (isValidPassword(password)) {
                Optional<User> userOptional = userRepository.findById(username);
                if (userOptional.isPresent()) {
                    User user = userOptional.get();
                    user.setPassword(passwordEncoder.encode(password));
                    userRepository.save(user);
                } else {
                    throw new UserNotFoundException(username);
                }
            } else {
                throw new InvalidPasswordException();
            }
        } else {
            throw new NotAuthorizedException();
        }
    }
}
