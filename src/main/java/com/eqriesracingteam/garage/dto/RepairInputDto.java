package com.eqriesracingteam.garage.dto;

import com.eqriesracingteam.garage.model.AppointmentStatus;
import com.eqriesracingteam.garage.model.Car;
import com.eqriesracingteam.garage.model.Inventory;
import com.eqriesracingteam.garage.model.Repair;

import java.time.LocalDateTime;
import java.util.List;

public class RepairInputDto {

    public long id;
    public AppointmentStatus appointmentStatus;
    public LocalDateTime repairDateWorkshop;
    public List<Inventory> itemsUsed;
    public long carId;

    public Repair toRepair(){
       var repair = new Repair();

       repair.setId(id);
       repair.setRepairDateWorkshop(repairDateWorkshop);
       repair.setAppointmentStatus(appointmentStatus);

       return repair;
    }
}
