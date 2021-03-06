package com.eqriesracingteam.garage.repository;

import com.eqriesracingteam.garage.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//Passes through String -> username is the id and is String
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findById(String s);
}
