package com.example.libsystem.utils;

import com.example.libsystem.model.Author;
import com.example.libsystem.payload.AuthorDTO;


public class AuthorUtil {
    public static AuthorDTO toDto(Author author) {
        return new AuthorDTO(author.getId(),author.getName());
    }
    public static Author fromDto(AuthorDTO authorDTO) {
        Author author = new Author();
        author.setName(authorDTO.getName());
        author.setUsername(authorDTO.getUsername());
//        author.setPassword(authorDTO.getPassword());
        author.setRole(authorDTO.getRole());
        if (authorDTO.getId()!=0)
            author.setId(authorDTO.getId());
        return author;
    }

    public static void update(Author tobeUpdated, AuthorDTO authorDTO) {
        tobeUpdated.setName(authorDTO.getName());
    }
}
