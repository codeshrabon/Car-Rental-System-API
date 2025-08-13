package com.car_rental_system.car_rental_system_API.controller;

import com.car_rental_system.car_rental_system_API.model.Customer;
import com.car_rental_system.car_rental_system_API.service.CustomerService;
import jakarta.servlet.ServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin")
public class CustomerController {

    @Autowired
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    //get all the customer
    @GetMapping("customers")
    public ResponseEntity<List<Customer>> getCustomers() {
        try {
            List<Customer> allCustomers = customerService.getAllCustomers();
            System.out.println("User gets all customers");
            System.out.println(allCustomers);
            return new ResponseEntity<>(allCustomers, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //add customers to DB by postman
    @PostMapping("customer/addCustomers")
    public ResponseEntity<List<Customer>> addCustomer(@RequestBody List<Customer> customer) {
        try {
            List<Customer> addedCustomer = customerService.addCustomers(customer);
            System.out.println("User adds customers");
            return ResponseEntity.ok().body(addedCustomer);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //get customers by their ID
    @GetMapping("customers/{id}")
    public ResponseEntity<Optional<Customer>> getCustomer(@PathVariable Long id) {
        System.out.println("Admin want customer ID: " + id);

        try {
            Optional<Customer> getCustomerById = customerService.getCustomerByID(id);
            System.out.println(getCustomerById);
            return ResponseEntity.ok().body(getCustomerById);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //edit customer data
    @PutMapping("customers/editCustomer/{id}")
    public ResponseEntity<Optional<Customer>> editCustomer(@RequestBody Customer customer, @PathVariable Long id, ServletRequest servletRequest) {
        System.out.println("Admin want customer ID: " + id);
        try {
            Optional<Customer> editCustomer = customerService.editCustomer(customer,id);
            if(editCustomer.isPresent()) {
                System.out.println("Admin editing customer ID: " + id);
                System.out.println(editCustomer.get());
                return ResponseEntity.ok(editCustomer);
            }else  {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //delete customer data
    @DeleteMapping("customer/delete/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Long id) {
        System.out.println("Admin want delete customer ID: " + id);
        try {
            boolean isDeleted = customerService.deleteCustomer(id);
            if(isDeleted) {
                return ResponseEntity.ok().body("Customer deleted successfully");
            }else  {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}

