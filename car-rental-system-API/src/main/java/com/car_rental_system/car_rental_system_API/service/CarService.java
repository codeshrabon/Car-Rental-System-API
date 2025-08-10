package com.car_rental_system.car_rental_system_API.service;

import com.car_rental_system.car_rental_system_API.model.Car;
import com.car_rental_system.car_rental_system_API.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    // get all cars
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    //add cars by postman
    public List<Car> addCars(List<Car> car) {

        return carRepository.saveAll(car);
    }

    //finding car by its ID
    public Optional<Car> findCarsById(Long id) {
        return carRepository.findById(id);
    }

}
