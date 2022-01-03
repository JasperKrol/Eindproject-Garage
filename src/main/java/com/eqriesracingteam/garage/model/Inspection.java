package com.eqriesracingteam.garage.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name = "inspecties")
public class Inspection extends Appointment{

    @JsonIgnoreProperties("inspections")
    @ManyToOne
    @JoinColumn(name = "car_id", referencedColumnName = "id")
    private Car scheduledCar;

//    @OneToMany(mappedBy = "deficiency", cascade = CascadeType.ALL, orphanRemoval = true)
//    @LazyCollection(LazyCollectionOption.FALSE)
//    private List<Deficiency> deficiencies = new ArrayList<>();

    public Car getScheduledCar() {
        return scheduledCar;
    }

    public void setScheduledCar(Car scheduledCar) {
        this.scheduledCar = scheduledCar;
    }

//    public List<Deficiency> getDeficiencies() {
//        return deficiencies;
//    }
//
//    public void setDeficiencies(List<Deficiency> deficiencies) {
//        this.deficiencies = deficiencies;
//    }
}
