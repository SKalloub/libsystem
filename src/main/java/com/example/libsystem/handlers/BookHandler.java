package com.example.libsystem.handlers;

import com.example.libsystem.exceptions.BookNotFoundException;
import com.example.libsystem.exceptions.ResourceNotFoundException;
import com.example.libsystem.exceptions.UnAuthorizedMoveException;
import com.example.libsystem.exceptions.WrongUserNameOrPasswordException;
import com.example.libsystem.model.Book;
import com.example.libsystem.model.Customer;
import com.example.libsystem.model.Role;
import com.example.libsystem.model.User;
import com.example.libsystem.payload.BookDTO;
import com.example.libsystem.repositories.UserRepository;
import com.example.libsystem.services.BookService;
import com.example.libsystem.services.securityServices.JWTService;
import com.example.libsystem.utils.BookUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookHandler {

    @Autowired
    private BookService bookService;
//    @Autowired
//    private CustomerService customerService;
    @Autowired
    private JWTService jwtService;
    @Autowired
    private UserRepository userRepository;

    public List<BookDTO> getAllBooks() {
        List<BookDTO> bookDTOS = new ArrayList<>();
        bookService.getAllBooks().forEach(book -> bookDTOS.add(BookUtil.toDto(book)));
         return bookDTOS;
    }
    public BookDTO getBookById(int id) {
        return BookUtil.toDto(bookService.getBookById(id).orElseThrow(() -> new BookNotFoundException(id)));
    }

    public BookDTO addBook(BookDTO bookDTO) {
        if (!bookDTO.getName().isEmpty()) {
            Book book = BookUtil.fromDto(bookDTO);
            bookService.AddBook(book);
            return BookUtil.toDto(book);
        }
        return null;
    }
    public boolean deleteBookById(int id) {
        bookService.deleteBookById(id);
        return true;
    }

    public boolean updateBook(BookDTO bookDTO, int id, HttpHeaders headers) {

        String token = headers.getFirst(HttpHeaders.AUTHORIZATION);
        String username = jwtService.extractUserName(token.substring(7));
        User user = userRepository.findByUsername(username).orElseThrow((WrongUserNameOrPasswordException::new));
        Book book = bookService.getBookById(id).orElseThrow(() -> new BookNotFoundException(id));
        if (user.getRole() == Role.ADMIN || user.getId()==book.getId()) {

            if (bookDTO.getName() != null && !bookDTO.getName().isEmpty())
                book.setName(bookDTO.getName());
            if (bookDTO.getIsbn() != 0)
                book.setIsbn(book.getIsbn());
            bookService.updateBook(book);
            return true;
        }
            else {
                throw new UnAuthorizedMoveException();
        }

        }




    public BookDTO reserveBook(int bookID, int customerID) {
        Book book = bookService.getBookById(bookID).orElseThrow(() -> new BookNotFoundException(bookID));
        Customer customer = new Customer();
        customer.setId(customerID);
        if (book.isReserved())
            return null;
        book.setCustomer(customer);
        book.setReserved(true);
        bookService.updateBook(book);
        return BookUtil.toDto(book);
    }

    public BookDTO returnBook(int bookID) {
        Book book = bookService.getBookById(bookID).orElseThrow(() -> new BookNotFoundException(bookID));
        if (!book.isReserved())
            return null;
        book.setCustomer(null);
        book.setReserved(false);
        bookService.updateBook(book);
        return BookUtil.toDto(book);
    }

    public BookDTO getBookByIsbn(int isbn) {
        return BookUtil.toDto(bookService.getBookByIsbn(isbn).orElseThrow(() -> new BookNotFoundException(isbn)));
    }
    public List<BookDTO> getBooksByAuthorId(int authorID) {
        ArrayList<BookDTO> bookDTOS = new ArrayList<>();
        bookService.findBooksbyAuthorId(authorID).stream().forEach(book -> bookDTOS.add(BookUtil.toDto(book)));
        return bookDTOS;
    }

    public List<BookDTO> getBooksByCustomerId(int cid) {
        List<BookDTO> bookDTOS = new ArrayList<>();
        bookService.getBooksByCustomerId(cid).forEach(book -> bookDTOS.add(BookUtil.toDto(book)));
        return bookDTOS;
    }
}