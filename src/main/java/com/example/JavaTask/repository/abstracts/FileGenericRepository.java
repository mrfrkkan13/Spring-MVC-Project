package com.example.JavaTask.repository.abstracts;

import java.util.List;

public interface FileGenericRepository<T> {
    List<T> getAll();
    void save(T object);
    T getById(int id);
    void update(T object);
    void delete(int id);
}
