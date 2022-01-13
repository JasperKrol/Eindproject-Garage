package com.eqriesracingteam.garage.dto;

import com.eqriesracingteam.garage.model.*;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

public class RepairInputDto {

    public Long id;
    public AppointmentStatus appointmentStatus;
    public LocalDateTime repairDateWorkshop;
    public Collection<Long> inventoryItemIdList;
    public long carId;

}
