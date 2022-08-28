package com.example.libsystem.repositories;

import com.example.libsystem.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


public interface BookRepository extends JpaRepository<Book,Integer> {

    Optional<Book> findBookByIsbn(int isbn);

    List<Book> findBooksByAuthor_Id(int AuthorID);

    List<Book> findBooksByCustomer_Id(int customerID);
}
