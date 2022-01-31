package com.eqriesracingteam.garage.service;

import com.eqriesracingteam.garage.exceptions.AppointmentException;
import com.eqriesracingteam.garage.exceptions.BadRequestException;
import com.eqriesracingteam.garage.exceptions.RecordNotFoundException;
import com.eqriesracingteam.garage.model.*;
import com.eqriesracingteam.garage.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {

    private static final BigDecimal vatPercentage = new BigDecimal(0.21);
    private static final BigDecimal vatPercentageForCalculation = new BigDecimal(1.21);
    private static final BigDecimal inspectionFeeNoRepair = new BigDecimal(45);

    private final InvoiceRepository invoiceRepository;
    private final AppointmentRepository appointmentRepository;
    private final RepairRepository repairRepository;
    private final CustomerRepository customerRepository;
    private final RepairsItemsRepository repairsItemsRepository;

    @Autowired
    public InvoiceService(InvoiceRepository invoiceRepository, AppointmentRepository appointmentRepository, RepairRepository repairRepository, CustomerRepository customerRepository, RepairsItemsRepository repairsItemsRepository) {
        this.invoiceRepository = invoiceRepository;
        this.appointmentRepository = appointmentRepository;
        this.repairRepository = repairRepository;
        this.customerRepository = customerRepository;
        this.repairsItemsRepository = repairsItemsRepository;
    }

    //    BigDecimal nettoAmount = without vat
    //    BigDecimal grossAmount = with vat

    // TODO: 31-1-2022 Clean up code in create invoice with cleancode practices 

    // Methods
    public Invoice createInvoice(Long repairId) {

        Optional<Repair> optionalRepair = repairRepository.findById(repairId);

        Repair executedRepair = optionalRepair.orElseThrow(() -> new RecordNotFoundException("Repair with given id not found"));

        var appointment = executedRepair.getAppointment();

        var invoice = new Invoice();
        var customer = appointment.getCustomer();

        boolean approvalCustomer = approvalCustomer(appointment);
        boolean repairAndInspectionOk = statusCheck(appointment);

        invoice.setInvoicePaid(false);
        invoice.setInvoiceDate(LocalDate.now());

        if (repairAndInspectionOk) {
            if (!approvalCustomer) {

                BigDecimal grossAmount = inspectionFeeNoRepair;
                BigDecimal calculatedNettoAmount = grossAmount.divide(vatPercentageForCalculation, 2, RoundingMode.HALF_EVEN);
                BigDecimal calculatedVatAmount = calculatedNettoAmount.multiply(vatPercentage).setScale(2, RoundingMode.HALF_EVEN);

                invoice.setGrossAmount(grossAmount);
                invoice.setNettoAmount(calculatedNettoAmount);
                invoice.setVatAmount(calculatedVatAmount);
                appointment.setAppointmentStatus(AppointmentStatus.FACTUUR_AANGEMAAKT);
                invoice.setCustomer(customer);

                return invoiceRepository.save(invoice);
            }
            if (approvalCustomer) {

                List<RepairItems> repairItems = repairsItemsRepository.findAllByRepairId(repairId);

                BigDecimal calculatedNettoAmount = calculateTotalAmountOfPartsUsed(repairItems);
                BigDecimal calculatedVatAmount = calculatedNettoAmount.multiply(vatPercentage).setScale(2, RoundingMode.HALF_EVEN);
                BigDecimal calculatedGrossAmount = calculatedNettoAmount.add(calculatedVatAmount).setScale(2, RoundingMode.HALF_EVEN);

                invoice.setNettoAmount(calculatedNettoAmount);
                invoice.setVatAmount(calculatedVatAmount);
                invoice.setGrossAmount(calculatedGrossAmount);
                invoice.setCustomer(customer);

                invoice.setRepair(executedRepair);
                appointment.setAppointmentStatus(AppointmentStatus.FACTUUR_AANGEMAAKT);

                return invoiceRepository.save(invoice);
            }
        } else {
            throw new AppointmentException("please check for approval or appointment status");
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
            invoice.setCustomer(existingInvoice.getCustomer());
            invoice.setRepair(existingInvoice.getRepair());
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

    public boolean statusCheck(Appointment appointment) {
        AppointmentStatus status = appointment.getAppointmentStatus();
        return status == AppointmentStatus.REPARATIE_UITGEVOERD || status == AppointmentStatus.NIET_UITVOEREN;
    }

    public boolean approvalCustomer(Appointment appointment) {
        AppointmentStatus status = appointment.getAppointmentStatus();
        return status != AppointmentStatus.NIET_UITVOEREN;
    }

    public BigDecimal calculateTotalAmountOfPartsUsed(List<RepairItems> repairItems) {
        BigDecimal totalAmount = new BigDecimal(0);
        for (RepairItems repairItem : repairItems) {

            BigDecimal price = repairItem.getInventoryItem().getPrice();
            int amount = repairItem.getAmount();
            BigDecimal amountBD = BigDecimal.valueOf(amount);
            BigDecimal charge = price.multiply(amountBD);
            totalAmount = totalAmount.add(charge);
        }
        return totalAmount;
    }
}
