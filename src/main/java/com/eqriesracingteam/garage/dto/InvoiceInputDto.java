package com.eqriesracingteam.garage.dto;

import com.eqriesracingteam.garage.model.Customer;
import com.eqriesracingteam.garage.model.Invoice;
import com.eqriesracingteam.garage.model.Repair;

import java.math.BigDecimal;
import java.time.LocalDate;

public class InvoiceInputDto {

    long appointmentId;
    long repairId;
}
