package com.eqriesracingteam.garage.repository;

import com.eqriesracingteam.garage.GarageApplication;
import com.eqriesracingteam.garage.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ContextConfiguration(classes={GarageApplication.class})
@EnableConfigurationProperties
class UserRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    void testFindByID(){
        // given
        User testUser = new User("Jasper", "password", true, "061233244", "test1@email", "ROLE_USER");
    }

}