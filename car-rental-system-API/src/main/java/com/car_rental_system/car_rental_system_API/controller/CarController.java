package com.car_rental_system.car_rental_system_API.controller;

import com.car_rental_system.car_rental_system_API.model.Car;
import com.car_rental_system.car_rental_system_API.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin")
public class CarController {

    @Autowired
    private final CarService carService;


    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("cars")
    public ResponseEntity<List<Car>> getAllCars() {
        List<Car> allCars =  carService.getAllCars();
        System.out.println("User get all the cars");
        System.out.println(allCars);
        return ResponseEntity.ok(allCars);
    }

    @PostMapping("cars/addCar")
    public ResponseEntity<Void> addCar(@RequestBody List<Car> car) {
        List<Car> addedCars =  carService.addCars(car);
        System.out.println("User add a car");
        System.out.println(addedCars);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("cars/{id}")
    public ResponseEntity<Optional<Car>> getCarById(@PathVariable Long id) {
        System.out.println("User get Car by id: " + id);
        Optional<Car> findCarById = carService.findCarsById(id);
        System.out.println(findCarById);
        return ResponseEntity.status(HttpStatus.FOUND).body(findCarById);
    }

    @PutMapping("cars/update/{id}")
    public ResponseEntity<Optional<Car>> updateCar(@PathVariable Long id, @RequestBody Car car) {

        System.out.println("User update Car by id: " + id);
            Optional<Car> updatedCar = carService.updateCarbyId(car,id);
           if (updatedCar.isPresent()) {
               System.out.println(updatedCar.get());
               return ResponseEntity.ok(updatedCar);
           }else  {
               return ResponseEntity.notFound().build();
           }

    }

    @DeleteMapping("cars/delete/{id}")
    public ResponseEntity<String> deleteCar(@PathVariable Long id) {
        boolean isDeleted = carService.deleteCarById(id);
        if (isDeleted) {
            return ResponseEntity.ok("Car deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Car with id " + id + " not found.");
        }
    }

}
