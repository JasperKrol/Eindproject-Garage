package com.eqriesracingteam.garage.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "facturen")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long invoiceNumber;

    private BigDecimal grossAmount;
    private BigDecimal vatAmount;
    private BigDecimal nettoAmount;

    private boolean invoicePaid;

    // TODO: 10-1-2022 Customer / repair relation
    // one invoice can have one customer
    // one invoice at one repairList met onderdelen en daar over loopen

    private static final BigDecimal vatPercentage = new BigDecimal("0.21");
}
