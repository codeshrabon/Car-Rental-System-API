package com.car_rental_system.car_rental_system_API.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "rental")
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Car car;

    @ManyToOne
    private Customer customer;


}
