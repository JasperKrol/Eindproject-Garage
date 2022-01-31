package com.eqriesracingteam.garage.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "reparaties")
public class Repair {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private AppointmentStatus appointmentStatus;
    private LocalDateTime repairDateWorkshop;

    @JsonIgnoreProperties("repairs")
    @ManyToOne
    @JoinColumn(name = "car_id", referencedColumnName = "id")
    private Car scheduledCar;

    // Many2many relation w/ass. class
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "repair")
    @JsonIgnore
    private List<RepairItems> repairItems;

    @OneToOne
    @JoinColumn(name = "appointment_id", referencedColumnName = "id")
    @JsonIgnore
    private Appointment appointment;

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AppointmentStatus getAppointmentStatus() {
        return appointmentStatus;
    }

    public void setAppointmentStatus(AppointmentStatus appointmentStatus) {
        this.appointmentStatus = appointmentStatus;
    }

    public LocalDateTime getRepairDateWorkshop() {
        return repairDateWorkshop;
    }

    public void setRepairDateWorkshop(LocalDateTime repairDateWorkshop) {
        this.repairDateWorkshop = repairDateWorkshop;
    }

    public Car getScheduledCar() {
        return scheduledCar;
    }

    public void setScheduledCar(Car scheduledCar) {
        this.scheduledCar = scheduledCar;
    }

    public List<RepairItems> getRepairItems() {
        return repairItems;
    }

    public void setRepairItems(List<RepairItems> repairItems) {
        this.repairItems = repairItems;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }
}
