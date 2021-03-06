package com.eqriesracingteam.garage.controller;

import com.eqriesracingteam.garage.dto.InvoiceDto;
import com.eqriesracingteam.garage.dto.InvoiceInputDto;
import com.eqriesracingteam.garage.model.Invoice;
import com.eqriesracingteam.garage.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    // Requests
    // Post request
    @PostMapping(value = "/api/garage/invoices")
    public InvoiceDto createInvoice(@RequestBody InvoiceInputDto input) {
        var invoice = invoiceService.createInvoice(input.repairId);
        return InvoiceDto.fromInvoice(invoice);
    }

    // Get all
    @GetMapping(value = "/api/garage/invoices")
    public List<InvoiceDto> getAllInvoices() {
        var dtos = new ArrayList<InvoiceDto>();
        var invoices = invoiceService.getAllInvoices();

        for (Invoice invoice : invoices) {
            dtos.add(InvoiceDto.fromInvoice(invoice));
        }
        return dtos;
    }

    @GetMapping(value = "/api/garage/invoices/{id}")
    public InvoiceDto getOneInvoice(@PathVariable("id") long invoiceNumber) {
        var invoice = invoiceService.getOneInvoice(invoiceNumber);
        return InvoiceDto.fromInvoice(invoice);
    }

    // Adjust
    @PutMapping(value = "/api/garage/invoices/{id}")
    public InvoiceDto adjustInvoice(@PathVariable("id") long invoiceNumber, @RequestBody Invoice invoice) {
        invoiceService.adjustInvoice(invoiceNumber, invoice);
        return InvoiceDto.fromInvoice(invoice);

    }

    // Delete appointment
    @DeleteMapping(value = "/api/garage/invoices/{id}")
    public void deleteInvoice(@PathVariable("id") long invoiceNumber) {
        invoiceService.deleteInvoice(invoiceNumber);
    }

}
