package com.car_rental_system.car_rental_system_API.repository;

import com.car_rental_system.car_rental_system_API.model.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalRepository extends JpaRepository<Rental,Long> {
}
