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

    public Car addCar(Car car) {
        return carRepository.save(car);
    }

    //finding car by its ID
    public Optional<Car> findCarsById(Long id) {
        return carRepository.findById(id);
    }

    //update the car by its id
    public Optional<Car> updateCarbyId(Car car, Long id) {
        return carRepository.findById(id)
                .map(existingCar -> {
                    existingCar.setCarId(car.getCarId());
                    existingCar.setCarModel(car.getCarModel());
                    existingCar.setCarColor(car.getCarColor());
                    existingCar.setCarLicensePlate(car.getCarLicensePlate());
                    existingCar.setCarSize(car.getCarSize());
                    existingCar.setCarPrice(car.getCarPrice());
                    existingCar.setCarYear(car.getCarYear());
                    existingCar.setCarMake(car.getCarMake());
                    existingCar.setCarFuel(car.getCarFuel());
                    existingCar.setCarFuelType(car.getCarFuelType());
                    existingCar.setBasePricePerDay(car.getBasePricePerDay());
                    existingCar.setCreatedBy(car.getCreatedBy());
                    existingCar.setCreatedOn(car.getCreatedOn());
                    existingCar.setUpdatedBy(car.getUpdatedBy());
                    existingCar.setUpdatedOn(car.getUpdatedOn());
                    existingCar.setAvailable(car.isAvailable());

                    return carRepository.save(existingCar);
                });
    }

    public boolean deleteCarById(Long id) {
        if (carRepository.existsById(id)) {
            carRepository.deleteById(id);
            return true;  // deletion successful
        }
        return false; // car with id not found
    }

}
