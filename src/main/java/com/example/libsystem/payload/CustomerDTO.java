package com.example.libsystem.payload;

import com.example.libsystem.model.Role;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomerDTO extends UserDTO{
    private int id;
    @JsonBackReference
    private List<BookDTO> bookDTOs;
    public CustomerDTO(String name) {
        this.name = name;
    }

    public CustomerDTO(String username, String password, String name, Role role) {
        super(username,password,name,role);
    }
}
