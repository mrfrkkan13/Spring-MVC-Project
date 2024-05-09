package com.example.JavaTask.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;

import com.example.JavaTask.model.Book;
import com.example.JavaTask.repository.concrete.FileBookRepositoryImpl;
import com.example.JavaTask.service.concrete.BookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BookServiceImplTest {

    @Mock
    private FileBookRepositoryImpl bookRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    private List<Book> books;

    @BeforeEach
    public void setUp() {
        books = new ArrayList<>();
        books.add(new Book(1, "Book 1", 20.0, "123456789", 1));
        books.add(new Book(2, "Book 2", 25.0, "987654321", 2));
    }

    @Test
    public void testGetAllBooks() {
        when(bookRepository.getAll()).thenReturn(books);

        List<Book> allBooks = bookService.getAll();

        assertEquals(2, allBooks.size());
    }

    @Test
    public void testGetBookById() {
        int bookId = 1;
        when(bookRepository.getById(bookId)).thenReturn(books.get(0));

        Book book = bookService.getById(bookId);

        assertNotNull(book);
        assertEquals(bookId, book.getBookId());
    }

    @Test
    public void testSaveBook() {
        Book newBook = new Book(3, "Book 3", 30.0, "456789123", 3);

        bookService.save(newBook);

        verify(bookRepository, times(1)).save(newBook);
    }

    @Test
    public void testUpdateBook() {
        Book updatedBook = new Book(1, "Updated Book 1", 22.0, "123456789", 1);

        bookService.update(updatedBook);

        verify(bookRepository, times(1)).update(updatedBook);
    }

    @Test
    public void testDeleteBook() {
        int bookId = 1;

        bookService.delete(bookId);

        verify(bookRepository, times(1)).delete(bookId);
    }
}
