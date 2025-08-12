package com.car_rental_system.car_rental_system_API.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

public enum RentalStatus {
    BOOKED,
    ONGOING,
    COMPLETED,
    CANCELLED,
}