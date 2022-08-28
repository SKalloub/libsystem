package com.example.libsystem.payload;

import com.example.libsystem.model.Book;
import com.example.libsystem.model.Role;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)

public class AuthorDTO extends UserDTO{
    private int id;
    @JsonBackReference
    private List<BookDTO> bookDTO;


    public AuthorDTO(String name) {
        this.name = name;
    }
    public AuthorDTO(int id, String name) {
        this.id = id;
        this.name =name;
    }
    public AuthorDTO(String username, String password, String name, Role role) {

        super(username,password,name,role);
    }

    public AuthorDTO(int authorID) {
        this.id = authorID;
    }
}
