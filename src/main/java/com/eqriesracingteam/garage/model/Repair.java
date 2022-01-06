package com.eqriesracingteam.garage.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "reparaties")
public class Repair {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    private AppointmentStatus appointmentStatus;

    @JsonIgnoreProperties("repairs")
    @ManyToOne
    @JoinColumn(name = "car_id", referencedColumnName = "id")
    private Car scheduledCar;

    // TODO: 5-1-2022 many to many relation
    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany
    @JoinTable(name = "repairs_items", joinColumns = @JoinColumn(name = "repair_id"), inverseJoinColumns = @JoinColumn(name = "inventory_id"))
    private List<Inventory> repairItems = new ArrayList<>();

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

    public Car getScheduledCar() {
        return scheduledCar;
    }

    public void setScheduledCar(Car scheduledCar) {
        this.scheduledCar = scheduledCar;
    }

    public List<Inventory> getRepairItems() {
        return repairItems;
    }

    public void setRepairItems(List<Inventory> repairItems) {
        this.repairItems = repairItems;
    }

    public void addInventoryItem(Inventory inventoryItem) {
        this.repairItems.add(inventoryItem);
    }

    public void removeInventoryItem(Inventory inventoryItem) {
        this.repairItems.remove(inventoryItem);
    }
}
