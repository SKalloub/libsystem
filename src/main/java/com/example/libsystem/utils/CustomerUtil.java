package com.example.libsystem.utils;


import com.example.libsystem.model.Customer;
import com.example.libsystem.payload.CustomerDTO;

public class CustomerUtil {
    public static CustomerDTO toDto(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setName(customer.getName());
        customerDTO.setId(customer.getId());
        return customerDTO;
    }
    public static Customer fromDto(CustomerDTO customerDTO){
        Customer customer = new Customer();
        customer.setName(customerDTO.getName());
        customer.setId(customerDTO.getId());
        return customer;

    }
}
