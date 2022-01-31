package com.eqriesracingteam.garage.dto;

import com.eqriesracingteam.garage.model.AppointmentStatus;
import com.eqriesracingteam.garage.model.Car;
import com.eqriesracingteam.garage.model.Repair;
import com.eqriesracingteam.garage.model.RepairItems;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.LocalDateTime;
import java.util.List;

public class RepairDto {

    public Long id;
    public AppointmentStatus appointmentStatus;
    public LocalDateTime repairDateWorkshop;

    @JsonSerialize
    @JsonIgnoreProperties({"repair"})
    public List<RepairItems> repairItems;
    public Car carForReparation;

    public static RepairDto fromRepair(Repair repair) {

        var dto = new RepairDto();

        dto.id = repair.getId();
        dto.repairDateWorkshop = repair.getRepairDateWorkshop();
        dto.appointmentStatus = repair.getAppointmentStatus();

        if (repair.getScheduledCar() != null) {
            dto.carForReparation = repair.getScheduledCar();
        }
        if (repair.getRepairItems() != null) {
            dto.repairItems = repair.getRepairItems();
        }

        return dto;
    }
}
