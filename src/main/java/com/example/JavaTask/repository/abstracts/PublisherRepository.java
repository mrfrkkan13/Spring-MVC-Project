package com.example.JavaTask.repository.abstracts;


import com.example.JavaTask.model.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher,Integer> {
    boolean existByName(String name);
}
