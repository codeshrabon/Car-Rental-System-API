package com.car_rental_system.car_rental_system_API.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Travel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long travelDays;
    private String travelDestination;
    private String travelDestinationCity;
    private String travelDestinationState;
    private String travelDestinationZip;

}
