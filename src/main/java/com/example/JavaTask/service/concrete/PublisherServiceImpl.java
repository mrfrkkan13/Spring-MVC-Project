package com.example.JavaTask.service.concrete;

import java.util.List;


import com.example.JavaTask.model.Publisher;
import com.example.JavaTask.repository.abstracts.FilePublisherRepository;
import com.example.JavaTask.service.abstracts.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PublisherServiceImpl implements PublisherService {

    @Autowired
    private FilePublisherRepository publisherRepository;

    @Override
    public List<Publisher> getAll() {
        return publisherRepository.getAll();
    }
    @Override
    public void save(Publisher publisher) {
        publisherRepository.save(publisher);
    }
    @Override
    public Publisher getById(int publisherId) {
        return publisherRepository.getById(publisherId);
    }
    @Override
    public void update(Publisher publisher) {
        publisherRepository.update(publisher);
    }
    @Override
    public void delete(int publisherId) {
        publisherRepository.delete(publisherId);
    }


    @Override
    public List<String> listBooksAndAuthorsOfTwoPublishers() {
        return publisherRepository.listBooksAndAuthorsOfTwoPublishers();
    }
}
