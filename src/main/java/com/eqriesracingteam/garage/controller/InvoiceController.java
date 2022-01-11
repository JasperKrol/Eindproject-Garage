package com.eqriesracingteam.garage.controller;

import com.eqriesracingteam.garage.dto.InvoiceDto;
import com.eqriesracingteam.garage.dto.InvoiceInputDto;
import com.eqriesracingteam.garage.model.Invoice;
import com.eqriesracingteam.garage.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    // Requests
    // Post request
    @PostMapping(value = "/api/garage/invoices")
    public InvoiceDto createInvoice(@RequestBody InvoiceInputDto dto){
        var invoice = invoiceService.createInvoice(dto.toInvoice());
        return InvoiceDto.fromInvoice(invoice);
    }

    // Get all
    // TODO: 11-1-2022 request param for invoice number or customer
    @GetMapping(value = "/api/garage/invoices")
    public List<InvoiceDto> getAllInvoices(){
        var dtos = new ArrayList<InvoiceDto>();
        var invoices = invoiceService.getAllInvoices();

        for (Invoice invoice : invoices) {
            dtos.add(InvoiceDto.fromInvoice(invoice));
        }
        return dtos;
    }
}
