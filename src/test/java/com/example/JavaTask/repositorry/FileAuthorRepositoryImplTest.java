package com.example.JavaTask.repositorry;

import static org.junit.jupiter.api.Assertions.*;


import com.example.JavaTask.model.Author;
import com.example.JavaTask.repository.concrete.FileAuthorRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileAuthorRepositoryImplTest {

    private FileAuthorRepositoryImpl fileAuthorRepository;
    private static final String TEST_FILE_NAME = "C:\\Dev\\JavaTask\\src\\DB\\Yazar.txt";

    @BeforeEach
    public void setUp() throws IOException {
        Path tempDir = Files.createTempDirectory("temp-dir");
        String testFilePath = tempDir.resolve(TEST_FILE_NAME).toString();
        fileAuthorRepository = new FileAuthorRepositoryImpl();
        writeSampleDataToFile(testFilePath);
    }

    private void writeSampleDataToFile(String filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("1;John;Doe;1");
            writer.newLine();
            writer.write("2;Jane;Smith;2");
            writer.newLine();
        }
    }

    @Test
    public void testGetAllAuthors() {
        List<Author> authors = fileAuthorRepository.getAll();
        assertNotNull(authors);
        assertEquals(2, authors.size());
    }

    @Test
    public void testSaveAuthor() {
        Author newAuthor = new Author(3, "Alice", "Johnson", 3);
        fileAuthorRepository.save(newAuthor);
        List<Author> authors = fileAuthorRepository.getAll();
        assertEquals(3, authors.size());
        assertTrue(authors.contains(newAuthor));
    }

    @Test
    public void testGetAuthorById() {
        Author author = fileAuthorRepository.getById(1);
        assertNotNull(author);
        assertEquals(1, author.getAuthorId());
        assertEquals("John", author.getFirstName());
        assertEquals("Doe", author.getLastName());
        assertEquals(1, author.getBookId());
    }

    @Test
    public void testUpdateAuthor() {
        Author updatedAuthor = new Author(2, "Updated Jane", "Updated Smith", 2);
        fileAuthorRepository.update(updatedAuthor);
        Author author = fileAuthorRepository.getById(2);
        assertEquals("Updated Jane", author.getFirstName());
        assertEquals("Updated Smith", author.getLastName());
    }

    @Test
    public void testDeleteAuthor() {
        fileAuthorRepository.delete(1);
        List<Author> authors = fileAuthorRepository.getAll();
        assertEquals(1, authors.size());
        assertNull(fileAuthorRepository.getById(1));
    }
}
