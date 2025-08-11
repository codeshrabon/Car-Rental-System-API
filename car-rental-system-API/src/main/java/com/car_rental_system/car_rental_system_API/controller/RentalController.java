package com.car_rental_system.car_rental_system_API.controller;

import com.car_rental_system.car_rental_system_API.model.Rental;
import com.car_rental_system.car_rental_system_API.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/admin")
public class RentalController {

    @Autowired
    private final RentalService rentalService;

    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @PostMapping("addRentInfo")
    public ResponseEntity<Optional<Rental>> addRentalInfo(@RequestBody Rental rental){
        Optional<Rental> rentalInfo = rentalService.addRentalInfo(rental);
        if(rentalInfo.isPresent()){
            return ResponseEntity.ok(rentalInfo);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

}
