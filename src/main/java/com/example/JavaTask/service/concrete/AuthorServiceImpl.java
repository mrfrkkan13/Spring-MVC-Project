package com.example.JavaTask.service.concrete;

import java.util.List;


import com.example.JavaTask.model.Author;
import com.example.JavaTask.repository.abstracts.FileAuthorRepository;
import com.example.JavaTask.service.abstracts.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private FileAuthorRepository authorRepository;

    @Override
    public List<Author> getAll() {
        return authorRepository.getAll();
    }
    @Override
    public void save(Author author) {
        authorRepository.save(author);
    }
    @Override
    public Author getById(int authorId) {
        return authorRepository.getById(authorId);
    }
    @Override
    public void update(Author author) {
        authorRepository.update(author);
    }
    @Override
    public void delete(int authorId) {
        authorRepository.delete(authorId);
    }

}