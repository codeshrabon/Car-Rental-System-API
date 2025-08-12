package com.car_rental_system.car_rental_system_API.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

@Entity
@Data
@Table(name = "rental")
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "car_Id", nullable = false)
    private Car car;

    @ManyToOne
    @JoinColumn(name = "customer_id",  nullable = false)
    private Customer customer;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createdOn;

    private String updatedBy;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date updatedOn;

    private Date rentalStartDate;
    private Date rentalEndDate;
    private Date actualReturnDate;

    private double totalPrice;

    @Enumerated(EnumType.STRING)
    private RentalStatus rentalStatus;

    private String paymentMethod; //cash, card, online(bkash,rocket)
    private boolean paymentComplete;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

}
