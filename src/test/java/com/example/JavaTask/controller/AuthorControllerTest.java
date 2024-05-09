package com.example.JavaTask.controller;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;


import com.example.JavaTask.model.Author;
import com.example.JavaTask.service.abstracts.AuthorService;
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
public class AuthorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthorService authorService;

    private Author author1;
    private Author author2;

    @BeforeEach
    public void setUp() {
        author1 = new Author(1, "John", "Doe", 1);
        author2 = new Author(2, "Jane", "Smith", 2);
    }

    @Test
    public void testGetAllAuthors() throws Exception {
        List<Author> authors = Arrays.asList(author1, author2);
        when(authorService.getAll()).thenReturn(authors);

        mockMvc.perform(get("/authors/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].authorId").value(1))
                .andExpect(jsonPath("$[0].firstName").value("John"))
                .andExpect(jsonPath("$[0].lastName").value("Doe"))
                .andExpect(jsonPath("$[0].bookId").value(1))
                .andExpect(jsonPath("$[1].authorId").value(2))
                .andExpect(jsonPath("$[1].firstName").value("Jane"))
                .andExpect(jsonPath("$[1].lastName").value("Smith"))
                .andExpect(jsonPath("$[1].bookId").value(2));
    }

    @Test
    public void testAddAuthor() throws Exception {
        mockMvc.perform(post("/authors/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"authorId\": 3, \"firstName\": \"Alice\", \"lastName\": \"Brown\", \"bookId\": 3 }"))
                .andExpect(status().isOk());

        verify(authorService, times(1)).save(any(Author.class));
    }

    @Test
    public void testGetAuthorById() throws Exception {
        when(authorService.getById(1)).thenReturn(author1);

        mockMvc.perform(get("/authors/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.authorId").value(1))
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Doe"))
                .andExpect(jsonPath("$.bookId").value(1));
    }

    @Test
    public void testUpdateAuthor() throws Exception {
        mockMvc.perform(put("/authors/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"authorId\": 1, \"firstName\": \"Updated John\", \"lastName\": \"Updated Doe\", \"bookId\": 1 }"))
                .andExpect(status().isOk());

        verify(authorService, times(1)).update(any(Author.class));
    }

    @Test
    public void testDeleteAuthor() throws Exception {
        mockMvc.perform(delete("/authors/1"))
                .andExpect(status().isOk());

        verify(authorService, times(1)).delete(1);
    }
}
