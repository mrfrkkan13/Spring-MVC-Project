package com.example.JavaTask.service.concrete;

import java.util.List;


import com.example.JavaTask.model.Book;
import com.example.JavaTask.repository.abstracts.FileBookRepository;
import com.example.JavaTask.service.abstracts.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private FileBookRepository bookRepository;

    @Override
    public List<Book> getAll() {
        return bookRepository.getAll();
    }
    @Override
    public void save(Book book) {
        bookRepository.save(book);
    }
    @Override
    public Book getById(int bookId) {
        return bookRepository.getById(bookId);
    }
    @Override
    public void update(Book book) {
        bookRepository.update(book);
    }
    @Override
    public void delete(int bookId) {
        bookRepository.delete(bookId);
    }
}