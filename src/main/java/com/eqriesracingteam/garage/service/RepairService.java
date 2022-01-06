package com.eqriesracingteam.garage.service;

import com.eqriesracingteam.garage.exceptions.AppointmentException;
import com.eqriesracingteam.garage.exceptions.BadRequestException;
import com.eqriesracingteam.garage.model.AppointmentStatus;
import com.eqriesracingteam.garage.model.Repair;
import com.eqriesracingteam.garage.repository.RepairRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RepairService {

    private RepairRepository repairRepository;

    // Constructor

    @Autowired
    public RepairService(RepairRepository repairRepository) {
        this.repairRepository = repairRepository;
    }

    // Methods

    public Repair createRepairAppointment(Repair repair) {
        var date = repair.getRepairDateWorkshop();
        List<Repair> repairs = repairRepository.findAllByAppointmentDate(date);

        if (!repairs.isEmpty()){
            repair.setAppointmentStatus(AppointmentStatus.REPARATIE_GEPLAND);
            Repair newRepair = repairRepository.save(repair);

            return newRepair;
        }
        throw new AppointmentException("Appointment date with timeslot taken");
    }


    public Repair getOneAppointment(long id) {
        Optional<Repair> optionalRepair = repairRepository.findById(id);

        if (optionalRepair.isPresent()){
            return optionalRepair.get();
        } else {
            throw new AppointmentException("Appointment date with timeslot taken");
        }
    }

    // TODO: 6-1-2022 request param for date search option or car
    public List<Repair> getAllRepairAppointments(){
        return repairRepository.findAll();
    }
}
