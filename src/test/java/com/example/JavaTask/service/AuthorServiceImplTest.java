package com.example.JavaTask.service;
import static org.junit.jupiter.api.Assertions.*;


import com.example.JavaTask.model.Author;
import com.example.JavaTask.repository.abstracts.FileAuthorRepository;
import com.example.JavaTask.service.concrete.AuthorServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class AuthorServiceImplTest {

    @Mock
    private FileAuthorRepository authorRepository;

    @InjectMocks
    private AuthorServiceImpl authorService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAll() {
        // Test verisi oluştur
        List<Author> authors = new ArrayList<>();
        authors.add(new Author(1, "John", "Doe", 1));
        authors.add(new Author(2, "Jane", "Smith", 2));

        // authorRepository.getAllAuthors() metodunu çağırınca test verisini döndürmesi için ayarla
        when(authorRepository.getAll()).thenReturn(authors);

        // authorService.getAll() metodunu çağır
        List<Author> result = authorService.getAll();

        // Sonucun doğruluğunu kontrol et
        assertEquals(authors.size(), result.size());
        assertEquals(authors.get(0), result.get(0));
        assertEquals(authors.get(1), result.get(1));

        // authorRepository.getAllAuthors() metodunun çağrıldığını kontrol et
        verify(authorRepository, times(1)).getAll();
    }

    @Test
    public void testSave() {
        // Test verisi oluştur
        Author author = new Author(1, "John", "Doe", 1);

        // authorRepository.saveAuthor() metodunu çağırınca herhangi bir çıktı dönmemesi için ayarla
        doNothing().when(authorRepository).save(author);

        // authorService.save() metodunu çağır
        authorService.save(author);

        // authorRepository.saveAuthor() metodunun çağrıldığını kontrol et
        verify(authorRepository, times(1)).save(author);
    }

    @Test
    public void testGetById() {
        // Test verisi oluştur
        int authorId = 1;
        Author author = new Author(authorId, "John", "Doe", 1);

        // authorRepository.getAuthorById() metodunu çağırınca test verisini döndürmesi için ayarla
        when(authorRepository.getById(authorId)).thenReturn(author);

        // authorService.getById() metodunu çağır
        Author result = authorService.getById(authorId);

        // Sonucun doğruluğunu kontrol et
        assertEquals(author, result);

        // authorRepository.getAuthorById() metodunun çağrıldığını kontrol et
        verify(authorRepository, times(1)).getById(authorId);
    }

    @Test
    public void testUpdate() {
        // Test verisi oluştur
        Author author = new Author(1, "John", "Doe", 1);

        // authorRepository.updateAuthor() metodunu çağırınca herhangi bir çıktı dönmemesi için ayarla
        doNothing().when(authorRepository).update(author);

        // authorService.update() metodunu çağır
        authorService.update(author);

        // authorRepository.updateAuthor() metodunun çağrıldığını kontrol et
        verify(authorRepository, times(1)).update(author);
    }

    @Test
    public void testDelete() {
        // Test verisi oluştur
        int authorId = 1;

        // authorRepository.deleteAuthor() metodunu çağırınca herhangi bir çıktı dönmemesi için ayarla
        doNothing().when(authorRepository).delete(authorId);

        // authorService.delete() metodunu çağır
        authorService.delete(authorId);

        // authorRepository.deleteAuthor() metodunun çağrıldığını kontrol et
        verify(authorRepository, times(1)).delete(authorId);
    }


}
