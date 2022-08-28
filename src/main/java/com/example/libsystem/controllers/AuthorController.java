package com.example.libsystem.controllers;

import com.example.libsystem.handlers.AuthorHandler;
import com.example.libsystem.payload.AuthorDTO;
import com.example.libsystem.payload.BookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    @Autowired
    private AuthorHandler authorHandler;

    @GetMapping
    public List<AuthorDTO> getAllAuthors() {
        return authorHandler.getAllAuthors();
    }


    @GetMapping("/{id}")
    public AuthorDTO getAuthorById(@PathVariable(name = "id") int id) {
        return authorHandler.getAuthorById(id);
    }

    @PostMapping
    public ResponseEntity<String> AddAuthor(@RequestBody AuthorDTO authorDTO, @RequestHeader HttpHeaders aInHeaders) {
        if (authorHandler.addAuthor(authorDTO,aInHeaders))
            return new ResponseEntity<>("Successfully Added!", HttpStatus.OK);//Wrong Status
        else
            return new ResponseEntity<>("Adding Author Failed!", HttpStatus.BAD_REQUEST);
    }
    @PutMapping("/updateAuthor/{id}")
    public ResponseEntity<String> updateAuthor(@RequestBody AuthorDTO authorDTO, @PathVariable(name = "id") int id) {
        if (authorHandler.updateAuthor(authorDTO, id))
            return new ResponseEntity<>("Successfully updated!", HttpStatus.OK);
        else
            return new ResponseEntity<>("Updating Author Failed!", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/deleteAuthor/{id}")
    public ResponseEntity<String> deleteAuthorById(@PathVariable(name = "id") int id) {
        if (authorHandler.deleteAuthorById(id))
            return new ResponseEntity<>("Successfully Deleted!", HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity<>("Deleting Author Failed!", HttpStatus.BAD_REQUEST);
    }
}
