package com.eqriesracingteam.garage.service;

import com.eqriesracingteam.garage.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;
}
