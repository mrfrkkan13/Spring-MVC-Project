package com.example.JavaTask.repositorry;

import static org.junit.jupiter.api.Assertions.*;


import com.example.JavaTask.model.Publisher;
import com.example.JavaTask.repository.concrete.FilePublisherRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FilePublisherRepositoryImplTest {

    private FilePublisherRepositoryImpl filePublisherRepository;
    private static final String TEST_FILE_NAME = "C:\\Dev\\JavaTask\\src\\DB\\Yayinevi.txt";

    @BeforeEach
    public void setUp() throws IOException {
        Path tempDir = Files.createTempDirectory("temp-dir");
        String testFilePath = tempDir.resolve(TEST_FILE_NAME).toString();
        filePublisherRepository = new FilePublisherRepositoryImpl();
        // Test için örnek verileri dosyaya yazalım
        writeSampleDataToFile(testFilePath);
    }

    private void writeSampleDataToFile(String filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("1;Publisher 1");
            writer.newLine();
            writer.write("2;Publisher 2");
            writer.newLine();
        }
    }

    @Test
    public void testGetAllPublishers() {
        List<Publisher> publishers = filePublisherRepository.getAll();
        assertNotNull(publishers);
        assertEquals(2, publishers.size());
    }

    @Test
    public void testSavePublisher() {
        Publisher newPublisher = new Publisher(3, "Publisher 3");
        filePublisherRepository.save(newPublisher);
        List<Publisher> publishers = filePublisherRepository.getAll();
        assertEquals(3, publishers.size());
        assertTrue(publishers.contains(newPublisher));
    }

    @Test
    public void testGetPublisherById() {
        Publisher publisher = filePublisherRepository.getById(1);
        assertNotNull(publisher);
        assertEquals(1, publisher.getPublisherId());
        assertEquals("Publisher 1", publisher.getPublisherName());
    }

    @Test
    public void testUpdatePublisher() {
        Publisher updatedPublisher = new Publisher(2, "Updated Publisher 2");
        filePublisherRepository.update(updatedPublisher);
        Publisher publisher = filePublisherRepository.getById(2);
        assertEquals("Updated Publisher 2", publisher.getPublisherName());
    }

    @Test
    public void testDeletePublisher() {
        filePublisherRepository.delete(1);
        List<Publisher> publishers = filePublisherRepository.getAll();
        assertEquals(1, publishers.size());
        assertNull(filePublisherRepository.getById(1));
    }
}
