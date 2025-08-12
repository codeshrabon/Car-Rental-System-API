package com.car_rental_system.car_rental_system_API.controller;

import com.car_rental_system.car_rental_system_API.model.Car;
import com.car_rental_system.car_rental_system_API.model.Customer;
import com.car_rental_system.car_rental_system_API.model.Rental;
import com.car_rental_system.car_rental_system_API.service.CarService;
import com.car_rental_system.car_rental_system_API.service.CustomerService;
import com.car_rental_system.car_rental_system_API.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import java.util.*;

public class RentalCLI implements CommandLineRunner {



    private static CarService carService;
    private static CustomerService customerService;
    private static RentalService rentalService;

    private static Scanner scanner = new Scanner(System.in);

    public RentalCLI(CarService carService, CustomerService customerService, RentalService rentalService) {
        this.carService = carService;
        this.customerService = customerService;
        this.rentalService = rentalService;
    }


    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            System.out.println("\n==== Car Rental System (Admin Panel) ====");
            System.out.println("1. Add Car");
            System.out.println("2. Add Customer");
            System.out.println("3. Rent a Car");
            System.out.println("4. View Rentals");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = Integer.parseInt(scanner.nextLine());

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

    private static void addCar() {
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
        System.out.print("Base Price Per Day: "); car.setBasePricePerDay(Double.parseDouble(scanner.nextLine()));
        car.setAvailable(true);
        carService.addCars(car);

        System.out.println("Car added successfully.");
    }

    private static void addCustomer() {
        Customer customer = new Customer();
        System.out.print("Customer ID: "); customer.setCustomerId(scanner.nextLine());
        System.out.print("Name: "); customer.setCustomerName(scanner.nextLine());
        System.out.print("Email: "); customer.setCustomerEmail(scanner.nextLine());
        System.out.print("Phone: "); customer.setCustomerPhone(scanner.nextLine());
        System.out.print("Address: "); customer.setCustomerAddress(scanner.nextLine());
        System.out.print("City: "); customer.setCustomerCity(scanner.nextLine());
        System.out.print("State: "); customer.setCustomerState(scanner.nextLine());
        System.out.print("Zip: "); customer.setCustomerZip(scanner.nextLine());
        customerService.addCustomers(customer);

        System.out.println("Customer added successfully.");
    }

    private static void rentCar() {
        System.out.print("Enter Customer ID: ");
        String custId = scanner.nextLine();
        Customer customer = customerService.stream()
                .filter(c -> c.getCustomerId().equals(custId))
                .findFirst().orElse(null);

        if (customer == null) {
            System.out.println("Customer not found!");
            return;
        }

        System.out.print("Enter Car ID: ");
        String carId = scanner.nextLine();
        Car car = carService.stream()
                .filter(c -> c.getCarId().equals(carId) && c.isAvailable())
                .findFirst().orElse(null);

        if (car == null) {
            System.out.println("Car not available or not found!");
            return;
        }

        Rental rental = new Rental();
        rental.setCar(car);
        rental.setCustomer(customer);

        System.out.print("Enter rental start date (yyyy-MM-dd): ");
        rentalService.setRenalStartDate(scanner.nextLine());
        System.out.print("Enter rental end date (yyyy-MM-dd): ");
        rental.setEndDate(scanner.nextLine());
        System.out.print("Payment Method: ");
        rental.setPaymentMethod(scanner.nextLine());

        car.setAvailable(false);
        rentalService.(rental);

        System.out.println("\n✅ Rental Processed Successfully! Receipt:");
        System.out.println(rental);
    }

    private static void viewRentals() {
        rentals.forEach(System.out::println);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
