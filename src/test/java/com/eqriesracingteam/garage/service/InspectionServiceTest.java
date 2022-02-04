package com.eqriesracingteam.garage.service;


import com.eqriesracingteam.garage.model.Inspection;
import com.eqriesracingteam.garage.model.InspectionStatus;
import com.eqriesracingteam.garage.repository.InspectionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InspectionServiceTest {

    @InjectMocks
    private InspectionService inspectionService;

    @Mock
    private InspectionRepository inspectionRepository;

    @Mock
    Inspection inspection;

    @Test
    void createInspection() {

        Inspection inspection = new Inspection();
        inspection.setFindings("test");
        inspection.setInspectionStatus(InspectionStatus.INSPECTIE_GEPLAND);
        LocalDateTime date = LocalDateTime.of(2020, Month.APRIL, 12, 22, 10);
        inspection.setInspectionDate(date);
        inspection.setId(22L);

        long id = inspection.getId();

        when(inspectionRepository.findById(id)).thenReturn(Optional.of(inspection));

        Inspection expected = inspection;
        Inspection found = inspectionService.getInspection(id);
        assertEquals(expected, found);
    }

}