package com.eqriesracingteam.garage.service;

import com.eqriesracingteam.garage.exceptions.AppointmentException;
import com.eqriesracingteam.garage.exceptions.BadRequestException;
import com.eqriesracingteam.garage.model.*;
import com.eqriesracingteam.garage.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {

    private InvoiceRepository invoiceRepository;
    private AppointmentRepository appointmentRepository;
    private RepairRepository repairRepository;
    private CustomerRepository customerRepository;
    private RepairsItemsRepository repairsItemsRepository;


    @Autowired
    public InvoiceService(InvoiceRepository invoiceRepository, AppointmentRepository appointmentRepository, RepairRepository repairRepository, CustomerRepository customerRepository, RepairsItemsRepository repairsItemsRepository) {
        this.invoiceRepository = invoiceRepository;
        this.appointmentRepository = appointmentRepository;
        this.repairRepository = repairRepository;
        this.customerRepository = customerRepository;
        this.repairsItemsRepository = repairsItemsRepository;
    }

    private static final BigDecimal vatPercentage = new BigDecimal(0.21);
    private static final BigDecimal vatPercentageForCalculation = new BigDecimal(1.21);
    private static final BigDecimal inspectionFeeNoRepair = new BigDecimal(45);

    //    BigDecimal nettoAmount = without vat
    //    BigDecimal grossAmount = with vat

    // Methods
    public Invoice createInvoice(Long appointmentId, Long repairId) {


        var appointment = appointmentRepository.getById(appointmentId);
        Repair executedRepair = repairRepository.getById(repairId);
        repairId = executedRepair.getId();

        boolean approvalCustomer = approvalCustomer(appointment);
        boolean repairAndInspectionOk = statusCheck(appointment);

        var invoice = new Invoice();

        invoice.setInvoicePaid(false);
        invoice.setInvoiceDate(LocalDate.now());

        if (appointmentRepository.existsById(appointmentId) && repairAndInspectionOk) {
            if (!approvalCustomer) {
                //                var customer = appointment.getCustomer();
                BigDecimal grossAmount = inspectionFeeNoRepair;
                BigDecimal calculatedNettoAmount = grossAmount.divide(vatPercentageForCalculation, 2, RoundingMode.HALF_EVEN);
                BigDecimal calculatedVatAmount = calculatedNettoAmount.multiply(vatPercentage).setScale(2, RoundingMode.HALF_EVEN);

                invoice.setGrossAmount(grossAmount);
                invoice.setNettoAmount(calculatedNettoAmount);
                invoice.setVatAmount(calculatedVatAmount);
                appointment.setAppointmentStatus(AppointmentStatus.FACTUUR_AANGEMAAKT);
                //                invoice.setCustomer(customer);

                return invoiceRepository.save(invoice);

            } else if (approvalCustomer) {

                List<RepairItems> repairItems = repairsItemsRepository.findAllByRepairId(repairId);

                BigDecimal calculatedNettoAmount = calculatePartsCharge(repairItems);
                BigDecimal calculatedVatAmount = calculatedNettoAmount.multiply(vatPercentage).setScale(2, RoundingMode.HALF_EVEN);
                BigDecimal calculatedGrossAmount = calculatedNettoAmount.add(calculatedVatAmount).setScale(2, RoundingMode.HALF_EVEN);

                invoice.setNettoAmount(calculatedNettoAmount);
                invoice.setVatAmount(calculatedVatAmount);
                invoice.setGrossAmount(calculatedGrossAmount);

                appointment.setAppointmentStatus(AppointmentStatus.FACTUUR_AANGEMAAKT);

                return invoiceRepository.save(invoice);
            } else {
                throw new AppointmentException("Appointment status not ready");
            }
        }
        return invoice;
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

    public BigDecimal calculatePartsCharge(List<RepairItems> repairItems) {
        BigDecimal partsCharge = new BigDecimal(0);
        for (RepairItems repairItem : repairItems) {

            BigDecimal price = repairItem.getInventoryItem().getPrice();
            int amount = repairItem.getAmount();
            BigDecimal amountBD = BigDecimal.valueOf(amount);
            BigDecimal charge = price.multiply(amountBD);
            partsCharge = partsCharge.add(charge);
        }
        return partsCharge;
    }
}
