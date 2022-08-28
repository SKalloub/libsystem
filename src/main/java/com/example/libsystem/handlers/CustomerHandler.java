package com.example.libsystem.handlers;

import com.example.libsystem.exceptions.AuthorNotFoundException;
import com.example.libsystem.exceptions.ResourceNotFoundException;
import com.example.libsystem.model.Author;
import com.example.libsystem.model.Customer;
import com.example.libsystem.payload.AuthorDTO;
import com.example.libsystem.payload.BookDTO;
import com.example.libsystem.payload.CustomerDTO;
import com.example.libsystem.services.CustomerService;
import com.example.libsystem.utils.AuthorUtil;
import com.example.libsystem.utils.CustomerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomerHandler {
    @Autowired
    private CustomerService customerService;
    public List<CustomerDTO> getAllCustomers() {
        List<CustomerDTO> customerDTOS = new ArrayList<>();
         customerService.getAllCustomers().stream().forEach(customer -> customerDTOS.add(CustomerUtil.toDto(customer)));
        return customerDTOS;
    }

    public CustomerDTO getCustomerById(int id) {
        return CustomerUtil.toDto(customerService.getCustomerById(id).orElseThrow(() -> new ResourceNotFoundException()));
    }
    public boolean addCustomer(CustomerDTO customerDTO) {
        if (!customerDTO.getName().isEmpty()) {

//            User user = userRepository.findByUserName(username);
            Customer customer = CustomerUtil.fromDto(customerDTO);
            customerService.addCustomer(customer);
            return true;
        }

        return false;
    }

    public boolean deleteCustomerById(int id) {
        customerService.getCustomerById(id).orElseThrow(() -> new ResourceNotFoundException());
        customerService.deleteCustomerById(id);
        return true;
    }

    public boolean updateCustomer(CustomerDTO customerDTO, int id) {
        Customer customer = customerService.getCustomerById(id).orElseThrow(() -> new ResourceNotFoundException());
        if (customerDTO.getName() !=null && !customerDTO.getName().isEmpty())
            customer.setName(customerDTO.getName());


        customerService.updateCustomer(customer);
        return true;
    }

//    public List<BookDTO> getBooks(int id) {
//
//    }
}
