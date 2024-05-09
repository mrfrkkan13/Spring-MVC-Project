package com.example.JavaTask.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;

import com.example.JavaTask.model.Publisher;
import com.example.JavaTask.repository.abstracts.FilePublisherRepository;
import com.example.JavaTask.service.concrete.PublisherServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PublisherServiceImplTest {

    @Mock
    private FilePublisherRepository publisherRepository;

    @InjectMocks
    private PublisherServiceImpl publisherService;

    private List<Publisher> publishers;

    @BeforeEach
    public void setUp() {
        publishers = new ArrayList<>();
        publishers.add(new Publisher(1, "Publisher 1"));
        publishers.add(new Publisher(2, "Publisher 2"));
    }

    @Test
    public void testGetAllPublishers() {
        when(publisherRepository.getAll()).thenReturn(publishers);

        List<Publisher> allPublishers = publisherService.getAll();

        assertEquals(2, allPublishers.size());
    }

    @Test
    public void testGetPublisherById() {
        int publisherId = 1;
        when(publisherRepository.getById(publisherId)).thenReturn(publishers.get(0));

        Publisher publisher = publisherService.getById(publisherId);

        assertNotNull(publisher);
        assertEquals(publisherId, publisher.getPublisherId());
    }

    @Test
    public void testSavePublisher() {
        Publisher newPublisher = new Publisher(3, "Publisher 3");

        publisherService.save(newPublisher);

        verify(publisherRepository, times(1)).save(newPublisher);
    }

    @Test
    public void testUpdatePublisher() {
        Publisher updatedPublisher = new Publisher(1, "Updated Publisher 1");

        publisherService.update(updatedPublisher);

        verify(publisherRepository, times(1)).update(updatedPublisher);
    }

    @Test
    public void testDeletePublisher() {
        int publisherId = 1;

        publisherService.delete(publisherId);

        verify(publisherRepository, times(1)).delete(publisherId);
    }
}
