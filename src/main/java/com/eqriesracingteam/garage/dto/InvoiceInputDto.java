package com.eqriesracingteam.garage.dto;

import com.eqriesracingteam.garage.model.Invoice;

import java.math.BigDecimal;

public class InvoiceInputDto {

    public long invoiceNumber;
    public BigDecimal grossAmount;
    public BigDecimal vatAmount;
    public BigDecimal nettoAmount;
    public boolean invoicePaid;

    public Invoice toInvoice(){
        var invoice = new Invoice();

        invoice.setInvoiceNumber(invoiceNumber);
        invoice.setGrossAmount(grossAmount);
        invoice.setVatAmount(vatAmount);
        invoice.setNettoAmount(nettoAmount);
        invoice.setInvoicePaid(invoicePaid);

        return invoice;
    }
}
