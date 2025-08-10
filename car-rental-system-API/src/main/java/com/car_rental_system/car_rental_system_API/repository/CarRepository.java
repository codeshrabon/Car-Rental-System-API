package com.car_rental_system.car_rental_system_API.repository;

import com.car_rental_system.car_rental_system_API.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car,Long> {
}
