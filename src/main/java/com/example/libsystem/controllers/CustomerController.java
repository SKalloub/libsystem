package com.example.libsystem.controllers;

import com.example.libsystem.handlers.CustomerHandler;
import com.example.libsystem.payload.BookDTO;
import com.example.libsystem.payload.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerHandler customerHandler;

    @GetMapping
    public List<CustomerDTO> getAllCustomers() {
        return customerHandler.getAllCustomers();
    }

    @GetMapping("/{id}")
    public CustomerDTO getCustomerById(@PathVariable(name = "id") int id) {
        return customerHandler.getCustomerById(id);
    }
    @PostMapping("/addCustomer")
    public ResponseEntity<String> addCustomer(@RequestBody CustomerDTO customerDTO) {
        if (customerHandler.addCustomer(customerDTO))
            return new ResponseEntity<>("Successfully Added!", HttpStatus.OK);
        else
            return new ResponseEntity<>("Adding Customer Failed!", HttpStatus.BAD_REQUEST);
    }
    @PutMapping("/updateCustomer/{id}")
    public ResponseEntity<String> updateCustomer(@PathVariable(name = "id") int id, @RequestBody CustomerDTO customerDTO) {
        if (customerHandler.updateCustomer(customerDTO,id))
            return new ResponseEntity<>("Successfully updated!", HttpStatus.OK);
        else
            return new ResponseEntity<>("Updating Customer Failed!", HttpStatus.BAD_REQUEST);
    }
    @DeleteMapping("/deleteCustomer/{id}")
    public ResponseEntity<String> deleteCustomerById(@PathVariable(name = "id") int id) {
        if (customerHandler.deleteCustomerById(id))
            return new ResponseEntity<>("Successfully Deleted!", HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity<>("Deleting Customer Failed!", HttpStatus.BAD_REQUEST);
    }
//    @GetMapping("/{id}/books")
//    public List<BookDTO> getCustomerBooksById(@PathVariable(name = "id") int id) {
//        return customerHandler.getBooks(id);
//    }
}
