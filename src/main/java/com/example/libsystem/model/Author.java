package com.example.libsystem.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "author")
public class Author extends User {
    @JsonManagedReference(value = "author")
    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    private List<Book> bookList;

    public Author(int authorID) {
        super(authorID);
    }
}
