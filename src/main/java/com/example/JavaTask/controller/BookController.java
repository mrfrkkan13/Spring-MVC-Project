package com.example.JavaTask.controller;

import java.util.List;


import com.example.JavaTask.model.Book;
import com.example.JavaTask.service.abstracts.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookServiceImp;

    @GetMapping("/getAll")
    public List<Book> getAllBooks() {
        return bookServiceImp.getAll();
    }

    @PostMapping("/")
    public void addBook(@RequestBody Book book) {
        bookServiceImp.save(book);
    }

    @GetMapping("/{bookId}")
    public Book getBookById(@PathVariable int bookId) {
        return bookServiceImp.getById(bookId);
    }

    @PutMapping("/{bookId}")
    public void updateBook(@PathVariable int bookId, @RequestBody Book book) {
        book.setBookId(bookId);
        bookServiceImp.update(book);
    }

    @DeleteMapping("/{bookId}")
    public void deleteBook(@PathVariable int bookId) {
        bookServiceImp.delete(bookId);
    }
}