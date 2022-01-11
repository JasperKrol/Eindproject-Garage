package com.eqriesracingteam.garage.service;

import com.eqriesracingteam.garage.exceptions.BadRequestException;
import com.eqriesracingteam.garage.model.Invoice;
import com.eqriesracingteam.garage.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {

    private InvoiceRepository invoiceRepository;

    @Autowired
    public InvoiceService(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    // Methods
    public Invoice createInvoice(Invoice invoice) {
        Long invoiceNumber = invoice.getInvoiceNumber();

        if (invoiceRepository.existsById(invoiceNumber)){
            throw new BadRequestException("Invoice with invoice number " + invoiceNumber + " already exists");
        } else {
            Invoice newInvoice = invoiceRepository.save(invoice);
            return newInvoice;
        }
    }

    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }
}
