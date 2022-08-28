package com.example.libsystem.repositories;

import com.example.libsystem.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
public interface AuthorRepository extends JpaRepository<Author,Integer> {

}
