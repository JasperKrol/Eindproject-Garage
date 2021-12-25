package com.eqriesracingteam.garage.service;

import com.eqriesracingteam.garage.dto.AppointmentDto;
import com.eqriesracingteam.garage.exceptions.AppointmentException;
import com.eqriesracingteam.garage.exceptions.BadRequestException;
import com.eqriesracingteam.garage.model.Appointment;
import com.eqriesracingteam.garage.model.AppointmentStatus;
import com.eqriesracingteam.garage.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
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

        if (appointments.isEmpty()){
            Appointment newAppointment = appointmentRepository.save(appointment);
            newAppointment.setAppointmentStatus(AppointmentStatus.AFSPRAAK_GEPLAND);
            return newAppointment;
        } else {
            throw new BadRequestException("Appointment taken");
        }
    }

}
