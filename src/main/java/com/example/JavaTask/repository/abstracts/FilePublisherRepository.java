package com.example.JavaTask.repository.abstracts;



import com.example.JavaTask.model.Publisher;

import java.util.List;

public interface FilePublisherRepository extends FileGenericRepository<Publisher>{
    List<String> listBooksAndAuthorsOfTwoPublishers();

}
