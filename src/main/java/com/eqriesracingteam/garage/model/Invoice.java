package com.eqriesracingteam.garage.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "facturen")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long invoiceNumber;

    private LocalDate invoiceDate;
    private BigDecimal grossAmount;
    private BigDecimal vatAmount;
    private BigDecimal nettoAmount;
    private boolean invoicePaid;
    private static final BigDecimal vatPercentage = new BigDecimal("0.21");

    // TODO: 10-1-2022 Customer / repair relation
    // one invoice can have one customer
    // one invoice at one repairList met onderdelen en daar over loopen

    // Getters and setters


    public long getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(long invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public void setInvoiceDate(LocalDate invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public LocalDate getInvoiceDate() {
        return invoiceDate;
    }

    public BigDecimal getGrossAmount() {
        return grossAmount;
    }

    public void setGrossAmount(BigDecimal grossAmount) {
        this.grossAmount = grossAmount;
    }

    public BigDecimal getVatAmount() {
        return vatAmount;
    }

    public void setVatAmount(BigDecimal vatAmount) {
        this.vatAmount = vatAmount;
    }

    public BigDecimal getNettoAmount() {
        return nettoAmount;
    }

    public void setNettoAmount(BigDecimal nettoAmount) {
        this.nettoAmount = nettoAmount;
    }

    public boolean isInvoicePaid() {
        return invoicePaid;
    }

    public void setInvoicePaid(boolean invoicePaid) {
        this.invoicePaid = invoicePaid;
    }
}
