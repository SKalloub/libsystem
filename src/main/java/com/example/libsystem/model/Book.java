package com.example.libsystem.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private int isbn;
    @Column
    private String name;
    @Column
    private boolean reserved;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "authorID")
    private Author author;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "customerID")
    private Customer customer;
}
