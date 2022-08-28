package com.example.libsystem.services;

import com.example.libsystem.model.Book;
import com.example.libsystem.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;


    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
    public Optional<Book> getBookById(int id) {
        System.err.println(id);
        return bookRepository.findById(id);
    }
    public void AddBook(Book book) {
        bookRepository.save(book);
    }
    public void updateBook(Book book) {
        bookRepository.save(book);
    }
    public Optional<Book> getBookByIsbn(int isbn){
        return bookRepository.findBookByIsbn(isbn);
    }
    public void deleteBookById(int id) {
        bookRepository.deleteById(id);
    }
    public List<Book> findBooksbyAuthorId(int authorID) {
        return bookRepository.findBooksByAuthor_Id(authorID);
    }
    public List<Book> getBooksByCustomerId(int customerID) {
        return bookRepository.findBooksByCustomer_Id(customerID);
    }
}
