package com.eqriesracingteam.garage.service;

import com.eqriesracingteam.garage.model.*;
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
import java.util.ArrayList;
import java.util.List;

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

    private static final BigDecimal vatPercentage = new BigDecimal(0.21);
    private static final BigDecimal vatPercentageToNetAmount = new BigDecimal(1.21);
    private static final BigDecimal inspectionFeeNoRepair = new BigDecimal(45);

    @Test
    void calculateTotalPriceOfRepair(){

        // Arrange
        Repair repair = new Repair();
        repair.setId(1l);

        Inventory inventoryItem1 = new Inventory();
        inventoryItem1.setPrice(BigDecimal.valueOf(100));
        inventoryItem1.setItemDescription("testitem");

        Inventory inventoryItem2 = new Inventory();
        inventoryItem1.setPrice(BigDecimal.valueOf(50));
        inventoryItem1.setItemDescription("unit2");

        RepairItems repairItems1 = new RepairItems();
        repairItems1.setRepair(repair);
        repairItems1.setInventoryItem(inventoryItem1);
        repairItems1.setAmount(2);

        RepairItems repairItems2 = new RepairItems();
        repairItems2.setRepair(repair);
        repairItems2.setInventoryItem(inventoryItem2);
        repairItems2.setAmount(3);

        List<RepairItems> repairItems = new ArrayList<>();
        repairItems.add(repairItems1);
        repairItems.add(repairItems2);
        System.out.println(repairItems);

        BigDecimal nettoPrice = new BigDecimal(0);
        for (RepairItems repairItem : repairItems){
            BigDecimal price = repairItem.getInventoryItem().getPrice();
            int amount = repairItem.getAmount();
            BigDecimal amountBD = BigDecimal.valueOf(amount);
            BigDecimal totalPrice = price.multiply(amountBD);
            nettoPrice = nettoPrice.add(totalPrice);
        }

        // Arrange
        BigDecimal expected = BigDecimal.valueOf(100*2 + 3*50);
        BigDecimal found = nettoPrice;

        // Assert
        assertEquals(expected,found);



    }
    @Test
    void createInvoice_withNoApprovalForRepair() {
        // Arrange
        Appointment appointment = new Appointment();
        appointment.setId(1l);
        appointment.setAppointmentStatus(AppointmentStatus.NIET_UITVOEREN);
        Invoice invoice = new Invoice();
        invoice.setInvoicePaid(false);
        invoice.setInvoiceDate(LocalDate.now());


        // Act

        if (appointment.getAppointmentStatus() == AppointmentStatus.NIET_UITVOEREN){
            BigDecimal grossAmount = inspectionFeeNoRepair;
            BigDecimal calculatedNettoAmount = grossAmount.divide(vatPercentageToNetAmount, 2, RoundingMode.HALF_EVEN);
            BigDecimal calculatedVatAmount = calculatedNettoAmount.multiply(vatPercentage).setScale(2,RoundingMode.HALF_EVEN);
            invoice.setGrossAmount(grossAmount);
            invoice.setNettoAmount(calculatedNettoAmount);
            invoice.setVatAmount(calculatedVatAmount);
        }

        BigDecimal expectedNetAmount = BigDecimal.valueOf(37.19);
        BigDecimal expectedVatAmount = BigDecimal.valueOf(7.81);
        BigDecimal expectedGrossAmount = BigDecimal.valueOf(45);

        BigDecimal foundNetAmount = invoice.getNettoAmount().setScale(2, RoundingMode.HALF_EVEN);
        BigDecimal foundVatAmount = invoice.getVatAmount().setScale(2, RoundingMode.HALF_EVEN);
        BigDecimal foundGrossAmount = invoice.getGrossAmount();
        // Assert

        assertEquals(expectedNetAmount, foundNetAmount );
        assertEquals(expectedVatAmount ,foundVatAmount);
        assertEquals(expectedGrossAmount,foundGrossAmount);
    }

    @Test
    void createInvoice_withApproval() {
        // Arrange
        Appointment appointment = new Appointment();
        appointment.setId(1l);
        appointment.setAppointmentStatus(AppointmentStatus.REPARATIE_UITGEVOERD);
        Invoice invoice = new Invoice();

        Repair repair = new Repair();
        repair.setId(1l);

        Inventory inventoryItem1 = new Inventory();
        inventoryItem1.setPrice(BigDecimal.valueOf(100));
        inventoryItem1.setItemDescription("testitem");

        Inventory inventoryItem2 = new Inventory();
        inventoryItem1.setPrice(BigDecimal.valueOf(50));
        inventoryItem1.setItemDescription("unit2");

        RepairItems repairItems1 = new RepairItems();
        repairItems1.setRepair(repair);
        repairItems1.setInventoryItem(inventoryItem1);
        repairItems1.setAmount(2);

        RepairItems repairItems2 = new RepairItems();
        repairItems2.setRepair(repair);
        repairItems2.setInventoryItem(inventoryItem2);
        repairItems2.setAmount(3);

        List<RepairItems> repairItems = new ArrayList<>();
        repairItems.add(repairItems1);
        repairItems.add(repairItems2);


        // Act

        if (appointment.getAppointmentStatus() == AppointmentStatus.REPARATIE_UITGEVOERD){
            BigDecimal grossAmount = inspectionFeeNoRepair;
            BigDecimal calculatedNettoAmount = grossAmount.divide(vatPercentageToNetAmount, 2, RoundingMode.HALF_EVEN);
            BigDecimal calculatedVatAmount = calculatedNettoAmount.multiply(vatPercentage).setScale(2,RoundingMode.HALF_EVEN);
            invoice.setGrossAmount(grossAmount);
            invoice.setNettoAmount(calculatedNettoAmount);
            invoice.setVatAmount(calculatedVatAmount);
        }

        BigDecimal expectedNetAmount = BigDecimal.valueOf(37.19);
        BigDecimal expectedVatAmount = BigDecimal.valueOf(7.81);
        BigDecimal expectedGrossAmount = BigDecimal.valueOf(45);

        BigDecimal foundNetAmount = invoice.getNettoAmount().setScale(2, RoundingMode.HALF_EVEN);
        BigDecimal foundVatAmount = invoice.getVatAmount().setScale(2, RoundingMode.HALF_EVEN);
        BigDecimal foundGrossAmount = invoice.getGrossAmount();
        // Assert

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