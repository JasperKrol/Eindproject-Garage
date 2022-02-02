package com.eqriesracingteam.garage.service;

import com.eqriesracingteam.garage.exceptions.AppointmentException;
import com.eqriesracingteam.garage.exceptions.BadRequestException;
import com.eqriesracingteam.garage.exceptions.RecordNotFoundException;
import com.eqriesracingteam.garage.model.Appointment;
import com.eqriesracingteam.garage.model.AppointmentStatus;
import com.eqriesracingteam.garage.model.Car;
import com.eqriesracingteam.garage.model.Repair;
import com.eqriesracingteam.garage.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RepairService {

    private final RepairRepository repairRepository;
    private final CarRepository carRepository;
    private final InventoryRepository inventoryRepository;
    private final RepairsItemsRepository repairsItemsRepository;
    private final AppointmentRepository appointmentRepository;

    // Constructor

    @Autowired
    public RepairService(RepairRepository repairRepository, CarRepository carRepository, InventoryRepository inventoryRepository, RepairsItemsRepository repairsItemsRepository, AppointmentRepository appointmentRepository) {
        this.repairRepository = repairRepository;
        this.carRepository = carRepository;
        this.inventoryRepository = inventoryRepository;
        this.repairsItemsRepository = repairsItemsRepository;
        this.appointmentRepository = appointmentRepository;
    }

    // Methods
    public Repair getOneAppointment(Long id) {
        Optional<Repair> optionalRepair = repairRepository.findById(id);

        if (optionalRepair.isPresent()) {
            return optionalRepair.get();
        } else {
            throw new AppointmentException("Could not find repair with that id");
        }
    }

    public List<Repair> getAllRepairAppointments() {
        return repairRepository.findAll();
    }

    public void adjustRepairAppointment(Long id, Repair repair) {

        Optional<Repair> optionalRepair = repairRepository.findById(id);

        if (optionalRepair.isPresent()) {
            Repair existingRepair = optionalRepair.get();

            repair.setId(existingRepair.getId());
            repair.setRepairItems(existingRepair.getRepairItems());
            repair.setScheduledCar(existingRepair.getScheduledCar());
            repair.setAppointment(existingRepair.getAppointment());

            if (repair.getAppointmentStatus() == AppointmentStatus.REPARATIE_UITGEVOERD) {
                optionalRepair.get().getAppointment().setAppointmentStatus(AppointmentStatus.REPARATIE_UITGEVOERD);
            }
            repairRepository.save(repair);

        } else {
            throw new AppointmentException("Appointment with id not found");
        }
    }

    public void deleteRepairAppointment(Long id) {
        var existingAppointment = repairRepository.findById(id);

        if (existingAppointment.isPresent()) {
            repairRepository.deleteById(id);
        } else {
            throw new AppointmentException("Appointment not found");
        }
    }

    public Repair createRepairAppointment(Repair repair, Long appointmentId) {
        var date = repair.getRepairDateWorkshop();
        List<Repair> repairs = repairRepository.findAllByRepairDateWorkshop(date);

        Optional<Appointment> optionalAppointment = appointmentRepository.findById(appointmentId);
        Appointment appointmentForRepair = optionalAppointment.orElseThrow(() -> new AppointmentException("Appointment id not found"));

        boolean approvalCustomer = approvalCustomer(appointmentForRepair);
        Car carForAppointment = appointmentForRepair.getCarForAppointment();

        if (carForAppointment == null) {
            throw new RecordNotFoundException("car has not been matched to appointment");
        }

        if (repairs.isEmpty() && approvalCustomer) {

            repair.setAppointmentStatus(AppointmentStatus.REPARATIE_GEPLAND);
            repair.setScheduledCar(carForAppointment);

            Repair newRepair = repairRepository.save(repair);
            return newRepair;

        } else {
            throw new BadRequestException("No Approval for customer or not yet set");
        }
    }

    public void assignAppointment(long repairId, Long appointmentId) {
        var optionalRepair = repairRepository.findById(repairId);
        var optionalAppointment = appointmentRepository.findById(appointmentId);

        if (optionalAppointment.isPresent() && optionalRepair.isPresent()) {
            var repair = optionalRepair.get();
            var appointment = optionalAppointment.get();

            repair.setAppointment(appointment);
            repairRepository.save(repair);
        }
    }

    public boolean approvalCustomer(Appointment appointment) {
        AppointmentStatus status = appointment.getAppointmentStatus();
        return status == AppointmentStatus.AKKOORD_KLANT;
    }
}
