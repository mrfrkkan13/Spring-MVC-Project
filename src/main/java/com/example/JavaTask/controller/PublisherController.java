package com.example.JavaTask.controller;

import java.util.List;


import com.example.JavaTask.model.Publisher;
import com.example.JavaTask.service.abstracts.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/publishers")
public class PublisherController {

    @Autowired
    private PublisherService publisherServiceImpl;

    @GetMapping("/getAllPublisher")
    public List<Publisher> getAllPublishers() {
        return publisherServiceImpl.getAll();
    }

    @PostMapping("/")
    public void addPublisher(@RequestBody Publisher publisher) {
        publisherServiceImpl.save(publisher);
    }

    @GetMapping("/{publisherId}")
    public Publisher getPublisherById(@PathVariable int publisherId) {
        return publisherServiceImpl.getById(publisherId);
    }

    @PutMapping("/{publisherId}")
    public void updatePublisher(@PathVariable int publisherId, @RequestBody Publisher publisher) {
        publisher.setPublisherId(publisherId);
        publisherServiceImpl.update(publisher);
    }

    @DeleteMapping("/{publisherId}")
    public void deletePublisher(@PathVariable int publisherId) {
        publisherServiceImpl.delete(publisherId);
    }

    @GetMapping("/listPublisherWithBookandAuthor")
    public  List<String> listBooksAndAuthorsOfTwoPublishers(){
        return publisherServiceImpl.listBooksAndAuthorsOfTwoPublishers();
    }
}
