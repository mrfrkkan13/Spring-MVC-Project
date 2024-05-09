package com.example.JavaTask.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;


import com.example.JavaTask.model.Book;
import com.example.JavaTask.service.abstracts.BookService;
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
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    private Book book1;
    private Book book2;

    @BeforeEach
    public void setUp() {
        book1 = new Book(1, "Book 1", 10.0, "ISBN-1234", 1);
        book2 = new Book(2, "Book 2", 15.0, "ISBN-5678", 2);
    }

    @Test
    public void testGetAllBooks() throws Exception {
        List<Book> books = Arrays.asList(book1, book2);
        when(bookService.getAll()).thenReturn(books);

        mockMvc.perform(get("/books/getAll"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].bookId").value(1))
                .andExpect(jsonPath("$[0].title").value("Book 1"))
                .andExpect(jsonPath("$[0].price").value(10.0))
                .andExpect(jsonPath("$[0].isbn").value("ISBN-1234"))
                .andExpect(jsonPath("$[0].publisherId").value(1))
                .andExpect(jsonPath("$[1].bookId").value(2))
                .andExpect(jsonPath("$[1].title").value("Book 2"))
                .andExpect(jsonPath("$[1].price").value(15.0))
                .andExpect(jsonPath("$[1].isbn").value("ISBN-5678"))
                .andExpect(jsonPath("$[1].publisherId").value(2));
    }

    @Test
    public void testAddBook() throws Exception {
        mockMvc.perform(post("/books/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"bookId\": 3, \"title\": \"Book 3\", \"price\": 20.0, \"isbn\": \"ISBN-91011\", \"publisherId\": 3 }"))
                .andExpect(status().isOk());

        verify(bookService, times(1)).save(any(Book.class));
    }

    @Test
    public void testGetBookById() throws Exception {
        when(bookService.getById(1)).thenReturn(book1);

        mockMvc.perform(get("/books/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.bookId").value(1))
                .andExpect(jsonPath("$.title").value("Book 1"))
                .andExpect(jsonPath("$.price").value(10.0))
                .andExpect(jsonPath("$.isbn").value("ISBN-1234"))
                .andExpect(jsonPath("$.publisherId").value(1));
    }

    @Test
    public void testUpdateBook() throws Exception {
        mockMvc.perform(put("/books/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"bookId\": 1, \"title\": \"Updated Book 1\", \"price\": 12.0, \"isbn\": \"ISBN-5678\", \"publisherId\": 1 }"))
                .andExpect(status().isOk());

        verify(bookService, times(1)).update(any(Book.class));
    }

    @Test
    public void testDeleteBook() throws Exception {
        mockMvc.perform(delete("/books/1"))
                .andExpect(status().isOk());

        verify(bookService, times(1)).delete(1);
    }
}
