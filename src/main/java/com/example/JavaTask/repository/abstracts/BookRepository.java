package com.example.JavaTask.repository.abstracts;


import com.example.JavaTask.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {
    boolean existByName(String name);
}
