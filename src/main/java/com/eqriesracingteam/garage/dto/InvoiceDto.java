package com.eqriesracingteam.garage.dto;

import com.eqriesracingteam.garage.model.Customer;
import com.eqriesracingteam.garage.model.Invoice;
import com.eqriesracingteam.garage.model.Repair;
import com.eqriesracingteam.garage.model.RepairItems;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;

public class InvoiceDto {

    public long invoiceNumber;
    public LocalDate invoiceDate;
    public BigDecimal grossAmount;
    public BigDecimal vatAmount;
    public BigDecimal nettoAmount;
    public boolean invoicePaid;

    // TODO: 10-1-2022 relations in dto zetten
    public Customer customer;
    public Repair repair;
//    public Collection<RepairItems> repairItems;

    public static InvoiceDto fromInvoice(Invoice invoice){
        var dto = new InvoiceDto();

        dto.invoiceNumber = invoice.getInvoiceNumber();
        dto.invoiceDate = invoice.getInvoiceDate();
        dto.grossAmount = invoice.getGrossAmount();
        dto.vatAmount = invoice.getVatAmount();
        dto.nettoAmount = invoice.getNettoAmount();
        dto.invoicePaid = invoice.isInvoicePaid();

        dto.customer = invoice.getCustomer();
        dto.repair = invoice.getRepair();
//        dto.repairItems = invoice.getRepairItems();

        return dto;
    }
}
