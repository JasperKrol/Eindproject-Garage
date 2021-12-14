package com.eqriesracingteam.garage.repository;

import com.eqriesracingteam.garage.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <User, String> {
}
