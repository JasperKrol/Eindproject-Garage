package com.eqriesracingteam.garage.service;

import com.eqriesracingteam.garage.exceptions.BadRequestException;
import com.eqriesracingteam.garage.model.Appointment;
import com.eqriesracingteam.garage.model.AppointmentStatus;
import com.eqriesracingteam.garage.model.Invoice;
import com.eqriesracingteam.garage.model.Repair;
import com.eqriesracingteam.garage.repository.AppointmentRepository;
import com.eqriesracingteam.garage.repository.InvoiceRepository;
import com.eqriesracingteam.garage.repository.RepairRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {

    private InvoiceRepository invoiceRepository;
    private AppointmentRepository appointmentRepository;
    private RepairRepository repairRepository;

    private static final BigDecimal vatPercentage = new BigDecimal("0.21");
    private static final BigDecimal vatPercentageToNetAmount = new BigDecimal("1.21");
    private static final BigDecimal inspectionFeeNoRepair = new BigDecimal(45);


    @Autowired
    public InvoiceService(InvoiceRepository invoiceRepository, AppointmentRepository appointmentRepository, RepairRepository repairRepository) {
        this.invoiceRepository = invoiceRepository;
        this.appointmentRepository = appointmentRepository;
        this.repairRepository = repairRepository;
    }

    // Methods
    public Invoice createInvoice(Invoice invoice, Long appointmentId, Long repairId) {
//        BigDecimal nettoAmount = new BigDecimal(0); // without vat
//        BigDecimal vatAmount = new BigDecimal(0);
//        BigDecimal grossAmount = new BigDecimal(45); // with vat

        Appointment appointment = appointmentRepository.getById(appointmentId);
        Repair ExecutedRepair = repairRepository.getById(repairId);
        boolean approvalCustomer = approvalCustomer(appointment);
        boolean repairAndInspectionOk = statusCheck(appointment);

        invoice.setInvoicePaid(false);
        invoice.setInvoiceDate(LocalDate.now());

        if (repairAndInspectionOk) {
            if (!approvalCustomer) {
                BigDecimal grossAmount = inspectionFeeNoRepair;
                BigDecimal calculatedNettoAmount = grossAmount.divide(vatPercentageToNetAmount, 2, RoundingMode.HALF_EVEN);
                BigDecimal calculatedVatAmount = calculatedNettoAmount.multiply(vatPercentage).setScale(2,RoundingMode.HALF_EVEN);

                invoice.setGrossAmount(grossAmount);
                invoice.setNettoAmount(calculatedNettoAmount);
                invoice.setVatAmount(calculatedVatAmount);
            } else {

            }
        }

        return invoiceRepository.save(invoice);
    }


    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    public Invoice getOneInvoice(long invoiceNumber) {
        Optional<Invoice> optionalInvoice = invoiceRepository.findById(invoiceNumber);

        if (optionalInvoice.isPresent()) {
            return optionalInvoice.get();
        } else {
            throw new BadRequestException("Invoice with invoice number " + invoiceNumber + " not found");
        }
    }

    public void adjustInvoice(long invoiceNumber, Invoice invoice) {
        Optional<Invoice> optionalInvoice = invoiceRepository.findById(invoiceNumber);

        if (optionalInvoice.isPresent()) {
            Invoice existingInvoice = optionalInvoice.get();

            invoice.setInvoiceNumber(existingInvoice.getInvoiceNumber());
            invoiceRepository.save(invoice);
        } else {
            throw new BadRequestException("Invoice with invoice number" + invoiceNumber + " not found");
        }
    }

    public void deleteInvoice(long invoiceNumber) {
        if (invoiceRepository.existsById(invoiceNumber)) {
            invoiceRepository.deleteById(invoiceNumber);
        } else {
            throw new BadRequestException("Invoice with invoice number" + invoiceNumber + " not found");
        }
    }

    // TODO: 11-1-2022 assingments to invoice customer appointment

    public boolean statusCheck(Appointment appointment) {
        AppointmentStatus status = appointment.getAppointmentStatus();
        if (status == AppointmentStatus.REPARATIE_UITGEVOERD || status == AppointmentStatus.NIET_UITVOEREN) {
            return true;
        }
        return false;
    }

    public boolean approvalCustomer(Appointment appointment) {
        AppointmentStatus status = appointment.getAppointmentStatus();
        if (status == AppointmentStatus.NIET_UITVOEREN) {
            return false;
        }
        return true;
    }
}
