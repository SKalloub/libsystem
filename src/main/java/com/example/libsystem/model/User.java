package com.example.libsystem.model;

import lombok.Data;

import javax.persistence.*;
import java.lang.annotation.Inherited;

@Data
@Table(name = "users")
@Entity
@Inheritance(strategy = InheritanceType.JOINED)

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(unique = true)
    private String username;
    @Column
    private String Password;
    @Column
    private String name;
    @Column
    private Role role;

    public User(String username, String password) {
        this.username = username;
        this.Password = password;
    }

    public User() {

    }

    public User(int id) {
        this.id = id;
    }
}
