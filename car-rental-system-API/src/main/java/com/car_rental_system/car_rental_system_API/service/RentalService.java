package com.car_rental_system.car_rental_system_API.service;

import com.car_rental_system.car_rental_system_API.model.Car;
import com.car_rental_system.car_rental_system_API.model.Rental;
import com.car_rental_system.car_rental_system_API.repository.CarRepository;
import com.car_rental_system.car_rental_system_API.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RentalService {

    @Autowired
    private final RentalRepository rentalRepository;

    @Autowired
    private final CarRepository carRepository;

    public RentalService(RentalRepository rentalRepository, CarRepository carRepository) {
        this.rentalRepository = rentalRepository;
        this.carRepository = carRepository;
    }





    public Optional<Rental> addRentalInfo(Rental rental, Car car , Long id) {
        Optional<Rental> addRental = Optional.of(rentalRepository.save(rental));



        Optional<Car> addCar =
        return addRental.map(rent ->{
            rent.setCarName(rental.getCarName());
            rent.setTravelDays(rental.getTravelDays());

        })
    }
}
