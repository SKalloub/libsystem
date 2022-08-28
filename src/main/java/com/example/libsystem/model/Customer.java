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
@Table(name = "customer")

public class Customer extends User
{
@JsonManagedReference(value = "customer")
@OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
private List<Book> bookList;
}
