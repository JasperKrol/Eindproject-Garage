package com.eqriesracingteam.garage.service;

import com.eqriesracingteam.garage.model.RegistrationPaper;
import com.eqriesracingteam.garage.repository.RegistrationPaperRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RegistrationPaperServiceTest {
    @InjectMocks
    private RegistrationPaperService registrationPaperService;

    @Mock
    private RegistrationPaperRepository registrationPaperRepository;

    @Mock
    RegistrationPaper registrationPaper;

    @Test
    public void getFileByFileName() {
        registrationPaper = new RegistrationPaper();
        registrationPaper.setName("vossen.jpeg");

        Mockito
                .when(registrationPaperRepository.findByNameEquals("vossen.jpeg"))
                .thenReturn(registrationPaper);

        String name = "vossen.jpeg";
        String expected = "vossen.jpeg";

        RegistrationPaper found = registrationPaperRepository.findByNameEquals(name);

        assertEquals(expected, found.getName());
    }

}