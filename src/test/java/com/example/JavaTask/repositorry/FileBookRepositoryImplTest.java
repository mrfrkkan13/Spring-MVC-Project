package com.example.JavaTask.repositorry;

import static org.junit.jupiter.api.Assertions.*;


import com.example.JavaTask.model.Book;
import com.example.JavaTask.repository.concrete.FileBookRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileBookRepositoryImplTest {

    private FileBookRepositoryImpl fileBookRepository;
    private static final String TEST_FILE_NAME = "C:\\Dev\\JavaTask\\src\\DB\\Kitap.txt";

    @BeforeEach
    public void setUp() throws IOException {
        Path tempDir = Files.createTempDirectory("temp-dir");
        String testFilePath = tempDir.resolve(TEST_FILE_NAME).toString();
        fileBookRepository = new FileBookRepositoryImpl();
        // Test için örnek verileri dosyaya yazalım
        writeSampleDataToFile(testFilePath);
    }

    private void writeSampleDataToFile(String filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("1;Book 1;29.99;1234567890;1");
            writer.newLine();
            writer.write("2;Book 2;19.99;0987654321;2");
            writer.newLine();
        }
    }

    @Test
    public void testGetAllBooks() {
        List<Book> books = fileBookRepository.getAll();
        assertNotNull(books);
        assertEquals(2, books.size());
    }

    @Test
    public void testSaveBook() {
        Book newBook = new Book(3, "Book 3", 39.99, "9876543210", 3);
        fileBookRepository.save(newBook);
        List<Book> books = fileBookRepository.getAll();
        assertEquals(3, books.size());
        assertTrue(books.contains(newBook));
    }

    @Test
    public void testGetBookById() {
        Book book = fileBookRepository.getById(1);
        assertNotNull(book);
        assertEquals(1, book.getBookId());
        assertEquals("Book 1", book.getTitle());
        assertEquals(29.99, book.getPrice());
        assertEquals("1234567890", book.getIsbn());
        assertEquals(1, book.getPublisherId());
    }

    @Test
    public void testUpdateBook() {
        Book updatedBook = new Book(2, "Updated Book 2", 24.99, "1111111111", 1);
        fileBookRepository.update(updatedBook);
        Book book = fileBookRepository.getById(2);
        assertEquals("Updated Book 2", book.getTitle());
        assertEquals(24.99, book.getPrice());
        assertEquals("1111111111", book.getIsbn());
        assertEquals(1, book.getPublisherId());
    }

    @Test
    public void testDeleteBook() {
        fileBookRepository.delete(1);
        List<Book> books = fileBookRepository.getAll();
        assertEquals(1, books.size());
        assertNull(fileBookRepository.getById(1));
    }
}
