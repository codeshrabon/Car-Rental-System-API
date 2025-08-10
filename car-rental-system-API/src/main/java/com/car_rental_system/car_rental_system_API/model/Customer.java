package com.car_rental_system.car_rental_system_API.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;


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
    private String createdBy;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createdOn;

    private String updatedBy;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date updatedOn;


}
