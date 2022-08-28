package com.example.libsystem.payload;

import com.example.libsystem.model.Customer;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
@JsonInclude(JsonInclude.Include.NON_NULL)

@AllArgsConstructor
public class BookDTO {
    private int id;
    private String name;
    private int isbn;
    @JsonManagedReference(value = "author")
    private AuthorDTO authorDTO;
    @JsonManagedReference(value = "customer")
    private CustomerDTO customerDTO;
    public BookDTO(){

    }
    public BookDTO(String name, int isbn, int authorID) {
        this.name = name;
        this.isbn = isbn;
        this.authorDTO = new AuthorDTO(authorID);
    }
    public BookDTO(int isbn) {
        this.isbn = isbn;
    }
    public BookDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AuthorDTO getAuthorDTO() {
        return authorDTO;
    }

    public void setAuthorDTO(AuthorDTO authorDTO) {
        this.authorDTO = authorDTO;
    }

    public CustomerDTO getCustomerDTO() {
        return customerDTO;
    }

    public void setCustomerDTO(CustomerDTO customerDTO) {
        this.customerDTO = customerDTO;
    }
}
