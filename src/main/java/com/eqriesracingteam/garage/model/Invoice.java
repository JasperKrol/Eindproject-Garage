package com.eqriesracingteam.garage.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "facturen")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long invoiceNumber;

    private LocalDate invoiceDate;
    private BigDecimal nettoAmount; // without vat
    private BigDecimal vatAmount;
    private BigDecimal grossAmount; // with vat
    private boolean invoicePaid;

    // TODO: 10-1-2022 Customer / repair relation
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;


    // one invoice at one repairList met onderdelen en daar over loopen
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "repair_id", referencedColumnName = "id")
    private Repair repair;

    //    @OneToOne(fetch = FetchType.EAGER)
    //    @JoinColumn(name = "repair_id", referencedColumnName = "id")
    //    private Collection<RepairItems> repairItems;

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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Repair getRepair() {
        return repair;
    }

    public void setRepair(Repair repair) {
        this.repair = repair;
    }

    @Override
    public String toString() {
        return "Invoice{" + "invoiceNumber=" + invoiceNumber +
                ", invoiceDate=" + invoiceDate +
                ", nettoAmount=" + nettoAmount +
                ", vatAmount=" + vatAmount +
                ", grossAmount=" + grossAmount +
                ", invoicePaid=" + invoicePaid +
                ", customer=" + customer +
                ", repair=" + repair +
                '}';
    }
}
