package com.eqriesracingteam.garage.dto;

import com.eqriesracingteam.garage.model.Customer;
import com.eqriesracingteam.garage.model.Invoice;
import com.eqriesracingteam.garage.model.Repair;

import java.math.BigDecimal;
import java.time.LocalDate;

public class InvoiceInputDto {

    public long invoiceNumber;
    public LocalDate invoiceDate;
    public BigDecimal grossAmount;
    public BigDecimal vatAmount;
    public BigDecimal nettoAmount;
    public boolean invoicePaid;

    public Customer customer;
    public Repair repair;

    public Invoice toInvoice(){
        var invoice = new Invoice();

        invoice.setInvoiceNumber(invoiceNumber);
        invoice.setInvoiceDate(invoiceDate);
        invoice.setGrossAmount(grossAmount);
        invoice.setVatAmount(vatAmount);
        invoice.setNettoAmount(nettoAmount);
        invoice.setInvoicePaid(invoicePaid);

        return invoice;
    }
}
