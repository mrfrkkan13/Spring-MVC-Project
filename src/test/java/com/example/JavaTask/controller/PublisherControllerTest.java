package com.example.JavaTask.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;


import com.example.JavaTask.model.Publisher;
import com.example.JavaTask.service.abstracts.PublisherService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class PublisherControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PublisherService publisherService;

    private Publisher publisher1;
    private Publisher publisher2;

    @BeforeEach
    public void setUp() {
        publisher1 = new Publisher(1, "Publisher 1");
        publisher2 = new Publisher(2, "Publisher 2");
    }

    @Test
    public void testGetAllPublishers() throws Exception {
        List<Publisher> publishers = Arrays.asList(publisher1, publisher2);
        when(publisherService.getAll()).thenReturn(publishers);

        mockMvc.perform(get("/publishers/getAllPublisher"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].publisherId").value(1))
                .andExpect(jsonPath("$[0].publisherName").value("Publisher 1"))
                .andExpect(jsonPath("$[1].publisherId").value(2))
                .andExpect(jsonPath("$[1].publisherName").value("Publisher 2"));
    }

    @Test
    public void testAddPublisher() throws Exception {
        mockMvc.perform(post("/publishers/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"publisherId\": 3, \"publisherName\": \"Publisher 3\" }"))
                .andExpect(status().isOk());

        verify(publisherService, times(1)).save(any(Publisher.class));
    }

    @Test
    public void testGetPublisherById() throws Exception {
        when(publisherService.getById(1)).thenReturn(publisher1);

        mockMvc.perform(get("/publishers/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.publisherId").value(1))
                .andExpect(jsonPath("$.publisherName").value("Publisher 1"));
    }

    @Test
    public void testUpdatePublisher() throws Exception {
        mockMvc.perform(put("/publishers/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"publisherId\": 1, \"publisherName\": \"Updated Publisher 1\" }"))
                .andExpect(status().isOk());

        verify(publisherService, times(1)).update(any(Publisher.class));
    }

    @Test
    public void testDeletePublisher() throws Exception {
        mockMvc.perform(delete("/publishers/1"))
                .andExpect(status().isOk());

        verify(publisherService, times(1)).delete(1);
    }
}