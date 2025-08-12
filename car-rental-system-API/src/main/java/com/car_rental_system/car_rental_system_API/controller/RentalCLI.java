package com.car_rental_system.car_rental_system_API.controller;

import com.car_rental_system.car_rental_system_API.model.Car;
import com.car_rental_system.car_rental_system_API.model.Customer;
import com.car_rental_system.car_rental_system_API.model.Rental;
import com.car_rental_system.car_rental_system_API.service.CarService;
import com.car_rental_system.car_rental_system_API.service.CustomerService;
import com.car_rental_system.car_rental_system_API.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

@Component
public class RentalCLI implements CommandLineRunner {

    private final CarService carService;
    private final CustomerService customerService;
    private final RentalService rentalService;
    private final Scanner scanner = new Scanner(System.in);

    @Autowired
    public RentalCLI(CarService carService, CustomerService customerService, RentalService rentalService) {
        this.carService = carService;
        this.customerService = customerService;
        this.rentalService = rentalService;
    }

    @Override
    public void run(String... args) {
        boolean running = true;

        while (running) {
            System.out.println("\n==== Car Rental System (Admin Panel) ====");
            System.out.println("1. Add Car");
            System.out.println("2. Add Customer");
            System.out.println("3. Rent a Car");
            System.out.println("4. View Rentals");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid number.");
                continue;
            }

            switch (choice) {
                case 1 -> addCar();
                case 2 -> addCustomer();
                case 3 -> rentCar();
                case 4 -> viewRentals();
                case 5 -> running = false;
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private void addCar() {
        Car car = new Car();
        System.out.print("Car ID: "); car.setCarId(scanner.nextLine());
        System.out.print("Brand: "); car.setCarBrand(scanner.nextLine());
        System.out.print("Model: "); car.setCarModel(scanner.nextLine());
        System.out.print("Color: "); car.setCarColor(scanner.nextLine());
        System.out.print("License Plate: "); car.setCarLicensePlate(scanner.nextLine());
        System.out.print("Size: "); car.setCarSize(scanner.nextLine());
        System.out.print("Price: "); car.setCarPrice(scanner.nextLine());
        System.out.print("Year: "); car.setCarYear(scanner.nextLine());
        System.out.print("Make: "); car.setCarMake(scanner.nextLine());
        System.out.print("Fuel: "); car.setCarFuel(scanner.nextLine());
        System.out.print("Fuel Type: "); car.setCarFuelType(scanner.nextLine());
        System.out.print("Base Price Per Day: ");
        car.setBasePricePerDay(Double.parseDouble(scanner.nextLine()));
        car.setAvailable(true);

        carService.addCars(List.of(car));
        System.out.println("✅ Car added successfully.");
    }

    private void addCustomer() {
        Customer customer = new Customer();
        System.out.print("Customer ID: "); customer.setCustomerId(scanner.nextLine());
        System.out.print("Name: "); customer.setCustomerName(scanner.nextLine());
        System.out.print("Email: "); customer.setCustomerEmail(scanner.nextLine());
        System.out.print("Phone: "); customer.setCustomerPhone(scanner.nextLine());
        System.out.print("Address: "); customer.setCustomerAddress(scanner.nextLine());
        System.out.print("City: "); customer.setCustomerCity(scanner.nextLine());
        System.out.print("State: "); customer.setCustomerState(scanner.nextLine());
        System.out.print("Zip: "); customer.setCustomerZip(scanner.nextLine());

        customerService.addCustomers(List.of(customer));
        System.out.println(" Customer added successfully.");
    }

    private void rentCar() {
        System.out.print("Enter Customer ID: ");
        String custId = scanner.nextLine();
        Customer customer = customerService.getAllCustomers().stream()
                .filter(c -> c.getCustomerId().equals(custId))
                .findFirst().orElse(null);

        if (customer == null) {
            System.out.println("Customer not found!");
            return;
        }

        System.out.print("Enter Car ID: ");
        String carId = scanner.nextLine();
        Car car = carService.getAllCars().stream()
                .filter(c -> c.getCarId().equals(carId) && c.isAvailable())
                .findFirst().orElse(null);

        if (car == null) {
            System.out.println("Car not available or not found!");
            return;
        }

        Rental rental = new Rental();
        rental.setCar(car);
        rental.setCustomer(customer);

        try {
            System.out.print("Enter rental start date (yyyy-MM-dd): ");
            rental.setRentalStartDate(parseDate(scanner.nextLine()));

            System.out.print("Enter rental end date (yyyy-MM-dd): ");
            rental.setRentalEndDate(parseDate(scanner.nextLine()));
        } catch (ParseException e) {
            System.out.println("Invalid date format.");
            return;
        }

        System.out.print("Payment Method: ");
        rental.setPaymentMethod(scanner.nextLine());
        rental.setPaymentComplete(false);

        car.setAvailable(false);
        rentalService.createRentals(rental);

        System.out.println("\n✅ Rental Processed Successfully!");
        System.out.println(rental);
    }

    private void viewRentals() {
        List<Rental> rentals = rentalService.getAllRentals();
        if (rentals.isEmpty()) {
            System.out.println("No rentals found.");
        } else {
            rentals.forEach(System.out::println);
        }
    }

    private Date parseDate(String dateStr) throws ParseException {
        return new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
    }
}
