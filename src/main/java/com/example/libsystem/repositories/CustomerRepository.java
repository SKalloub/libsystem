package com.example.libsystem.repositories;

import com.example.libsystem.model.Book;
import com.example.libsystem.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {

}
