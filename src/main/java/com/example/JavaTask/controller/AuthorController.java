package com.example.JavaTask.controller;

import java.util.List;


import com.example.JavaTask.model.Author;
import com.example.JavaTask.service.abstracts.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    @Autowired
    private AuthorService authorServiceImpl;
    @GetMapping("/")
    public List<Author> getAllAuthors() {
        return authorServiceImpl.getAll();
    }
    @PostMapping("/")
    public void addAuthor(@RequestBody Author author) {
        authorServiceImpl.save(author);
    }
    @GetMapping("/{authorId}")
    public Author getAuthorById(@PathVariable int authorId) {
        return authorServiceImpl.getById(authorId);
    }
    @PutMapping("/{authorId}")
    public void updateAuthor(@PathVariable int authorId, @RequestBody Author author) {
        author.setAuthorId(authorId);
        authorServiceImpl.update(author);
    }
    @DeleteMapping("/{authorId}")
    public void deleteAuthor(@PathVariable int authorId) {
        authorServiceImpl.delete(authorId);
    }
}
