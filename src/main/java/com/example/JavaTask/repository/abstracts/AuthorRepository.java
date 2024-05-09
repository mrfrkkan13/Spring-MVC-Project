package com.example.JavaTask.repository.abstracts;


import com.example.JavaTask.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Integer> {
    boolean existsByName(String name);
}
