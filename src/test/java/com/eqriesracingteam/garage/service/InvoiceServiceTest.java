package com.eqriesracingteam.garage.service;

import com.eqriesracingteam.garage.model.Appointment;
import com.eqriesracingteam.garage.model.AppointmentStatus;
import com.eqriesracingteam.garage.repository.AppointmentRepository;
import com.eqriesracingteam.garage.repository.InvoiceRepository;
import com.eqriesracingteam.garage.repository.RepairRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class InvoiceServiceTest {

    @Mock
    InvoiceRepository invoiceRepository;
    @Mock
    AppointmentRepository appointmentRepository;
    @Mock
    RepairRepository repairRepository;

    @InjectMocks
    InvoiceService invoiceService;

    @Test
    void createInvoice() {
    }

    @Test
    void statusCheck() {
    }

    @Test
    void approvalCustomer() {
        // Arrange
        Appointment appointment = new Appointment();
        appointment.setAppointmentStatus(AppointmentStatus.AKKOORD_KLANT);

        // Act
        boolean statusOk = invoiceService.approvalCustomer(appointment);

        // Assert
        assertTrue(statusOk);
    }
}