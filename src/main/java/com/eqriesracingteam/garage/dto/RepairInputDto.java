package com.eqriesracingteam.garage.dto;

import com.eqriesracingteam.garage.model.*;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

public class RepairInputDto {

    public Long id;
    public AppointmentStatus appointmentStatus;
    public LocalDateTime repairDateWorkshop;
    public List<Long> inventoryItemIdList;
    public long appointmentId;

    public Repair toRepair(){
        var repair = new Repair();

        repair.setId(id);
        repair.setAppointmentStatus(appointmentStatus);
        repair.setRepairDateWorkshop(repairDateWorkshop);

        return repair;
    }

}
