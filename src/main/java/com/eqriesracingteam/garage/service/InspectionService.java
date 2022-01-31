package com.eqriesracingteam.garage.service;

import com.eqriesracingteam.garage.exceptions.AppointmentException;
import com.eqriesracingteam.garage.exceptions.InspectionException;
import com.eqriesracingteam.garage.exceptions.RecordNotFoundException;
import com.eqriesracingteam.garage.model.*;
import com.eqriesracingteam.garage.repository.AppointmentRepository;
import com.eqriesracingteam.garage.repository.CarRepository;
import com.eqriesracingteam.garage.repository.InspectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class InspectionService {

    private final InspectionRepository inspectionRepository;
    private final CarRepository carRepository;
    private final AppointmentRepository appointmentRepository;

    @Autowired
    public InspectionService(InspectionRepository inspectionRepository, CarRepository carRepository, AppointmentRepository appointmentRepository) {
        this.inspectionRepository = inspectionRepository;
        this.carRepository = carRepository;
        this.appointmentRepository = appointmentRepository;
    }

    public List<Inspection> getInspections(LocalDateTime inspectionDate, String licensePlate) {

        if (inspectionDate == null) {
            return inspectionRepository.findAll();
        }
        if (licensePlate == null) {
            return inspectionRepository.findAll();
        } else {
            return inspectionRepository.findAllByInspectionDate(inspectionDate);
        }

    }

    public Inspection getInspection(long id) {
        Optional<Inspection> inspectionOptional = inspectionRepository.findById(id);

        if (inspectionOptional.isPresent()) {
            return inspectionOptional.get();
        } else {
            throw new RecordNotFoundException("Inspection not found");
        }
    }

    public Inspection createInspection(Inspection inspection, long appointmentId) {
        var inspectionDate = inspection.getInspectionDate();
        List<Inspection> inspections = inspectionRepository.findAllByInspectionDate(inspectionDate);

        Optional<Appointment> optionalAppointment = appointmentRepository.findById(appointmentId);

        Appointment appointmentForInspection = optionalAppointment.orElseThrow(() -> new AppointmentException("Appointment id not found"));
        Car carForAppointment = appointmentForInspection.getCarForAppointment();

        if (carForAppointment == null) {
            throw new RecordNotFoundException("car has not been matched to appointment");
        }

        inspection.setInspectionStatus(InspectionStatus.INSPECTIE_GEPLAND);

        if (inspections.size() > 0) {
            throw new InspectionException("No more space on this date/time combination");
        } else {
            inspection.setAppointment(appointmentForInspection);
            inspection.setScheduledCar(carForAppointment);
            return inspectionRepository.save(inspection);

        }
    }

    public Inspection updateInspection(long id, Inspection inspection) {
        Optional<Inspection> optionalInspection = inspectionRepository.findById(id);

        if (optionalInspection.isPresent()) {
            Inspection plannedInspection = optionalInspection.get();
            var appointment = plannedInspection.getAppointment();

            inspection.setId(plannedInspection.getId());
            inspection.setScheduledCar(plannedInspection.getScheduledCar());

            inspectionRepository.save(plannedInspection);

            if (plannedInspection.getInspectionStatus() == InspectionStatus.INSPECTION_UITGEVOERD) {
                appointment.setAppointmentStatus(AppointmentStatus.INSPECTIE_KLAAR);
            }

            return plannedInspection;


        } else {
            throw new InspectionException("Inspection could not be saved or found");
        }
    }

    public void deleteInspection(long id) {
        Optional<Inspection> optionalInspection = inspectionRepository.findById(id);

        if (!optionalInspection.isPresent()) {
            throw new InspectionException("Inspection with id not found");
        } else {
            inspectionRepository.deleteById(id);
        }
    }

    public Inspection assignCarToInspection(long id, long carId) {
        Optional<Inspection> optionalInspection = inspectionRepository.findById(id);
        Optional<Car> optionalCar = carRepository.findById(carId);

        if (optionalInspection.isPresent() && optionalCar.isPresent()) {
            var inspection = optionalInspection.get();
            var car = optionalCar.get();

            inspection.setScheduledCar(car);
            return inspectionRepository.save(inspection);
        } else {
            throw new InspectionException("Inspection with id not found");
        }
    }
}
