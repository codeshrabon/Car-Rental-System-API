package com.car_rental_system.car_rental_system_API.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

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
    private String createdBy;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createdOn;

    private String updatedBy;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date updatedOn;
    private boolean isAvailable = true;


}
