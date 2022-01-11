package com.eqriesracingteam.garage.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "reparaties")
public class Repair {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

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
    private List<RepairsItems> repairsItems = new ArrayList<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public List<RepairsItems> getRepairItems() {
        return repairsItems;
    }

    public void setRepairItems(List<RepairsItems> repairsItems) {
        this.repairsItems = repairsItems;
    }

//    public void addInventoryItem(RepairsItems inventoryItem) {
//        this.repairsItems.add(inventoryItem);
//    }
//
//    public void removeInventoryItem(Inventory inventoryItem) {
//        this.repairsItems.remove(inventoryItem);
//    }
}
