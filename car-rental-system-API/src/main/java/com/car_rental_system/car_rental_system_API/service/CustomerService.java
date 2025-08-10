package com.car_rental_system.car_rental_system_API.service;

import com.car_rental_system.car_rental_system_API.model.Customer;
import com.car_rental_system.car_rental_system_API.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    //get all customers from DB
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }


    public List<Customer> addCustomers(List<Customer> customer) {
        return customerRepository.saveAll(customer);
    }

    public Optional<Customer> getCustomerByID(Long id) {
        return customerRepository.findById(id);
    }
}
