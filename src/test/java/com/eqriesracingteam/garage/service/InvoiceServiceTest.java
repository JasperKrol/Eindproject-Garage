package com.eqriesracingteam.garage.service;

import com.eqriesracingteam.garage.model.Appointment;
import com.eqriesracingteam.garage.model.AppointmentStatus;
import com.eqriesracingteam.garage.model.Invoice;
import com.eqriesracingteam.garage.model.Repair;
import com.eqriesracingteam.garage.repository.AppointmentRepository;
import com.eqriesracingteam.garage.repository.InvoiceRepository;
import com.eqriesracingteam.garage.repository.RepairRepository;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

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

    private static final BigDecimal vatPercentage = new BigDecimal("0.21");
    private static final BigDecimal vatPercentageToNetAmount = new BigDecimal("1.21");

    @Test
    void createInvoice() {
        // Arrange
        Appointment appointment = new Appointment();
        appointment.setAppointmentStatus(AppointmentStatus.NIET_UITVOEREN);
        Invoice invoice = new Invoice();

        BigDecimal nettoAmount = new BigDecimal(37.19); // without vat
        BigDecimal vatAmount = new BigDecimal(7.81);
        BigDecimal grossAmount = new BigDecimal(45); // with vat
        invoice.setInvoicePaid(false);
        invoice.setInvoiceDate(LocalDate.now());


        // Act

        if (appointment.getAppointmentStatus() == AppointmentStatus.NIET_UITVOEREN){

            invoice.setNettoAmount(nettoAmount);
            invoice.setVatAmount(vatAmount);
            invoice.setGrossAmount(grossAmount);
        }

        BigDecimal expectedNetAmount = BigDecimal.valueOf(37.19);
        BigDecimal expectedVatAmount = BigDecimal.valueOf(7.81);
        BigDecimal expectedGrossAmount = BigDecimal.valueOf(45);

        // Assert
        BigDecimal foundNetAmount = invoice.getNettoAmount().setScale(2, RoundingMode.HALF_EVEN);
        BigDecimal foundVatAmount = invoice.getVatAmount().setScale(2, RoundingMode.HALF_EVEN);
        BigDecimal foundGrossAmount = invoice.getGrossAmount();

        assertEquals(expectedNetAmount, foundNetAmount );
        assertEquals(expectedVatAmount ,foundVatAmount);
        assertEquals(expectedGrossAmount,foundGrossAmount);
    }

    @Test
    void statusCheck() {
        // Arrange
        Appointment appointment = new Appointment();
        appointment.setAppointmentStatus(AppointmentStatus.NIET_UITVOEREN);

        // Act
        boolean statusOk = invoiceService.statusCheck(appointment);

        // Assert
        assertTrue(statusOk);
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