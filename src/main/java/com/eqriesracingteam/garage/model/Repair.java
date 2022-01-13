package com.eqriesracingteam.garage.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;

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
    @OneToMany(mappedBy = "inventoryItem")
    @JsonIgnore
    private Collection<RepairItems> repairItems;

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

    public Collection<RepairItems> getRepairItems() {
        return repairItems;
    }

    public void setRepairItems(Collection<RepairItems> repairItems) {
        this.repairItems = repairItems;
    }

//    public void addInventoryItem(RepairsItems inventoryItem) {
//        this.repairsItems.add(inventoryItem);
//    }
//
//    public void removeInventoryItem(Inventory inventoryItem) {
//        this.repairsItems.remove(inventoryItem);
//    }
}
