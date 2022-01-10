package com.eqriesracingteam.garage.controller;

import com.eqriesracingteam.garage.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;


}
