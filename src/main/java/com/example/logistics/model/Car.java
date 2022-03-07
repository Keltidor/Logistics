package com.example.logistics.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_id")
    private Long carId;

    @Column(name = "driver_id")
    private Long driverId;

    @Column(name = "date_of_creation")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfCreation;

    @Enumerated(EnumType.STRING)
    @Column(name = "car_type")
    private CarType carType;

    public enum CarType {
        AUTO,
        CARGO,
        BUS,
    }
}
