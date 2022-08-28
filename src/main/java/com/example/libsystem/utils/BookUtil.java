package com.example.libsystem.utils;


import com.example.libsystem.exceptions.AuthorNotFoundException;
import com.example.libsystem.model.Author;
import com.example.libsystem.model.Book;
import com.example.libsystem.payload.BookDTO;
import com.example.libsystem.repositories.AuthorRepository;
import com.example.libsystem.services.AuthorService;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.beans.factory.annotation.Autowired;
public class BookUtil {

//    @Autowired
//    private static AuthorService authorService;
    public static BookDTO toDto(Book book) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setName(book.getName());
        bookDTO.setIsbn(book.getIsbn());
        bookDTO.setId(book.getId());
        bookDTO.setAuthorDTO(AuthorUtil.toDto(book.getAuthor()));
        bookDTO.setCustomerDTO(CustomerUtil.toDto(book.getCustomer()));
        return bookDTO;
    }
    public static Book fromDto(BookDTO bookDTO) {
        Book book = new Book();
        book.setName(bookDTO.getName());
        book.setIsbn(bookDTO.getIsbn());
        book.setId(bookDTO.getId());
        book.setAuthor(new Author(bookDTO.getAuthorDTO().getId()));
        return book;
    }
}
