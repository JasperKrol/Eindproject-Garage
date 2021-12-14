package com.eqriesracingteam.garage.repository;

import com.eqriesracingteam.garage.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

//Passes through String -> username is the id and is String
public interface UserRepository extends JpaRepository <User, String> {
}
