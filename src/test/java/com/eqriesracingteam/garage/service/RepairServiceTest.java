package com.eqriesracingteam.garage.service;

import com.eqriesracingteam.garage.model.Appointment;
import com.eqriesracingteam.garage.model.AppointmentStatus;
import com.eqriesracingteam.garage.model.Inspection;
import com.eqriesracingteam.garage.model.Repair;
import com.eqriesracingteam.garage.repository.AppointmentRepository;
import com.eqriesracingteam.garage.repository.RepairRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class RepairServiceTest {

    @Mock
    RepairRepository repairRepository;

    @Mock
    RepairService repairService;

    @InjectMocks
    AppointmentRepository appointmentRepository;

    @InjectMocks
    InvoiceService invoiceService;

    @Mock
    Inspection inspection;


//    @Test
//    public void addInspection() {
//
//        Repair repair = new Repair();
//        repair.setAppointmentStatus(AppointmentStatus.NIET_UITVOEREN);
//        repair.setRepairDateWorkshop(LocalDateTime.now());
//        repair.setId(1L);
//
//
//
//
//        Appointment appointment = new Appointment();
//        appointment.setDescription("test");
//        appointment.setAppointmentStatus(AppointmentStatus.NIET_UITVOEREN);
//        LocalDateTime date = LocalDateTime.of(2020, Month.APRIL, 12, 22, 10);
//        appointment.setAppointmentDate(date);
//        appointment.setId(22L);
//
//
//        when(appointmentRepository.save(appointment)).thenReturn(appointment);
//
//        repair.setAppointment(appointment);
//        when(repairRepository.save(repair)).thenReturn(repair);
//
//        Repair expect = repair;
//        Repair found = repairService.createRepairAppointment(repair, appointment.getId());
//
//        assertEquals(expect, found);
//    }
}