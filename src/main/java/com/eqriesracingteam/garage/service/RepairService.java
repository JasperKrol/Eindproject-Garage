package com.eqriesracingteam.garage.service;

import com.eqriesracingteam.garage.exceptions.AppointmentException;
import com.eqriesracingteam.garage.exceptions.BadRequestException;
import com.eqriesracingteam.garage.model.AppointmentStatus;
import com.eqriesracingteam.garage.model.Car;
import com.eqriesracingteam.garage.model.Repair;
import com.eqriesracingteam.garage.repository.CarRepository;
import com.eqriesracingteam.garage.repository.RepairRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RepairService {

    private RepairRepository repairRepository;
    private CarRepository carRepository;

    // Constructor

    @Autowired
    public RepairService(RepairRepository repairRepository, CarRepository carRepository) {
        this.repairRepository = repairRepository;
        this.carRepository = carRepository;
    }

    // Methods

    public Repair createRepairAppointment(Repair repair, long carId) {
//        var date = repair.getRepairDateWorkshop();
        var optionalCar = carRepository.findById(carId);
        repair.setAppointmentStatus(AppointmentStatus.REPARATIE_GEPLAND);
        if (optionalCar.isPresent()){
            var car = optionalCar.get();
            repair.setScheduledCar(car);

            return repairRepository.save(repair);


//            Repair newRepair = repairRepository.save(repair);
//
//            return newRepair;
        }
        throw new AppointmentException("Appointment date with timeslot taken");
    }


    public Repair getOneAppointment(long id) {
        Optional<Repair> optionalRepair = repairRepository.findById(id);

        if (optionalRepair.isPresent()){
            return optionalRepair.get();
        } else {
            throw new AppointmentException("Could not find repair with that id");
        }
    }

    // TODO: 6-1-2022 request param for date search option or car
    public List<Repair> getAllRepairAppointments(){
        return repairRepository.findAll();
    }
}
