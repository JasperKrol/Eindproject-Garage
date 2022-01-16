package com.eqriesracingteam.garage.service;

import com.eqriesracingteam.garage.GarageApplication;
import com.eqriesracingteam.garage.model.RepairItems;
import com.eqriesracingteam.garage.repository.RepairsItemsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ContextConfiguration(classes = {GarageApplication.class})
@EnableConfigurationProperties
class RepairsItemsServiceTest {

    @Autowired
    private RepairsItemsService repairsItemsService;

    @MockBean
    private RepairsItemsRepository repairsItemsRepository;

    @Mock
    RepairItems repairItems;

    @BeforeEach
    public void setUp() {
    }
}