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

        invoice.setInvoicePaid(false);

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

    public Invoice getOneInvoice(long invoiceNumber) {
        Optional<Invoice> optionalInvoice = invoiceRepository.findById(invoiceNumber);

        if (optionalInvoice.isPresent()){
            return optionalInvoice.get();
        } else {
            throw new BadRequestException("Invoice with invoice number " + invoiceNumber + " not found");
        }
    }

    public void adjustInvoice(long invoiceNumber, Invoice invoice) {
        Optional<Invoice> optionalInvoice = invoiceRepository.findById(invoiceNumber);

        if (optionalInvoice.isPresent()){
            Invoice existingInvoice = optionalInvoice.get();

            invoice.setInvoiceNumber(existingInvoice.getInvoiceNumber());
            invoiceRepository.save(invoice);
        } else {
            throw new BadRequestException("Invoice with invoice number" + invoiceNumber + " not found");
        }
    }

    public void deleteInvoice(long invoiceNumber) {
        if (invoiceRepository.existsById(invoiceNumber)){
            invoiceRepository.deleteById(invoiceNumber);
        } else{
            throw new BadRequestException("Invoice with invoice number" + invoiceNumber + " not found");
        }
    }

    // TODO: 11-1-2022 assingments to invoice customer appointment
}
