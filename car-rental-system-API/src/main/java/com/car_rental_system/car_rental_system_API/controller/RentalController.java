package com.car_rental_system.car_rental_system_API.controller;

import com.car_rental_system.car_rental_system_API.model.Rental;
import com.car_rental_system.car_rental_system_API.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin")
public class RentalController {

    @Autowired
    private final RentalService rentalService;

    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    //get all rental car
    @GetMapping("/rentals/rentedCar")
    public ResponseEntity<List<Rental>> getAllRentals() {
        List<Rental> allRentedInfo = rentalService.getAllRentals();
        return new ResponseEntity<>( allRentedInfo ,HttpStatus.FOUND);
    }

    //get rental info by ID
    @GetMapping("/rentals/rentalCars/{id}")
    public ResponseEntity<Rental> getRentalById(@PathVariable Long id) {
        return rentalService.getRentalById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    // creating rental
    @PostMapping("/rentals/car/{carId}/customer/{customerId}")
    public ResponseEntity<Rental> createRental(@PathVariable Long carId,
                                               @PathVariable Long customerId,
                                               @RequestBody Rental rental) {
        Rental created = rentalService.createRental(carId, customerId, rental);
        return ResponseEntity.ok(created);
    }

    //update rental by id
    @PutMapping("/rentals/{id}")
    public ResponseEntity<Rental> updateRental(@PathVariable Long id, @RequestBody Rental rental) {
        Rental updated = rentalService.updateRental(id, rental);
        return ResponseEntity.ok(updated);
    }

    // delete rent by id
    @DeleteMapping("/rentals/{id}")
    public ResponseEntity<Void> deleteRental(@PathVariable Long id) {
        rentalService.deleteRental(id);
        return ResponseEntity.noContent().build();
    }

}
