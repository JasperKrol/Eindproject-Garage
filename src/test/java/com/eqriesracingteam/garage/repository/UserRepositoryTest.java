package com.eqriesracingteam.garage.repository;

import com.eqriesracingteam.garage.GarageApplication;
import com.eqriesracingteam.garage.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ContextConfiguration(classes={GarageApplication.class})
@EnableConfigurationProperties
class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    void testFindByID(){
        // given
        User testUser = new User();
        testUser.setUsername("Testy");
        testUser.setPassword("Password123");
        testUser.setTelephoneNumber("06565656");
        testUser.setEnabled(true);
        testUser.setEmail("test@test.nl");
        entityManager.persist(testUser);
        entityManager.flush();

        // when
        Optional<User> found = userRepository.findById("testy");

        // then
        String expected = "Testy";
        String actual = found.get().getUsername();

        assertEquals(expected,actual);
    }

}