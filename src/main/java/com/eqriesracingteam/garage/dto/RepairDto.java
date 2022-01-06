package com.eqriesracingteam.garage.dto;

import com.eqriesracingteam.garage.model.AppointmentStatus;
import com.eqriesracingteam.garage.model.Car;
import com.eqriesracingteam.garage.model.Inventory;
import com.eqriesracingteam.garage.model.Repair;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.LocalDateTime;
import java.util.List;

public class RepairDto {

    public long id;
    public AppointmentStatus appointmentStatus;
    public LocalDateTime repairDateWorkshop;

    public List<Inventory> itemsUsed;

    @JsonSerialize
    public CarDto carForReparation;

    public static RepairDto fromRepair(Repair repair){

        var dto = new RepairDto();

        dto.id = repair.getId();
        dto.repairDateWorkshop = repair.getRepairDateWorkshop();
        dto.appointmentStatus = repair.getAppointmentStatus();
        dto.carForReparation = CarDto.fromCar(repair.getScheduledCar());

        return dto;
    }
}
