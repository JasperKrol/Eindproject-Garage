package com.eqriesracingteam.garage.service;

import com.eqriesracingteam.garage.dto.AppointmentDto;
import com.eqriesracingteam.garage.exceptions.AppointmentException;
import com.eqriesracingteam.garage.exceptions.BadRequestException;
import com.eqriesracingteam.garage.exceptions.RecordNotFoundException;
import com.eqriesracingteam.garage.model.Appointment;
import com.eqriesracingteam.garage.model.AppointmentStatus;
import com.eqriesracingteam.garage.model.Car;
import com.eqriesracingteam.garage.repository.AppointmentRepository;
import com.eqriesracingteam.garage.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class AppointmentService {

    private AppointmentRepository appointmentRepository;
    private CarRepository carRepository;

    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository, CarRepository carRepository) {
        this.appointmentRepository = appointmentRepository;
        this.carRepository = carRepository;
    }

    // TODO: 29-12-2021 date param
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
        //        if (date == null) {
        //            return appointmentRepository.findAll();
        //        } else {
        //            return appointmentRepository.findAllByAppointmentDate(date);
        //        }
    }

    public Appointment getAppointment(long id) {
        Optional<Appointment> optionalAppointment = appointmentRepository.findById(id);

        if (optionalAppointment.isPresent()) {
            return optionalAppointment.get();
        } else {
            throw new AppointmentException("Appointment not found");
        }
    }

    public Appointment createAppointment(Appointment appointment) {
        var date = appointment.getAppointmentDate();
        List<Appointment> appointments = appointmentRepository.findAllByAppointmentDate(date);

        if (appointments.isEmpty()) {
            appointment.setAppointmentStatus(AppointmentStatus.AFSPRAAK_GEPLAND);
            Appointment newAppointment = appointmentRepository.save(appointment);

            return newAppointment;
        } else {
            throw new BadRequestException("Appointment taken");
        }
    }

    public void updateAppointment(long id, Appointment appointment) {
       Optional<Appointment> optionalAppointment = appointmentRepository.findById(id);

       Appointment existingAppointment = optionalAppointment.orElseThrow(()-> new AppointmentException("appointment not found"));
       appointment.setId(existingAppointment.getId());
       appointment.setCarForAppointment(existingAppointment.getCarForAppointment());
       appointment.setCustomer(existingAppointment.getCustomer());
       appointment.setRepair(existingAppointment.getRepair());

       appointmentRepository.save(appointment);
    }

    public void deleteAppointment(long id) {
        var existingAppointment = appointmentRepository.findById(id);

        if (existingAppointment.isPresent()) {
            appointmentRepository.deleteById(id);
        } else {
            throw new AppointmentException("Appointment not found");
        }
    }

    public Appointment assignCarToAppointment(long id, long carId) {
        var optionalAppointment = appointmentRepository.findById(id);
        var optionalCar = carRepository.findById(carId);

        if (!optionalAppointment.isPresent() || !optionalCar.isPresent()) {
            throw new RecordNotFoundException("Please check input for car id + " + id + " and/or appointment id" + id);
        } else {
            var existingAppointment = optionalAppointment.get();
            var existingCar = optionalCar.get();

            existingAppointment.setCarForAppointment(existingCar);
            return appointmentRepository.save(existingAppointment);
        }
    }
}
