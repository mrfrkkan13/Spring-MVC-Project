package com.example.JavaTask.service.abstracts;



import com.example.JavaTask.model.Publisher;

import java.util.List;

public interface PublisherService extends GenericService<Publisher> {
    List<String> listBooksAndAuthorsOfTwoPublishers();
}
