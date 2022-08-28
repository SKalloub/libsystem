package com.example.libsystem.services;

import com.example.libsystem.model.Customer;
import com.example.libsystem.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }
    public Optional<Customer> getCustomerById(int id) {
        return customerRepository.findById(id);
    }
    public void deleteCustomerById(int id) {
        customerRepository.deleteById(id);
    }
    public void updateCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    public void addCustomer(Customer customer) {
        customerRepository.save(customer);
    }
}
