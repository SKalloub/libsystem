package com.example.libsystem.services;

import com.example.libsystem.model.Author;
import com.example.libsystem.repositories.AuthorRepository;
//import com.example.libsystem.repositories.UserRepository;
import com.example.libsystem.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }
    public Optional<Author> findAuthorById(int id) {
        return authorRepository.findById(id);
    }
    public void addAuthor(Author author) {
        authorRepository.save(author);
    }
    public void updateAuthor(Author author) {
        authorRepository.save(author);
    }
    public void deleteAuthorById(int id) {
        authorRepository.deleteById(id);
    }
}
