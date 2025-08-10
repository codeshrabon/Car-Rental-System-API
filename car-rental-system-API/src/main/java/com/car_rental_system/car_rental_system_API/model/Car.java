package com.car_rental_system.car_rental_system_API.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String carId;
    private String carBrand;

    private String carModel;
    private String carColor;
    private String carLicensePlate;
    private String carSize;
    private String carPrice;
    private String carYear;
    private String carMake;
    private String carFuel;
    private String carFuelType;
    private double basePricePerDay;
    private boolean isAvailable = true;


}
