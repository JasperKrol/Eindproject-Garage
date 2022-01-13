package com.eqriesracingteam.garage.dto;

import com.eqriesracingteam.garage.model.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

public class RepairDto {

    public Long id;
    public AppointmentStatus appointmentStatus;
    public LocalDateTime repairDateWorkshop;
    public Collection<RepairItems> repairItems;
    public CarDto carForReparation;

    public static RepairDto fromRepair(Repair repair){

        var dto = new RepairDto();

        dto.id = repair.getId();
        dto.repairDateWorkshop = repair.getRepairDateWorkshop();
        dto.appointmentStatus = repair.getAppointmentStatus();
        dto.repairItems = repair.getRepairItems();
        dto.carForReparation = CarDto.fromCar(repair.getScheduledCar());

        return dto;
    }
}
