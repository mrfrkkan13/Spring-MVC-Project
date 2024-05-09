package com.example.JavaTask.service.abstracts;



import java.util.List;

public interface GenericService<T> {
    List<T> getAll();
    void save(T object);
    T getById(int id);
    void update(T object);
    void delete(int id);
}
