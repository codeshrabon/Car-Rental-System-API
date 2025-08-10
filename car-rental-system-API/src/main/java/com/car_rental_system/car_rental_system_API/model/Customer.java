package com.car_rental_system.car_rental_system_API.model;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String customerId;
    private String customerName;
    private String customerEmail;
    private String customerPhone;
    private String customerAddress;
    private String customerCity;
    private String customerState;
    private String customerZip;
    private String customerZipCode;


}
