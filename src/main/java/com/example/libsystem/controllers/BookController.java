package com.example.libsystem.controllers;

import com.example.libsystem.handlers.BookHandler;
import com.example.libsystem.payload.AuthorDTO;
import com.example.libsystem.payload.BookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookHandler bookHandler;

    @GetMapping()
    public List<BookDTO> getAllBooks(/*@RequestParam(name = "authorID", required = false) String authorID*/) {
        return bookHandler.getAllBooks();
    }

    @GetMapping("/{id}")
    public BookDTO getBookById(@PathVariable(name = "id") int id) {

        return bookHandler.getBookById(id);

    }

    @GetMapping("/isbn/{isbn}")
    public BookDTO getBookByIsbn(@PathVariable(name = "isbn") int isbn) {
        return bookHandler.getBookByIsbn(isbn);
    }

    @PostMapping("/addBook")
    public ResponseEntity<BookDTO> AddBook(@RequestBody BookDTO bookDTO, @RequestParam(name = "authorID", required = true) String authorID) {
        bookDTO.setAuthorDTO(new AuthorDTO(Integer.parseInt(authorID)));
        BookDTO book = bookHandler.addBook(bookDTO);
        if (book != null)
            return new ResponseEntity<>(book, HttpStatus.OK);
        else
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/updateBook/{id}")
    public ResponseEntity<String> updateBook(@RequestBody BookDTO bookDTO, @PathVariable(name = "id") int id, @RequestHeader HttpHeaders aInHeaders) {
        if (bookHandler.updateBook(bookDTO, id, aInHeaders))
            return new ResponseEntity<>("Successfully updated!", HttpStatus.OK);
        else
            return new ResponseEntity<>("Updating Book Failed!", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/deleteBook/{id}")
    public ResponseEntity<String> deleteBookById(@PathVariable(name = "id") int id) {
        if (bookHandler.deleteBookById(id))
            return new ResponseEntity<>("Successfully Deleted!", HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity<>("Deleting Book Failed!", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/reserveBook/{id}")
    public ResponseEntity<BookDTO> reserveBook(@PathVariable(name = "id") int bookID, @RequestParam(name = "customerID") int customerID) {
        BookDTO bookDTO = bookHandler.reserveBook(bookID, customerID);
        if (bookDTO != null)
            return new ResponseEntity<>(bookDTO, HttpStatus.OK);
        else
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
    }

    @PostMapping("/returnBook/{id}")
    public ResponseEntity<BookDTO> returnBook(@PathVariable(name = "id") int bookID) {
        BookDTO bookDTO = bookHandler.returnBook(bookID);
        if (bookDTO != null)
            return new ResponseEntity<>(bookDTO, HttpStatus.OK);
        else
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
    }

    @GetMapping("/getCustomerBooks/{customerID}")
    public List<BookDTO> getCustomerBooksById(@PathVariable(name = "customerID") int cid) {
        return bookHandler.getBooksByCustomerId(cid);
    }
}
