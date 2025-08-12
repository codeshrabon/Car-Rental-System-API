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


    //add customer from postman
    public List<Customer> addCustomers(List<Customer> customer) {
        return customerRepository.saveAll(customer);
    }

    //add customer by admin
    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Optional<Customer> getCustomerByID(Long id) {
        return customerRepository.findById(id);
    }

    public Optional<Customer> editCustomer(Customer customer, Long id) {
        return customerRepository.findById(id)
                .map(existingCustomer ->{
                    existingCustomer.setCustomerId(customer.getCustomerId());
                    existingCustomer.setCustomerName(customer.getCustomerName());
                    existingCustomer.setCustomerPhone(customer.getCustomerPhone());
                    existingCustomer.setCustomerEmail(customer.getCustomerEmail());
                    existingCustomer.setCustomerAddress(customer.getCustomerAddress());
                    existingCustomer.setCustomerCity(customer.getCustomerCity());
                    existingCustomer.setCustomerState(customer.getCustomerState());
                    existingCustomer.setCustomerZip(customer.getCustomerZip());
                    existingCustomer.setCustomerZipCode(customer.getCustomerZipCode());
                    existingCustomer.setCreatedBy(customer.getCreatedBy());
                    existingCustomer.setCreatedOn(customer.getCreatedOn());
                    existingCustomer.setUpdatedBy(customer.getUpdatedBy());
                    existingCustomer.setUpdatedOn(customer.getUpdatedOn());
                    return existingCustomer;

                });

    }

    public boolean deleteCustomer(Long id) {
        if (customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
