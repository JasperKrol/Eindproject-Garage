package com.eqriesracingteam.garage.dto;

import com.eqriesracingteam.garage.model.Invoice;

import java.math.BigDecimal;
import java.time.LocalDate;

public class InvoiceInputDto {

    public long invoiceNumber;
    public LocalDate invoiceDate;
    public BigDecimal grossAmount;
    public BigDecimal vatAmount;
    public BigDecimal nettoAmount;
    public boolean invoicePaid;

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
