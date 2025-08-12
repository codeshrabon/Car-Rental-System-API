package com.car_rental_system.car_rental_system_API.service;

import com.car_rental_system.car_rental_system_API.model.*;
import com.car_rental_system.car_rental_system_API.repository.CarRepository;
import com.car_rental_system.car_rental_system_API.repository.CustomerRepository;
import com.car_rental_system.car_rental_system_API.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.List;
import java.util.Optional;

@Service
public class RentalService {

    @Autowired
    private final RentalRepository rentalRepository;

    @Autowired
    private final CarRepository carRepository;

    @Autowired
    private final CustomerRepository customerRepository;

    public RentalService(RentalRepository rentalRepository, CarRepository carRepository, CustomerRepository customerRepository) {
        this.rentalRepository = rentalRepository;
        this.carRepository = carRepository;
        this.customerRepository = customerRepository;
    }





    /*public Optional<Rental> addRentalInfo(Rental rental, Car car , Long id) {
        Optional<Rental> addRental = Optional.of(rentalRepository.save(rental));

        Optional<Car> carOpt = carRepository.findById(car.getId());

        carOpt.ifPresent(addRentalInfo(car.setCarId(car.setCarId();
        );));


        *//*Optional<Car> addCar =
        return addRental.map(rent ->{
            rent.setCar(rental.getCarName());
            rent.setTravelDays(rental.getTravelDays());

        })*//*
        return addRental;
    }*/

    public List<Rental> getAllRentals() {
        List<Rental> allRentals = rentalRepository.findAll();
        return allRentals;
    }

    public Optional<Rental> getRentalById(Long id) {
        return rentalRepository.findById(id);
    }

    public Rental createRental(Long carId, Long customerId, Rental rentalDetails) {
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new RuntimeException("Car not found"));
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not Found"));

        rentalDetails.setCar(car);
        rentalDetails.setCustomer(customer);

        //calcualte days between rentalDate and returndate
        long days = ChronoUnit.DAYS.between((Temporal) rentalDetails.getRentalStartDate(), (Temporal) rentalDetails.getRentalEndDate())+1;
        if (days <= 0) {
            throw new IllegalArgumentException("Return date must be after rental date");
        }


        //calculate total price based on car daily rate
        double totalPrice = car.getBasePricePerDay() * days;
        rentalDetails.setTotalPrice(totalPrice);

        //set initial status and payment info
        rentalDetails.setRentalStatus(RentalStatus.BOOKED);

        //PaymentStatus  paymentStatus = rentalDetails.getPaymentStatus();
        rentalDetails.setPaymentComplete(false);

        return  rentalRepository.save(rentalDetails);

    }


    public Rental updateRental(Long id, Rental rental) {
        return rentalRepository.findById(id)
                .map(rent -> {
                    rent.setRentalStartDate(rental.getRentalStartDate());
                    rent.setRentalEndDate(rental.getRentalEndDate());
                    rent.setRentalStatus(rental.getRentalStatus());
                    rent.setPaymentComplete(rental.isPaymentComplete());
                    rent.setPaymentMethod(rental.getPaymentMethod());

                    // recalculate if date has been changes
                    long days = ChronoUnit.DAYS.between((Temporal) rental.getRentalStartDate(), (Temporal) rental.getRentalEndDate()) + 1;
                    double totalPrice = rent.getTotalPrice() * days;

                    return rentalRepository.save(rent);

                })
                .orElseThrow(() -> new RuntimeException("Rental not found"));
    }

    public void deleteRental(Long id) {
        rentalRepository.deleteById(id);
    }

    public void createRentals(Rental rental) {
        rentalRepository.save(rental);
    }
}
